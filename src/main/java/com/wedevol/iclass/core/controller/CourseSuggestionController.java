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

import com.wedevol.iclass.core.entity.CourseSuggestion;
import com.wedevol.iclass.core.service.CourseSuggestionService;
import com.wedevol.iclass.core.view.response.CourseSuggFull;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Course Suggestion Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/coursesuggestions")
public class CourseSuggestionController {

	@Autowired
	private CourseSuggestionService courseSuggestionService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CourseSuggFull> findAllFullInfo() {
		return courseSuggestionService.findAllFullInfo();
	}

	@ApiIgnore
	@RequestMapping(value = "/{courseSuggestionId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CourseSuggestion findById(@PathVariable Long courseSuggestionId) {
		return courseSuggestionService.findById(courseSuggestionId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public CourseSuggestion create(@Valid @RequestBody CourseSuggestion courseSuggestion) {
		return courseSuggestionService.create(courseSuggestion);
	}

	@RequestMapping(value = "/{courseSuggestionId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long courseSuggestionId, @Valid @RequestBody CourseSuggestion courseSuggestion) {
		courseSuggestionService.update(courseSuggestionId, courseSuggestion);
	}

	@ApiIgnore
	@RequestMapping(value = "/{courseSuggestionId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long courseSuggestionId) {
		courseSuggestionService.delete(courseSuggestionId);
	}
}
