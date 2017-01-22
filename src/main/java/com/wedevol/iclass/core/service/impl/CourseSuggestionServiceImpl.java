package com.wedevol.iclass.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.CourseSuggestion;
import com.wedevol.iclass.core.entity.enums.UserType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.CourseSuggestionRepository;
import com.wedevol.iclass.core.service.CourseSuggestionService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.StudentService;

/**
 * Course Suggestion Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class CourseSuggestionServiceImpl implements CourseSuggestionService {

	protected static final Logger logger = LoggerFactory.getLogger(CourseSuggestionServiceImpl.class);

	@Autowired
	private CourseSuggestionRepository courseSuggestionRepository;

	@Autowired
	private StudentService studentService;

	@Autowired
	private InstructorService instructorService;

	@Override
	public List<CourseSuggestion> findAll() {
		final Iterable<CourseSuggestion> coursesIterator = courseSuggestionRepository.findAll();
		return Lists.newArrayList(coursesIterator);
	}

	@Override
	public CourseSuggestion findById(Long courseSuggestionId) {
		final Optional<CourseSuggestion> courseObj = Optional.ofNullable(
				courseSuggestionRepository.findOne(courseSuggestionId));
		return courseObj.orElseThrow(
				() -> new ResourceNotFoundException(NotFoundErrorType.COURSE_SUGGESTION_NOT_FOUND));
	}

	@Override
	public void create(CourseSuggestion courseSuggestion) {
		// The user should exist
		validateUser(courseSuggestion.getUserId(), courseSuggestion.getUserType());
		courseSuggestion.setRequestedAt(new Date());
		courseSuggestionRepository.save(courseSuggestion);
	}

	private void validateUser(Long userId, String userTypeStr) {
		if (UserType.INSTRUCTOR.getDescription().equals(userTypeStr)) {
			instructorService.findById(userId);
		} else if (UserType.STUDENT.getDescription().equals(userTypeStr)) {
			studentService.findById(userId);
		} else {
			// TODO: validate the course suggestion type appropriately
			throw new BadRequestException(BadRequestErrorType.USER_TYPE_NOT_VALID);
		}
	}

	@Override
	public void update(Long courseSuggestionId, CourseSuggestion courseSuggestion) {
		// The course suggestion should exist
		CourseSuggestion existingCourse = findById(courseSuggestionId);
		existingCourse.setStatus(courseSuggestion.getStatus());
		courseSuggestionRepository.save(existingCourse);
	}

	@Override
	public void delete(Long courseId) {
		// The course suggestion should exist
		findById(courseId);
		courseSuggestionRepository.delete(courseId);
	}
}