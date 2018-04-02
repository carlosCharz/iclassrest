package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.DATE_FORMAT_QUERY_DB;
import static com.wedevol.iclass.core.util.CommonUtil.dateToString;
import static com.wedevol.iclass.core.util.CoreUtil.areValidClassStatusFilters;

import java.util.Arrays;
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
import com.wedevol.iclass.core.entity.Clase;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorEnrollment;
import com.wedevol.iclass.core.entity.InstructorEnrollmentId;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.StudentEnrollmentId;
import com.wedevol.iclass.core.entity.enums.ClassStatusType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.ClassRepository;
import com.wedevol.iclass.core.service.BatchNotificationService;
import com.wedevol.iclass.core.service.ClassService;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.InstructorEnrollmentService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.NotificationService;
import com.wedevol.iclass.core.service.StudentEnrollmentService;
import com.wedevol.iclass.core.service.StudentService;
import com.wedevol.iclass.core.view.response.ClassResponse;

/**
 * Class Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class ClassServiceImpl implements ClassService {

	protected static final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private StudentService studentService;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private InstructorEnrollmentService instructorEnrollmentService;

	@Autowired
	private StudentEnrollmentService studentEnrollmentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private BatchNotificationService batchNotificationService;
	
	@Autowired
	private BusinessSetting bussinessSetting;

	@Override
	public List<Clase> findAll() {
		final Iterable<Clase> classIterator = classRepository.findAll();
		return Lists.newArrayList(classIterator);
	}

	@Override
	public Clase findById(Long classId) {
		Optional<Clase> classObj = classRepository.findById(classId);
		return classObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.CLASS_NOT_FOUND));
	}

	@Override
	public Clase create(Clase c) {
		// Fields missing validation
		if (c.getStudentId() == null || c.getInstructorId() == null || c.getCourseId() == null || c.getWeekDay() == null
				|| c.getClassDate() == null || c.getStartTime() == null || c.getEndTime() == null) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// Date times validation
		if (c.getStartTime() >= c.getEndTime()) {
			throw new BadRequestException(BadRequestErrorType.DATETIMES_NOT_VALID);
		}
		// The student should exist
		final Student student = studentService.findById(c.getStudentId());
		// The instructor should exist
		final Instructor instructor = instructorService.findById(c.getInstructorId());
		// The course should exist
		final Course course = courseService.findById(c.getCourseId());
		// The instructor enrollment should exist
		final InstructorEnrollmentId insEnrId = new InstructorEnrollmentId(c.getInstructorId(), c.getCourseId());
		final InstructorEnrollment enrollment = instructorEnrollmentService.findById(insEnrId);
		// The student enrollment should exist
		final StudentEnrollmentId stuEnrId = new StudentEnrollmentId(c.getStudentId(), c.getCourseId());
		studentEnrollmentService.findById(stuEnrId);
		c.setStatus(ClassStatusType.REQUESTED.getDescription());
		// Set the price and currency from the instructor enrollment
		c.setCurrency(enrollment.getCurrency());
		c.setPrice(enrollment.getPrice());
		// Save the class
		final Clase clase = classRepository.save(c);
		// Send notification
		notificationService.sendNewClassRequestNotificationToInstructor(instructor.getFcmToken(), student, course);
		return clase;
	}

	@Override
	public void update(Long classId, Clase c) {
		// The class should exist
		Clase existingClass = findById(classId);
		// TODO: analyze if we need to update the student, instructor, course
		if (c.getStudentId() != null) {
			// The student should exist
			studentService.findById(c.getStudentId());
			existingClass.setStudentId(c.getStudentId());
		}
		if (c.getInstructorId() != null) {
			// The instructor should exist
			instructorService.findById(c.getInstructorId());
			existingClass.setInstructorId(c.getInstructorId());
		}
		if (c.getCourseId() != null) {
			// The course should exist
			courseService.findById(c.getCourseId());
			existingClass.setCourseId(c.getCourseId());
		}
		if (!isNullOrEmpty(c.getWeekDay())) {
			existingClass.setWeekDay(c.getWeekDay());
		}
		if (c.getClassDate() != null) {
			existingClass.setClassDate(c.getClassDate());
		}
		if (c.getStartTime() != null) {
			existingClass.setStartTime(c.getStartTime());
		}
		if (c.getEndTime() != null) {
			existingClass.setEndTime(c.getEndTime());
		}
		if (!isNullOrEmpty(c.getStatus())) {
			existingClass.setStatus(c.getStatus());
		}
		if (c.getRatingToInstructor() != null) {
			existingClass.setRatingToInstructor(c.getRatingToInstructor());
		}
		if (c.getRatingToStudent() != null) {
			existingClass.setRatingToStudent(c.getRatingToStudent());
		}
		if (c.getPrice() != null) {
			existingClass.setPrice(c.getPrice());
		}
		if (c.getCurrency() != null) {
			existingClass.setCurrency(c.getCurrency());
		}
		// Update
		classRepository.save(existingClass);
	}

	@Override
	public void delete(Long classId) {
		// The class should exist
		findById(classId);
		classRepository.deleteById(classId);
	}

	@Override
	public List<ClassResponse> findComingClassesByStudentIdByDateTimeWithClassStatusFilter(Long studentId,
			Date actualDate, Integer actualTime, String statusFilter) {
		// The class status should be valid
		if (!areValidClassStatusFilters(statusFilter)) {
			throw new BadRequestException(BadRequestErrorType.CLASS_STATUS_NOT_VALID);
		}
		// The student should exist
		studentService.findById(studentId);
		final List<String> classStatusList = Arrays.asList(statusFilter.split(","));
		final String actualDateStr = dateToString(actualDate, DATE_FORMAT_QUERY_DB);
		return classRepository.findComingClassesWithStudentIdWithDateTimeWithClassStatusFilter(studentId, actualDateStr,
				actualTime, classStatusList);
	}

	@Override
	public List<ClassResponse> findHistoricClassesByStudentIdWithClassStatusFilter(Long studentId,
			String statusFilter) {
		// The class status should be valid
		if (!areValidClassStatusFilters(statusFilter)) {
			throw new BadRequestException(BadRequestErrorType.CLASS_STATUS_NOT_VALID);
		}
		// The student should exist
		studentService.findById(studentId);
		final List<String> classStatusList = Arrays.asList(statusFilter.split(","));
		return classRepository.findHistoricClassesWithStudentIdWithClassStatusFilter(studentId, classStatusList);
	}

	@Override
	public List<ClassResponse> findComingClassesByInstructorIdByDateTimeWithClassStatusFilter(Long instructorId,
			Date actualDate, Integer actualTime, String statusFilter) {
		// The class status should be valid
		if (!areValidClassStatusFilters(statusFilter)) {
			throw new BadRequestException(BadRequestErrorType.CLASS_STATUS_NOT_VALID);
		}
		final List<String> classStatusList = Arrays.asList(statusFilter.split(","));
		// The instructor should exist
		instructorService.findById(instructorId);
		final String actualDateStr = dateToString(actualDate, DATE_FORMAT_QUERY_DB);
		return classRepository.findComingClassesWithInstructorIdWithDateTimeWithClassStatusFilter(instructorId,
				actualDateStr, actualTime, classStatusList);
	}

	@Override
	public List<ClassResponse> findHistoricClassesByInstructorIdWithClassStatusFilter(Long instructorId,
			String statusFilter) {
		// The class status should be valid
		if (!areValidClassStatusFilters(statusFilter)) {
			throw new BadRequestException(BadRequestErrorType.CLASS_STATUS_NOT_VALID);
		}
		final List<String> classStatusList = Arrays.asList(statusFilter.split(","));
		// The instructor should exist
		instructorService.findById(instructorId);
		return classRepository.findHistoricClassesWithInstructorIdWithClassStatusFilter(instructorId, classStatusList);
	}

	@Override
	public void instructorConfirmClass(Long classId, Long instructorId) {
		// The class should exist
		Clase existingClass = findById(classId);
		// The student should exist
		final Student student = studentService.findById(existingClass.getStudentId());
		// The instructor should exist
		final Instructor instructor = instructorService.findById(existingClass.getInstructorId());
		// The course should exist
		final Course course = courseService.findById(existingClass.getCourseId());
		// Change status
		existingClass.setStatus(ClassStatusType.CONFIRMED.getDescription());
		// Update
		classRepository.save(existingClass);
		// Send notification
		notificationService.sendClassConfirmedNotificationToStudent(student.getFcmToken(), instructor, course);
		// Save batch notification
		batchNotificationService.saveClassComingSoonReminder(student, instructor, course, existingClass);
	}

	@Override
	public void instructorRejectClass(Long classId, Long instructorId) {
		// The class should exist
		Clase existingClass = findById(classId);
		// The student should exist
		final Student student = studentService.findById(existingClass.getStudentId());
		// The instructor should exist
		final Instructor instructor = instructorService.findById(existingClass.getInstructorId());
		// The course should exist
		final Course course = courseService.findById(existingClass.getCourseId());
		// Change status
		existingClass.setStatus(ClassStatusType.REJECTED.getDescription());
		// Update
		classRepository.save(existingClass);
		// Send notification
		notificationService.sendClassRejectedNotificationToStudent(student.getFcmToken(), instructor, course);
		// Delete batch notifications
		batchNotificationService.deleteClassComingSoonReminder(existingClass.getId());
	}

	@Override
	public void studentCancelClass(Long classId, Long studentId) {
		// The class should exist
		Clase existingClass = findById(classId);
		// The student should exist
		final Student student = studentService.findById(existingClass.getStudentId());
		// The instructor should exist
		final Instructor instructor = instructorService.findById(existingClass.getInstructorId());
		// The course should exist
		final Course course = courseService.findById(existingClass.getCourseId());
		// Change status
		existingClass.setStatus(ClassStatusType.CANCELLED.getDescription());
		// Update
		classRepository.save(existingClass);
		// Send notification
		notificationService.sendClassCancelledNotificationToInstructor(instructor.getFcmToken(), student, course);
	}

	@Override
	public void rateInstructorClass(Long classId, Long instructorId, Float rating) {
		// The class should exist
		Clase existingClass = findById(classId);
		// The course should exist
		final Course course = courseService.findById(existingClass.getCourseId());
		// The instructor should exist
		final Instructor instructor = instructorService.findById(instructorId);
		// The student should exist
		final Student student = studentService.findById(existingClass.getStudentId());
		// Update the instructor rating and rating count
		final Float newRating = (instructor.getRatingCount() * instructor.getRating() + rating)
				/ (instructor.getRatingCount() + 1);
		Instructor newInstructor = new Instructor();
		newInstructor.setRating(newRating);
		newInstructor.setRatingCount(instructor.getRatingCount() + 1);
		instructorService.update(instructor.getId(), newInstructor);
		// Update instructor rating
		existingClass.setRatingToInstructor(rating);
		classRepository.save(existingClass);
		// Process if class is DONE
		processClassToDoneAfterRatings(existingClass, instructor, student);
		// Send notification
		notificationService.sendFinishedClassRatingNotification(instructor.getFcmToken(), course, rating);
	}

	@Override
	public void rateStudentClass(Long classId, Long studentId, Float rating) {
		// The class should exist
		Clase existingClass = findById(classId);
		// The course should exist
		final Course course = courseService.findById(existingClass.getCourseId());
		// The student should exist
		final Student student = studentService.findById(studentId);
		// The instructor should exist
		final Instructor instructor = instructorService.findById(existingClass.getInstructorId());
		// Update the student rating and rating count
		final Float newRating = (student.getRatingCount() * student.getRating() + rating)
				/ (student.getRatingCount() + 1);
		Student newStudent = new Student();
		newStudent.setRating(newRating);
		newStudent.setRatingCount(student.getRatingCount() + 1);
		studentService.update(student.getId(), newStudent);
		// Update student rating
		existingClass.setRatingToStudent(rating);
		classRepository.save(existingClass);
		// Process if class is DONE
		processClassToDoneAfterRatings(existingClass, instructor, student);
		// Send notification
		notificationService.sendFinishedClassRatingNotification(student.getFcmToken(), course, rating);
	}
	
	private void processClassToDoneAfterRatings(Clase existingClass, Instructor instructor, Student student) {
		if (existingClass.getRatingToInstructor() != null && existingClass.getRatingToStudent() != null) {
			// Update class to DONE
			Clase newClase = new Clase();
			newClase.setStatus(ClassStatusType.DONE.getDescription());
			this.update(existingClass.getId(), newClase);
			final Integer diffHours = existingClass.getEndTime() - existingClass.getStartTime();
			final Integer totalHoursInstructor = instructor.getTotalHours() + diffHours;
			final Integer totalHoursStudent = student.getTotalHours() + diffHours;
			// Update the instructor total hours
			Instructor newInstructor = new Instructor();
			newInstructor.setTotalHours(totalHoursInstructor);
			newInstructor.setLevel(totalHoursInstructor/bussinessSetting.getLevelBase() + 1);
			instructorService.update(instructor.getId(), newInstructor);
			// Update the student total hours
			Student newStudent = new Student();
			newStudent.setTotalHours(totalHoursStudent);
			newStudent.setLevel(totalHoursStudent/bussinessSetting.getLevelBase() + 1);
			studentService.update(student.getId(), newStudent);
		}
	}

}
