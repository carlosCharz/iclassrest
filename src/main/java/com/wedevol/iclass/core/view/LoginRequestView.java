package com.wedevol.iclass.core.view;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Login request view
 * 
 * @author charz
 *
 */
public class LoginRequestView implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 80, message = "Email must be maximum 80 characters")
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Email invalid format")
	private String email;

	@NotNull
	@Size(min = 6, max = 64, message = "Password must be 6 characters minimum")
	private String password;

	protected LoginRequestView() {
	}

	public LoginRequestView(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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
}
