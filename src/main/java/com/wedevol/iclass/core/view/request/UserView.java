package com.wedevol.iclass.core.view.request;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateSerialize;
import com.wedevol.iclass.core.entity.constraint.Gender;
import com.wedevol.iclass.core.entity.constraint.PlaceOptions;

/**
 * User View
 * 
 * @author charz
 *
 */
public class UserView implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 2, max = 45, message = "First name must be between 2 - 45 characters")
	private String firstName;

	@NotNull
	@Size(min = 2, max = 45, message = "Last name must be between 2 - 45 characters")
	private String lastName;

	@NotNull
	@Size(min = 7, max = 20, message = "Phone number must be between 7 - 20 digits")
	@Digits(integer = 20, fraction = 0, message = "Phone number must be just digits")
	private String phone;

	@NotNull
	@Size(max = 80, message = "Email must be maximum 80 characters")
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Email invalid format")
	private String email;

	@NotNull
	@Size(min = 6, max = 64, message = "Password must be 6 characters minimum")
	private String password;

	@Past
	@JsonDeserialize(using = CustomDateDeserialize.class)
	@JsonSerialize(using = CustomDateSerialize.class)
	private Date birthday;

	@Gender
	// It has size validation, gender validation and default message
	private String gender;

	@Size(min = 2, max = 100, message = "Profile picture url must be between 2 - 100 characters")
	private String profilePictureUrl;

	@PlaceOptions
	// It has place options validation and default message
	private String placeOptions;

	@Digits(integer = 20, fraction = 0, message = "Faculty id must be just digits")
	private Long facultyId;

	@Digits(integer = 20, fraction = 0, message = "University id must be just digits")
	private Long universityId;

	@Digits(integer = 20, fraction = 0, message = "Course id must be just digits")
	private Long courseId;

	@NotNull
	@Size(min = 2, max = 255, message = "FCM token must be between 2 - 255 characters")
	private String fcmToken;
	
	@NotNull
	@Size(min = 2, max = 255, message = "Device id must be between 2 - 255 characters")
	private String deviceId;

	public UserView() {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	public Set<String> getPlaceOptions() {
		return placeOptions == null ? Collections.emptySet()
				: Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(placeOptions.split(","))));
	}

	public void setPlaceOptions(Set<String> placeOptionsSet) {
		placeOptions = placeOptionsSet == null ? null : String.join(",", placeOptionsSet);
	}

	public Long getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}

	public Long getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Long universityId) {
		this.universityId = universityId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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

}
