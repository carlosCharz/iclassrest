package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.DATE_FORMAT_QUERY_DB;
import static com.wedevol.iclass.core.util.CommonUtil.dateToString;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.InstructorSchedule;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.InstructorScheduleRepository;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.InstructorScheduleService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.view.response.ScheduleBasic;

/**
 * Instructor Schedule Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class InstructorScheduleServiceImpl implements InstructorScheduleService {

	protected static final Logger logger = LoggerFactory.getLogger(InstructorScheduleServiceImpl.class);

	@Autowired
	private InstructorScheduleRepository scheduleRepository;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private CourseService courseService;

	@Override
	public List<InstructorSchedule> findAll() {
		final Iterable<InstructorSchedule> scheduleIterator = scheduleRepository.findAll();
		return Lists.newArrayList(scheduleIterator);
	}

	@Override
	public InstructorSchedule findById(Long scheduleId) {
		Optional<InstructorSchedule> scheduleObj = Optional.ofNullable(scheduleRepository.findOne(scheduleId));
		return scheduleObj.orElseThrow(
				() -> new ResourceNotFoundException(NotFoundErrorType.INSTRUCTOR_SCHEDULE_NOT_FOUND));
	}

	@Override
	public InstructorSchedule create(InstructorSchedule schedule) {
		// Fields missing validation
		if (schedule.getInstructorId() == null || schedule.getWeekDay() == null || schedule.getClassDate() == null
				|| schedule.getStartTime() == null || schedule.getEndTime() == null) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// Date times validation
		if (schedule.getStartTime() >= schedule.getEndTime()) {
			throw new BadRequestException(BadRequestErrorType.DATETIMES_NOT_VALID);
		}
		// The instructor should exist
		instructorService.findById(schedule.getInstructorId());
		// Save
		return scheduleRepository.save(schedule);
	}

	@Override
	public void update(Long scheduleId, InstructorSchedule schedule) {
		// The instructor schedule should exist
		InstructorSchedule existingSchedule = findById(scheduleId);
		if (schedule.getInstructorId() != null) {
			// TODO: analyze if we need to validate this
			// The instructor should exist
			instructorService.findById(schedule.getInstructorId());
			existingSchedule.setInstructorId(schedule.getInstructorId());
		}
		if (schedule.getWeekDay() != null) {
			existingSchedule.setWeekDay(schedule.getWeekDay());
		}
		if (schedule.getClassDate() != null) {
			existingSchedule.setClassDate(schedule.getClassDate());
		}
		if (schedule.getStartTime() != null) {
			existingSchedule.setStartTime(schedule.getStartTime());
		}
		if (schedule.getEndTime() != null) {
			existingSchedule.setEndTime(schedule.getEndTime());
		}
		// Update
		scheduleRepository.save(existingSchedule);
	}

	@Override
	public void delete(Long scheduleId) {
		// The instructor schedule should exist
		findById(scheduleId);
		scheduleRepository.delete(scheduleId);
	}

	@Override
	public List<InstructorSchedule> findSchedulesForWeekByInstructorId(Long instructorId) {
		// Then, the instructor should exist
		instructorService.findById(instructorId);
		return scheduleRepository.findByInstructorIdOrderByClassDateAscStartTimeAsc(instructorId);
	}

	@Override
	public List<ScheduleBasic> findSchedulesByCourseIdByDate(Long courseId, Date classDate) {
		// The course should exist
		courseService.findById(courseId);
		final String dateStr = dateToString(classDate, DATE_FORMAT_QUERY_DB);
		return scheduleRepository.findSchedulesWithCourseIdWithDate(courseId, dateStr);
	}

	@Override
	public List<ScheduleBasic> findSchedulesByCourseIdByWeekDay(Long courseId, String weekDayStr) {
		// The course should exist
		courseService.findById(courseId);
		// TODO: missing validation for the weekDay
		return scheduleRepository.findSchedulesByCourseIdWithWeekDay(courseId, weekDayStr);
	}

}
