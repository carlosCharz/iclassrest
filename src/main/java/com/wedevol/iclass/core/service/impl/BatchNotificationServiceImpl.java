package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.isNullOrEmpty;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.BatchNotification;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.BatchNotificationRepository;
import com.wedevol.iclass.core.service.BatchNotificationService;
import com.wedevol.iclass.core.service.NotificationService;

/**
 * Batch Notification Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class BatchNotificationServiceImpl implements BatchNotificationService {

	protected static final Logger logger = LoggerFactory.getLogger(BatchNotificationServiceImpl.class);

	@Autowired
	private BatchNotificationRepository batchRepository;
	
	@Autowired
	private NotificationService notificationService;

	/********************* CRUD for batch notification ****************************/
	@Override
	public List<BatchNotification> findAll() {
		final Iterable<BatchNotification> batchIterator = batchRepository.findAll();
		return Lists.newArrayList(batchIterator);
	}

	@Override
	public BatchNotification findById(Long batchId) {
		final Optional<BatchNotification> batchObj = Optional.ofNullable(batchRepository.findOne(batchId));
		return batchObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.BATCH_NOTIFICATION_NOT_FOUND));
	}

	@Override
	public BatchNotification create(BatchNotification batch) {
		// Fields missing validation
		if (batch.getMessage() == null || batch.getTokenTo() == null || batch.getScheduledAt() == null) {
			//TODO: validate the optional attributes if they come (notification type)
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// Save
		return batchRepository.save(batch);
	}

	@Override
	public void update(Long batchId, BatchNotification batch) {
		// The batch notification should exist
		BatchNotification existingBatch = findById(batchId);
		if (!isNullOrEmpty(batch.getMessage())) {
			existingBatch.setMessage(batch.getMessage());
		}
		if (!isNullOrEmpty(batch.getTokenTo())) {
			existingBatch.setTokenTo(batch.getTokenTo());
		}
		if (batch.getScheduledAt()!=null) {
			existingBatch.setScheduledAt(batch.getScheduledAt());
		}
		if (batch.getClassId()!=null) {
			existingBatch.setClassId(batch.getClassId());
		}
		//TODO: change this to enum
		if (!isNullOrEmpty(batch.getNotificationType())) {
			existingBatch.setNotificationType(batch.getNotificationType());
		}
		// Update
		batchRepository.save(existingBatch);
	}

	@Override
	public void delete(Long batchId) {
		// The batch notification should exist
		findById(batchId);
		batchRepository.delete(batchId);
	}

}
