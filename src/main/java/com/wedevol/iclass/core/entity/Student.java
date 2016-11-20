package com.wedevol.iclass.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Student Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "firstname", nullable = false)
	private String firstName;

	@Column(name = "lastname", nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = true)
	private Date birthday;

	@Column(nullable = true)
	private String gender;

	@Column(name = "profilepictureurl", nullable = true)
	private String profilePictureUrl;

	@Column(name = "placeoptions", nullable = true)
	private String placeOptions;

	@Column(nullable = true)
	private String university;

	@Column(nullable = false)
	private Float rating;

	@Column(nullable = false)
	private Integer level;

	@Column(name = "totalhours", nullable = false)
	private Integer totalHours;

	protected Student() {
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

	public String getPlaceOptions() {
		return placeOptions;
	}

	public void setPlaceOptions(String placeOptions) {
		this.placeOptions = placeOptions;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
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

}
