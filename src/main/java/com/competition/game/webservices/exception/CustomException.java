package com.competition.game.webservices.exception;

import java.io.Serializable;

public class CustomException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public CustomException(String message) {
		super(message);
	}

	public CustomException(String message, Throwable t) {
		super(message, t);
	}

	public CustomException() {
		super();
	}

	public CustomException(Exception e) {
		super(e);
	}
}
