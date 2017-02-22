package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Faculty;

/**
 * Faculty Service Interface
 * 
 * @author charz
 *
 */
public interface FacultyService extends BaseService<Faculty> {

	List<Faculty> findAll();

	Faculty findByName(String name);
	
	List<Faculty> findFacultiesByUniversityId(Long universityId);

}
