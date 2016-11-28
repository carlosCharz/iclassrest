package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Course;
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
	 * Return the student having the passed email or null if no student is
	 * found.
	 * 
	 * @param email
	 */
	public Student findByEmail(String email);

	// TODO: filter query by status
	@Query("SELECT c FROM StudentCourse sc, Course c WHERE c.id = sc.studentCourseId.courseId AND sc.studentCourseId.studentId = :studentId")
	public List<Course> findStudentCourses(@Param("studentId") Long studentId);

}
