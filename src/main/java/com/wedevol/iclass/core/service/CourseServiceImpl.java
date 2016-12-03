package com.wedevol.iclass.core.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.enums.BadRequestErrorType;
import com.wedevol.iclass.core.enums.NotFoundErrorType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.repository.CourseRepository;

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

	/********************* CRUD for student ****************************/
	@Override
	public List<Course> findAll() {
		logger.info("Course service -> find all");
		final Iterable<Course> coursesIterator = courseRepository.findAll();
		return Lists.newArrayList(coursesIterator);
	}

	@Override
	public Course findByName(String name) {
		logger.info("Course service -> find by name");
		return courseRepository.findByName(name);
	}

	@Override
	public Course findById(Long courseId) {
		logger.info("Course service -> find by id");
		Optional<Course> courseObj = Optional.ofNullable(courseRepository.findOne(courseId));
		return courseObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.COURSE_NOT_FOUND));
	}

	@Override
	public void create(Course course) {
		logger.info("Course service -> create");
		// We first search by name, the course should not exist
		Optional<Course> courseObj = Optional.ofNullable(findByName(course.getName()));
		courseObj.ifPresent(s -> new BadRequestException(BadRequestErrorType.BAD_REQUEST_EXCEPTION));
		courseRepository.save(course);
	}

	@Override
	public void update(Long courseId, Course course) {
		logger.info("Course service -> update");
		// The course should exist
		Optional<Course> courseObj = Optional.ofNullable(courseRepository.findOne(courseId));
		Course existingCourse = courseObj.orElseThrow(
				() -> new ResourceNotFoundException(NotFoundErrorType.COURSE_NOT_FOUND));
		existingCourse.setName(course.getName());
		existingCourse.setDescription(course.getDescription());
		existingCourse.setUniversity(course.getUniversity());
		courseRepository.save(existingCourse);
	}

	@Override
	public void delete(Long courseId) {
		logger.info("Course service -> delete");
		// The course should exist
		Optional<Course> courseObj = Optional.ofNullable(courseRepository.findOne(courseId));
		courseObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.COURSE_NOT_FOUND));
		courseRepository.delete(courseId);
	}

}
