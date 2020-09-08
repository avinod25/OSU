package com.osu.demo.api.model;

/**
 * The Class LobsterResponse.
 * 
 * @author Vinod Arroju
 */
public class LobsterResponse extends BaseResponse {
	
	/** The data. */
	private LobsterInfo data;

	/**
	 * Gets the data.
	 *
	 * @return the {@code LobsterInfo}
	 */
	public LobsterInfo getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the {@code LobsterInfo}
	 */
	public void setData(LobsterInfo data) {
		this.data = data;
	}

}
