package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.StudentCourse;
import com.wedevol.iclass.core.entity.StudentCourseId;

/**
 * Student_Course Service Interface
 * 
 * @author charz
 *
 */
public interface StudentCourseService {

	List<StudentCourse> findAll();

	StudentCourse findById(StudentCourseId id);

	void create(StudentCourse studentCourse);

	void update(StudentCourseId id, StudentCourse studentCourse);

	void delete(StudentCourseId id);

	List<Course> findCourses(Long studentId);

	List<Student> findStudents(Long courseId);

}
