package com.competition.game.webservices.api.v1;

public class TopPlayer {

	private String name;

	private Status status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TopPlayer(String name, Status status) {
		super();
		this.name = name;
		this.status = status;
	}

	public TopPlayer() {

	}

}
