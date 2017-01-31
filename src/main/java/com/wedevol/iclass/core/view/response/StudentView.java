package com.wedevol.iclass.core.view.response;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.wedevol.iclass.core.entity.Faculty;
import com.wedevol.iclass.core.entity.Student;
import com.wedevol.iclass.core.entity.University;

/**
 * Student Response View
 * 
 * @author charz
 *
 */
public class StudentView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String password;
	private Date birthday;
	private String gender;
	private String profilePictureUrl;
	private String placeOptions;
	private Long facultyId;
	private String facultyName;
	private Long universityId;
	private String universityName;
	private String fcmToken;

	protected StudentView() {
	}
	
	public StudentView(Long id) {
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
	
	public static StudentView from(Student student) {
		StudentView studentView = new StudentView(student.getId());
		studentView.setFirstName(student.getFirstName());
		studentView.setLastName(student.getLastName());
		studentView.setPhone(student.getPhone());
		studentView.setEmail(student.getEmail());
		studentView.setPassword(student.getPassword());
		studentView.setBirthday(student.getBirthday());
		studentView.setGender(student.getGender());
		studentView.setProfilePictureUrl(student.getProfilePictureUrl());
		studentView.setPlaceOptions(student.getPlaceOptions());
		studentView.setFacultyId(student.getFacultyId());
		studentView.setUniversityId(student.getUniversityId());
		studentView.setFcmToken(student.getFcmToken());
		return studentView;
	}
	
	public static StudentView from(Student student, University university, Faculty faculty) {
		StudentView studentView = StudentView.from(student);
		studentView.setUniversityName(university.getShortName());
		studentView.setFacultyName(faculty.getShortName());
		return studentView;
	}

}