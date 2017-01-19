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
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;
import com.wedevol.iclass.core.entity.InstructorEnrollment;
import com.wedevol.iclass.core.entity.InstructorEnrollmentId;
import com.wedevol.iclass.core.entity.ScheduleBasic;
import com.wedevol.iclass.core.entity.enums.CourseStatusType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.repository.InstructorManagerRepository;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.InstructorEnrollmentService;
import com.wedevol.iclass.core.service.InstructorManagerService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.view.UserView;

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
	private InstructorEnrollmentService instructorEnrollmentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private InstructorService instructorService;

	@Override
	public Instructor createInstructorWithCourse(UserView instructorView) {
		// Create the user
		Instructor instructorNew = new Instructor.InstructorBuilder(instructorView.getFirstName(),
				instructorView.getLastName(), instructorView.getPhone(), instructorView.getEmail(),
				hashSHA256(instructorView
											.getPassword()))
															.build();
		if (instructorView.getBirthday() != null) {
			instructorNew.setBirthday(instructorView.getBirthday());
		}
		if (instructorView.getGender() != null) {
			instructorNew.setGender(instructorView.getGender());
		}
		if (instructorView.getProfilePictureUrl() != null) {
			instructorNew.setProfilePictureUrl(instructorView.getProfilePictureUrl());
		}
		if (instructorView.getPlaceOptions() != null) {
			instructorNew.setPlaceOptions(instructorView.getPlaceOptions());
		}
		if (instructorView.getUniversity() != null) {
			instructorNew.setUniversity(instructorView.getUniversity());
		}
		final Instructor instructorSaved = instructorService.create(instructorNew);

		// Create the course
		final Long courseId = instructorView.getCourseId();
		if (courseId != null) {
			final InstructorEnrollmentId enrId = new InstructorEnrollmentId(instructorSaved.getId(), courseId);
			final InstructorEnrollment enr = new InstructorEnrollment(enrId, CourseStatusType.FREE.getDescription());
			// TODO: stop hardcoding
			enr.setPrice(15);
			enr.setCurrency("S/.");
			instructorEnrollmentService.create(enr);
		}
		return instructorSaved;
	}

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
	public List<ScheduleBasic> findSchedulesByCourseIdByWeekDay(Long courseId, String weekDayStr) {
		// The course should exist
		courseService.findById(courseId);
		return insMgrRepository.findSchedulesByCourseIdWithWeekDay(courseId, weekDayStr);
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

	@Override
	public List<InstructorBasic> findInstructorsByCourseIdByWeekDayByTime(Long courseId, String weekDayStr,
			Integer startTime, Integer endTime) {
		// Date times validation
		if (startTime >= endTime) {
			throw new BadRequestException(BadRequestErrorType.DATETIMES_NOT_VALID);
		}
		// The course should exist
		courseService.findById(courseId);
		return insMgrRepository.findInstructorsWithCourseIdWithWeekDayWithTime(courseId, weekDayStr, startTime,
				endTime);
	}

}
