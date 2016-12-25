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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateDeserialize;
import com.wedevol.iclass.core.entity.constraint.CustomDateSerialize;
import com.wedevol.iclass.core.entity.constraint.Gender;
import com.wedevol.iclass.core.entity.constraint.PlaceOptions;

/**
 * Instructor Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "instructor")
@DynamicInsert
public class Instructor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 2, max = 45, message = "First name must be between 2 - 45 characters")
	@Column(name = "firstname")
	private String firstName;

	@NotNull
	@Size(min = 2, max = 45, message = "Last name must be between 2 - 45 characters")
	@Column(name = "lastname")
	private String lastName;

	@NotNull
	@Size(min = 7, max = 20, message = "Phone number must be between 7 - 20 digits")
	@Digits(integer = 20, fraction = 0, message = "Phone number must be just digits")
	@Column
	private String phone;

	@NotNull
	@Size(max = 80, message = "Email must be maximum 80 characters")
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Email invalid format")
	@Column
	private String email;

	@NotNull
	@Size(min = 6, max = 64, message = "Password must be 6 characters minimum")
	@Column
	private String password;

	@Past
	@JsonDeserialize(using = CustomDateDeserialize.class)
	@JsonSerialize(using = CustomDateSerialize.class)
	@Column(nullable = true)
	private Date birthday;

	@Gender // It has size validation, gender validation and default message
	@Column(nullable = true)
	private String gender;

	@Size(min = 2, max = 100, message = "Profile picture url must be between 2 - 100 characters")
	@Column(name = "profilepictureurl", nullable = true)
	private String profilePictureUrl;

	@PlaceOptions // It has place options validation and default message
	@Column(name = "placeoptions", nullable = true)
	private String placeOptions;

	@Size(min = 2, max = 100, message = "University name must be between 2 - 100 characters")
	@Column(nullable = true)
	private String university;

	@Column
	private Float rating;

	@Column
	private Integer level;

	@Column(name = "totalhours")
	private Integer totalHours;

	protected Instructor() {
	}

	private Instructor(InstructorBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.phone = builder.phone;
		this.email = builder.email;
		this.password = builder.password;
		this.birthday = builder.birthday;
		this.gender = builder.gender;
		this.profilePictureUrl = builder.profilePictureUrl;
		this.placeOptions = builder.placeOptions;
		this.university = builder.university;
		this.rating = builder.rating;
		this.level = builder.level;
		this.totalHours = builder.totalHours;
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

	/**
	 * Instructor Builder
	 * 
	 * @author charz
	 *
	 */
	public static class InstructorBuilder {

		private final String firstName;
		private final String lastName;
		private final String phone;
		private final String email;
		private final String password;
		private Date birthday;
		private String gender;
		private String profilePictureUrl;
		private String placeOptions;
		private String university;
		private Float rating;
		private Integer level;
		private Integer totalHours;

		public InstructorBuilder(String firstName, String lastName, String phone, String email, String password) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.phone = phone;
			this.email = email;
			this.password = password;
		}

		public InstructorBuilder birthday(Date birthday) {
			this.birthday = birthday;
			return this;
		}

		public InstructorBuilder gender(String gender) {
			this.gender = gender;
			return this;
		}

		public InstructorBuilder profilePictureUrl(String profilePictureUrl) {
			this.profilePictureUrl = profilePictureUrl;
			return this;
		}

		public InstructorBuilder placeOptions(String placeOptions) {
			this.placeOptions = placeOptions;
			return this;
		}

		public InstructorBuilder university(String university) {
			this.university = university;
			return this;
		}

		public InstructorBuilder rating(Float rating) {
			this.rating = rating;
			return this;
		}

		public InstructorBuilder level(Integer level) {
			this.level = level;
			return this;
		}

		public InstructorBuilder totalHours(Integer totalHours) {
			this.totalHours = totalHours;
			return this;
		}

		public Instructor build() {
			// TODO: analyze if we need to validate here. IllegalStateException.
			return new Instructor(this);
		}

	}

}