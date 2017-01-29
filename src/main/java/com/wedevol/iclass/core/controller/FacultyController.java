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

import com.wedevol.iclass.core.entity.Faculty;
import com.wedevol.iclass.core.service.FacultyService;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Faculty Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/faculties")
public class FacultyController {

	protected static final Logger logger = LoggerFactory.getLogger(FacultyController.class);

	@Autowired
	private FacultyService facultyService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Faculty> findAll() {
		return facultyService.findAll();
	}

	@ApiIgnore
	@RequestMapping(value = "/{facultyId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Faculty findById(@PathVariable Long facultyId) {
		return facultyService.findById(facultyId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Faculty create(@Valid @RequestBody Faculty faculty) {
		return facultyService.create(faculty);
	}

	@ApiIgnore
	@RequestMapping(value = "/{facultyId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long facultyId, @Valid @RequestBody Faculty faculty) {
		facultyService.update(facultyId, faculty);
	}

	@ApiIgnore
	@RequestMapping(value = "/{facultyId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long facultyId) {
		facultyService.delete(facultyId);
	}
}
