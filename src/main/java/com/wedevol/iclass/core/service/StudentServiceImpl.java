package com.wedevol.iclass.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.enums.BadRequestErrorType;
import com.wedevol.iclass.core.enums.NotFoundErrorType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.repository.StudentRepository;
import com.wedevol.iclass.core.util.Util;

/**
 * Student Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

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
		Optional<Student> studentObj = Optional.ofNullable(studentRepository.findOne(userId));
		return studentObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.USER_NOT_FOUND));
	}

	@Override
	public void create(Student student) {
		// We first search by email, the student should not exist
		Optional<Student> studentObj = Optional.ofNullable(findByEmail(student.getEmail()));
		studentObj.ifPresent(s -> new BadRequestException(BadRequestErrorType.BAD_REQUEST_EXCEPTION));
		// TODO: the password should be hashed
		studentRepository.save(student);
	}

	@Override
	public void update(Long userId, Student student) {
		Optional<Student> studentObj = Optional.ofNullable(studentRepository.findOne(userId));
		Student existingStudent = studentObj.orElseThrow(() -> new ResourceNotFoundException(
				NotFoundErrorType.USER_NOT_FOUND));
		// TODO: analyze the full changed fields
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setPhone(student.getPhone());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setPassword(Util.hashSHA256(student.getPassword()));
		studentRepository.save(existingStudent);
	}

	@Override
	public void delete(Long userId) {
		Optional<Student> studentObj = Optional.ofNullable(studentRepository.findOne(userId));
		studentObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.USER_NOT_FOUND));
		studentRepository.delete(userId);
	}

}
