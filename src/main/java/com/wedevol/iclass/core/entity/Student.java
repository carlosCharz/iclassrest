package com.wedevol.iclass.core.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateSerialize;
import com.wedevol.iclass.core.entity.constraint.Gender;
import com.wedevol.iclass.core.entity.constraint.PlaceOptions;

/**
 * Student Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "student")
@DynamicInsert
// It excludes null properties
public class Student implements Serializable {

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

	@Size(min = 7, max = 20, message = "Phone number must be between 7 - 20 digits")
	@Digits(integer = 20, fraction = 0, message = "Phone number must be just digits")
	@Column
	private String phone;

	@Size(max = 80, message = "Email must be maximum 80 characters")
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Email invalid format")
	@Column
	private String email;

	@Size(min = 6, max = 64, message = "Password must be 6 characters minimum")
	@Column
	private String password;

	@Past
	@JsonDeserialize(using = CustomDateDeserialize.class)
	@JsonSerialize(using = CustomDateSerialize.class)
	@Column
	private Date birthday;

	@Gender // It has size validation, gender validation and default message
	@Column
	private String gender;

	@Size(min = 2, max = 100, message = "Profile picture url must be between 2 - 100 characters")
	@Column(name = "profilepictureurl")
	private String profilePictureUrl;

	@PlaceOptions // It has place options validation and default message
	@Column(name = "placeoptions")
	private String placeOptions;

	@Digits(integer = 20, fraction = 0, message = "Faculty id must be just digits")
	@Column(name = "facultyid")
	private Long facultyId;

	@Digits(integer = 20, fraction = 0, message = "University id must be just digits")
	@Column(name = "universityid")
	private Long universityId;

	@Column
	private Float rating;
	
	@Column(name = "ratingcount")
	private Integer ratingCount;

	@Column
	private Integer level;

	@Column(name = "totalhours")
	private Integer totalHours;

	@Size(min = 2, max = 255, message = "FCM token must be between 2 - 255 characters")
	@Column(name = "fcmtoken")
	private String fcmToken;

	@Size(min = 2, max = 255, message = "Device id must be between 2 - 255 characters")
	@Column(name = "deviceid")
	private String deviceId;

	@Column
	private boolean active;

	public Student() {
	}

	private Student(Long id) {
		this.id = id;
	}

	public static Student from(Long id) {
		return new Student(id);
	}

	private Student(StudentBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.phone = builder.phone;
		this.email = builder.email;
		this.password = builder.password;
		this.birthday = builder.birthday;
		this.gender = builder.gender;
		this.profilePictureUrl = builder.profilePictureUrl;
		this.placeOptions = builder.placeOptions;
		this.universityId = builder.universityId;
		this.facultyId = builder.facultyId;
		this.rating = builder.rating;
		this.ratingCount = builder.ratingCount;
		this.level = builder.level;
		this.totalHours = builder.totalHours;
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
	 * Student Builder
	 * 
	 * @author charz
	 *
	 */
	public static class StudentBuilder {

		private final String firstName;
		private final String lastName;
		private final String phone;
		private final String email;
		private final String password;
		private Date birthday;
		private String gender;
		private String profilePictureUrl;
		private String placeOptions;
		private Long universityId;
		private Long facultyId;
		private Float rating;
		private Integer ratingCount;
		private Integer level;
		private Integer totalHours;
		private String fcmToken;
		private String deviceId;
		private boolean active;

		public StudentBuilder(String firstName, String lastName, String phone, String email, String password) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.phone = phone;
			this.email = email;
			this.password = password;
		}

		public StudentBuilder birthday(Date birthday) {
			this.birthday = birthday;
			return this;
		}

		public StudentBuilder gender(String gender) {
			this.gender = gender;
			return this;
		}

		public StudentBuilder profilePictureUrl(String profilePictureUrl) {
			this.profilePictureUrl = profilePictureUrl;
			return this;
		}

		public StudentBuilder placeOptions(String placeOptions) {
			this.placeOptions = placeOptions;
			return this;
		}

		public StudentBuilder universityId(Long universityId) {
			this.universityId = universityId;
			return this;
		}

		public StudentBuilder facultyId(Long facultyId) {
			this.facultyId = facultyId;
			return this;
		}

		public StudentBuilder rating(Float rating) {
			this.rating = rating;
			return this;
		}
		
		public StudentBuilder ratingCount(Integer ratingCount) {
			this.ratingCount = ratingCount;
			return this;
		}

		public StudentBuilder level(Integer level) {
			this.level = level;
			return this;
		}

		public StudentBuilder totalHours(Integer totalHours) {
			this.totalHours = totalHours;
			return this;
		}

		public StudentBuilder fcmToken(String fcmToken) {
			this.fcmToken = fcmToken;
			return this;
		}

		public StudentBuilder deviceId(String deviceId) {
			this.deviceId = deviceId;
			return this;
		}

		public StudentBuilder isActive(boolean active) {
			this.active = active;
			return this;
		}

		public Student build() {
			return new Student(this);
		}

	}

	@Override
	public String toString() {
		return String.format("Student[id=%d, firstName='%s', lastName='%s']%n", id, firstName, lastName);
	}

	@JsonIgnore
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

}
