package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.InstructorEnrollment;
import com.wedevol.iclass.core.entity.InstructorEnrollmentId;
import com.wedevol.iclass.core.util.DefaultInterface;

/**
 * Instructor Enrollment Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorEnrollmentService extends DefaultInterface {

	List<InstructorEnrollment> findAll();

	InstructorEnrollment findById(InstructorEnrollmentId id);

	InstructorEnrollment create(InstructorEnrollment instructorEnrollment);

	void update(InstructorEnrollmentId id, InstructorEnrollment instructorEnrollment);

	void delete(InstructorEnrollmentId id);

	Float getAveragePriceForCourse(Long courseId);

	void approveCourseInstructorEnrollment(Long instructorId, Long courseId);

	void denyCourseInstructorEnrollment(Long instructorId, Long courseId);

}
