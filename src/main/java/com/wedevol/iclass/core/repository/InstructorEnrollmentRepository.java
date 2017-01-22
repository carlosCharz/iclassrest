package com.wedevol.iclass.core.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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
	
	/**
	 * Return the price average for the instructors of a course
	 * 
	 * @param courseId
	 * @return averagePrice
	 */
	@Query("SELECT ROUND(AVG(enr.price), 2) as average FROM InstructorEnrollment enr WHERE enr.id.courseId = :courseId")
	public Float findAveragePriceForCourseId(@Param("courseId") Long courseId);


}
