package com.osu.demo.api.model;

/**
 * The Class BaseResponse.
 * 
 * @author Vinod Arroju
 */
public class BaseResponse {
	
	/** The status. */
	private Status status;
	
	/** The message. */
	private String message;

	/**
	 * Gets the status.
	 *
	 * @return the {@code Status}
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the {@code Status}
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Gets the message.
	 *
	 * @return the {@code String}
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the {@code String}
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
