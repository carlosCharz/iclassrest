package com.wedevol.iclass.core.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;
import com.wedevol.iclass.core.entity.ScheduleBasic;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.repository.InstructorManagerRepository;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.InstructorManagerService;
import com.wedevol.iclass.core.util.CommonUtil;
import com.wedevol.iclass.core.util.CoreUtil;

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

	@Override
	public List<Course> findCoursesByInstructorId(Long instructorId, String statusFilter) {
		if (!CoreUtil.areValidCourseStatusFilters(statusFilter)) {
			throw new BadRequestException(BadRequestErrorType.COURSE_STATUS_NOT_VALID);
		}
		final List<String> status = Arrays.asList(statusFilter.split(","));
		return insMgrRepository.findCoursesWithInstructorId(instructorId, status);
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
		final String dateStr = CommonUtil.dateToString(classDate);
		return insMgrRepository.findInstructorsWithCourseIdWithDateTime(courseId, dateStr, startTime, endTime);
	}

	@Override
	public List<ScheduleBasic> findScheduleByCourseIdByDate(Long courseId, Date classDate) {
		// The course should exist
		courseService.findById(courseId);
		final String dateStr = CommonUtil.dateToString(classDate);
		return insMgrRepository.findSchedulesWithCourseIdWithDate(courseId, dateStr);
	}

}
