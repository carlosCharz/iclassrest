package com.wedevol.iclass.core.service;

import java.util.List;

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
	
	Float getAveragePriceForCourse(Long courseId);

}
