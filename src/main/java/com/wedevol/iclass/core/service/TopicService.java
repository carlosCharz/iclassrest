package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Topic;

/**
 * Topic Service Interface
 * 
 * @author charz
 *
 */
public interface TopicService extends BaseService<Topic> {

	List<Topic> findAll();

	Topic findByName(String name);

}
