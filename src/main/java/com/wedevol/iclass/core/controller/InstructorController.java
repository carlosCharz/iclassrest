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

import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.service.InstructorService;

/**
 * Instructor Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/instructors")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;

	/********************* CRUD for instructor ****************************/
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Instructor> findAll() {
		return instructorService.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Instructor findById(@PathVariable Long userId) {
		return instructorService.findById(userId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@Valid @RequestBody Instructor instructor) {
		instructorService.create(instructor);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long userId, @Valid @RequestBody Instructor instructor) {
		instructorService.update(userId, instructor);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long userId) {
		instructorService.delete(userId);
	}
}
