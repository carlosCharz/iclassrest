package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Faculty;

/**
 * Faculty Service Interface
 * 
 * @author charz
 *
 */
public interface FacultyService {

	List<Faculty> findAll();

	Faculty findByName(String name);

	Faculty findById(Long facultyId);

	Faculty create(Faculty faculty);

	void update(Long facultyId, Faculty faculty);

	void delete(Long facultyId);
	
	List<Faculty> findFacultiesByUniversityId(Long universityId);

}
