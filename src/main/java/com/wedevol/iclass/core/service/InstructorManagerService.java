package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;

/**
 * Instructor Managers Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorManagerService {

	List<Course> findCoursesByInstructor(Long instructorId, String statusFilter);

	List<Instructor> findInstructorsByCourse(Long courseId);

	List<InstructorBasic> findInstructorsByCourseByDate(Long courseId, Date classDate);

}
