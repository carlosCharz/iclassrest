package com.wedevol.iclass.core.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Faculty;
import com.wedevol.iclass.core.entity.University;
import com.wedevol.iclass.core.service.UniversityService;

/**
 * University Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/universities")
public class UniversityController {

	protected static final Logger logger = LoggerFactory.getLogger(UniversityController.class);

	@Autowired
	private UniversityService universityService;

	/********************* CRUD for university ****************************/

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<University> findAll() {
		return universityService.findAll();
	}

	@ApiIgnore
	@RequestMapping(value = "/{universityId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public University findById(@PathVariable Long universityId) {
		return universityService.findById(universityId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public University create(@Valid @RequestBody University university) {
		return universityService.create(university);
	}

	@ApiIgnore
	@RequestMapping(value = "/{universityId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long universityId, @Valid @RequestBody University university) {
		universityService.update(universityId, university);
	}

	@ApiIgnore
	@RequestMapping(value = "/{universityId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long universityId) {
		universityService.delete(universityId);
	}
	
	@RequestMapping(value = "/{universityId}/faculties", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Faculty> findFacultiesByUniversityId(@PathVariable Long universityId) {
		logger.info("Find faculties of the university " + universityId);
		return universityService.findFacultiesByUniversityId(universityId);
	}
	
	@RequestMapping(value = "/{universityId}/faculties/{facultyId}/courses", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Course> findCoursesByFacultyIdByUniversityId(@PathVariable Long facultyId, @PathVariable Long universityId) {
		logger.info("Find courses of the faculty "+facultyId+" of the university " + universityId);
		return universityService.findCoursesByFacultyIdByUniversityId(facultyId, universityId);
	}
}
