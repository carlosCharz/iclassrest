package com.wedevol.iclass.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Faculty;
import com.wedevol.iclass.core.entity.University;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.UniversityRepository;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.FacultyService;
import com.wedevol.iclass.core.service.UniversityService;

/**
 * University Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class UniversityServiceImpl implements UniversityService {

	protected static final Logger logger = LoggerFactory.getLogger(UniversityServiceImpl.class);

	@Autowired
	private UniversityRepository universityRepository;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private CourseService courseService;

	@Override
	public List<University> findAll() {
		final Iterable<University> universityIterator = universityRepository.findAllByOrderByNameAsc();
		return Lists.newArrayList(universityIterator);
	}

	@Override
	public University findByName(String name) {
		return universityRepository.findByName(name);
	}

	@Override
	public University findById(Long universityId) {
		final Optional<University> universityObj = Optional.ofNullable(universityRepository.findOne(universityId));
		return universityObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.UNIVERSITY_NOT_FOUND));
	}

	@Override
	public University create(University university) {
		// Fields missing validation
		if (university.getName() == null || university.getShortName() == null) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// The university should not exist
		final Optional<University> universityObj = Optional.ofNullable(findByName(university.getName()));
		if (universityObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.UNIVERSITY_ALREADY_EXISTS);
		}
		// Save
		return universityRepository.save(university);
	}

	@Override
	public void update(Long universityId, University university) {
		// The university should exist
		University existingUniversity = findById(universityId);
		if (!isNullOrEmpty(university.getName())) {
			existingUniversity.setName(university.getName());
		}
		if (!isNullOrEmpty(university.getShortName())) {
			existingUniversity.setShortName(university.getShortName());
		}
		// Update
		universityRepository.save(existingUniversity);
	}

	@Override
	public void delete(Long universityId) {
		// The university should exist
		findById(universityId);
		universityRepository.delete(universityId);
	}

	@Override
	public List<Faculty> findFacultiesByUniversityId(Long universityId) {
		return facultyService.findFacultiesByUniversityId(universityId);
	}

	@Override
	public List<Course> findCoursesByFacultyIdByUniversityId(Long facultyId, Long universityId) {
		return courseService.findCoursesByFacultyIdByUniversityId(facultyId, universityId);
	}

}
