package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.ClassEntity;

/**
 * Class Service Interface
 * 
 * @author charz
 *
 */
public interface ClassService {

	List<ClassEntity> findAll();

	ClassEntity findById(Long classId);

	void create(ClassEntity c);

	void update(Long classId, ClassEntity c);

	void delete(Long classId);

}
