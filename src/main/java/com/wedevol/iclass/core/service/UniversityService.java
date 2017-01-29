package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Faculty;
import com.wedevol.iclass.core.entity.University;

/**
 * University Service Interface
 * 
 * @author charz
 *
 */
public interface UniversityService {

	List<University> findAll();

	University findByName(String name);

	University findById(Long universityId);

	University create(University university);

	void update(Long universityId, University university);

	void delete(Long universityId);
	
	List<Faculty> findFacultiesByUniversityId(Long universityId);
	
	List<Course> findCoursesByFacultyIdByUniversityId(Long facultyId, Long universityId);

}
