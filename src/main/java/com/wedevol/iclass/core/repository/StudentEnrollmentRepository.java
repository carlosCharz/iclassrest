package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Course;
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

	/**
	 * Return the student's list of courses
	 * 
	 * @param studentId
	 * @return list of courses
	 */
	// TODO: filter query by status
	@Query(value="SELECT cou.* FROM student_enrollment enr, course cou WHERE cou.id = enr.courseId AND enr.studentId = :studentId", nativeQuery = true)
	public List<Object[]> findCourses(@Param("studentId") Long studentId);
	
	/**
	 * Return the student's list of courses with topic information
	 * 
	 * @param studentId
	 * @return list of courses
	 */
	// TODO: filter query by status
	@Query("SELECT cou FROM StudentEnrollment enr, Course cou WHERE cou.id = enr.studentEnrollmentId.courseId AND enr.studentEnrollmentId.studentId = :studentId")
	public List<Course> findCoursesComplete(@Param("studentId") Long studentId);

}
