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
import com.wedevol.iclass.core.entity.Faculty;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.FacultyRepository;
import com.wedevol.iclass.core.service.FacultyService;
import com.wedevol.iclass.core.service.UniversityService;

/**
 * Faculty Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class FacultyServiceImpl implements FacultyService {

	protected static final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

	@Autowired
	private FacultyRepository facultyRepository;

	@Autowired
	private UniversityService universityService;

	@Override
	public List<Faculty> findAll() {
		final Iterable<Faculty> facultiesIterator = facultyRepository.findAll();
		return Lists.newArrayList(facultiesIterator);
	}

	@Override
	public Faculty findByName(String name) {
		return facultyRepository.findByName(name);
	}

	@Override
	public Faculty findById(Long facultyId) {
		final Optional<Faculty> facultyObj = Optional.ofNullable(facultyRepository.findOne(facultyId));
		return facultyObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.FACULTY_NOT_FOUND));
	}

	@Override
	public Faculty create(Faculty faculty) {
		// Fields missing validation
		if (faculty.getName() == null || faculty.getShortName() == null || faculty.getUniversityId() == null) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// The faculty should not exist
		final Optional<Faculty> facultyObj = Optional.ofNullable(findByName(faculty.getName()));
		if (facultyObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.FACULTY_ALREADY_EXISTS);
		}
		// Then, the university should exist
		universityService.findById(faculty.getUniversityId());
		// Save
		return facultyRepository.save(faculty);
	}

	@Override
	public void update(Long facultyId, Faculty faculty) {
		// The faculty should exist
		Faculty existingFaculty = findById(facultyId);
		if (!isNullOrEmpty(faculty.getName())) {
			existingFaculty.setName(faculty.getName());
		}
		if (!isNullOrEmpty(faculty.getShortName())) {
			existingFaculty.setShortName(faculty.getShortName());
		}
		if (faculty.getUniversityId() != null) {
			// The university should exist
			universityService.findById(faculty.getUniversityId());
			existingFaculty.setUniversityId(faculty.getUniversityId());
		}
		// Update
		facultyRepository.save(existingFaculty);
	}

	@Override
	public void delete(Long facultyId) {
		// The faculty should exist
		findById(facultyId);
		facultyRepository.delete(facultyId);
	}

	@Override
	public List<Faculty> findFacultiesByUniversityId(Long universityId) {
		// The university should exist
		universityService.findById(universityId);
		return facultyRepository.findFacultiesWithUniversityId(universityId);
	}

}
