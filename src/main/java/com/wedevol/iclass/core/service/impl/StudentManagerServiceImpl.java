package com.wedevol.iclass.core.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.repository.StudentManagerRepository;
import com.wedevol.iclass.core.service.StudentManagerService;
import static com.wedevol.iclass.core.util.CoreUtil.*;

/**
 * Student Manager Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class StudentManagerServiceImpl implements StudentManagerService {

	protected static final Logger logger = LoggerFactory.getLogger(StudentManagerServiceImpl.class);

	@Autowired
	private StudentManagerRepository stuMgrRepository;

	@Override
	public List<Course> findCoursesByStudentId(Long studentId, String statusFilter) {
		if (!areValidCourseStatusFilters(statusFilter)) {
			throw new BadRequestException(BadRequestErrorType.COURSE_STATUS_NOT_VALID);
		}
		final List<String> status = Arrays.asList(statusFilter.split(","));
		return stuMgrRepository.findCoursesWithStudentId(studentId, status);
	}

	@Override
	public List<Student> findStudentsByCourseId(Long courseId) {
		return stuMgrRepository.findStudentsWithCourseId(courseId);
	}

}
