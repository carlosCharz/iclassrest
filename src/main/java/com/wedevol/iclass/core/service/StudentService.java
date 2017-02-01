package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.CourseFullInfo;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.view.request.UserView;
import com.wedevol.iclass.core.view.response.StudentView;

/**
 * Student Service Interface
 * 
 * @author charz
 *
 */
public interface StudentService {

	List<Student> findAll();

	Student findByEmail(String email);
	
	Student findByDeviceId(String deviceId);

	Student findById(Long userId);

	Student create(Student student);

	void update(Long userId, Student student);

	void delete(Long userId);
	
	void setUserInactive(Long userId);
	
	Student createStudentWithCourse(UserView studentView);

	List<Student> findStudentsByCourseId(Long courseId);
	
	List<CourseFullInfo> findCoursesByStudentIdWithCourseStatusFilter(Long studentId, String courseStatusFilter);
	
	List<ClassFullInfo> findClassesByStudentIdByDateTimeWithClassStatusFilter(Long studentId, Date actualDate,
			Integer actualTime, String statusFilter);
	
	StudentView getStudentByIdWithFullInfo(Long userId);
	
	Student getStudentByEmail(String email);
}
