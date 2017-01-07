package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.Course;
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
	 * @param statusList
	 * @return list of courses
	 */
	@Query("SELECT cou FROM InstructorEnrollment enr, Course cou WHERE cou.id = enr.id.courseId AND enr.id.instructorId = :instructorId AND enr.status in :statusList")
	public List<Course> findCoursesWithInstructorId(@Param("instructorId") Long instructorId,
			@Param("statusList") List<String> statusList);

	/**
	 * Return the instructor list of a course
	 * 
	 * @param courseId
	 * @return list of instructors
	 */
	@Query("SELECT ins FROM InstructorEnrollment enr, Instructor ins WHERE ins.id = enr.id.instructorId AND enr.id.courseId = :courseId")
	public List<Instructor> findInstructorsWithCourseId(@Param("courseId") Long courseId);

	/**
	 * Return the instructors of a course for an specific date (course status: free or payed, schedule: available =
	 * true)
	 * 
	 * @param courseId
	 * @param classDateStr
	 * @param startTime
	 * @param endTime
	 * @return list of courses
	 */
	@Query("SELECT new com.wedevol.iclass.core.entity.InstructorBasic(ins.id, ins.firstName, ins.lastName, ins.rating, ins.level, enr.price, enr.currency) FROM Instructor ins, InstructorEnrollment enr, InstructorSchedule sch WHERE ins.id = sch.instructorId AND ins.id = enr.id.instructorId AND enr.id.courseId = :courseId AND sch.available = true AND (enr.status = 'free' OR enr.status = 'payed') AND DATE_FORMAT(classDate, '%d/%m/%Y') = :classDateStr AND sch.startTime <= :startTime AND sch.endTime >= :endTime")
	public List<InstructorBasic> findInstructorsWithCourseIdWithDateTime(@Param("courseId") Long courseId,
			@Param("classDateStr") String classDateStr, @Param("startTime") Integer startTime,
			@Param("endTime") Integer endTime);

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
	 * Return classes of an instructor since hh:mm dd/MM/yyyy filtered by the supplied class status
	 * 
	 * @param instructorId
	 * @param actualDateStr
	 * @param actualTime
	 * @param classStatusList
	 * @return list of classes
	 */
	public List<ClassFullInfo> findClassesWithInstructorIdWithDateTimeFilteringStatus(
			@Param("instructorId") Long instructorId, @Param("actualDateStr") String actualDateStr,
			@Param("startTime") Integer actualTime, @Param("classStatusList") List<String> classStatusList);

}
