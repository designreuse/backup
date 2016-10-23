package com.tresbu.nvidia.common.data;

public class LoginData extends NvidiaData {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5763453151577090747L;
	
	private String email;
	private String password;
	public LoginData() {
		super();
	}
	public LoginData(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}

}
