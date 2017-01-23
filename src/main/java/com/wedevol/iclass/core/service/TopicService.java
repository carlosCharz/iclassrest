package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Topic;

/**
 * Topic Service Interface
 * 
 * @author charz
 *
 */
public interface TopicService {

	List<Topic> findAll();

	Topic findByName(String name);

	Topic findById(Long topicId);

	Topic create(Topic topic);

	void update(Long topicId, Topic topic);

	void delete(Long topicId);

}
