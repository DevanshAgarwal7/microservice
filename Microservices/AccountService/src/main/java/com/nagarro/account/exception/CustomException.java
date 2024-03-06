package com.nagarro.account.exception;

public class CustomException {

	private String message;

	public CustomException(String message) {
		setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
