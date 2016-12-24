package com.wedevol.iclass.core.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.StudentEnrollment;
import com.wedevol.iclass.core.entity.StudentEnrollmentId;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.CourseRepository;
import com.wedevol.iclass.core.repository.StudentEnrollmentRepository;
import com.wedevol.iclass.core.repository.StudentRepository;
import com.wedevol.iclass.core.service.StudentEnrollmentService;
import com.wedevol.iclass.core.util.CoreUtil;

/**
 * Student Enrollment Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {

	protected static final Logger logger = LoggerFactory.getLogger(StudentEnrollmentServiceImpl.class);

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentEnrollmentRepository enrRepository;

	/************** Courses & Students **********************/
	@Override
	public List<Course> findCourses(Long studentId, String statusFilter) {
		logger.info("StudentEnrollment service -> find student courses filtered by the supplied course status");
		if (!CoreUtil.areValidCourseStatusFilters(statusFilter)) {
			throw new BadRequestException(BadRequestErrorType.COURSE_STATUS_NOT_VALID);
		}
		final List<String> status = Arrays.asList(statusFilter.split(","));
		return enrRepository.findCourses(studentId, status);
	}

	@Override
	public List<Student> findStudents(Long courseId) {
		logger.info("StudentEnrollment service -> find students of a course");
		return enrRepository.findStudents(courseId);
	}

	/***************** CRUD for Student Enrollment ***********************/

	@Override
	public List<StudentEnrollment> findAll() {
		logger.info("StudentEnrollment service -> find all");
		final Iterable<StudentEnrollment> scIterator = enrRepository.findAll();
		return Lists.newArrayList(scIterator);
	}

	@Override
	public StudentEnrollment findById(StudentEnrollmentId id) {
		logger.info("StudentEnrollment service -> find by id");
		Optional<StudentEnrollment> scObj = Optional.ofNullable(enrRepository.findOne(id));
		return scObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.STUDENT_COURSE_NOT_FOUND));
	}

	@Override
	public void create(StudentEnrollment studentEnrollment) {
		logger.info("StudentEnrollment service -> create");
		// We first search by id, the studentEnrollment should not exist
		Optional<StudentEnrollment> enrObj = Optional.ofNullable(
				enrRepository.findOne(studentEnrollment.getStudentEnrollmentId()));
		enrObj.ifPresent(s -> new BadRequestException(BadRequestErrorType.BAD_REQUEST_EXCEPTION));
		enrRepository.save(studentEnrollment);
	}

	@Override
	public void update(StudentEnrollmentId id, StudentEnrollment studentEnrollment) {
		logger.info("StudentEnrollment service -> update");
		// The studentEnrollment should exist
		final StudentEnrollment existingEnr = findById(id);
		// TODO: analyze the full changed fields
		existingEnr.setStatus(studentEnrollment.getStatus());
		enrRepository.save(existingEnr);
	}

	@Override
	public void delete(StudentEnrollmentId id) {
		logger.info("StudentEnrollment service -> delete");
		// The studentEnrollment should exist
		findById(id);
		enrRepository.delete(id);
	}

}
