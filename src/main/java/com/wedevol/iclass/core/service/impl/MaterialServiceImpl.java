package com.wedevol.iclass.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Material;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.MaterialRepository;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.MaterialService;

/**
 * Material Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class MaterialServiceImpl implements MaterialService {

	protected static final Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	private CourseService courseService;

	@Override
	public List<Material> findAll() {
		final Iterable<Material> materialIterator = materialRepository.findAllByOrderByNameAsc();
		return Lists.newArrayList(materialIterator);
	}

	@Override
	public Material findByName(String name) {
		return materialRepository.findByName(name);
	}

	@Override
	public Material findById(Long materialId) {
		final Optional<Material> materialObj = Optional.ofNullable(materialRepository.findOne(materialId));
		return materialObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.MATERIAL_NOT_FOUND));
	}

	@Override
	public boolean doesMaterialExist(Long courseId, String materialName) {
		final Optional<Material> materialObj = Optional.ofNullable(
				materialRepository.findMaterialWithCourseWithName(courseId, materialName));
		return materialObj.isPresent();
	}

	@Override
	public Material create(Material material) {
		// Fields missing validation
		if (material.getCourseId() == null || isNullOrEmpty(material.getName())
				|| isNullOrEmpty(material.getMaterialType()) || isNullOrEmpty(material.getUrl())) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// Save
		return materialRepository.save(material);
	}

	@Override
	public void update(Long materialId, Material material) {
		// The material should exist
		Material existingMaterial = findById(materialId);
		if (!isNullOrEmpty(material.getName())) {
			existingMaterial.setName(material.getName());
		}
		if (!isNullOrEmpty(material.getMaterialType())) {
			existingMaterial.setMaterialType(material.getMaterialType());
		}
		// Update
		materialRepository.save(existingMaterial);
	}

	@Override
	public void delete(Long materialId) {
		// The material should exist
		findById(materialId);
		materialRepository.delete(materialId);
	}

	@Override
	public List<Material> findMaterialsByCourseId(Long courseId) {
		// The course should exist
		courseService.findById(courseId);
		return materialRepository.findMaterialsWithCourse(courseId);
	}

}
