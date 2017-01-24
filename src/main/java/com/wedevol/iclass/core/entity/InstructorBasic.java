package com.wedevol.iclass.core.entity;

import java.io.Serializable;

/**
 * Instructor basic class
 * 
 * @author charz
 *
 */
public class InstructorBasic implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private Float rating;
	private Integer level;
	private Float price;
	private String currency;

	protected InstructorBasic() {
	}

	public InstructorBasic(Long id, String firstName, String lastName, Float rating, Integer level, Float price,
			String currency) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rating = rating;
		this.level = level;
		this.price = price;
		this.currency = currency;
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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Override
	public String toString() {
		return String.format("InstructorBasic[id=%d, firstName='%s', lastName='%s']%n", id, firstName, lastName);
	}
}
