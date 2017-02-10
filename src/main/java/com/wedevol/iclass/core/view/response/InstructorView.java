package com.wedevol.iclass.core.view.response;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wedevol.iclass.core.entity.Faculty;
import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.University;
import com.wedevol.iclass.core.entity.constraint.CustomDateDeserialize;

/**
 * Instructor Response View
 * 
 * @author charz
 *
 */
public class InstructorView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String password;
	@JsonDeserialize(using = CustomDateDeserialize.class)
	private Date birthday;
	private String gender;
	private String profilePictureUrl;
	private String placeOptions;
	private Long facultyId;
	private String facultyName;
	private Long universityId;
	private String universityName;
	private String fcmToken;
	private String deviceId;
	private Float rating;
	private Integer ratingCount;
	private Integer level;
	private Integer totalHours;

	protected InstructorView() {
	}

	public InstructorView(Long id) {
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

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	public Integer getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	public static InstructorView from(Instructor instructor) {
		InstructorView instructorView = new InstructorView(instructor.getId());
		instructorView.setFirstName(instructor.getFirstName());
		instructorView.setLastName(instructor.getLastName());
		instructorView.setPhone(instructor.getPhone());
		instructorView.setEmail(instructor.getEmail());
		instructorView.setPassword(instructor.getPassword());
		instructorView.setBirthday(instructor.getBirthday());
		instructorView.setGender(instructor.getGender());
		instructorView.setProfilePictureUrl(instructor.getProfilePictureUrl());
		instructorView.setPlaceOptions(instructor.getPlaceOptions().isEmpty() ? null : instructor.getPlaceOptions());
		instructorView.setFacultyId(instructor.getFacultyId());
		instructorView.setUniversityId(instructor.getUniversityId());
		instructorView.setFcmToken(instructor.getFcmToken());
		instructorView.setDeviceId(instructor.getDeviceId());
		instructorView.setRating(instructor.getRating());
		instructorView.setRatingCount(instructor.getRatingCount());
		instructorView.setLevel(instructor.getLevel());
		instructorView.setTotalHours(instructor.getTotalHours());
		return instructorView;
	}

	public static InstructorView from(Instructor instructor, University university, Faculty faculty) {
		InstructorView instructorView = InstructorView.from(instructor);
		instructorView.setUniversityName(university.getShortName());
		instructorView.setFacultyName(faculty.getShortName());
		return instructorView;
	}

}
