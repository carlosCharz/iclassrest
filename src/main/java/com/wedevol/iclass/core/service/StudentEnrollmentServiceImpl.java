package com.wedevol.iclass.core.service;

import java.util.ArrayList;
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
import com.wedevol.iclass.core.enums.NotFoundErrorType;
import com.wedevol.iclass.core.enums.ServerErrorType;
import com.wedevol.iclass.core.exception.NotImplementedException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.repository.CourseRepository;
import com.wedevol.iclass.core.repository.StudentEnrollmentRepository;
import com.wedevol.iclass.core.repository.StudentRepository;

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
	public List<Course> findCourses(Long studentId) {
		logger.info("StudentEnrollment service -> find student courses");
		List<Object[]> coursesObjList = enrRepository.findCourses(studentId);
		List<Course> courses = new ArrayList<Course>();
		coursesObjList	.stream()
						.forEach((row) -> {
							final Long id = new Long((Integer) row[0]);
							final String name = (String) row[1];
							final String description = (String) row[2];
							final String university = (String) row[3];
							Course course = new Course.CourseBuilder(name)	.description(description)
																			.university(university)
																			.build();
							course.setId(id);
							courses.add(course);
						});
		return courses;
	}

	@Override
	public List<Course> findCoursesComplete(Long studentId) {
		logger.info("StudentEnrollment service -> find student courses with topic information");
		List<Course> courses = enrRepository.findCoursesComplete(studentId);
		return courses;
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
