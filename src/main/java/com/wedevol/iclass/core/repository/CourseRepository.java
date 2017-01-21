package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.CourseFullInfo;

/**
 * Course Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface CourseRepository extends CrudRepository<Course, Long> {

	/**
	 * Return the course having the passed course name or null if no course is found.
	 * 
	 * @param courseName
	 * @return course
	 */
	public Course findByName(String name);
	
	/**
	 * Return the student's list of courses filtered by the supplied course status
	 * 
	 * @param studentId
	 * @param courseStatusList
	 * @return list of courses
	 */
	@Query("SELECT new com.wedevol.iclass.core.entity.CourseFullInfo(cou.id, cou.name, cou.description, cou.faculty, cou.university, enr.status) FROM StudentEnrollment enr, Course cou WHERE cou.id = enr.id.courseId AND enr.id.studentId = :studentId AND enr.status in :courseStatusList")
	public List<CourseFullInfo> findCoursesWithStudentIdWithCourseStatusFilter(@Param("studentId") Long studentId,
			@Param("courseStatusList") List<String> courseStatusList);

}
