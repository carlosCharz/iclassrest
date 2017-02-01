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
import com.wedevol.iclass.core.exception.UnauthorizedException;
import com.wedevol.iclass.core.exception.enums.UnauthorizedErrorType;
import com.wedevol.iclass.core.service.AdminService;
import com.wedevol.iclass.core.service.AuthService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.StudentService;
import com.wedevol.iclass.core.view.response.AdminView;
import com.wedevol.iclass.core.view.response.InstructorView;
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
		final Student student = studentService.getStudentByEmail(email);
		final String passwordHashed = hashSHA256(password);
		if (passwordHashed.equals(student.getPassword())) {
			return studentService.getStudentByIdWithFullInfo(student.getId());
		} else {
			throw new UnauthorizedException(UnauthorizedErrorType.INCORRECT_CREDENTIALS);
		}
	}

	@Override
	public InstructorView loginInstructor(String email, String password) {
		final Instructor instructor = instructorService.getInstructorByEmail(email);
		final String passwordHashed = hashSHA256(password);
		if (passwordHashed.equals(instructor.getPassword())) {
			return instructorService.getInstructorByIdWithFullInfo(instructor.getId());
		} else {
			throw new UnauthorizedException(UnauthorizedErrorType.INCORRECT_CREDENTIALS);
		}
	}

	@Override
	public AdminView loginAdmin(String email, String password) {
		final Admin admin = adminService.getAdminByEmail(email);
		final String passwordHashed = hashSHA256(password);
		if (passwordHashed.equals(admin.getPassword())) {
			return adminService.getAdminByIdWithFullInfo(admin.getId());
		} else {
			throw new UnauthorizedException(UnauthorizedErrorType.INCORRECT_CREDENTIALS);
		}
	}

	@Override
	public void refreshFCMToken(String fcmToken, String deviceId) {
		// Search in the student repo
		final Optional<Student> studentObj = Optional.ofNullable(studentService.findByDeviceId(deviceId));
		if (studentObj.isPresent()) {
			Student student = Student.from(studentObj.get().getId());
			student.setFcmToken(fcmToken);
			studentService.update(student.getId(), student);
		}
		// Search in the instructor repo
		final Optional<Instructor> instructorObj = Optional.ofNullable(instructorService.findByDeviceId(deviceId));
		if (instructorObj.isPresent()) {
			Instructor instructor = Instructor.from(instructorObj.get().getId());
			instructor.setFcmToken(fcmToken);
			instructorService.update(instructor.getId(), instructor);
		}
		// Search in the admin repo
		final Optional<Admin> adminObj = Optional.ofNullable(adminService.findByDeviceId(deviceId));
		if (adminObj.isPresent()) {
			Admin admin = Admin.from(adminObj.get().getId());
			admin.setFcmToken(fcmToken);
			adminService.update(admin.getId(), admin);
		}
	}

}
