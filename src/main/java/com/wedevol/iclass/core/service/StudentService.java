package com.wedevol.iclass.core.service;

import java.util.Date;
import java.util.List;

import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.view.request.UserView;
import com.wedevol.iclass.core.view.response.ClassFull;
import com.wedevol.iclass.core.view.response.CourseFull;
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

	List<CourseFull> findCoursesByStudentIdWithCourseStatusFilter(Long studentId, String courseStatusFilter);

	List<ClassFull> findComingClassesByStudentIdByDateTimeWithClassStatusFilter(Long studentId, Date actualDate,
			Integer actualTime, String statusFilter);

	List<ClassFull> findHistoricClassesByStudentIdWithClassStatusFilter(Long studentId, String statusFilter);

	StudentFull getStudentByIdWithFullInfo(Long userId);

	Student getStudentByEmail(String email);
}
