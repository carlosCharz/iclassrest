package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.view.response.CourseFull;

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

	Course create(Course course);

	void update(Long courseId, Course course);

	void delete(Long courseId);
	
	List<Instructor> findInstructorsByCourseId(Long courseId);
	
	List<CourseFull> findCoursesByStudentIdWithCourseStatusFilter(Long studentId, String courseStatusFilter);
	
	List<Student> findStudentsByCourseId(Long courseId);
	
	List<CourseFull> findCoursesByInstructorIdWithCourseStatusFilter(Long instructorId, String courseStatusFilter);
	
	List<Course> findCoursesByFacultyIdByUniversityId(Long facultyId, Long universityId);

}
