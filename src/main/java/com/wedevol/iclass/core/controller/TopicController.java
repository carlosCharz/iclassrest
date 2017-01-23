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

import com.wedevol.iclass.core.entity.Topic;
import com.wedevol.iclass.core.service.TopicService;

/**
 * Topic Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/topics")
public class TopicController {

	protected static final Logger logger = LoggerFactory.getLogger(TopicController.class);

	@Autowired
	private TopicService topicService;

	/********************* CRUD for topic ****************************/

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Topic> findAll() {
		return topicService.findAll();
	}

	@ApiIgnore
	@RequestMapping(value = "/{topicId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Topic findById(@PathVariable Long topicId) {
		return topicService.findById(topicId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Topic create(@Valid @RequestBody Topic topic) {
		return topicService.create(topic);
	}

	@ApiIgnore
	@RequestMapping(value = "/{topicId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long topicId, @Valid @RequestBody Topic topic) {
		topicService.update(topicId, topic);
	}

	@ApiIgnore
	@RequestMapping(value = "/{topicId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long topicId) {
		topicService.delete(topicId);
	}
}
