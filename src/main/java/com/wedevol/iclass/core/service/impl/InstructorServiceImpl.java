package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.DATE_FORMAT_QUERY_DB;
import static com.wedevol.iclass.core.util.CommonUtil.dateToString;
import static com.wedevol.iclass.core.util.CommonUtil.hashSHA256;

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
import com.wedevol.iclass.core.entity.Faculty;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorEnrollment;
import com.wedevol.iclass.core.entity.InstructorEnrollmentId;
import com.wedevol.iclass.core.entity.University;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.InstructorRepository;
import com.wedevol.iclass.core.service.ClassService;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.FacultyService;
import com.wedevol.iclass.core.service.InstructorEnrollmentService;
import com.wedevol.iclass.core.service.InstructorScheduleService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.NotificationService;
import com.wedevol.iclass.core.service.UniversityService;
import com.wedevol.iclass.core.view.request.UserView;
import com.wedevol.iclass.core.view.response.ClassResponse;
import com.wedevol.iclass.core.view.response.CourseResponse;
import com.wedevol.iclass.core.view.response.InstructorBasic;
import com.wedevol.iclass.core.view.response.InstructorFull;
import com.wedevol.iclass.core.view.response.ScheduleBasic;

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
	private UniversityService universityService;

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private NotificationService notificationService;

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
		if (!isNullOrEmpty(instructor.getFirstName())) {
			existingInstructor.setFirstName(instructor.getFirstName());
		}
		if (!isNullOrEmpty(instructor.getLastName())) {
			existingInstructor.setLastName(instructor.getLastName());
		}
		if (!isNullOrEmpty(instructor.getPhone())) {
			existingInstructor.setPhone(instructor.getPhone());
		}
		// TODO: analyze if we allow to change the email
		if (!isNullOrEmpty(instructor.getPassword())) {
			existingInstructor.setPassword(hashSHA256(instructor.getPassword()));
		}
		if (instructor.getBirthday() != null) {
			existingInstructor.setBirthday(instructor.getBirthday());
		}
		if (!isNullOrEmpty(instructor.getGender())) {
			existingInstructor.setGender(instructor.getGender());
		}
		if (!isNullOrEmpty(instructor.getProfilePictureUrl())) {
			existingInstructor.setProfilePictureUrl(instructor.getProfilePictureUrl());
		}
		if (!instructor.getPlaceOptions().isEmpty()) {
			existingInstructor.setPlaceOptions(instructor.getPlaceOptions());
		}
		if (instructor.getUniversityId() != null) {
			// The university should exist
			universityService.findById(instructor.getUniversityId());
			existingInstructor.setUniversityId(instructor.getUniversityId());
		}
		if (instructor.getFacultyId() != null) {
			// The faculty should exist
			facultyService.findById(instructor.getFacultyId());
			existingInstructor.setFacultyId(instructor.getFacultyId());
		}
		if (!isNullOrEmpty(instructor.getFcmToken())) {
			existingInstructor.setFcmToken(instructor.getFcmToken());
		}
		if (!isNullOrEmpty(instructor.getDeviceId())) {
			existingInstructor.setDeviceId(instructor.getDeviceId());
		}
		if (instructor.getRating() != null) {
			existingInstructor.setRating(instructor.getRating());
		}
		if (instructor.getRatingCount() != null) {
			existingInstructor.setRatingCount(instructor.getRatingCount());
		}
		if (instructor.getTotalHours() != null) {
			existingInstructor.setTotalHours(instructor.getTotalHours());
		}
		if (instructor.getLevel() != null) {
			existingInstructor.setLevel(instructor.getLevel());
		}
		if (!isNullOrEmpty(instructor.getDescription())) {
			existingInstructor.setDescription(instructor.getDescription());
		}
		// Save
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
	@Deprecated
	public List<InstructorBasic> findInstructorsByCourseIdByDateTime(Long courseId, Date classDate, Integer startTime,
			Integer endTime) {
		// Date times validation
		if (startTime >= endTime) {
			throw new BadRequestException(BadRequestErrorType.DATETIMES_NOT_VALID);
		}
		// The course should exist
		courseService.findById(courseId);
		final String dateStr = dateToString(classDate, DATE_FORMAT_QUERY_DB);
		return instructorRepository.findInstructorsWithCourseIdWithDateTime(courseId, dateStr, startTime, endTime);
	}

	@Override
	public List<CourseResponse> findCoursesByInstructorIdWithCourseStatusFilter(Long instructorId,
			String courseStatusFilter) {
		return courseService.findCoursesByInstructorIdWithCourseStatusFilter(instructorId, courseStatusFilter);
	}

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
		if (!instructorView.getPlaceOptions().isEmpty()) {
			instructorNew.setPlaceOptions(instructorView.getPlaceOptions());
		}
		if (instructorView.getUniversityId() != null) {
			// The university should exist
			universityService.findById(instructorView.getUniversityId());
			instructorNew.setUniversityId(instructorView.getUniversityId());
		}
		if (instructorView.getFacultyId() != null) {
			// The faculty should exist
			facultyService.findById(instructorView.getFacultyId());
			instructorNew.setFacultyId(instructorView.getFacultyId());
		}
		instructorNew.setFcmToken(instructorView.getFcmToken());
		instructorNew.setDeviceId(instructorView.getDeviceId());
		instructorNew.setActive(true);

		// Create the instructor
		final Instructor instructorSaved = this.create(instructorNew);

		// Create the enrollment if there is courseId
		final Long courseId = instructorView.getCourseId();
		if (courseId != null) {
			// Build the enrollment object
			final InstructorEnrollmentId enrId = new InstructorEnrollmentId(instructorSaved.getId(), courseId);
			final InstructorEnrollment enr = new InstructorEnrollment(enrId);
			enr.setPrice(instructorEnrollmentService.getAveragePriceForCourse(courseId));
			enr.setCurrency(bussinessSetting.getInstructorDefaultCurrency());
			instructorEnrollmentService.create(enr);
		}

		// Send notification
		notificationService.sendWelcomeNotificationToInstructor(instructorNew.getFcmToken());

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
	public List<ClassResponse> findComingClassesByInstructorIdByDateTimeWithClassStatusFilter(Long instructorId,
			Date actualDate, Integer actualTime, String classStatusFilter) {
		return classService.findComingClassesByInstructorIdByDateTimeWithClassStatusFilter(instructorId, actualDate,
				actualTime, classStatusFilter);
	}

	@Override
	public List<ClassResponse> findHistoricClassesByInstructorIdWithClassStatusFilter(Long instructorId,
			String classStatusFilter) {
		return classService.findHistoricClassesByInstructorIdWithClassStatusFilter(instructorId, classStatusFilter);
	}

	@Override
	public void setUserInactive(Long userId) {
		// The instructor should exist
		Instructor existingInstructor = findById(userId);
		existingInstructor.setActive(false);
		// Save
		instructorRepository.save(existingInstructor);
	}

	@Override
	public InstructorFull getInstructorByIdWithFullInfo(Long userId) {
		Instructor instructor = this.findById(userId);
		// The university should exist
		final University university = universityService.findById(instructor.getUniversityId());
		// The faculty should exist
		final Faculty faculty = facultyService.findById(instructor.getFacultyId());
		return InstructorFull.from(instructor, university, faculty);
	}

	@Override
	public Instructor getInstructorByEmail(String email) {
		final Optional<Instructor> instructorObj = Optional.ofNullable(this.findByEmail(email));
		final Instructor instructor = instructorObj.orElseThrow(
				() -> new ResourceNotFoundException(NotFoundErrorType.INSTRUCTOR_NOT_FOUND));
		return instructor;
	}

	@Override
	public Instructor findByDeviceId(String deviceId) {
		return instructorRepository.findByDeviceId(deviceId);
	}

}
