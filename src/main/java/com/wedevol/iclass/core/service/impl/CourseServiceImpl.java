package com.wedevol.iclass.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.CourseRepository;
import com.wedevol.iclass.core.service.CourseService;

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
		courseObj.ifPresent(s -> new BadRequestException(BadRequestErrorType.BAD_REQUEST_EXCEPTION));
		courseRepository.save(course);
	}

	@Override
	public void update(Long courseId, Course course) {
		// The course should exist
		Course existingCourse = findById(courseId);
		existingCourse.setName(course.getName());
		existingCourse.setDescription(course.getDescription());
		existingCourse.setUniversity(course.getUniversity());
		courseRepository.save(existingCourse);
	}

	@Override
	public void delete(Long courseId) {
		// The course should exist
		findById(courseId);
		courseRepository.delete(courseId);
	}

}
