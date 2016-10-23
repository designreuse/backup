package com.nvidia.cosmos.cloud.rest.util;

public class QuayApplication {
	public QuayApplication(){
		
	}
	String avatar_email ;
	String name;
	String redirect_uri;
	String application_uri;
	String description;
	/**
	 * @return the avatar_email
	 */
	public String getAvatar_email() {
		return avatar_email;
	}
	/**
	 * @param avatar_email the avatar_email to set
	 */
	public void setAvatar_email(String avatar_email) {
		this.avatar_email = avatar_email;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the redirect_uri
	 */
	public String getRedirect_uri() {
		return redirect_uri;
	}
	/**
	 * @param redirect_uri the redirect_uri to set
	 */
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	/**
	 * @return the application_uri
	 */
	public String getApplication_uri() {
		return application_uri;
	}
	/**
	 * @param application_uri the application_uri to set
	 */
	public void setApplication_uri(String application_uri) {
		this.application_uri = application_uri;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param avatar_email
	 * @param name
	 * @param redirect_uri
	 * @param application_uri
	 * @param description
	 */
	public QuayApplication(String avatar_email, String name, String redirect_uri, String application_uri,
			String description) {
		super();
		this.avatar_email = avatar_email;
		this.name = name;
		this.redirect_uri = redirect_uri;
		this.application_uri = application_uri;
		this.description = description;
	}
	
	
	
}
