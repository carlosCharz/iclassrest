package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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

	/**
	 * Return the notifications to be sent (now -5h > scheduledAt)
	 * 
	 * @return list of batch notifications
	 */
	@Query(value = "SELECT * FROM batch_notification bat WHERE DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 5 HOUR), '%Y%m%d %H') > DATE_FORMAT(bat.scheduledAt, '%Y%m%d %H')", nativeQuery = true)
	public List<BatchNotification> getNotificationsToBeSent();

	/**
	 * Return batch notifications that matches with the provided classId
	 * 
	 * @param classId
	 * @return batchs
	 */
	public List<BatchNotification> findByClassId(Long classId);

}
