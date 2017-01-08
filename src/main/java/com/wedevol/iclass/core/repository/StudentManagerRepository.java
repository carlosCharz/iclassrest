package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;

/**
 * Student Manager Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface StudentManagerRepository extends CrudRepository<Student, Long> {

	/**
	 * Return the student list of a course
	 * 
	 * @param courseId
	 * @return list of students
	 */
	@Query("SELECT stu FROM StudentEnrollment enr, Student stu WHERE stu.id = enr.studentEnrollmentId.studentId AND enr.studentEnrollmentId.courseId = :courseId")
	public List<Student> findStudentsWithCourseId(@Param("courseId") Long courseId);

	/**
	 * Return the student's list of courses filtered by the supplied course status
	 * 
	 * @param studentId
	 * @param courseStatusList
	 * @return list of courses
	 */
	@Query("SELECT cou FROM StudentEnrollment enr, Course cou WHERE cou.id = enr.studentEnrollmentId.courseId AND enr.studentEnrollmentId.studentId = :studentId AND enr.status in :courseStatusList")
	public List<Course> findCoursesWithStudentIdWithCourseStatusFilter(@Param("studentId") Long studentId,
			@Param("courseStatusList") List<String> courseStatusList);

	/**
	 * Return classes of a student since hh:mm dd/MM/yyyy filtered by the supplied class status
	 * 
	 * @param studentId
	 * @param actualDateStr
	 * @param actualTime
	 * @param classStatusList
	 * @return list of classes
	 */
	@Query("SELECT new com.wedevol.iclass.core.entity.ClassFullInfo(cla.id AS classId, cla.startTime, cla.endTime, cla.classDate, cla.status AS classStatus, cou.id AS courseId, cou.name AS courseName, 'instructor', ins.id AS userId, ins.firstName, ins.lastName, ins.phone, enr.price, enr.currency) FROM ClassRoom cla, Course cou, Instructor ins, InstructorEnrollment enr WHERE ins.id = cla.instructorId AND cou.id = cla.courseId AND enr.id.courseId = cou.id AND enr.id.instructorId = cla.instructorId AND enr.id.courseId = cla.courseId AND cla.studentId = :studentId AND cla.status in :classStatusList AND (:actualDateStr < DATE_FORMAT(classDate, '%d/%m/%Y') OR (:actualDateStr = DATE_FORMAT(classDate, '%d/%m/%Y') AND :actualTime <= cla.startTime)) order by cla.classDate asc, cla.startTime asc")
	public List<ClassFullInfo> findClassesWithStudentIdWithDateTimeWithClassStatusFilter(
			@Param("studentId") Long studentId, @Param("actualDateStr") String actualDateStr,
			@Param("actualTime") Integer actualTime, @Param("classStatusList") List<String> classStatusList);

}
