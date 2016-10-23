package com.tresbu.nvidia.dao.entity;

import java.io.Serializable;


/**
 * The persistent class for the login database table.
 * 
 */
public class LoginEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int userId;

	private String email;

	private String password;

	public LoginEntity() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}