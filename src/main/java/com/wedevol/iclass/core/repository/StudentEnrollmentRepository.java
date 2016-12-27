package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;
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
	 * Return the student's list of courses filtered by the supplied course status
	 * 
	 * @param studentId
	 * @param statusList
	 * @return list of courses
	 */
	@Query("SELECT cou FROM StudentEnrollment enr, Course cou WHERE cou.id = enr.studentEnrollmentId.courseId AND enr.studentEnrollmentId.studentId = :studentId AND enr.status in :statusList")
	public List<Course> findCourses(@Param("studentId") Long studentId, @Param("statusList") List<String> statusList);

	/**
	 * Return the student list of a course
	 * 
	 * @param courseId
	 * @return list of students
	 */
	@Query("SELECT stu FROM StudentEnrollment enr, Student stu WHERE stu.id = enr.studentEnrollmentId.studentId AND enr.studentEnrollmentId.courseId = :courseId")
	public List<Student> findStudents(@Param("courseId") Long courseId);

}
