package com.wedevol.iclass.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Course;

/**
 * Course Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface CourseRepository extends CrudRepository<Course, Long> {

	/**
	 * Return the course having the passed course name or null if no course is
	 * found.
	 * 
	 * @param courseName
	 * @return course
	 */
	public Course findByName(String name);

}
