package com.wedevol.iclass.core.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.wedevol.iclass.core.entity.ClassRoom;
import com.wedevol.iclass.core.service.ClassService;

/**
 * Class Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/classes")
public class ClassController {

	@Autowired
	private ClassService classService;

	/************* CRUD for class ****************/

	@ApiIgnore
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassRoom> findAll() {
		return classService.findAll();
	}

	@ApiIgnore
	@RequestMapping(value = "/{classId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassRoom findById(@PathVariable Long classId) {
		return classService.findById(classId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@Valid @RequestBody ClassRoom c) {
		classService.create(c);
	}

	@RequestMapping(value = "/{classId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long classId, @Valid @RequestBody ClassRoom c) {
		classService.update(classId, c);
	}

	@ApiIgnore
	@RequestMapping(value = "/{classId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long classId) {
		classService.delete(classId);
	}
}
