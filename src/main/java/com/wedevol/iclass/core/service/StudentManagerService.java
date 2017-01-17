package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.view.StudentView;

/**
 * Student Manager Service Interface
 * 
 * @author charz
 *
 */
public interface StudentManagerService {
	
	void createStudentWithCourse(StudentView studentView);

	List<Student> findStudentsByCourseId(Long courseId);

	List<Course> findCoursesByStudentIdWithCourseStatusFilter(Long studentId, String courseStatusFilter);

	List<ClassFullInfo> findClassesByStudentIdByDateTimeWithClassStatusFilter(Long studentId, Date actualDate,
			Integer actualTime, String statusFilter);

}
