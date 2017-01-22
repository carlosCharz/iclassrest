package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CoreUtil.areValidCourseStatusFilters;

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
	public void create(Course course) {
		// We first search by name, the course should not exist
		final Optional<Course> courseObj = Optional.ofNullable(findByName(course.getName()));
		if (courseObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.COURSE_ALREADY_EXISTS);
		}
		courseRepository.save(course);
	}

	@Override
	public void update(Long courseId, Course course) {
		// The course should exist
		Course existingCourse = findById(courseId);
		existingCourse.setName(course.getName());
		existingCourse.setDescription(course.getDescription());
		existingCourse.setFaculty(course.getFaculty());
		existingCourse.setUniversity(course.getUniversity());
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
		if (!areValidCourseStatusFilters(courseStatusFilter)) {
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
		if (!areValidCourseStatusFilters(courseStatusFilter)) {
			throw new BadRequestException(BadRequestErrorType.COURSE_STATUS_NOT_VALID);
		}
		final List<String> courseStatusList = Arrays.asList(courseStatusFilter.split(","));
		return courseRepository.findCoursesWithInstructorIdWithCourseStatusFilter(instructorId, courseStatusList);
	}

}
