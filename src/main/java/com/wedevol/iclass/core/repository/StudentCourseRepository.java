package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.StudentCourse;
import com.wedevol.iclass.core.entity.StudentCourseId;

/**
 * Student_Course Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface StudentCourseRepository extends CrudRepository<StudentCourse, StudentCourseId> {
	
	/**
	 * Return the student's list of courses
	 * 
	 * @param studentId
	 * @return list of courses
	 */
	// TODO: filter query by status
	@Query("SELECT c FROM StudentCourse sc, Course c WHERE c.id = sc.studentCourseId.courseId AND sc.studentCourseId.studentId = :studentId")
	public List<Course> findCourses(@Param("studentId") Long studentId);

}
