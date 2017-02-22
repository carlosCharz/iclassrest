package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.view.response.CourseResponse;

/**
 * Course Service Interface
 * 
 * @author charz
 *
 */
public interface CourseService extends BaseService<Course> {

	List<Course> findAll();

	Course findByName(String name);
	
	List<Instructor> findInstructorsByCourseId(Long courseId);
	
	List<CourseResponse> findCoursesByStudentIdWithCourseStatusFilter(Long studentId, String courseStatusFilter);
	
	List<Student> findStudentsByCourseId(Long courseId);
	
	List<CourseResponse> findCoursesByInstructorIdWithCourseStatusFilter(Long instructorId, String courseStatusFilter);
	
	List<Course> findCoursesByFacultyIdByUniversityId(Long facultyId, Long universityId);

}
