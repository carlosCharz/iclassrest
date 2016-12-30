package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;

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

}
