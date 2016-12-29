package com.wedevol.iclass.core.service;

import java.util.List;

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

}
