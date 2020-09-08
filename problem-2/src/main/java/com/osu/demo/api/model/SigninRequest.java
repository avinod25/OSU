package com.osu.demo.api.model;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The Class SigninRequest.
 * 
 * @author Vinod Arroju
 */
public class SigninRequest {
	
	/** The username. */
	@Schema(description  = "User name",name="username",required=true,example ="osutest")
	@NotEmpty(message = "Please provide a user name")
	private String username;
	
	/** The password. */
	@Schema(description  = "Password",name="password",required=true,example ="Welcome@01")
	@NotEmpty(message = "Please provide password")
	private String password;
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
