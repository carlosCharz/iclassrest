package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.CourseFullInfo;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;
import com.wedevol.iclass.core.entity.ScheduleBasic;

/**
 * Instructor Manager Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface InstructorManagerRepository extends CrudRepository<Instructor, Long> {
	/**
	 * Return the instructor's list of courses filtered by the supplied course status
	 * 
	 * @param instructorId
	 * @param courseStatusList
	 * @return list of courses
	 */
	@Query("SELECT new com.wedevol.iclass.core.entity.CourseFullInfo(cou.id, cou.name, cou.description, cou.faculty, cou.university, enr.status, enr.price, enr.currency) FROM InstructorEnrollment enr, Course cou WHERE cou.id = enr.id.courseId AND enr.id.instructorId = :instructorId AND enr.status in :courseStatusList")
	public List<CourseFullInfo> findCoursesWithInstructorIdWithCourseStatusFilter(
			@Param("instructorId") Long instructorId, @Param("courseStatusList") List<String> courseStatusList);


	/**
	 * Return the available schedules of a course for an specific date (course status: free or payed, schedule:
	 * available = true)
	 * 
	 * @param courseId
	 * @param classDateStr
	 * @return list of schedules
	 */
	@Query("SELECT new com.wedevol.iclass.core.entity.ScheduleBasic(sch.id, ins.id, sch.startTime, sch.endTime) FROM Instructor ins, InstructorEnrollment enr, InstructorSchedule sch WHERE ins.id = sch.instructorId AND ins.id = enr.id.instructorId AND enr.id.courseId = :courseId AND sch.available = true AND (enr.status = 'free' OR enr.status = 'payed') AND DATE_FORMAT(classDate, '%d/%m/%Y') = :classDateStr")
	public List<ScheduleBasic> findSchedulesWithCourseIdWithDate(@Param("courseId") Long courseId,
			@Param("classDateStr") String classDateStr);

	/**
	 * Return the available schedules of a course for a week day (course status: free or payed, schedule: available =
	 * true)
	 * 
	 * @param courseId
	 * @param weekDay
	 * @return list of schedules
	 */
	@Query("SELECT new com.wedevol.iclass.core.entity.ScheduleBasic(sch.id, ins.id, sch.startTime, sch.endTime) FROM Instructor ins, InstructorEnrollment enr, InstructorSchedule sch WHERE ins.id = sch.instructorId AND ins.id = enr.id.instructorId AND enr.id.courseId = :courseId AND sch.available = true AND (enr.status = 'free' OR enr.status = 'payed') AND sch.weekDay = :weekDayStr")
	public List<ScheduleBasic> findSchedulesByCourseIdWithWeekDay(@Param("courseId") Long courseId,
			@Param("weekDayStr") String weekDayStr);

	/**
	 * Return classes of an instructor since hh:mm dd/MM/yyyy filtered by the supplied class status
	 * 
	 * @param instructorId
	 * @param actualDateStr
	 * @param actualTime
	 * @param classStatusList
	 * @return list of classes
	 */
	@Query("SELECT new com.wedevol.iclass.core.entity.ClassFullInfo(cla.id AS classId, cla.startTime, cla.endTime, cla.weekDay, cla.classDate, cla.status AS classStatus, cou.id AS courseId, cou.name AS courseName, 'student', stu.id AS userId, stu.firstName, stu.lastName, stu.phone, enr.price, enr.currency) FROM ClassRoom cla, Course cou, Student stu, InstructorEnrollment enr WHERE stu.id = cla.studentId AND cou.id = cla.courseId AND enr.id.courseId = cou.id AND enr.id.instructorId = cla.instructorId AND enr.id.instructorId = :instructorId AND cla.status in :classStatusList AND (:actualDateStr < DATE_FORMAT(classDate, '%d/%m/%Y') OR (:actualDateStr = DATE_FORMAT(classDate, '%d/%m/%Y') AND :actualTime <= cla.startTime)) order by cla.classDate asc, cla.startTime asc")
	public List<ClassFullInfo> findClassesWithInstructorIdWithDateTimeWithClassStatusFilter(
			@Param("instructorId") Long instructorId, @Param("actualDateStr") String actualDateStr,
			@Param("actualTime") Integer actualTime, @Param("classStatusList") List<String> classStatusList);

}
