package com.wedevol.iclass.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.enums.ErrorType;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.repository.StudentRepository;

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
	public Student findByEmail(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findById(Long userId) {
		Optional<Student> studentObj = Optional.ofNullable(studentRepository.findOne(userId));
		return studentObj.orElseThrow(() -> new ResourceNotFoundException(ErrorType.STUDENT_NOT_FOUND));
	}

	@Override
	public void create(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void update(Long userId, Student student) {
		// TODO: validate that id is not null
		studentRepository.save(student);
	}

	@Override
	public void delete(Long userId) {
		studentRepository.delete(userId);
	}

}
