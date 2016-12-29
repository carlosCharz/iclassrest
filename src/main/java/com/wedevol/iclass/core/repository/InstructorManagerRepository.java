package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;

/**
 * Instructor Manager Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface InstructorManagerRepository extends CrudRepository<Instructor, Long> {
	/**
	 * Return the instructor's list of courses filtered by the supplied course status
	 * 
	 * @param instructorId
	 * @param statusList
	 * @return list of courses
	 */
	@Query("SELECT cou FROM InstructorEnrollment enr, Course cou WHERE cou.id = enr.id.courseId AND enr.id.instructorId = :instructorId AND enr.status in :statusList")
	public List<Course> findCoursesWithInstructorId(@Param("instructorId") Long instructorId,
			@Param("statusList") List<String> statusList);

	/**
	 * Return the instructor list of a course
	 * 
	 * @param courseId
	 * @return list of instructors
	 */
	@Query("SELECT ins FROM InstructorEnrollment enr, Instructor ins WHERE ins.id = enr.id.instructorId AND enr.id.courseId = :courseId")
	public List<Instructor> findInstructorsWithCourseId(@Param("courseId") Long courseId);

	/**
	 * Return the instructors of a course for an specific date
	 * 
	 * @param courseId
	 * @return list of courses
	 */
	// TODO: finish this method, add the status = available
	@Query("SELECT new com.wedevol.iclass.core.entity.InstructorBasic(ins.id, ins.firstName, ins.lastName, ins.rating, ins.level, 0, 'S/.') FROM Instructor ins")
	public List<InstructorBasic> findInstructorsWithCourseIdWithDate();
}
