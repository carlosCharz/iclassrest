package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CoreUtil.areValidEnrollmentStatusFilters;

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
import com.wedevol.iclass.core.entity.DepartmentId;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.CourseRepository;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.DepartmentService;
import com.wedevol.iclass.core.service.FacultyService;
import com.wedevol.iclass.core.service.InstructorService;
import com.wedevol.iclass.core.service.StudentService;
import com.wedevol.iclass.core.service.UniversityService;
import com.wedevol.iclass.core.view.response.CourseResponse;

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

	@Autowired
	private UniversityService universityService;

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private DepartmentService departmentService;

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
		if (course.getName() == null || course.getUniversityId() == null || course.getFacultyId() == null) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// The department should exist
		final DepartmentId id = new DepartmentId(course.getUniversityId(), course.getFacultyId());
		departmentService.findById(id);
		// TODO: analyze this restriction
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
		if (course.getFacultyId() != null) {
			// The faculty should exist
			facultyService.findById(course.getFacultyId());
			existingCourse.setFacultyId(course.getFacultyId());
		}
		if (course.getUniversityId() != null) {
			// The university should exist
			universityService.findById(course.getUniversityId());
			existingCourse.setUniversityId(course.getUniversityId());
		}
		// The department should exist
		final DepartmentId id = new DepartmentId(course.getUniversityId(), course.getFacultyId());
		departmentService.findById(id);
		// Save
		courseRepository.save(existingCourse);
	}

	@Override
	public void delete(Long courseId) {
		// The course should exist
		findById(courseId);
		courseRepository.delete(courseId);
	}

	@Override
	public List<CourseResponse> findCoursesByStudentIdWithCourseStatusFilter(Long studentId,
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
	public List<CourseResponse> findCoursesByInstructorIdWithCourseStatusFilter(Long instructorId,
			String courseStatusFilter) {
		if (!areValidEnrollmentStatusFilters(courseStatusFilter)) {
			throw new BadRequestException(BadRequestErrorType.COURSE_STATUS_NOT_VALID);
		}
		final List<String> courseStatusList = Arrays.asList(courseStatusFilter.split(","));
		return courseRepository.findCoursesWithInstructorIdWithCourseStatusFilter(instructorId, courseStatusList);
	}

	@Override
	public List<Course> findCoursesByFacultyIdByUniversityId(Long facultyId, Long universityId) {
		// The faculty should exist
		facultyService.findById(facultyId);				
		// The university should exist
		universityService.findById(universityId);
		// The department should exist
		final DepartmentId id = new DepartmentId(universityId, facultyId);
		departmentService.findById(id);
		return courseRepository.findCoursesWithFacultyIdWithUniversityId(facultyId, universityId);
	}

}
