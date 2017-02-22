package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.StudentEnrollment;
import com.wedevol.iclass.core.entity.StudentEnrollmentId;
import com.wedevol.iclass.core.util.DefaultInterface;

/**
 * Student Enrollment Service Interface
 * 
 * @author charz
 *
 */
public interface StudentEnrollmentService extends DefaultInterface {

	List<StudentEnrollment> findAll();

	StudentEnrollment findById(StudentEnrollmentId id);

	StudentEnrollment create(StudentEnrollment studentEnrollment);

	void update(StudentEnrollmentId id, StudentEnrollment studentEnrollment);

	void delete(StudentEnrollmentId id);

}
