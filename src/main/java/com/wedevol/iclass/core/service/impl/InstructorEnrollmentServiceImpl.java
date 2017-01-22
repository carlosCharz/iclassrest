package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.isNullOrEmpty;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.configuration.BusinessSetting;
import com.wedevol.iclass.core.entity.InstructorEnrollment;
import com.wedevol.iclass.core.entity.InstructorEnrollmentId;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.InstructorEnrollmentRepository;
import com.wedevol.iclass.core.service.InstructorEnrollmentService;

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
	private InstructorEnrollmentRepository enrRepository;

	@Autowired
	private BusinessSetting bussinessSetting;

	@Override
	public List<InstructorEnrollment> findAll() {
		final Iterable<InstructorEnrollment> icIterator = enrRepository.findAll();
		return Lists.newArrayList(icIterator);
	}

	@Override
	public InstructorEnrollment findById(InstructorEnrollmentId id) {
		final Optional<InstructorEnrollment> icObj = Optional.ofNullable(enrRepository.findOne(id));
		return icObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.INSTRUCTOR_COURSE_NOT_FOUND));
	}

	@Override
	public InstructorEnrollment create(InstructorEnrollment instructorEnrollment) {
		// Fields missing validation
		if (instructorEnrollment.getId() == null
				|| (instructorEnrollment.getId() != null && instructorEnrollment.getId().getInstructorId() == null)
				|| (instructorEnrollment.getId() != null && instructorEnrollment.getId().getCourseId() == null)
				|| instructorEnrollment.getStatus() == null || instructorEnrollment.getPrice() == null
				|| instructorEnrollment.getCurrency() == null) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// We first search by id, the instructorEnrollment should not exist
		final Optional<InstructorEnrollment> enrObj = Optional.ofNullable(enrRepository.findOne(instructorEnrollment
																													.getId()));
		if (enrObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.ENROLLMENT_ALREADY_EXISTS);
		}
		// Save
		return enrRepository.save(instructorEnrollment);
	}

	@Override
	public void update(InstructorEnrollmentId id, InstructorEnrollment instructorEnrollment) {
		// The instructorEnrollment should exist
		InstructorEnrollment existingEnr = findById(id);
		if (!isNullOrEmpty(instructorEnrollment.getStatus())) {
			existingEnr.setStatus(instructorEnrollment.getStatus());
		}
		if (instructorEnrollment.getPrice() != null) {
			existingEnr.setPrice(instructorEnrollment.getPrice());
		}
		if (instructorEnrollment.getCurrency() != null) {
			existingEnr.setCurrency(instructorEnrollment.getCurrency());
		}
		// Save
		enrRepository.save(existingEnr);
	}

	@Override
	public void delete(InstructorEnrollmentId id) {
		// The instructorEnrollment should exist
		findById(id);
		enrRepository.delete(id);
	}

	@Override
	public Float getAveragePriceForCourse(Long courseId) {
		final Optional<Float> priceObj = Optional.ofNullable(enrRepository.findAveragePriceForCourseId(courseId));
		return priceObj.orElse(bussinessSetting.getInstructorDefaultPrice());
	}

}
