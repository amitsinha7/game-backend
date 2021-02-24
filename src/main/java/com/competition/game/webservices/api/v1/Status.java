package com.competition.game.webservices.api.v1;

public class Status {
	private int solvedTasks;
	private int successfulAttempt;

	public int getSolvedTasks() {
		return solvedTasks;
	}

	public void setSolvedTasks(int solvedTasks) {
		this.solvedTasks = solvedTasks;
	}

	public int getSuccessfulAttempt() {
		return successfulAttempt;
	}

	public void setSuccessfulAttempt(int successfulAttempt) {
		this.successfulAttempt = successfulAttempt;
	}

}
