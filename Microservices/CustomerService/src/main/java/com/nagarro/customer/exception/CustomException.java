package com.nagarro.customer.exception;

public class CustomException {

	private String message;

	public CustomException(String message) {
		setMessage(message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
