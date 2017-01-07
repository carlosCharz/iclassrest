package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;
import com.wedevol.iclass.core.entity.ScheduleBasic;

/**
 * Instructor Manager Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorManagerService {

	List<Course> findCoursesByInstructorId(Long instructorId, String statusFilter);

	List<Instructor> findInstructorsByCourseId(Long courseId);

	List<InstructorBasic> findInstructorsByCourseIdByDateTime(Long courseId, Date classDate, Integer startTime,
			Integer endTime);

	List<ScheduleBasic> findScheduleByCourseIdByDate(Long courseId, Date classDate);

	List<ClassFullInfo> findClassesByInstructorIdByDateTimeFilteringStatus(Long instructorId, Date actualDate,
			Integer actualTime, String statusFilter);

}
