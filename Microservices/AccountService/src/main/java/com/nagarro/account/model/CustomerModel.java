package com.nagarro.account.model;

public class CustomerModel {

	private int customerId;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String phoneNumber;
	private String addingTime;
	private String updatingTime;

	public String getUpdatingTime() {
		return updatingTime;
	}

	public void setUpdatingTime(String updatingTime) {
		this.updatingTime = updatingTime;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddingTime() {
		return addingTime;
	}

	public void setAddingTime(String addingTime) {
		this.addingTime = addingTime;
	}
}
