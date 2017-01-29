package com.wedevol.iclass.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.StudentEnrollment;
import com.wedevol.iclass.core.entity.StudentEnrollmentId;
import com.wedevol.iclass.core.entity.enums.EnrollmentStatusType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.StudentEnrollmentRepository;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.DepartmentService;
import com.wedevol.iclass.core.service.StudentService;

/**
 * Department Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class DepartmentServiceImpl implements DepartmentService {

	protected static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	@Autowired
	private StudentEnrollmentRepository enrRepository;

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentService studentService;

	/***************** CRUD for Student Enrollment ***********************/

	@Override
	public List<StudentEnrollment> findAll() {
		final Iterable<StudentEnrollment> scIterator = enrRepository.findAll();
		return Lists.newArrayList(scIterator);
	}

	@Override
	public StudentEnrollment findById(StudentEnrollmentId id) {
		final Optional<StudentEnrollment> scObj = Optional.ofNullable(enrRepository.findOne(id));
		return scObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.STUDENT_COURSE_NOT_FOUND));
	}

	@Override
	public StudentEnrollment create(StudentEnrollment studentEnrollment) {
		// Fields missing validation
		if (studentEnrollment.getId() == null
				|| (studentEnrollment.getId() != null && studentEnrollment.getId().getStudentId() == null)
				|| (studentEnrollment.getId() != null && studentEnrollment.getId().getCourseId() == null)) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// The course should exist
		courseService.findById(studentEnrollment.getId().getCourseId());
		// The student should exist
		studentService.findById(studentEnrollment.getId().getStudentId());
		// The studentEnrollment should not exist
		final Optional<StudentEnrollment> enrObj = Optional.ofNullable(
				enrRepository.findOne(studentEnrollment.getId()));
		if (enrObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.ENROLLMENT_ALREADY_EXISTS);
		}
		studentEnrollment.setStatus(EnrollmentStatusType.FREE.getDescription());
		// Save
		return enrRepository.save(studentEnrollment);
	}

	@Override
	public void update(StudentEnrollmentId id, StudentEnrollment studentEnrollment) {
		// The studentEnrollment should exist
		StudentEnrollment existingEnr = findById(id);
		existingEnr.setStatus(studentEnrollment.getStatus());
		enrRepository.save(existingEnr);
	}

	@Override
	public void delete(StudentEnrollmentId id) {
		// The studentEnrollment should exist
		findById(id);
		enrRepository.delete(id);
	}

}
