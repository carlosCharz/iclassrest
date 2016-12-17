package com.wedevol.iclass.core.service;

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
import com.wedevol.iclass.core.enums.BadRequestErrorType;
import com.wedevol.iclass.core.enums.NotFoundErrorType;
import com.wedevol.iclass.core.enums.ServerErrorType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.NotImplementedException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.repository.CourseRepository;
import com.wedevol.iclass.core.repository.StudentEnrollmentRepository;
import com.wedevol.iclass.core.repository.StudentRepository;
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
		logger.info("StudentEnrollment service -> find student courses");
		if (!CoreUtil.areValidCourseStatusFilters(statusFilter)) {
			throw new BadRequestException(BadRequestErrorType.COURSE_STATUS_NOT_VALID);
		}
		final List<String> status = Arrays.asList(statusFilter.split(","));
		return enrRepository.findCourses(studentId, status);
	}

	@Override
	public List<Student> findStudents(Long courseId) {
		// TODO Auto-generated method stub
		throw new NotImplementedException(ServerErrorType.NOT_IMPLEMENTED);
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
		logger.info("Student_Course service -> find by id");
		Optional<StudentEnrollment> scObj = Optional.ofNullable(enrRepository.findOne(id));
		return scObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.STUDENT_COURSE_NOT_FOUND));
	}

	@Override
	public void create(StudentEnrollment studentEnrollment) {
		// TODO Auto-generated method stub
		throw new NotImplementedException(ServerErrorType.NOT_IMPLEMENTED);
	}

	@Override
	public void update(StudentEnrollmentId id, StudentEnrollment studentEnrollment) {
		// TODO Auto-generated method stub
		throw new NotImplementedException(ServerErrorType.NOT_IMPLEMENTED);
	}

	@Override
	public void delete(StudentEnrollmentId id) {
		// TODO Auto-generated method stub
		throw new NotImplementedException(ServerErrorType.NOT_IMPLEMENTED);
	}

}
