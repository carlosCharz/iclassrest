package com.wedevol.iclass.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Topic;

/**
 * Topic Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface TopicRepository extends CrudRepository<Topic, Long> {

	/**
	 * Return the topic having the passed topic name or null if no topic is
	 * found.
	 * 
	 * @param topicName
	 * @return topic
	 */
	public Topic findByName(String name);

}
