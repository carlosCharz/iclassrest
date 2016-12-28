package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;
import com.wedevol.iclass.core.view.InstructorCourseRequest;

/**
 * Instructor Managers Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorManagerService {

	List<Course> findCoursesByInstructor(Long instructorId, String statusFilter);

	List<Instructor> findInstructorsByCourse(Long courseId);
	
	List<InstructorBasic> findInstructorsByCourseByDate(InstructorCourseRequest request);

}
