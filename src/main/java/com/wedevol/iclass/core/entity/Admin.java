package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

/**
 * Admin Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "admin")
@DynamicInsert
// It excludes null properties
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 2, max = 45, message = "First name must be between 2 - 45 characters")
	@Column(name = "firstname")
	private String firstName;

	@Size(min = 2, max = 45, message = "Last name must be between 2 - 45 characters")
	@Column(name = "lastname")
	private String lastName;

	@Size(max = 80, message = "Email must be maximum 80 characters")
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Email invalid format")
	@Column
	private String email;

	@Size(min = 6, max = 64, message = "Password must be 6 characters minimum")
	@Column
	private String password;

	@Digits(integer = 20, fraction = 0, message = "University id must be just digits")
	@Column(name = "universityid")
	private Long universityId;

	@Size(min = 2, max = 255, message = "FCM token must be between 2 - 255 characters")
	@Column(name = "fcmtoken")
	private String fcmToken;
	
	@Size(min = 2, max = 255, message = "Device id must be between 2 - 255 characters")
	@Column(name = "deviceid")
	private String deviceId;

	@Column
	private boolean active;

	public Admin() {
	}
	
	private Admin(Long id) {
		this.id = id;
	}

	public static Admin from(Long id) {
		return new Admin(id);
	}

	private Admin(AdminBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.password = builder.password;
		this.universityId = builder.universityId;
		this.active = builder.active;
		this.fcmToken = builder.fcmToken;
		this.deviceId = builder.deviceId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Long universityId) {
		this.universityId = universityId;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Admin Builder
	 * 
	 * @author charz
	 *
	 */
	public static class AdminBuilder {

		private final String firstName;
		private final String lastName;
		private final String email;
		private final String password;
		private Long universityId;
		private String fcmToken;
		private String deviceId;
		private boolean active;

		public AdminBuilder(String firstName, String lastName, String email, String password) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
		}

		public AdminBuilder university(Long universityId) {
			this.universityId = universityId;
			return this;
		}

		public AdminBuilder fcmToken(String fcmToken) {
			this.fcmToken = fcmToken;
			return this;
		}
		
		public AdminBuilder deviceId(String deviceId) {
			this.deviceId = deviceId;
			return this;
		}

		public AdminBuilder isActive(boolean active) {
			this.active = active;
			return this;
		}

		public Admin build() {
			return new Admin(this);
		}

	}
	
	@Override
	public String toString() {
		return String.format("Admin[id=%d, firstName='%s', lastName='%s']%n", id, firstName, lastName);
	}

}
