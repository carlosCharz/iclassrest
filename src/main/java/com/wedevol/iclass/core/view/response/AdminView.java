package com.wedevol.iclass.core.view.response;

import java.io.Serializable;

import com.wedevol.iclass.core.entity.Admin;
import com.wedevol.iclass.core.entity.University;

/**
 * Admin Response View
 * 
 * @author charz
 *
 */
public class AdminView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long universityId;
	private String universityName;
	private String fcmToken;
	private String deviceId;
	private boolean active;

	protected AdminView() {
	}

	public AdminView(Long id) {
		this.id = id;
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

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
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

	public static AdminView from(Admin admin) {
		AdminView adminView = new AdminView(admin.getId());
		adminView.setFirstName(admin.getFirstName());
		adminView.setLastName(admin.getLastName());
		adminView.setEmail(admin.getEmail());
		adminView.setPassword(admin.getPassword());
		adminView.setUniversityId(admin.getUniversityId());
		adminView.setActive(admin.isActive());
		adminView.setFcmToken(admin.getFcmToken());
		adminView.setDeviceId(admin.getDeviceId());
		return adminView;
	}

	public static AdminView from(Admin admin, University university) {
		AdminView adminView = AdminView.from(admin);
		adminView.setUniversityName(university.getShortName());
		return adminView;
	}

}
