package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.view.request.UserView;
import com.wedevol.iclass.core.view.response.ClassResponse;
import com.wedevol.iclass.core.view.response.CourseResponse;
import com.wedevol.iclass.core.view.response.StudentFull;

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

	List<CourseResponse> findCoursesByStudentIdWithCourseStatusFilter(Long studentId, String courseStatusFilter);

	List<ClassResponse> findComingClassesByStudentIdByDateTimeWithClassStatusFilter(Long studentId, Date actualDate,
			Integer actualTime, String statusFilter);

	List<ClassResponse> findHistoricClassesByStudentIdWithClassStatusFilter(Long studentId, String statusFilter);

	StudentFull getStudentByIdWithFullInfo(Long userId);

	Student getStudentByEmail(String email);
}
