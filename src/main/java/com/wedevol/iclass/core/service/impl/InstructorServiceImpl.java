package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.dateToString;
import static com.wedevol.iclass.core.util.CommonUtil.hashSHA256;
import static com.wedevol.iclass.core.util.CommonUtil.isNullOrEmpty;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.configuration.BusinessSetting;
import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.CourseFullInfo;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;
import com.wedevol.iclass.core.entity.InstructorEnrollment;
import com.wedevol.iclass.core.entity.InstructorEnrollmentId;
import com.wedevol.iclass.core.entity.ScheduleBasic;
import com.wedevol.iclass.core.entity.enums.EnrollmentStatusType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.InstructorRepository;
import com.wedevol.iclass.core.service.ClassService;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.InstructorEnrollmentService;
import com.wedevol.iclass.core.service.InstructorScheduleService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.view.UserView;

/**
 * Instructor Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class InstructorServiceImpl implements InstructorService {

	protected static final Logger logger = LoggerFactory.getLogger(InstructorServiceImpl.class);

	@Autowired
	private InstructorRepository instructorRepository;

	@Autowired
	private CourseService courseService;

	@Autowired
	private ClassService classService;

	@Autowired
	private InstructorEnrollmentService instructorEnrollmentService;

	@Autowired
	private InstructorScheduleService instructorScheduleService;

	@Autowired
	private BusinessSetting bussinessSetting;

	@Override
	public List<Instructor> findAll() {
		final Iterable<Instructor> instructorsIterator = instructorRepository.findAll();
		return Lists.newArrayList(instructorsIterator);
	}

	@Override
	public Instructor findByEmail(String email) {
		return instructorRepository.findByEmail(email);
	}

	@Override
	public Instructor findById(Long userId) {
		Optional<Instructor> instructorObj = Optional.ofNullable(instructorRepository.findOne(userId));
		return instructorObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.INSTRUCTOR_NOT_FOUND));
	}

	@Override
	public Instructor create(Instructor instructor) {
		// We first search by email, the instructor should not exist
		Optional<Instructor> instructorObj = Optional.ofNullable(findByEmail(instructor.getEmail()));
		if (instructorObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.USER_ALREADY_EXISTS);
		}
		return instructorRepository.save(instructor);
	}

	@Override
	public void update(Long userId, Instructor instructor) {
		// The instructor should exist
		Instructor existingInstructor = findById(userId);
		existingInstructor.setFirstName(instructor.getFirstName());
		existingInstructor.setLastName(instructor.getLastName());
		existingInstructor.setPhone(instructor.getPhone());
		existingInstructor.setEmail(instructor.getEmail());
		// TODO: validate that the password has changed
		// existingInstructor.setPassword(hashSHA256(instructor.getPassword()));
		if (instructor.getBirthday() != null) {
			existingInstructor.setBirthday(instructor.getBirthday());
		}
		if (!isNullOrEmpty(instructor.getGender())) {
			existingInstructor.setGender(instructor.getGender());
		}
		if (!isNullOrEmpty(instructor.getProfilePictureUrl())) {
			existingInstructor.setProfilePictureUrl(instructor.getProfilePictureUrl());
		}
		existingInstructor.setPlaceOptions(instructor.getPlaceOptions());
		if (!isNullOrEmpty(instructor.getUniversity())) {
			existingInstructor.setUniversity(instructor.getUniversity());
		}
		instructorRepository.save(existingInstructor);
	}

	@Override
	public void delete(Long userId) {
		// The instructor should exist
		findById(userId);
		instructorRepository.delete(userId);
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
		return instructorRepository.findInstructorsWithCourseIdWithWeekDayWithTime(courseId, weekDayStr, startTime,
				endTime);
	}

	@Override
	public List<Instructor> findInstructorsByCourseId(Long courseId) {
		return instructorRepository.findInstructorsWithCourseId(courseId);
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
		return instructorRepository.findInstructorsWithCourseIdWithDateTime(courseId, dateStr, startTime, endTime);
	}

	@Override
	public List<CourseFullInfo> findCoursesByInstructorIdWithCourseStatusFilter(Long instructorId,
			String courseStatusFilter) {
		return courseService.findCoursesByInstructorIdWithCourseStatusFilter(instructorId, courseStatusFilter);
	}

	@Override
	@Deprecated
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
		if (!instructorView.getPlaceOptions().isEmpty()) {
			instructorNew.setPlaceOptions(instructorView.getPlaceOptions());
		}
		if (instructorView.getUniversity() != null) {
			instructorNew.setUniversity(instructorView.getUniversity());
		}
		if (instructorView.getFcmToken() != null) {
			instructorNew.setFcmToken(instructorView.getFcmToken());
		}
		instructorNew.setActive(true);
		final Instructor instructorSaved = this.create(instructorNew);

		// Create the enrollment if there is courseId
		final Long courseId = instructorView.getCourseId();
		if (courseId != null) {
			// The course should exist
			courseService.findById(courseId);
			// Create the enrollment
			final InstructorEnrollmentId enrId = new InstructorEnrollmentId(instructorSaved.getId(), courseId);
			final InstructorEnrollment enr = new InstructorEnrollment(enrId, EnrollmentStatusType.REQUESTED.getDescription());
			enr.setPrice(instructorEnrollmentService.getAveragePriceForCourse(courseId));
			enr.setCurrency(bussinessSetting.getInstructorDefaultCurrency());
			instructorEnrollmentService.create(enr);
		}
		return instructorSaved;
	}

	@Override
	public List<ScheduleBasic> findSchedulesByCourseIdByDate(Long courseId, Date classDate) {
		return instructorScheduleService.findSchedulesByCourseIdByDate(courseId, classDate);
	}

	@Override
	public List<ScheduleBasic> findSchedulesByCourseIdByWeekDay(Long courseId, String weekDayStr) {
		return instructorScheduleService.findSchedulesByCourseIdByWeekDay(courseId, weekDayStr);
	}

	@Override
	public List<ClassFullInfo> findClassesByInstructorIdByDateTimeWithClassStatusFilter(Long instructorId,
			Date actualDate, Integer actualTime, String classStatusFilter) {
		return classService.findClassesByInstructorIdByDateTimeWithClassStatusFilter(instructorId, actualDate,
				actualTime, classStatusFilter);
	}

}
