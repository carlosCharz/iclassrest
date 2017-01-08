package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.dateToString;
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
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.repository.StudentManagerRepository;
import com.wedevol.iclass.core.service.StudentManagerService;
import com.wedevol.iclass.core.service.StudentService;

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
	private StudentManagerRepository stuMgrRepository;

	@Override
	public List<Course> findCoursesByStudentIdWithCourseStatusFilter(Long studentId, String courseStatusFilter) {
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
