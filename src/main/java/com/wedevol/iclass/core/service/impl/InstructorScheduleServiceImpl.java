package com.wedevol.iclass.core.service.impl;

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
import com.wedevol.iclass.core.entity.ScheduleBasic;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.InstructorScheduleRepository;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.InstructorScheduleService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.util.CommonUtil;

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
	public void create(InstructorSchedule schedule) {
		// TODO: Analize if the instructor schedule should not exist first, and validate null fields
		// Date times validation
		if (schedule.getStartTime() >= schedule.getEndTime()) {
			throw new BadRequestException(BadRequestErrorType.DATETIMES_NOT_VALID);
		}
		// The instructor should exist
		instructorService.findById(schedule.getInstructorId());
		// Save
		scheduleRepository.save(schedule);
	}

	@Override
	public void update(Long scheduleId, InstructorSchedule schedule) {
		// The instructor schedule should exist
		InstructorSchedule existingSchedule = findById(scheduleId);
		// Then, the instructor should exist
		instructorService.findById(schedule.getInstructorId());
		// Update
		existingSchedule.setInstructorId(schedule.getInstructorId());
		existingSchedule.setWeekDay(schedule.getWeekDay());
		existingSchedule.setClassDate(schedule.getClassDate());
		existingSchedule.setStartTime(schedule.getStartTime());
		existingSchedule.setEndTime(schedule.getEndTime());
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
		final String dateStr = dateToString(classDate, CommonUtil.DATE_FORMAT_QUERY_DB);
		return scheduleRepository.findSchedulesWithCourseIdWithDate(courseId, dateStr);
	}

	@Override
	public List<ScheduleBasic> findSchedulesByCourseIdByWeekDay(Long courseId, String weekDayStr) {
		// The course should exist
		courseService.findById(courseId);
		return scheduleRepository.findSchedulesByCourseIdWithWeekDay(courseId, weekDayStr);
	}

}
