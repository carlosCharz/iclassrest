package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.Clase;

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
	@Query("SELECT new com.wedevol.iclass.core.entity.ClassFullInfo(cla.id AS classId, cla.startTime, cla.endTime, cla.weekDay, cla.classDate, cla.status AS classStatus, cou.id AS courseId, cou.name AS courseName, 'instructor', ins.id AS userId, ins.firstName, ins.lastName, ins.phone, enr.price, enr.currency) FROM Clase cla, Course cou, Instructor ins, InstructorEnrollment enr WHERE ins.id = cla.instructorId AND cou.id = cla.courseId AND enr.id.courseId = cou.id AND enr.id.instructorId = cla.instructorId AND enr.id.courseId = cla.courseId AND cla.studentId = :studentId AND cla.status in :classStatusList AND (:actualDateStr < DATE_FORMAT(cla.classDate, '%Y%m%d') OR (:actualDateStr = DATE_FORMAT(cla.classDate, '%Y%m%d') AND :actualTime <= cla.startTime)) order by cla.classDate asc, cla.startTime asc")
	public List<ClassFullInfo> findComingClassesWithStudentIdWithDateTimeWithClassStatusFilter(
			@Param("studentId") Long studentId, @Param("actualDateStr") String actualDateStr,
			@Param("actualTime") Integer actualTime, @Param("classStatusList") List<String> classStatusList);

	/**
	 * Return historic classes of a student since HH dd/MM/yyyy filtered by the supplied class status
	 * 
	 * @param studentId
	 * @param actualDateStr
	 * @param actualTime
	 * @param classStatusList
	 * @return list of classes
	 */
	@Query("SELECT new com.wedevol.iclass.core.entity.ClassFullInfo(cla.id AS classId, cla.startTime, cla.endTime, cla.weekDay, cla.classDate, cla.status AS classStatus, cou.id AS courseId, cou.name AS courseName, 'instructor', ins.id AS userId, ins.firstName, ins.lastName, ins.phone, enr.price, enr.currency) FROM Clase cla, Course cou, Instructor ins, InstructorEnrollment enr WHERE ins.id = cla.instructorId AND cou.id = cla.courseId AND enr.id.courseId = cou.id AND enr.id.instructorId = cla.instructorId AND enr.id.courseId = cla.courseId AND cla.studentId = :studentId AND cla.status in :classStatusList AND (:actualDateStr > DATE_FORMAT(cla.classDate, '%Y%m%d') OR (:actualDateStr = DATE_FORMAT(cla.classDate, '%Y%m%d') AND :actualTime > cla.startTime)) order by cla.classDate desc, cla.startTime desc")
	public List<ClassFullInfo> findHistoricClassesWithStudentIdWithDateTimeWithClassStatusFilter(
			@Param("studentId") Long studentId, @Param("actualDateStr") String actualDateStr,
			@Param("actualTime") Integer actualTime, @Param("classStatusList") List<String> classStatusList);

	/**
	 * Return coming classes of an instructor since HH dd/MM/yyyy filtered by the supplied class status
	 * 
	 * @param instructorId
	 * @param actualDateStr
	 * @param actualTime
	 * @param classStatusList
	 * @return list of classes
	 */
	@Query("SELECT new com.wedevol.iclass.core.entity.ClassFullInfo(cla.id AS classId, cla.startTime, cla.endTime, cla.weekDay, cla.classDate, cla.status AS classStatus, cou.id AS courseId, cou.name AS courseName, 'student', stu.id AS userId, stu.firstName, stu.lastName, stu.phone, enr.price, enr.currency) FROM Clase cla, Course cou, Student stu, InstructorEnrollment enr WHERE stu.id = cla.studentId AND cou.id = cla.courseId AND enr.id.courseId = cou.id AND enr.id.instructorId = cla.instructorId AND enr.id.instructorId = :instructorId AND cla.status in :classStatusList AND (:actualDateStr < DATE_FORMAT(cla.classDate, '%Y%m%d') OR (:actualDateStr = DATE_FORMAT(cla.classDate, '%Y%m%d') AND :actualTime <= cla.startTime)) order by cla.classDate asc, cla.startTime asc")
	public List<ClassFullInfo> findComingClassesWithInstructorIdWithDateTimeWithClassStatusFilter(
			@Param("instructorId") Long instructorId, @Param("actualDateStr") String actualDateStr,
			@Param("actualTime") Integer actualTime, @Param("classStatusList") List<String> classStatusList);

	/**
	 * Return historic classes of an instructor since HH dd/MM/yyyy filtered by the supplied class status
	 * 
	 * @param instructorId
	 * @param actualDateStr
	 * @param actualTime
	 * @param classStatusList
	 * @return list of classes
	 */
	@Query("SELECT new com.wedevol.iclass.core.entity.ClassFullInfo(cla.id AS classId, cla.startTime, cla.endTime, cla.weekDay, cla.classDate, cla.status AS classStatus, cou.id AS courseId, cou.name AS courseName, 'student', stu.id AS userId, stu.firstName, stu.lastName, stu.phone, enr.price, enr.currency) FROM Clase cla, Course cou, Student stu, InstructorEnrollment enr WHERE stu.id = cla.studentId AND cou.id = cla.courseId AND enr.id.courseId = cou.id AND enr.id.instructorId = cla.instructorId AND enr.id.instructorId = :instructorId AND cla.status in :classStatusList AND (:actualDateStr > DATE_FORMAT(cla.classDate, '%Y%m%d') OR (:actualDateStr = DATE_FORMAT(cla.classDate, '%Y%m%d') AND :actualTime > cla.startTime)) order by cla.classDate desc, cla.startTime desc")
	public List<ClassFullInfo> findHistoricClassesWithInstructorIdWithDateTimeWithClassStatusFilter(
			@Param("instructorId") Long instructorId, @Param("actualDateStr") String actualDateStr,
			@Param("actualTime") Integer actualTime, @Param("classStatusList") List<String> classStatusList);

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
