package com.wedevol.iclass.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.InstructorRepository;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.util.CommonUtil;

/**
 * Instructor Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class InstructorServiceImpl implements InstructorService {

	protected static final Logger logger = LoggerFactory.getLogger(InstructorServiceImpl.class);

	@Autowired
	private InstructorRepository instructorRepository;

	/********************* CRUD for instructor ****************************/
	@Override
	public List<Instructor> findAll() {
		final Iterable<Instructor> instructorsIterator = instructorRepository.findAll();
		return Lists.newArrayList(instructorsIterator);
	}

	@Override
	public Instructor findByEmail(String email) {
		return instructorRepository.findByEmail(email);
	}

	@Override
	public Instructor findById(Long userId) {
		Optional<Instructor> instructorObj = Optional.ofNullable(instructorRepository.findOne(userId));
		return instructorObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.INSTRUCTOR_NOT_FOUND));
	}

	@Override
	public void create(Instructor instructor) {
		// We first search by email, the instructor should not exist
		Optional<Instructor> instructorObj = Optional.ofNullable(findByEmail(instructor.getEmail()));
		instructorObj.ifPresent(s -> new BadRequestException(BadRequestErrorType.BAD_REQUEST_EXCEPTION));
		instructor.setPassword(CommonUtil.hashSHA256(instructor.getPassword()));
		instructorRepository.save(instructor);
	}

	@Override
	public void update(Long userId, Instructor instructor) {
		// The instructor should exist
		Optional<Instructor> instructorObj = Optional.ofNullable(instructorRepository.findOne(userId));
		Instructor existingInstructor = instructorObj.orElseThrow(
				() -> new ResourceNotFoundException(NotFoundErrorType.INSTRUCTOR_NOT_FOUND));
		// TODO: analyze the full changed fields
		existingInstructor.setFirstName(instructor.getFirstName());
		existingInstructor.setLastName(instructor.getLastName());
		existingInstructor.setPhone(instructor.getPhone());
		existingInstructor.setEmail(instructor.getEmail());
		existingInstructor.setPassword(CommonUtil.hashSHA256(instructor.getPassword()));
		instructorRepository.save(existingInstructor);
	}

	@Override
	public void delete(Long userId) {
		// The instructor should exist
		Optional<Instructor> studentObj = Optional.ofNullable(instructorRepository.findOne(userId));
		studentObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.INSTRUCTOR_NOT_FOUND));
		instructorRepository.delete(userId);
	}

}
