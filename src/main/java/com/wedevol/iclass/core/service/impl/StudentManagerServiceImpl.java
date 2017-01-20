package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.dateToString;
import static com.wedevol.iclass.core.util.CommonUtil.hashSHA256;
import static com.wedevol.iclass.core.util.CoreUtil.areValidClassStatusFilters;
import static com.wedevol.iclass.core.util.CoreUtil.areValidCourseStatusFilters;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.CourseFullInfo;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.StudentEnrollment;
import com.wedevol.iclass.core.entity.StudentEnrollmentId;
import com.wedevol.iclass.core.entity.enums.CourseStatusType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.repository.StudentManagerRepository;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.StudentEnrollmentService;
import com.wedevol.iclass.core.service.StudentManagerService;
import com.wedevol.iclass.core.service.StudentService;
import com.wedevol.iclass.core.view.UserView;

/**
 * Student Manager Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class StudentManagerServiceImpl implements StudentManagerService {

	protected static final Logger logger = LoggerFactory.getLogger(StudentManagerServiceImpl.class);

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentEnrollmentService studentEnrollmentService;

	@Autowired
	private StudentManagerRepository stuMgrRepository;

	@Override
	public Student createStudentWithCourse(UserView studentView) {
		// Create the user
		Student studentNew = new Student.StudentBuilder(studentView.getFirstName(), studentView.getLastName(),
				studentView.getPhone(), studentView.getEmail(), hashSHA256(studentView.getPassword()))
																										.build();
		if (studentView.getBirthday() != null) {
			studentNew.setBirthday(studentView.getBirthday());
		}
		if (studentView.getGender() != null) {
			studentNew.setGender(studentView.getGender());
		}
		if (studentView.getProfilePictureUrl() != null) {
			studentNew.setProfilePictureUrl(studentView.getProfilePictureUrl());
		}
		if (studentView.getPlaceOptions() != null) {
			studentNew.setPlaceOptions(studentView.getPlaceOptions());
		}
		if (studentView.getUniversity() != null) {
			studentNew.setUniversity(studentView.getUniversity());
		}
		if (studentView.getFcmToken() != null) {
			studentNew.setFcmToken(studentView.getFcmToken());
		}
		studentNew.setActive(true);
		final Student studentSaved = studentService.create(studentNew);

		// Create the enrollment if there is courseId
		final Long courseId = studentView.getCourseId();
		if (courseId != null) {
			// The course should exist
			courseService.findById(courseId);
			// Create the enrollment
			final StudentEnrollmentId enrId = new StudentEnrollmentId(studentSaved.getId(), courseId);
			final StudentEnrollment enr = new StudentEnrollment(enrId, CourseStatusType.FREE.getDescription());
			studentEnrollmentService.create(enr);
		}
		return studentSaved;
	}

	@Override
	public List<CourseFullInfo> findCoursesByStudentIdWithCourseStatusFilter(Long studentId, String courseStatusFilter) {
		if (!areValidCourseStatusFilters(courseStatusFilter)) {
			throw new BadRequestException(BadRequestErrorType.COURSE_STATUS_NOT_VALID);
		}
		final List<String> courseStatusList = Arrays.asList(courseStatusFilter.split(","));
		return stuMgrRepository.findCoursesWithStudentIdWithCourseStatusFilter(studentId, courseStatusList);
	}

	@Override
	public List<Student> findStudentsByCourseId(Long courseId) {
		return stuMgrRepository.findStudentsWithCourseId(courseId);
	}

	@Override
	public List<ClassFullInfo> findClassesByStudentIdByDateTimeWithClassStatusFilter(Long studentId, Date actualDate,
			Integer actualTime, String statusFilter) {
		// The class status should be valid
		if (!areValidClassStatusFilters(statusFilter)) {
			throw new BadRequestException(BadRequestErrorType.CLASS_STATUS_NOT_VALID);
		}
		final List<String> classStatusList = Arrays.asList(statusFilter.split(","));
		// The student should exist
		studentService.findById(studentId);
		final String actualDateStr = dateToString(actualDate);
		return stuMgrRepository.findClassesWithStudentIdWithDateTimeWithClassStatusFilter(studentId, actualDateStr,
				actualTime, classStatusList);
	}

}
