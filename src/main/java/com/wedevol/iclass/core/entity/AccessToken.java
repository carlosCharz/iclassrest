package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;

import com.wedevol.iclass.core.entity.enums.UserType;

/**
 * Access Token Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "access_token")
@DynamicInsert
public class AccessToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Digits(integer = 20, fraction = 0, message = "User id must be just digits")
	@Column(name = "userid")
	private Long userId;

	// TODO: put a enum
	@Size(min = 2, max = 100, message = "User type must be between 2 - 100 characters")
	@Column(name = "usertype")
	private String userType;

	@Size(min = 10, max = 255, message = "Token must be maximum 255 characters")
	@Column
	private String token;

	public AccessToken() {
	}

	private AccessToken(Long id) {
		this.id = id;
	}

	public static AccessToken from(Long id) {
		return new AccessToken(id);
	}
	
	public AccessToken(Long userId, UserType userType) {
		this.userId = userId;
		this.userType = userType.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return String.format("AccessToken[id=%d, userId=%d, userType='%s', token='%s']%n", id, userId, userType, token);
	}

}
