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

import com.wedevol.iclass.core.annotation.Authorize;
import com.wedevol.iclass.core.entity.Clase;
import com.wedevol.iclass.core.service.ClassService;

import springfox.documentation.annotations.ApiIgnore;

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

	@Authorize(basic = true)
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Clase> findAll() {
		return classService.findAll();
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/{classId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Clase findById(@PathVariable Long classId) {
		return classService.findById(classId);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Clase create(@Valid @RequestBody Clase c) {
		return classService.create(c);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/{classId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long classId, @Valid @RequestBody Clase c) {
		classService.update(classId, c);
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/{classId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long classId) {
		classService.delete(classId);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/{classId}/instructors/{userId}/confirm", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void instructorConfirmClass(@PathVariable Long classId, @PathVariable Long userId) {
		classService.instructorConfirmClass(classId, userId);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/{classId}/instructors/{userId}/reject", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void instructorRejectClass(@PathVariable Long classId, @PathVariable Long userId) {
		classService.instructorRejectClass(classId, userId);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/{classId}/students/{userId}/cancel", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void studentCancelClass(@PathVariable Long classId, @PathVariable Long userId) {
		classService.studentCancelClass(classId, userId);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/{classId}/instructors/{instructorId}/rating/{rating:.+}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void rateInstructorClass(@PathVariable Long classId, @PathVariable Long instructorId,
			@PathVariable Float rating) {
		classService.rateInstructorClass(classId, instructorId, rating);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/{classId}/students/{studentId}/rating/{rating:.+}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void rateStudentClass(@PathVariable Long classId, @PathVariable Long studentId, @PathVariable Float rating) {
		classService.rateStudentClass(classId, studentId, rating);
	}

}
