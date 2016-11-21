package com.wedevol.iclass.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

/**
 * Student Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "student")
@DynamicInsert
// this enables the DBMS values
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "firstname")
	private String firstName;

	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "lastname")
	private String lastName;

	@NotNull
	@Size(min = 1, max = 20)
	@Column
	private String phone;

	@NotNull
	@Size(min = 1, max = 80)
	@Column
	private String email;

	@NotNull
	@Size(min = 6, max = 6)
	@Column
	private String password;

	@Column(nullable = true)
	private Date birthday;

	@Size(min = 1, max = 1)
	@Column(nullable = true)
	private String gender;

	@Size(max = 100)
	@Column(name = "profilepictureurl", nullable = true)
	private String profilePictureUrl;

	@Column(name = "placeoptions", nullable = true)
	private String placeOptions;

	@Size(max = 100)
	@Column(nullable = true)
	private String university;

	@Column
	private Float rating;

	@Column
	private Integer level;

	@Column(name = "totalhours")
	private Integer totalHours;

	protected Student() {
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

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getGender() {
		return gender;
	}

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public String getPlaceOptions() {
		return placeOptions;
	}

	public String getUniversity() {
		return university;
	}

	public Float getRating() {
		return rating;
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getTotalHours() {
		return totalHours;
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
		private String university;
		private Float rating;
		private Integer level;
		private Integer totalHours;

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

		public StudentBuilder university(String university) {
			this.university = university;
			return this;
		}

		public StudentBuilder rating(Float rating) {
			this.rating = rating;
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

		public Student build() {
			// TODO: analyze if we need to validate here. IllegalStateException.
			return new Student(this);
		}

	}

}
