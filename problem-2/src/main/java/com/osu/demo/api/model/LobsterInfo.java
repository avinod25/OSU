package com.osu.demo.api.model;

public class LobsterInfo{
	
	private Integer lobsterId;
	private String name;
	private String description;
	private LobsterKind kind;
	
	public LobsterInfo(Integer lobsterId, String name, String description, LobsterKind kind) {
		super();
		this.lobsterId = lobsterId;
		this.name = name;
		this.description = description;
		this.kind = kind;
	}

	public Integer getLobsterId() {
		return lobsterId;
	}

	public void setLobsterId(Integer lobsterId) {
		this.lobsterId = lobsterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LobsterKind getKind() {
		return kind;
	}

	public void setKind(LobsterKind kind) {
		this.kind = kind;
	}
	
}
