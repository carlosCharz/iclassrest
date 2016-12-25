package com.wedevol.iclass.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.ClassEntity;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.ClassRepository;
import com.wedevol.iclass.core.service.ClassService;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.StudentService;

/**
 * Class Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class ClassServiceImpl implements ClassService {

	protected static final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private StudentService studentService;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private CourseService courseService;

	/********************* CRUD for class ****************************/
	@Override
	public List<ClassEntity> findAll() {
		final Iterable<ClassEntity> classIterator = classRepository.findAll();
		return Lists.newArrayList(classIterator);
	}

	@Override
	public ClassEntity findById(Long classId) {
		Optional<ClassEntity> classObj = Optional.ofNullable(classRepository.findOne(classId));
		return classObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.CLASS_NOT_FOUND));
	}

	@Override
	public void create(ClassEntity c) {
		// TODO: Analize if the class should not exist first
		c.setRequestedAt(new Date());
		classRepository.save(c);
	}

	@Override
	public void update(Long classId, ClassEntity c) {
		// The class should exist
		ClassEntity existingClass = findById(classId);
		// Then, the student should exist
		studentService.findById(c.getStudentId());
		// Then, the instructor should exist
		instructorService.findById(c.getInstructorId());
		// Then, the course should exist
		courseService.findById(c.getCourseId());
		// Update
		existingClass.setStudentId(c.getStudentId());
		existingClass.setInstructorId(c.getInstructorId());
		existingClass.setCourseId(c.getCourseId());
		existingClass.setWeekDay(c.getWeekDay());
		existingClass.setClassDate(c.getClassDate());
		existingClass.setStartTime(c.getStartTime());
		existingClass.setEndTime(c.getEndTime());
		existingClass.setStatus(c.getStatus());
		classRepository.save(existingClass);
	}

	@Override
	public void delete(Long classId) {
		// The class should exist
		findById(classId);
		classRepository.delete(classId);
	}

}
