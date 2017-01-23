package com.wedevol.iclass.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Topic;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.TopicRepository;
import com.wedevol.iclass.core.service.CourseService;
import com.wedevol.iclass.core.service.TopicService;

/**
 * Topic Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class TopicServiceImpl implements TopicService {

	protected static final Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private CourseService courseService;

	/********************* CRUD for student ****************************/
	@Override
	public List<Topic> findAll() {
		final Iterable<Topic> topicsIterator = topicRepository.findAll();
		return Lists.newArrayList(topicsIterator);
	}

	@Override
	public Topic findByName(String name) {
		return topicRepository.findByName(name);
	}

	@Override
	public Topic findById(Long topicId) {
		final Optional<Topic> topicObj = Optional.ofNullable(topicRepository.findOne(topicId));
		return topicObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.TOPIC_NOT_FOUND));
	}

	@Override
	public Topic create(Topic topic) {
		// We first search by name, the topic should not exist
		final Optional<Topic> topicObj = Optional.ofNullable(findByName(topic.getName()));
		if (topicObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.TOPIC_ALREADY_EXISTS);
		}
		// Then, the course should exist
		courseService.findById(topic.getCourseId());
		// Save
		return topicRepository.save(topic);
	}

	@Override
	public void update(Long topicId, Topic topic) {
		// The topic should exist
		Topic existingTopic = findById(topicId);
		// Then, the course should exist
		courseService.findById(topic.getCourseId());
		// Update
		existingTopic.setCourseId(topic.getCourseId());
		existingTopic.setName(topic.getName());
		topicRepository.save(existingTopic);
	}

	@Override
	public void delete(Long topicId) {
		// The topic should exist
		findById(topicId);
		topicRepository.delete(topicId);
	}

}
