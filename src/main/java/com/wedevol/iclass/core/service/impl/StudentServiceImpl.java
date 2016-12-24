package com.wedevol.iclass.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.StudentRepository;
import com.wedevol.iclass.core.service.StudentService;
import com.wedevol.iclass.core.util.CommonUtil;

/**
 * Student Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class StudentServiceImpl implements StudentService {

	protected static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentRepository studentRepository;

	/********************* CRUD for student ****************************/
	@Override
	public List<Student> findAll() {
		logger.info("Student service -> find all");
		final Iterable<Student> studentsIterator = studentRepository.findAll();
		return Lists.newArrayList(studentsIterator);
	}

	@Override
	public Student findByEmail(String email) {
		logger.info("Student service -> find by email");
		return studentRepository.findByEmail(email);
	}

	@Override
	public Student findById(Long userId) {
		logger.info("Student service -> find by id");
		final Optional<Student> studentObj = Optional.ofNullable(studentRepository.findOne(userId));
		return studentObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.STUDENT_NOT_FOUND));
	}

	@Override
	public void create(Student student) {
		logger.info("Student service -> create");
		// We first search by email, the student should not exist
		final Optional<Student> studentObj = Optional.ofNullable(findByEmail(student.getEmail()));
		studentObj.ifPresent(s -> new BadRequestException(BadRequestErrorType.BAD_REQUEST_EXCEPTION));
		student.setPassword(CommonUtil.hashSHA256(student.getPassword()));
		studentRepository.save(student);
	}

	@Override
	public void update(Long userId, Student student) {
		logger.info("Student service -> update");
		// The student should exist
		Student existingStudent = findById(userId);
		// TODO: analyze the full changed fields
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setPhone(student.getPhone());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setPassword(CommonUtil.hashSHA256(student.getPassword()));
		studentRepository.save(existingStudent);
	}

	@Override
	public void delete(Long userId) {
		logger.info("Student service -> delete");
		// The student should exist
		findById(userId);
		studentRepository.delete(userId);
	}

}
