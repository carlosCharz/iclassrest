package com.wedevol.iclass.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.InstructorEnrollment;
import com.wedevol.iclass.core.entity.InstructorEnrollmentId;

/**
 * Instructor Enrollment Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface InstructorEnrollmentRepository extends CrudRepository<InstructorEnrollment, InstructorEnrollmentId> {

}
