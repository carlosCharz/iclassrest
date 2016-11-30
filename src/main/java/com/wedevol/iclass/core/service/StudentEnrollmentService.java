package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.StudentEnrollment;
import com.wedevol.iclass.core.entity.StudentEnrollmentId;

/**
 * Student Enrollment Service Interface
 * 
 * @author charz
 *
 */
public interface StudentEnrollmentService {

	List<StudentEnrollment> findAll();

	StudentEnrollment findById(StudentEnrollmentId id);

	void create(StudentEnrollment studentEnrollment);

	void update(StudentEnrollmentId id, StudentEnrollment studentEnrollment);

	void delete(StudentEnrollmentId id);

	List<Course> findCourses(Long studentId);

	List<Student> findStudents(Long courseId);

}
