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

import com.wedevol.iclass.core.entity.Department;
import com.wedevol.iclass.core.entity.DepartmentId;
import com.wedevol.iclass.core.service.DepartmentService;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Department Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("")
public class DepartmentController {

	protected static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService depaService;

	@ApiIgnore
	@RequestMapping(value = "/departments", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Department> findAll() {
		return depaService.findAll();
	}

	@ApiIgnore
	@RequestMapping(value = "/universities/{universityId}/faculties/{facultyId}/department", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Department findById(@PathVariable Long universityId, @PathVariable Long facultyId) {
		final DepartmentId id = new DepartmentId(universityId, facultyId);
		return depaService.findById(id);
	}

	@RequestMapping(value = "/departments", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Department create(@Valid @RequestBody Department department) {
		return depaService.create(department);
	}

	@ApiIgnore
	@RequestMapping(value = "/universities/{universityId}/faculties/{facultyId}/department", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long universityId, @PathVariable Long facultyId,
			@Valid @RequestBody Department department) {
		final DepartmentId id = new DepartmentId(universityId, facultyId);
		depaService.update(id, department);
	}

	@ApiIgnore
	@RequestMapping(value = "/universities/{universityId}/faculties/{facultyId}/department", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long universityId, @PathVariable Long facultyId) {
		final DepartmentId id = new DepartmentId(universityId, facultyId);
		depaService.delete(id);
	}
}
