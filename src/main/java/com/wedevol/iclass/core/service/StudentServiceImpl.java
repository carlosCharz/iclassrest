package com.wedevol.iclass.core.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.StudentCourse;
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
		Optional<Student> studentObj = Optional.ofNullable(studentRepository.findOne(userId));
		return studentObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.USER_NOT_FOUND));
	}

	@Override
	public void create(Student student) {
		logger.info("Student service -> create");
		// We first search by email, the student should not exist
		Optional<Student> studentObj = Optional.ofNullable(findByEmail(student.getEmail()));
		studentObj.ifPresent(s -> new BadRequestException(BadRequestErrorType.BAD_REQUEST_EXCEPTION));
		student.setPassword(Util.hashSHA256(student.getPassword()));
		studentRepository.save(student);
	}

	@Override
	public void update(Long userId, Student student) {
		logger.info("Student service -> update");
		Optional<Student> studentObj = Optional.ofNullable(studentRepository.findOne(userId));
		Student existingStudent = studentObj.orElseThrow(
				() -> new ResourceNotFoundException(NotFoundErrorType.USER_NOT_FOUND));
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
		logger.info("Student service -> delete");
		Optional<Student> studentObj = Optional.ofNullable(studentRepository.findOne(userId));
		studentObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.USER_NOT_FOUND));
		studentRepository.delete(userId);
	}

	/********************* Student courses ****************************/
	@Override
	public List<StudentCourse> findStudentCourses(Long studentId) {
		logger.info("Student service -> find student courses");
		List<StudentCourse> studentCourses = studentRepository.findStudentCourses(studentId);
		//List<Course> courses = studentCourses.stream().map((sc) -> new Course(sc.getStudentCourseId().getCourseId())).collect(Collectors.toList());
		return studentCourses;
	}

}
