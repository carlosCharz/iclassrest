package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.dateToString;
import static com.wedevol.iclass.core.util.CommonUtil.isNullOrEmpty;
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
import com.wedevol.iclass.core.entity.Clase;
import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.InstructorEnrollmentId;
import com.wedevol.iclass.core.entity.StudentEnrollmentId;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.ClassRepository;
import com.wedevol.iclass.core.service.ClassService;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.InstructorEnrollmentService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.StudentEnrollmentService;
import com.wedevol.iclass.core.service.StudentService;
import com.wedevol.iclass.core.util.CommonUtil;

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

	@Override
	public List<Clase> findAll() {
		final Iterable<Clase> classIterator = classRepository.findAll();
		return Lists.newArrayList(classIterator);
	}

	@Override
	public Clase findById(Long classId) {
		Optional<Clase> classObj = Optional.ofNullable(classRepository.findOne(classId));
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
		studentService.findById(c.getStudentId());
		// The instructor should exist
		instructorService.findById(c.getInstructorId());
		// The course should exist
		courseService.findById(c.getCourseId());
		// The instructor enrollment should exist
		final InstructorEnrollmentId insEnrId = new InstructorEnrollmentId(c.getInstructorId(), c.getCourseId());
		instructorEnrollmentService.findById(insEnrId);
		// The student enrollment should exist
		final StudentEnrollmentId stuEnrId = new StudentEnrollmentId(c.getStudentId(), c.getCourseId());
		studentEnrollmentService.findById(stuEnrId);
		// Save the class
		return classRepository.save(c);
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
		// Update
		classRepository.save(existingClass);
	}

	@Override
	public void delete(Long classId) {
		// The class should exist
		findById(classId);
		classRepository.delete(classId);
	}

	@Override
	public List<ClassFullInfo> findClassesByStudentIdByDateTimeWithClassStatusFilter(Long studentId, Date actualDate,
			Integer actualTime, String statusFilter) {
		// The class status should be valid
				if (!areValidClassStatusFilters(statusFilter)) {
					throw new BadRequestException(BadRequestErrorType.CLASS_STATUS_NOT_VALID);
				}
		// The student should exist
		studentService.findById(studentId);
		final List<String> classStatusList = Arrays.asList(statusFilter.split(","));
		final String actualDateStr = dateToString(actualDate, CommonUtil.DATE_FORMAT_QUERY_DB);
		return classRepository.findClassesWithStudentIdWithDateTimeWithClassStatusFilter(studentId, actualDateStr,
				actualTime, classStatusList);
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
		final String actualDateStr = dateToString(actualDate, CommonUtil.DATE_FORMAT_QUERY_DB);
		return classRepository.findClassesWithInstructorIdWithDateTimeWithClassStatusFilter(instructorId, actualDateStr,
				actualTime, classStatusList);
	}

}
