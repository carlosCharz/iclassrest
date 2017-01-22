package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.CourseFullInfo;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.ScheduleBasic;
import com.wedevol.iclass.core.view.UserView;

/**
 * Instructor Manager Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorManagerService {

	Instructor createInstructorWithCourse(UserView instructorView);

	List<CourseFullInfo> findCoursesByInstructorIdWithCourseStatusFilter(Long instructorId, String courseStatusFilter);

	List<ScheduleBasic> findSchedulesByCourseIdByDate(Long courseId, Date classDate);

	List<ScheduleBasic> findSchedulesByCourseIdByWeekDay(Long courseId, String weekDayStr);

	List<ClassFullInfo> findClassesByInstructorIdByDateTimeWithClassStatusFilter(Long instructorId, Date actualDate,
			Integer actualTime, String classStatusFilter);

}
