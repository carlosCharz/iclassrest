package com.wedevol.iclass.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.BatchNotification;

/**
 * Batch Notification Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface BatchNotificationRepository extends CrudRepository<BatchNotification, Long> {


}
