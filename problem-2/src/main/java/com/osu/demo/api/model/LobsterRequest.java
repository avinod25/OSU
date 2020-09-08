package com.osu.demo.api.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LobsterRequest{
	@NotEmpty(message = "Please provide a lobster name")
	private String name;
	private String description;
	@NotNull(message = "Please provide a valid lobster kind. Clawed or Spiny")
	private LobsterKind kind;
	
	public LobsterRequest(String name, String description, LobsterKind kind) {
		super();
		this.name = name;
		this.description = description;
		this.kind = kind;
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
