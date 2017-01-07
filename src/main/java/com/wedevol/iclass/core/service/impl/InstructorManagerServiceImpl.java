package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.dateToString;
import static com.wedevol.iclass.core.util.CoreUtil.areValidCourseStatusFilters;
import static com.wedevol.iclass.core.util.CoreUtil.areValidClassStatusFilters;

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
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;
import com.wedevol.iclass.core.entity.ScheduleBasic;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.repository.InstructorManagerRepository;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.InstructorManagerService;
import com.wedevol.iclass.core.service.InstructorService;

/**
 * Instructor Manager Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class InstructorManagerServiceImpl implements InstructorManagerService {

	protected static final Logger logger = LoggerFactory.getLogger(InstructorManagerServiceImpl.class);

	@Autowired
	private InstructorManagerRepository insMgrRepository;

	@Autowired
	private CourseService courseService;

	@Autowired
	private InstructorService instructorService;

	@Override
	public List<Course> findCoursesByInstructorIdWithCourseStatusFilter(Long instructorId, String courseStatusFilter) {
		if (!areValidCourseStatusFilters(courseStatusFilter)) {
			throw new BadRequestException(BadRequestErrorType.COURSE_STATUS_NOT_VALID);
		}
		final List<String> courseStatusList = Arrays.asList(courseStatusFilter.split(","));
		return insMgrRepository.findCoursesWithInstructorIdWithCourseStatusFilter(instructorId, courseStatusList);
	}

	@Override
	public List<Instructor> findInstructorsByCourseId(Long courseId) {
		return insMgrRepository.findInstructorsWithCourseId(courseId);
	}

	@Override
	public List<InstructorBasic> findInstructorsByCourseIdByDateTime(Long courseId, Date classDate, Integer startTime,
			Integer endTime) {
		// Date times validation
		if (startTime >= endTime) {
			throw new BadRequestException(BadRequestErrorType.DATETIMES_NOT_VALID);
		}
		// The course should exist
		courseService.findById(courseId);
		final String dateStr = dateToString(classDate);
		return insMgrRepository.findInstructorsWithCourseIdWithDateTime(courseId, dateStr, startTime, endTime);
	}

	@Override
	public List<ScheduleBasic> findSchedulesByCourseIdByDate(Long courseId, Date classDate) {
		// The course should exist
		courseService.findById(courseId);
		final String dateStr = dateToString(classDate);
		return insMgrRepository.findSchedulesWithCourseIdWithDate(courseId, dateStr);
	}

	@Override
	public List<ClassFullInfo> findClassesByInstructorIdByDateTimeWithClassStatusFilter(Long instructorId,
			Date actualDate, Integer actualTime, String statusFilter) {
		// The class status should be valid
		if (!areValidClassStatusFilters(statusFilter)) {
			throw new BadRequestException(BadRequestErrorType.CLASS_STATUS_NOT_VALID);
		}
		final List<String> classStatusList = Arrays.asList(statusFilter.split(","));
		// The instructor should exist
		instructorService.findById(instructorId);
		final String actualDateStr = dateToString(actualDate);
		return insMgrRepository.findClassesWithInstructorIdWithDateTimeWithClassStatusFilter(instructorId,
				actualDateStr, actualTime, classStatusList);
	}

}
