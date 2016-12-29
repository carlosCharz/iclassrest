package com.wedevol.iclass.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.StudentEnrollment;
import com.wedevol.iclass.core.entity.StudentEnrollmentId;

/**
 * Student Enrollment Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface StudentEnrollmentRepository extends CrudRepository<StudentEnrollment, StudentEnrollmentId> {

}
