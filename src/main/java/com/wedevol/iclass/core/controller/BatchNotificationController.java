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

import com.wedevol.iclass.core.entity.BatchNotification;
import com.wedevol.iclass.core.service.BatchNotificationService;

/**
 * Batch Notification Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/batch/notifications")
public class BatchNotificationController {

	protected static final Logger logger = LoggerFactory.getLogger(BatchNotificationController.class);

	@Autowired
	private BatchNotificationService batchService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<BatchNotification> findAll() {
		return batchService.findAll();
	}

	@ApiIgnore
	@RequestMapping(value = "/{batchId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public BatchNotification findById(@PathVariable Long batchId) {
		return batchService.findById(batchId);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public BatchNotification create(@Valid @RequestBody BatchNotification batch) {
		return batchService.create(batch);
	}

	@ApiIgnore
	@RequestMapping(value = "/{batchId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long batchId, @Valid @RequestBody BatchNotification batch) {
		batchService.update(batchId, batch);
	}

	@ApiIgnore
	@RequestMapping(value = "/{batchId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long batchId) {
		batchService.delete(batchId);
	}
	
	@ApiIgnore
	@RequestMapping(value = "/getNotificationsToBeSent", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<BatchNotification> getNotificationsToBeSent() {
		return batchService.getNotificationsToBeSent();
	}
}
