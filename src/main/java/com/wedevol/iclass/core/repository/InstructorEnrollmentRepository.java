package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
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
	 * Return the instructor's list of courses filtered by the supplied course status
	 * 
	 * @param instructorId
	 * @return list of courses
	 */
	@Query("SELECT cou FROM InstructorEnrollment enr, Course cou WHERE cou.id = enr.instructorEnrollmentId.courseId AND enr.instructorEnrollmentId.instructorId = :instructorId AND enr.status in :statusList")
	public List<Course> findCourses(@Param("instructorId") Long instructorId, @Param("statusList") List<String> statusList);

	/**
	 * Return the instructor list of a course
	 * 
	 * @param courseId
	 * @return list of instructors
	 */
	@Query("SELECT ins FROM InstructorEnrollment enr, Instructor ins WHERE ins.id = enr.instructorEnrollmentId.instructorId AND enr.instructorEnrollmentId.courseId = :courseId")
	public List<Instructor> findInstructors(@Param("courseId") Long courseId);

}
