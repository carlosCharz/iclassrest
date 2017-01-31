package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.hashSHA256;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Admin;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.UnauthorizedException;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.exception.enums.UnauthorizedErrorType;
import com.wedevol.iclass.core.service.AdminService;
import com.wedevol.iclass.core.service.AuthService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.StudentService;
import com.wedevol.iclass.core.view.response.StudentView;

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
	
	@Autowired
	private AdminService adminService;

	/********************* Authentication logic ****************************/

	@Override
	public StudentView loginStudent(String email, String password) {
		final Optional<Student> studentObj = Optional.ofNullable(studentService.findByEmail(email));
		final Student student = studentObj.orElseThrow(
				() -> new ResourceNotFoundException(NotFoundErrorType.STUDENT_NOT_FOUND));
		final String passwordHashed = hashSHA256(password);
		if (passwordHashed.equals(student.getPassword())) {
			// TODO: call the studentServive to get everything
			return StudentView.from(student);
		} else {
			throw new UnauthorizedException(UnauthorizedErrorType.INCORRECT_CREDENTIALS);
		}
	}

	@Override
	public Instructor loginInstructor(String email, String password) {
		final Optional<Instructor> instructorObj = Optional.ofNullable(instructorService.findByEmail(email));
		final Instructor instructor = instructorObj.orElseThrow(
				() -> new ResourceNotFoundException(NotFoundErrorType.INSTRUCTOR_NOT_FOUND));
		final String passwordHashed = hashSHA256(password);
		if (passwordHashed.equals(instructor.getPassword())) {
			return instructor;
		} else {
			throw new UnauthorizedException(UnauthorizedErrorType.INCORRECT_CREDENTIALS);
		}
	}
	
	@Override
	public Admin loginAdmin(String email, String password) {
		final Optional<Admin> adminObj = Optional.ofNullable(adminService.findByEmail(email));
		final Admin admin = adminObj.orElseThrow(
				() -> new ResourceNotFoundException(NotFoundErrorType.ADMIN_NOT_FOUND));
		final String passwordHashed = hashSHA256(password);
		if (passwordHashed.equals(admin.getPassword())) {
			return admin;
		} else {
			throw new UnauthorizedException(UnauthorizedErrorType.INCORRECT_CREDENTIALS);
		}
	}

}
