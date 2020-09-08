package com.osu.demo.api.model;

/**
 * The Class SigninResponse.
 * 
 * @author Vinod Arroju
 */
public class SigninResponse extends BaseResponse{
	
	/** The access token. */
	private String accessToken;
	
	/**
	 * Gets the access token.
	 *
	 * @return the {@code String}
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Sets the access token.
	 *
	 * @param accessToken the {@code String}
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
