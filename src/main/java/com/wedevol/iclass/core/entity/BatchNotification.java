package com.wedevol.iclass.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wedevol.iclass.core.entity.constraint.CustomDatetimeDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDatetimeSerialize;

/**
 * Batch Notification
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "batch_notification")
@DynamicInsert
public class BatchNotification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min = 2, max = 100, message = "Message must be between 2 - 100 characters")
	@Column
	private String message;
	
	@Size(min = 2, max = 255, message = "FCM token must be between 2 - 255 characters")
	@Column(name = "tokento")
	private String tokenTo;
	
	@JsonDeserialize(using = CustomDatetimeDeserialize.class)
	@JsonSerialize(using = CustomDatetimeSerialize.class)
	@Column(name = "scheduledat")
	private Date scheduledAt;
	
	@JsonDeserialize(using = CustomDatetimeDeserialize.class)
	@JsonSerialize(using = CustomDatetimeSerialize.class)
	@Column(name = "createdat")
	private Date createdAt;
	
	@Digits(integer = 20, fraction = 0, message = "Class id must be just digits")
	@Column(name = "classid")
	private Long classId;

	@Size(min = 2, max = 100, message = "Notification type must be between 2 - 100 characters")
	@Column(name = "notificationtype")
	// TODO: analyze enum
	private String notificationType;

	public static BatchNotification from(Long id) {
		return new BatchNotification(id);
	}

	private BatchNotification(Long id) {
		this.id = id;
	}
	
	public BatchNotification(String message, String tokenTo, Date scheduledAt, Long classId, String notificationType) {
		this.message = message;
		this.tokenTo = tokenTo;
		this.scheduledAt = scheduledAt;
		this.classId = classId;
		this.notificationType = notificationType;
	}

	protected BatchNotification() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTokenTo() {
		return tokenTo;
	}

	public void setTokenTo(String tokenTo) {
		this.tokenTo = tokenTo;
	}

	public Date getScheduledAt() {
		return scheduledAt;
	}

	public void setScheduledAt(Date scheduledAt) {
		this.scheduledAt = scheduledAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	@Override
	public String toString() {
		return String.format("BatchNotification[id=%d, message='%s', tokenTo='%s']%n", id, message, tokenTo);
	}
}
