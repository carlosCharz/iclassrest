package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorEnrollment;
import com.wedevol.iclass.core.entity.InstructorEnrollmentId;

/**
 * Instructor Enrollment Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorEnrollmentService {

	List<InstructorEnrollment> findAll();

	InstructorEnrollment findById(InstructorEnrollmentId id);

	void create(InstructorEnrollment instructorEnrollment);

	void update(InstructorEnrollmentId id, InstructorEnrollment instructorEnrollment);

	void delete(InstructorEnrollmentId id);

	List<Course> findCourses(Long instructorId, String statusFilter);

	List<Instructor> findInstructors(Long courseId);

}