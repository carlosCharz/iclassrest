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

import com.wedevol.iclass.core.entity.Clase;
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

	@ApiIgnore
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Clase> findAll() {
		return classService.findAll();
	}

	@ApiIgnore
	@RequestMapping(value = "/{classId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Clase findById(@PathVariable Long classId) {
		return classService.findById(classId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Clase create(@Valid @RequestBody Clase c) {
		return classService.create(c);
	}

	@RequestMapping(value = "/{classId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long classId, @Valid @RequestBody Clase c) {
		classService.update(classId, c);
	}

	@ApiIgnore
	@RequestMapping(value = "/{classId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long classId) {
		classService.delete(classId);
	}

	@RequestMapping(value = "/{classId}/instructors/{instructorId}/confirm", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void instructorConfirmClass(@PathVariable Long classId, @PathVariable Long instructorId) {
		classService.instructorConfirmClass(classId, instructorId);
	}

	@RequestMapping(value = "/{classId}/instructors/{instructorId}/reject", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void instructorRejectClass(@PathVariable Long classId, @PathVariable Long instructorId) {
		classService.instructorRejectClass(classId, instructorId);
	}
	
	@RequestMapping(value = "/{classId}/rate/{rating}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void rateInstructorClass(@PathVariable Long classId, @PathVariable Float rating) {
		classService.rateInstructorClass(classId, rating);
	}
	
	@ApiIgnore
	@RequestMapping(value = "/getConfirmedFinishedClasses", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Clase> getConfirmedFinishedClasses() {
		return classService.getConfirmedFinishedClasses();
	}

}
