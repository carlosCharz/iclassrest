package com.wedevol.iclass.core.view;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * User Basic View
 * 
 * @author charz
 *
 */
public class UserBasicView implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Digits(integer = 20, fraction = 0, message = "User id must be just digits")
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
