package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Course;

/**
 * Course Service Interface
 * 
 * @author charz
 *
 */
public interface CourseService {

	List<Course> findAll();

	Course findByName(String name);

	Course findById(Long courseId);

	void create(Course course);

	void update(Long courseId, Course course);

	void delete(Long courseId);

}
