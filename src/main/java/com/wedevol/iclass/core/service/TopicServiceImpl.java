package com.wedevol.iclass.core.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Course;
import com.wedevol.iclass.core.entity.Topic;
import com.wedevol.iclass.core.enums.BadRequestErrorType;
import com.wedevol.iclass.core.enums.NotFoundErrorType;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.repository.CourseRepository;
import com.wedevol.iclass.core.repository.TopicRepository;

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
	private CourseRepository courseRepository;

	/********************* CRUD for student ****************************/
	@Override
	public List<Topic> findAll() {
		logger.info("Topic service -> find all");
		final Iterable<Topic> topicsIterator = topicRepository.findAll();
		return Lists.newArrayList(topicsIterator);
	}

	@Override
	public Topic findByName(String name) {
		logger.info("Topic service -> find by name");
		return topicRepository.findByName(name);
	}

	@Override
	public Topic findById(Long topicId) {
		logger.info("Topic service -> find by id");
		Optional<Topic> topicObj = Optional.ofNullable(topicRepository.findOne(topicId));
		return topicObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.TOPIC_NOT_FOUND));
	}

	@Override
	public void create(Topic topic) {
		logger.info("Topic service -> create");
		// We first search by name, the topic should not exist
		Optional<Topic> topicObj = Optional.ofNullable(findByName(topic.getName()));
		topicObj.ifPresent(s -> new BadRequestException(BadRequestErrorType.BAD_REQUEST_EXCEPTION));
		// Then, the course should exist
		final Course course = topic.getCourse();
		Optional<Course> courseObj = Optional.ofNullable(courseRepository.findOne(course.getId()));
		courseObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.COURSE_NOT_FOUND));
		// Create
		topicRepository.save(topic);
	}

	@Override
	public void update(Long topicId, Topic topic) {
		logger.info("Topic service -> update");
		// The topic should exist
		Optional<Topic> topicObj = Optional.ofNullable(topicRepository.findOne(topicId));
		Topic existingTopic = topicObj.orElseThrow(
				() -> new ResourceNotFoundException(NotFoundErrorType.TOPIC_NOT_FOUND));
		// Then, the course should exist
		final Course course = topic.getCourse();
		Optional<Course> courseObj = Optional.ofNullable(courseRepository.findOne(course.getId()));
		courseObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.COURSE_NOT_FOUND));
		// Update
		existingTopic.setCourse(topic.getCourse());
		existingTopic.setName(topic.getName());
		topicRepository.save(existingTopic);
	}

	@Override
	public void delete(Long topicId) {
		logger.info("Topic service -> delete");
		// The topic should exist
		Optional<Topic> topicObj = Optional.ofNullable(topicRepository.findOne(topicId));
		topicObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.TOPIC_NOT_FOUND));
		topicRepository.delete(topicId);
	}

}
