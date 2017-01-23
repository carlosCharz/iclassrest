package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.hashSHA256;
import static com.wedevol.iclass.core.util.CommonUtil.isNullOrEmpty;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.CourseFullInfo;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.StudentEnrollment;
import com.wedevol.iclass.core.entity.StudentEnrollmentId;
import com.wedevol.iclass.core.entity.enums.EnrollmentStatusType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.StudentRepository;
import com.wedevol.iclass.core.service.ClassService;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.StudentEnrollmentService;
import com.wedevol.iclass.core.service.StudentService;
import com.wedevol.iclass.core.view.UserView;

/**
 * Student Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class StudentServiceImpl implements StudentService {

	protected static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseService courseService;

	@Autowired
	private ClassService classService;

	@Autowired
	private StudentEnrollmentService studentEnrollmentService;

	@Override
	public List<Student> findAll() {
		final Iterable<Student> studentsIterator = studentRepository.findAll();
		return Lists.newArrayList(studentsIterator);
	}

	@Override
	public Student findByEmail(String email) {
		return studentRepository.findByEmail(email);
	}

	@Override
	public Student findById(Long userId) {
		final Optional<Student> studentObj = Optional.ofNullable(studentRepository.findOne(userId));
		return studentObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.STUDENT_NOT_FOUND));
	}

	@Override
	public Student create(Student student) {
		// We first search by email, the student should not exist
		final Optional<Student> studentObj = Optional.ofNullable(findByEmail(student.getEmail()));
		if (studentObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.USER_ALREADY_EXISTS);
		}
		return studentRepository.save(student);
	}

	@Override
	public void update(Long userId, Student student) {
		// The student should exist
		Student existingStudent = findById(userId);
		if (!isNullOrEmpty(student.getFirstName())) {
			existingStudent.setFirstName(student.getFirstName());
		}
		if (!isNullOrEmpty(student.getLastName())) {
			existingStudent.setLastName(student.getLastName());
		}
		if (!isNullOrEmpty(student.getPhone())) {
			existingStudent.setPhone(student.getPhone());
		}
		// TODO: analyze if we allow to change the email
		if (student.getBirthday() != null) {
			existingStudent.setBirthday(student.getBirthday());
		}
		if (!isNullOrEmpty(student.getGender())) {
			existingStudent.setGender(student.getGender());
		}
		if (!isNullOrEmpty(student.getProfilePictureUrl())) {
			existingStudent.setProfilePictureUrl(student.getProfilePictureUrl());
		}
		if (!student.getPlaceOptions().isEmpty()){
			existingStudent.setPlaceOptions(student.getPlaceOptions());
		}		if (!isNullOrEmpty(student.getUniversity())) {
			existingStudent.setUniversity(student.getUniversity());
		}
		if (!isNullOrEmpty(student.getFcmToken())) {
			existingStudent.setFcmToken(student.getFcmToken());
		}
		// Save
		studentRepository.save(existingStudent);
	}

	@Override
	public void delete(Long userId) {
		// The student should exist
		findById(userId);
		studentRepository.delete(userId);
	}

	@Override
	public List<Student> findStudentsByCourseId(Long courseId) {
		// The course should exist
		courseService.findById(courseId);
		return studentRepository.findStudentsWithCourseId(courseId);
	}

	@Override
	public Student createStudentWithCourse(UserView studentView) {
		// Create the user
		Student studentNew = new Student.StudentBuilder(studentView.getFirstName(), studentView.getLastName(),
				studentView.getPhone(), studentView.getEmail(), hashSHA256(studentView.getPassword()))
																										.build();
		if (studentView.getBirthday() != null) {
			studentNew.setBirthday(studentView.getBirthday());
		}
		if (studentView.getGender() != null) {
			studentNew.setGender(studentView.getGender());
		}
		if (studentView.getProfilePictureUrl() != null) {
			studentNew.setProfilePictureUrl(studentView.getProfilePictureUrl());
		}
		if (!studentView.getPlaceOptions().isEmpty()) {
			studentNew.setPlaceOptions(studentView.getPlaceOptions());
		}
		if (studentView.getUniversity() != null) {
			studentNew.setUniversity(studentView.getUniversity());
		}
		if (studentView.getFcmToken() != null) {
			studentNew.setFcmToken(studentView.getFcmToken());
		}
		studentNew.setActive(true);
		final Student studentSaved = this.create(studentNew);

		// Create the enrollment if there is courseId
		final Long courseId = studentView.getCourseId();
		if (courseId != null) {
			/// Build the enrollment object
			final StudentEnrollmentId enrId = new StudentEnrollmentId(studentSaved.getId(), courseId);
			final StudentEnrollment enr = new StudentEnrollment(enrId);
			studentEnrollmentService.create(enr);
		}
		return studentSaved;
	}

	@Override
	public List<CourseFullInfo> findCoursesByStudentIdWithCourseStatusFilter(Long studentId,
			String courseStatusFilter) {
		return courseService.findCoursesByStudentIdWithCourseStatusFilter(studentId, courseStatusFilter);
	}

	@Override
	public List<ClassFullInfo> findClassesByStudentIdByDateTimeWithClassStatusFilter(Long studentId, Date actualDate,
			Integer actualTime, String statusFilter) {
		return classService.findClassesByStudentIdByDateTimeWithClassStatusFilter(studentId, actualDate, actualTime,
				statusFilter);
	}

}
