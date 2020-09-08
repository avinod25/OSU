package com.osu.demo.api.model;

import java.util.List;

public class LobstersResponse extends BaseResponse {
	private List<LobsterInfo> data;

	public List<LobsterInfo> getData() {
		return data;
	}

	public void setData(List<LobsterInfo> data) {
		this.data = data;
	}

}
