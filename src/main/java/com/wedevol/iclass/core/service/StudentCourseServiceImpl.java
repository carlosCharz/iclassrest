package com.wedevol.iclass.core.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.StudentCourse;
import com.wedevol.iclass.core.entity.StudentCourseId;
import com.wedevol.iclass.core.enums.ServerErrorType;
import com.wedevol.iclass.core.exception.NotImplementedException;
import com.wedevol.iclass.core.repository.CourseRepository;
import com.wedevol.iclass.core.repository.StudentCourseRepository;
import com.wedevol.iclass.core.repository.StudentRepository;

/**
 * Student_Course Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class StudentCourseServiceImpl implements StudentCourseService {

	protected static final Logger logger = LoggerFactory.getLogger(StudentCourseServiceImpl.class);

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentCourseRepository scRepository;

	/************** Courses & Students **********************/
	@Override
	public List<Course> findCourses(Long studentId) {
		logger.info("StudentCourse service -> find student courses");
		List<Course> courses = scRepository.findCourses(studentId);
		return courses;
	}

	@Override
	public List<Student> findStudents(Long courseId) {
		// TODO Auto-generated method stub
		throw new NotImplementedException(ServerErrorType.NOT_IMPLEMENTED);
	}

	/********************* CRUD for Student_Course ****************************/

	@Override
	public List<StudentCourse> findAll() {
		final Iterable<StudentCourse> scIterator = scRepository.findAll();
		return Lists.newArrayList(scIterator);
	}

	@Override
	public StudentCourse findById(StudentCourseId id) {
		// TODO Auto-generated method stub
		throw new NotImplementedException(ServerErrorType.NOT_IMPLEMENTED);
	}

	@Override
	public void create(StudentCourse studentCourse) {
		// TODO Auto-generated method stub
		throw new NotImplementedException(ServerErrorType.NOT_IMPLEMENTED);
	}

	@Override
	public void update(StudentCourseId id, StudentCourse studentCourse) {
		// TODO Auto-generated method stub
		throw new NotImplementedException(ServerErrorType.NOT_IMPLEMENTED);
	}

	@Override
	public void delete(StudentCourseId id) {
		// TODO Auto-generated method stub
		throw new NotImplementedException(ServerErrorType.NOT_IMPLEMENTED);
	}

}
