package com.wedevol.iclass.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Department;
import com.wedevol.iclass.core.entity.DepartmentId;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.DepartmentRepository;
import com.wedevol.iclass.core.service.DepartmentService;
import com.wedevol.iclass.core.service.FacultyService;
import com.wedevol.iclass.core.service.UniversityService;

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
	private DepartmentRepository depaRepository;

	@Autowired
	private UniversityService universityService;
	
	@Autowired
	private FacultyService facultyService;

	/***************** CRUD for Department ***********************/

	@Override
	public List<Department> findAll() {
		final Iterable<Department> depaIterator = depaRepository.findAll();
		return Lists.newArrayList(depaIterator);
	}

	@Override
	public Department findById(DepartmentId id) {
		final Optional<Department> depaObj = depaRepository.findById(id);
		return depaObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.DEPARTMENT_NOT_FOUND));
	}

	@Override
	public Department create(Department department) {
		// Fields missing validation
		if (department.getId() == null
				|| (department.getId() != null && department.getId().getUniversityId() == null)
				|| (department.getId() != null && department.getId().getFacultyId() == null)) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// The university should exist
		universityService.findById(department.getId().getUniversityId());
		// The faculty should exist
		facultyService.findById(department.getId().getFacultyId());
		// The department should not exist
		final Optional<Department> depaObj = depaRepository.findById(department.getId());
		if (depaObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.DEPARTMENT_ALREADY_EXISTS);
		}
		// Save
		return depaRepository.save(department);
	}

	@Override
	public void update(DepartmentId id, Department department) {
		// The department should exist
		Department existingDepa = findById(id);
		depaRepository.save(existingDepa);
	}

	@Override
	public void delete(DepartmentId id) {
		// The department should exist
		findById(id);
		depaRepository.deleteById(id);
	}

}
