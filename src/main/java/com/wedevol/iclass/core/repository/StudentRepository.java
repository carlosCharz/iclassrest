package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Student;

/**
 * Student Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface StudentRepository extends CrudRepository<Student, Long> {

	/**
	 * Return the student having the passed email or null if no student is found.
	 * 
	 * @param email
	 * @return student
	 */
	public Student findByEmail(String email);

	/**
	 * Return the student having the passed deviceId or null if no deviceId is found.
	 * 
	 * @param deviceId
	 * @return student
	 */
	public Student findByDeviceId(String deviceId);

	/**
	 * Return the students of a course
	 * 
	 * @param courseId
	 * @return list of students
	 */
	@Query(value = "SELECT stu "
			+ "FROM StudentEnrollment enr INNER JOIN Student stu ON stu.id = enr.id.studentId "
			+ "WHERE enr.id.courseId = :courseId", nativeQuery = true)
	public List<Student> findStudentsWithCourseId(@Param("courseId") Long courseId);

}
