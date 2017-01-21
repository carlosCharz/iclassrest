package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.*;

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
		final Iterable<Student> studentsIterator = studentRepository.findAll();
		return Lists.newArrayList(studentsIterator);
	}

	@Override
	public Student findByEmail(String email) {
		return studentRepository.findByEmail(email);
	}

	@Override
	public Student findById(Long userId) {
		final Optional<Student> studentObj = Optional.ofNullable(studentRepository.findOne(userId));
		return studentObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.STUDENT_NOT_FOUND));
	}

	@Override
	public Student create(Student student) {
		// We first search by email, the student should not exist
		final Optional<Student> studentObj = Optional.ofNullable(findByEmail(student.getEmail()));
		if (studentObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.USER_ALREADY_EXISTS);
		}
		return studentRepository.save(student);
	}

	@Override
	public void update(Long userId, Student student) {
		// The student should exist
		Student existingStudent = findById(userId);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setPhone(student.getPhone());
		existingStudent.setEmail(student.getEmail());
		// TODO: validate that the password has changed
		// existingStudent.setPassword(hashSHA256(student.getPassword()));
		if (student.getBirthday() != null) {
			existingStudent.setBirthday(student.getBirthday());
		}
		if (!isNullOrEmpty(student.getGender())) {
			existingStudent.setGender(student.getGender());
		}
		if (!isNullOrEmpty(student.getProfilePictureUrl())) {
			existingStudent.setProfilePictureUrl(student.getProfilePictureUrl());
		}
		existingStudent.setPlaceOptions(student.getPlaceOptions());
		if (!isNullOrEmpty(student.getUniversity())) {
			existingStudent.setUniversity(student.getUniversity());
		}
		studentRepository.save(existingStudent);
	}

	@Override
	public void delete(Long userId) {
		// The student should exist
		findById(userId);
		studentRepository.delete(userId);
	}

}
