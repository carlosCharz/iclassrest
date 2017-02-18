package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.view.response.InstructorBasic;

/**
 * Instructor Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface InstructorRepository extends CrudRepository<Instructor, Long> {

	/**
	 * Return the instructor having the passed email or null if no instructor is found.
	 * 
	 * @param email
	 * @return instructor
	 */
	public Instructor findByEmail(String email);

	/**
	 * Return the instructor having the passed deviceId or null if no deviceId is found.
	 * 
	 * @param deviceId
	 * @return instructor
	 */
	public Instructor findByDeviceId(String deviceId);

	/**
	 * Return the instructor list of a course
	 * 
	 * @param courseId
	 * @return list of instructors
	 */
	@Query(value = "SELECT ins.* "
			+ "FROM instructor_enrollment enr INNER JOIN instructor ins ON ins.id = enr.instructorId "
			+ "WHERE enr.courseId = :courseId", nativeQuery = true)
	public List<Instructor> findInstructorsWithCourseId(@Param("courseId") Long courseId);

	/**
	 * Return the instructors of a course for an specific date (course status: payed)
	 * 
	 * @param courseId
	 * @param classDateStr
	 * @param startTime
	 * @param endTime
	 * @return list of instructors
	 */
	@Deprecated
	@Query("SELECT new com.wedevol.iclass.core.view.response.InstructorBasic(ins.id, ins.firstName, ins.lastName, ins.rating, ins.level, enr.price, enr.currency) "
			+ "FROM Instructor ins, InstructorEnrollment enr, InstructorSchedule sch "
			+ "WHERE ins.id = sch.instructorId AND ins.id = enr.id.instructorId AND "
			+ "enr.id.courseId = :courseId AND enr.status = 'payed' AND "
			+ "DATE_FORMAT(sch.classDate, '%Y%m%d') = :classDateStr AND "
			+ "sch.startTime <= :startTime AND sch.endTime >= :endTime")
	public List<InstructorBasic> findInstructorsWithCourseIdWithDateTime(@Param("courseId") Long courseId,
			@Param("classDateStr") String classDateStr, @Param("startTime") Integer startTime,
			@Param("endTime") Integer endTime);

	/**
	 * Return the instructors of a course for an specific week day (course status: payed)
	 * 
	 * @param courseId
	 * @param weekDayStr
	 * @param startTime
	 * @param endTime
	 * @return list of instructors
	 */
	@Query("SELECT new com.wedevol.iclass.core.view.response.InstructorBasic(ins.id, ins.firstName, ins.lastName, ins.rating, ins.level, enr.price, enr.currency) "
			+ "FROM Instructor ins, InstructorEnrollment enr, InstructorSchedule sch "
			+ "WHERE ins.id = sch.instructorId AND ins.id = enr.id.instructorId AND "
			+ "enr.id.courseId = :courseId AND enr.status = 'payed' AND sch.weekDay = :weekDayStr AND "
			+ "sch.startTime <= :startTime AND sch.endTime >= :endTime")
	public List<InstructorBasic> findInstructorsWithCourseIdWithWeekDayWithTime(@Param("courseId") Long courseId,
			@Param("weekDayStr") String weekDayStr, @Param("startTime") Integer startTime,
			@Param("endTime") Integer endTime);

}
