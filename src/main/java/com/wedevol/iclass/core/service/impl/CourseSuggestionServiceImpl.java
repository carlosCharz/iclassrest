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
import com.wedevol.iclass.core.entity.CourseSuggestion;
import com.wedevol.iclass.core.entity.enums.CourseSuggestionStatusType;
import com.wedevol.iclass.core.entity.enums.UserType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.CourseSuggestionRepository;
import com.wedevol.iclass.core.service.CourseSuggestionService;
import com.wedevol.iclass.core.service.FacultyService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.StudentService;
import com.wedevol.iclass.core.service.UniversityService;

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
	
	@Autowired
	private UniversityService universityService;
	
	@Autowired
	private FacultyService facultyService;

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
	public CourseSuggestion create(CourseSuggestion courseSuggestion) {
		// Fields missing validation
		if (courseSuggestion.getName() == null || courseSuggestion.getUniversityId() == null || courseSuggestion.getFacultyId() == null ) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// The university should exist
		universityService.findById(courseSuggestion.getUniversityId());
		// The faculty should exist
		facultyService.findById(courseSuggestion.getFacultyId());
		// The user should exist
		validateUser(courseSuggestion.getUserId(), courseSuggestion.getUserType());
		courseSuggestion.setStatus(CourseSuggestionStatusType.SUGGESTED.getDescription());
		return courseSuggestionRepository.save(courseSuggestion);
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
		// TODO: analyze if the we can modify the user and userType
		if (!isNullOrEmpty(courseSuggestion.getName())) {
			existingCourse.setName(courseSuggestion.getName());
		}
		if (!isNullOrEmpty(courseSuggestion.getDescription())) {
			existingCourse.setDescription(courseSuggestion.getDescription());
		}
		if (courseSuggestion.getFacultyId()!=null) {
			// The faculty should exist
			facultyService.findById(courseSuggestion.getFacultyId());
			existingCourse.setFacultyId(courseSuggestion.getFacultyId());
		}
		if (courseSuggestion.getUniversityId()!=null) {
			// The university should exist
			universityService.findById(courseSuggestion.getUniversityId());
			existingCourse.setUniversityId(courseSuggestion.getUniversityId());
		}
		if (courseSuggestion.getStatus()!= null){
			existingCourse.setStatus(courseSuggestion.getStatus());
		}
		courseSuggestionRepository.save(existingCourse);
	}

	@Override
	public void delete(Long courseId) {
		// The course suggestion should exist
		findById(courseId);
		courseSuggestionRepository.delete(courseId);
	}
}
