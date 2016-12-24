package com.wedevol.iclass.core.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorEnrollment;
import com.wedevol.iclass.core.entity.InstructorEnrollmentId;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.CourseRepository;
import com.wedevol.iclass.core.repository.InstructorEnrollmentRepository;
import com.wedevol.iclass.core.repository.InstructorRepository;
import com.wedevol.iclass.core.service.InstructorEnrollmentService;
import com.wedevol.iclass.core.util.CoreUtil;

/**
 * Instructor Enrollment Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class InstructorEnrollmentServiceImpl implements InstructorEnrollmentService {

	protected static final Logger logger = LoggerFactory.getLogger(InstructorEnrollmentServiceImpl.class);

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private InstructorRepository instructorRepository;

	@Autowired
	private InstructorEnrollmentRepository enrRepository;

	/************** Courses & Instructors **********************/
	@Override
	public List<Course> findCourses(Long instructorId, String statusFilter) {
		logger.info("InstructorEnrollment service -> find instructors courses filtered by the supplied course status");
		if (!CoreUtil.areValidCourseStatusFilters(statusFilter)) {
			throw new BadRequestException(BadRequestErrorType.COURSE_STATUS_NOT_VALID);
		}
		final List<String> status = Arrays.asList(statusFilter.split(","));
		return enrRepository.findCourses(instructorId, status);
	}

	@Override
	public List<Instructor> findInstructors(Long courseId) {
		logger.info("InstructorEnrollment service -> find instructors of a course");
		return enrRepository.findInstructors(courseId);
	}

	/***************** CRUD for Instructor Enrollment ***********************/

	@Override
	public List<InstructorEnrollment> findAll() {
		logger.info("InstructorEnrollment service -> find all");
		final Iterable<InstructorEnrollment> icIterator = enrRepository.findAll();
		return Lists.newArrayList(icIterator);
	}

	@Override
	public InstructorEnrollment findById(InstructorEnrollmentId id) {
		logger.info("InstructorEnrollment service -> find by id");
		Optional<InstructorEnrollment> icObj = Optional.ofNullable(enrRepository.findOne(id));
		return icObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.INSTRUCTOR_COURSE_NOT_FOUND));
	}

	@Override
	public void create(InstructorEnrollment instructorEnrollment) {
		logger.info("InstructorEnrollment service -> create");
		// We first search by id, the instructorEnrollment should not exist
		Optional<InstructorEnrollment> enrObj = Optional.ofNullable(
				enrRepository.findOne(instructorEnrollment.getInstructorEnrollmentId()));
		enrObj.ifPresent(s -> new BadRequestException(BadRequestErrorType.BAD_REQUEST_EXCEPTION));
		enrRepository.save(instructorEnrollment);
	}

	@Override
	public void update(InstructorEnrollmentId id, InstructorEnrollment instructorEnrollment) {
		logger.info("InstructorEnrollment service -> update");
		// The instructorEnrollment should exist
		final InstructorEnrollment existingEnr = findById(id);
		// TODO: analyze the full changed fields
		existingEnr.setStatus(instructorEnrollment.getStatus());
		existingEnr.setPrice(instructorEnrollment.getPrice());
		existingEnr.setCurrency(instructorEnrollment.getCurrency());
		enrRepository.save(existingEnr);
	}

	@Override
	public void delete(InstructorEnrollmentId id) {
		logger.info("InstructorEnrollment service -> delete");
		// The instructorEnrollment should exist
		findById(id);
		enrRepository.delete(id);
	}

}
