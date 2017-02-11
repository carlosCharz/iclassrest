package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.InstructorSchedule;
import com.wedevol.iclass.core.view.response.ScheduleBasic;

/**
 * Instructor Schedule Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface InstructorScheduleRepository extends CrudRepository<InstructorSchedule, Long> {

	/**
	 * Return the schedules having the passed instructorId or null if no schedules are found.
	 * 
	 * @param instructorId
	 * @return schedules
	 */
	public List<InstructorSchedule> findByInstructorIdOrderByClassDateAscStartTimeAsc(Long instructorId);

	/**
	 * Return the schedules of a course for an specific date (course status: free or payed)
	 * 
	 * @param courseId
	 * @param classDateStr
	 * @return list of schedules
	 */
	@Query("SELECT new com.wedevol.iclass.core.view.response.ScheduleBasic(sch.id, ins.id, sch.startTime, sch.endTime) FROM Instructor ins, InstructorEnrollment enr, InstructorSchedule sch WHERE ins.id = sch.instructorId AND ins.id = enr.id.instructorId AND enr.id.courseId = :courseId AND enr.status = 'payed' AND DATE_FORMAT(sch.classDate, '%Y%m%d') = :classDateStr")
	public List<ScheduleBasic> findSchedulesWithCourseIdWithDate(@Param("courseId") Long courseId,
			@Param("classDateStr") String classDateStr);

	/**
	 * Return the schedules of a course for a week day (course status: payed)
	 * 
	 * @param courseId
	 * @param weekDay
	 * @return list of schedules
	 */
	@Query("SELECT new com.wedevol.iclass.core.view.response.ScheduleBasic(sch.id, ins.id, sch.startTime, sch.endTime) FROM Instructor ins, InstructorEnrollment enr, InstructorSchedule sch WHERE ins.id = sch.instructorId AND ins.id = enr.id.instructorId AND enr.id.courseId = :courseId AND enr.status = 'payed' AND sch.weekDay = :weekDayStr")
	public List<ScheduleBasic> findSchedulesByCourseIdWithWeekDay(@Param("courseId") Long courseId,
			@Param("weekDayStr") String weekDayStr);
}
