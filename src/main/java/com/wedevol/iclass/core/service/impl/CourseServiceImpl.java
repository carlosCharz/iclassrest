package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CoreUtil.areValidEnrollmentStatusFilters;
import static com.wedevol.iclass.core.util.CommonUtil.isNullOrEmpty;

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
import com.wedevol.iclass.core.entity.CourseFullInfo;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.CourseRepository;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.StudentService;

/**
 * Course Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class CourseServiceImpl implements CourseService {

	protected static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentService studentService;

	@Autowired
	private InstructorService instructorService;

	@Override
	public List<Course> findAll() {
		final Iterable<Course> coursesIterator = courseRepository.findAll();
		return Lists.newArrayList(coursesIterator);
	}

	@Override
	public Course findByName(String name) {
		return courseRepository.findByName(name);
	}

	@Override
	public Course findById(Long courseId) {
		final Optional<Course> courseObj = Optional.ofNullable(courseRepository.findOne(courseId));
		return courseObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.COURSE_NOT_FOUND));
	}

	@Override
	public Course create(Course course) {
		// Fields missing validation
		if (course.getName() == null) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// The course should not exist by name
		final Optional<Course> courseObj = Optional.ofNullable(findByName(course.getName()));
		if (courseObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.COURSE_ALREADY_EXISTS);
		}
		return courseRepository.save(course);
	}

	@Override
	public void update(Long courseId, Course course) {
		// The course should exist
		Course existingCourse = findById(courseId);
		if (!isNullOrEmpty(course.getName())) {
			existingCourse.setName(course.getName());
		}
		if (!isNullOrEmpty(course.getDescription())) {
			existingCourse.setDescription(course.getDescription());
		}
		if (!isNullOrEmpty(course.getFaculty())) {
			existingCourse.setFaculty(course.getFaculty());
		}
		if (!isNullOrEmpty(course.getUniversity())) {
			existingCourse.setUniversity(course.getUniversity());
		}
		courseRepository.save(existingCourse);
	}

	@Override
	public void delete(Long courseId) {
		// The course should exist
		findById(courseId);
		courseRepository.delete(courseId);
	}

	@Override
	public List<CourseFullInfo> findCoursesByStudentIdWithCourseStatusFilter(Long studentId,
			String courseStatusFilter) {
		// The student should exist
		studentService.findById(studentId);
		// The course status should be valid
		if (!areValidEnrollmentStatusFilters(courseStatusFilter)) {
			throw new BadRequestException(BadRequestErrorType.COURSE_STATUS_NOT_VALID);
		}
		final List<String> courseStatusList = Arrays.asList(courseStatusFilter.split(","));
		return courseRepository.findCoursesWithStudentIdWithCourseStatusFilter(studentId, courseStatusList);
	}

	@Override
	public List<Student> findStudentsByCourseId(Long courseId) {
		return studentService.findStudentsByCourseId(courseId);
	}

	@Override
	public List<Instructor> findInstructorsByCourseId(Long courseId) {
		return instructorService.findInstructorsByCourseId(courseId);
	}

	@Override
	public List<CourseFullInfo> findCoursesByInstructorIdWithCourseStatusFilter(Long instructorId,
			String courseStatusFilter) {
		if (!areValidEnrollmentStatusFilters(courseStatusFilter)) {
			throw new BadRequestException(BadRequestErrorType.COURSE_STATUS_NOT_VALID);
		}
		final List<String> courseStatusList = Arrays.asList(courseStatusFilter.split(","));
		return courseRepository.findCoursesWithInstructorIdWithCourseStatusFilter(instructorId, courseStatusList);
	}

}
