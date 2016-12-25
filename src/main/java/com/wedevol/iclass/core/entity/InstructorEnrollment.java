package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wedevol.iclass.core.entity.constraint.CourseStatus;
import com.wedevol.iclass.core.entity.constraint.Currency;
import com.wedevol.iclass.core.entity.enums.CourseStatusType;
import com.wedevol.iclass.core.entity.enums.CurrencyType;

/**
 * Instructor Enrollment Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "instructor_enrollment")
public class InstructorEnrollment implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InstructorEnrollmentId id;

	@NotNull
	@CourseStatus
	@Column
	private String status;
	
	@NotNull
	@Digits(integer = 2, fraction = 0, message = "Price must be just digits")
	@Column
	private Integer price;
	
	@NotNull
	@Currency
	@Column
	private String currency;

	protected InstructorEnrollment() {
	}

	public InstructorEnrollmentId getId() {
		return id;
	}

	public void setInstructorEnrollmentId(InstructorEnrollmentId instructorEnrollmentId) {
		this.id = instructorEnrollmentId;
	}

	public String getStatus() {
		return status;
	}

	@JsonIgnore
	public CourseStatusType getStatusType() {
		return CourseStatusType.valueOf(status);
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public String getCurrency() {
		return currency;
	}

	@JsonIgnore
	public CurrencyType getCurrencyType() {
		return CurrencyType.valueOf(currency);
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}