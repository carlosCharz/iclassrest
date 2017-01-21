package com.wedevol.iclass.core.controller;

import static com.wedevol.iclass.core.util.CommonUtil.dateToString;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wedevol.iclass.core.entity.ClassFullInfo;
import com.wedevol.iclass.core.entity.CourseFullInfo;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.service.StudentService;
import com.wedevol.iclass.core.view.UserView;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Student Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/students")
public class StudentController {

	protected static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@ApiIgnore
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Student> findAll() {
		return studentService.findAll();
	}

	@ApiIgnore
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Student findById(@PathVariable Long userId) {
		return studentService.findById(userId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Student create(@Valid @RequestBody UserView studentView) {
		return studentService.createStudentWithCourse(studentView);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long userId, @Valid @RequestBody Student student) {
		studentService.update(userId, student);
	}

	@ApiIgnore
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long userId) {
		studentService.delete(userId);
	}

	@RequestMapping(value = "/{userId}/courses", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CourseFullInfo> findCoursesByStudentIdWithCourseStatusFilter(@PathVariable Long userId,
			@RequestParam(value = "status", defaultValue = "free,payed") String courseStatusFilter) {
		logger.info("Find courses of a student filtered by the supplied course status: " + courseStatusFilter);
		return studentService.findCoursesByStudentIdWithCourseStatusFilter(userId, courseStatusFilter);
	}

	@RequestMapping(value = "/{userId}/classes", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassFullInfo> findClassesByStudentIdByDateTimeWithClassStatusFilter(@PathVariable Long userId,
			@RequestParam(value = "actualDate", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date actualDate,
			@RequestParam(value = "actualTime", required = true) Integer actualTime,
			@RequestParam(value = "status", defaultValue = "confirmed") String statusFilter) {
		logger.info("Find classes of a student since " + actualTime + " hours " + dateToString(actualDate)
				+ " filtered by the supplied class status: " + statusFilter);
		return studentService.findClassesByStudentIdByDateTimeWithClassStatusFilter(userId, actualDate, actualTime,
				statusFilter);
	}

}
