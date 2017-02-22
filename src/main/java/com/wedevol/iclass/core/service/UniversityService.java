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
public interface UniversityService extends BaseService<University>  {

	List<University> findAll();

	University findByName(String name);
	
	List<Faculty> findFacultiesByUniversityId(Long universityId);
	
	List<Course> findCoursesByFacultyIdByUniversityId(Long facultyId, Long universityId);

}
