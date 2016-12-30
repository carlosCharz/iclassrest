package com.wedevol.iclass.core.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.service.AuthService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.StudentService;
import com.wedevol.iclass.core.util.CommonUtil;

/**
 * Auth Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class AuthServiceImpl implements AuthService {

	protected static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	@Autowired
	private StudentService studentService;

	@Autowired
	private InstructorService instructorService;

	/********************* Authentication logic ****************************/

	@Override
	public Student loginStudent(String email, String password) {
		final Optional<Student> studentObj = Optional.ofNullable(studentService.findByEmail(email));
		final Student student = studentObj.orElseThrow(() -> new ResourceNotFoundException(
				NotFoundErrorType.STUDENT_NOT_FOUND));
		final String passwordHashed = CommonUtil.hashSHA256(password);
		if (passwordHashed.equals(student.getPassword())) {
			return student;
		} else {
			throw new ResourceNotFoundException(NotFoundErrorType.STUDENT_NOT_FOUND);
		}
	}

	@Override
	public Instructor loginInstructor(String email, String password) {
		final Optional<Instructor> instructorObj = Optional.ofNullable(instructorService.findByEmail(email));
		final Instructor instructor = instructorObj.orElseThrow(() -> new ResourceNotFoundException(
				NotFoundErrorType.INSTRUCTOR_NOT_FOUND));
		final String passwordHashed = CommonUtil.hashSHA256(password);
		if (passwordHashed.equals(instructor.getPassword())) {
			return instructor;
		} else {
			throw new ResourceNotFoundException(NotFoundErrorType.INSTRUCTOR_NOT_FOUND);
		}
	}

}
