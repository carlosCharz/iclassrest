package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wedevol.iclass.core.entity.constraint.Currency;
import com.wedevol.iclass.core.entity.constraint.EnrollmentStatus;
import com.wedevol.iclass.core.entity.enums.CurrencyType;
import com.wedevol.iclass.core.entity.enums.EnrollmentStatusType;

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

	@EnrollmentStatus
	@Column
	private String status;

	@Digits(integer = 2, fraction = 2, message = "Price must be just digits with maximum 2 decimals")
	@Column
	private Float price;

	@Currency
	@Column
	private String currency;

	protected InstructorEnrollment() {
	}

	public InstructorEnrollment(InstructorEnrollmentId id) {
		this.id = id;
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
	public EnrollmentStatusType getStatusType() {
		return EnrollmentStatusType.valueOf(status);
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
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
