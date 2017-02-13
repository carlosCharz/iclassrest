package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Clase;
import com.wedevol.iclass.core.view.response.ClassResponse;

/**
 * Class Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface ClassRepository extends CrudRepository<Clase, Long> {

	/**
	 * Return coming classes of a student since HH dd/MM/yyyy filtered by the supplied class status
	 * 
	 * @param studentId
	 * @param actualDateStr
	 * @param actualTime
	 * @param classStatusList
	 * @return list of classes
	 */
	@Query("SELECT new com.wedevol.iclass.core.view.response.ClassResponse(cla.id AS classId, cla.startTime, cla.endTime, cla.weekDay, cla.classDate, cla.status AS classStatus, cou.id AS courseId, cou.name AS courseName, 'instructor', ins.id AS userId, ins.firstName, ins.lastName, ins.phone, cla.price, cla.currency, cla.ratingToInstructor, cla.ratingToStudent) FROM Clase cla, Course cou, Instructor ins WHERE cou.id = cla.courseId AND ins.id = cla.instructorId AND cla.studentId = :studentId AND cla.status in :classStatusList AND cla.ratingToStudent IS NULL AND (:actualDateStr < DATE_FORMAT(cla.classDate, '%Y%m%d') OR (:actualDateStr = DATE_FORMAT(cla.classDate, '%Y%m%d') AND :actualTime <= cla.startTime)) ORDER by cla.classDate ASC, cla.startTime ASC")
	public List<ClassResponse> findComingClassesWithStudentIdWithDateTimeWithClassStatusFilter(
			@Param("studentId") Long studentId, @Param("actualDateStr") String actualDateStr,
			@Param("actualTime") Integer actualTime, @Param("classStatusList") List<String> classStatusList);

	/**
	 * Return historic classes of a student filtered by the supplied class status
	 * 
	 * @param studentId
	 * @param classStatusList
	 * @return list of classes
	 */
	@Query("SELECT new com.wedevol.iclass.core.view.response.ClassResponse(cla.id AS classId, cla.startTime, cla.endTime, cla.weekDay, cla.classDate, cla.status AS classStatus, cou.id AS courseId, cou.name AS courseName, 'instructor', ins.id AS userId, ins.firstName, ins.lastName, ins.phone, cla.price, cla.currency, cla.ratingToInstructor, cla.ratingToStudent) FROM Clase cla, Course cou, Instructor ins WHERE cou.id = cla.courseId AND ins.id = cla.instructorId AND cla.studentId = :studentId AND cla.status in :classStatusList ORDER by cla.classDate ASC, cla.startTime ASC")
	public List<ClassResponse> findHistoricClassesWithStudentIdWithClassStatusFilter(@Param("studentId") Long studentId,
			@Param("classStatusList") List<String> classStatusList);

	/**
	 * Return coming classes of an instructor since HH dd/MM/yyyy filtered by the supplied class status
	 * 
	 * @param instructorId
	 * @param actualDateStr
	 * @param actualTime
	 * @param classStatusList
	 * @return list of classes
	 */
	@Query("SELECT new com.wedevol.iclass.core.view.response.ClassResponse(cla.id AS classId, cla.startTime, cla.endTime, cla.weekDay, cla.classDate, cla.status AS classStatus, cou.id AS courseId, cou.name AS courseName, 'student', stu.id AS userId, stu.firstName, stu.lastName, stu.phone, cla.price, cla.currency, cla.ratingToInstructor, cla.ratingToStudent) FROM Clase cla, Course cou, Student stu WHERE cou.id = cla.courseId AND stu.id = cla.studentId AND cla.instructorId = :instructorId AND cla.status in :classStatusList AND cla.ratingToInstructor IS NULL AND (:actualDateStr < DATE_FORMAT(cla.classDate, '%Y%m%d') OR (:actualDateStr = DATE_FORMAT(cla.classDate, '%Y%m%d') AND :actualTime <= cla.startTime)) order by cla.classDate asc, cla.startTime asc")
	public List<ClassResponse> findComingClassesWithInstructorIdWithDateTimeWithClassStatusFilter(
			@Param("instructorId") Long instructorId, @Param("actualDateStr") String actualDateStr,
			@Param("actualTime") Integer actualTime, @Param("classStatusList") List<String> classStatusList);

	/**
	 * Return historic classes of an instructor filtered by the supplied class status
	 * 
	 * @param instructorId
	 * @param classStatusList
	 * @return list of classes
	 */
	@Query("SELECT new com.wedevol.iclass.core.view.response.ClassResponse(cla.id AS classId, cla.startTime, cla.endTime, cla.weekDay, cla.classDate, cla.status AS classStatus, cou.id AS courseId, cou.name AS courseName, 'student', stu.id AS userId, stu.firstName, stu.lastName, stu.phone, cla.price, cla.currency, cla.ratingToInstructor, cla.ratingToStudent) FROM Clase cla, Course cou, Student stu WHERE cou.id = cla.courseId AND stu.id = cla.studentId AND cla.instructorId = :instructorId AND cla.status in :classStatusList ORDER by cla.classDate ASC, cla.startTime ASC")
	public List<ClassResponse> findHistoricClassesWithInstructorIdWithClassStatusFilter(
			@Param("instructorId") Long instructorId, @Param("classStatusList") List<String> classStatusList);

	/**
	 * Return the confirmed finished classes to change the status to DONE (now - 5h > classDate + endTime AND status =
	 * CONFIRMED)
	 * 
	 * @return list of classes
	 */
	@Deprecated
	@Query(value = "SELECT * FROM class cla WHERE DATE_FORMAT(cla.classDate, '%Y%m%d') < DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 5 HOUR), '%Y%m%d') OR (DATE_FORMAT(cla.classDate, '%Y%m%d') = DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 5 HOUR), '%Y%m%d') AND CAST(DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 5 HOUR), '%H') AS SIGNED) >= cla.endTime) AND cla.status = 'confirmed'", nativeQuery = true)
	public List<Clase> getConfirmedFinishedClasses();

}
