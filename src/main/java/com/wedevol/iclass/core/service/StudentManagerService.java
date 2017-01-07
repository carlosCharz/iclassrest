package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;

/**
 * Student Manager Service Interface
 * 
 * @author charz
 *
 */
public interface StudentManagerService {

	List<Student> findStudentsByCourseId(Long courseId);

	List<Course> findCoursesByStudentIdWithCourseStatusFilter(Long studentId, String courseStatusFilter);

}
