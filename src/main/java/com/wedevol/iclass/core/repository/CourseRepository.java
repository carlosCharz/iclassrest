package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.view.response.CourseResponse;

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
	 * Return the student's list of courses filtered by the supplied course status order by course name asc
	 * 
	 * @param studentId
	 * @param courseStatusList
	 * @return list of courses
	 */
	@Query("SELECT new com.wedevol.iclass.core.view.response.CourseResponse(cou.id, cou.name, cou.description, cou.classMaterialUrl, cou.exerciseMaterialUrl, fac.shortName, uni.shortName, enr.status) "
			+ "FROM StudentEnrollment enr, Course cou, Faculty fac, University uni "
			+ "WHERE cou.id = enr.id.courseId AND fac.id = cou.facultyId AND uni.id=cou.universityId AND "
			+ "enr.id.studentId = :studentId AND enr.status in :courseStatusList "
			+ "order by cou.name asc")
	public List<CourseResponse> findCoursesWithStudentIdWithCourseStatusFilter(@Param("studentId") Long studentId,
			@Param("courseStatusList") List<String> courseStatusList);

	/**
	 * Return the instructor's list of courses filtered by the supplied course status order by course name asc
	 * 
	 * @param instructorId
	 * @param courseStatusList
	 * @return list of courses
	 */
	@Query("SELECT new com.wedevol.iclass.core.view.response.CourseResponse(cou.id, cou.name, cou.description, cou.classMaterialUrl, cou.exerciseMaterialUrl, fac.shortName, uni.shortName, enr.status, enr.price, enr.currency) "
			+ "FROM InstructorEnrollment enr, Course cou, Faculty fac, University uni "
			+ "WHERE cou.id = enr.id.courseId AND fac.id = cou.facultyId AND uni.id=cou.universityId AND "
			+ "enr.id.instructorId = :instructorId AND enr.status in :courseStatusList "
			+ "order by cou.name asc")
	public List<CourseResponse> findCoursesWithInstructorIdWithCourseStatusFilter(
			@Param("instructorId") Long instructorId, @Param("courseStatusList") List<String> courseStatusList);

	/**
	 * Return the courses of a faculty of a university order by course name asc
	 * 
	 * @param facultyId
	 * @param universityId
	 * @return list of courses
	 */
	@Query("SELECT cou "
			+ "FROM Course cou "
			+ "WHERE cou.facultyId = :facultyId AND cou.universityId = :universityId "
			+ "order by cou.name asc")
	public List<Course> findCoursesWithFacultyIdWithUniversityId(@Param("facultyId") Long facultyId,
			@Param("universityId") Long universityId);

}
