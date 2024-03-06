package com.nagarro.customer.convertor;

import java.util.ArrayList;
import java.util.List;

import com.nagarro.customer.entity.CustomerEntity;
import com.nagarro.customer.model.AccountModel;
import com.nagarro.customer.model.CustomerAccountWrapperModel;
import com.nagarro.customer.model.CustomerModel;

public class Convertor {

	public static CustomerEntity ModelToEntity(CustomerModel customerModel) {
		CustomerEntity entity = new CustomerEntity();
		entity.setCustomerId(customerModel.getCustomerId());
		entity.setFirstName(customerModel.getFirstName());
		entity.setLastName(customerModel.getLastName());
		entity.setAddress(customerModel.getAddress());
		entity.setEmail(customerModel.getEmail());
		entity.setPhoneNumber(customerModel.getPhoneNumber());
		entity.setAddingTime(customerModel.getAddingTime());
		entity.setUpdatingTime(customerModel.getUpdatingTime());

		return entity;
	}

	public static List<CustomerModel> EntityListToModelList(List<CustomerEntity> customerEntityList) {
		List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
		for (CustomerEntity entity : customerEntityList) {
			CustomerModel customerModel = new CustomerModel();
			customerModel.setCustomerId(entity.getCustomerId());
			customerModel.setFirstName(entity.getFirstName());
			customerModel.setLastName(entity.getLastName());
			customerModel.setAddress(entity.getAddress());
			customerModel.setEmail(entity.getEmail());
			customerModel.setPhoneNumber(entity.getPhoneNumber());
			customerModel.setAddingTime(entity.getAddingTime());
			customerModel.setUpdatingTime(entity.getUpdatingTime());

			customerModelList.add(customerModel);
		}
		return customerModelList;
	}

	public static CustomerModel EntityToModel(CustomerEntity entity) {
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerId(entity.getCustomerId());
		customerModel.setFirstName(entity.getFirstName());
		customerModel.setLastName(entity.getLastName());
		customerModel.setAddress(entity.getAddress());
		customerModel.setEmail(entity.getEmail());
		customerModel.setPhoneNumber(entity.getPhoneNumber());
		customerModel.setAddingTime(entity.getAddingTime());
		customerModel.setUpdatingTime(entity.getUpdatingTime());

		return customerModel;
	}

	public static CustomerAccountWrapperModel generateCustomerAccount(CustomerModel customerModel,
			AccountModel accountModel) {
		CustomerAccountWrapperModel customerAccount = new CustomerAccountWrapperModel();
		customerAccount.setAccountNumber(accountModel.getAccountNumber());
		customerAccount.setCustomerId(customerModel.getCustomerId());
		customerAccount.setFirstName(customerModel.getFirstName());
		customerAccount.setLastName(customerModel.getLastName());
		customerAccount.setAddress(customerModel.getAddress());
		customerAccount.setEmail(customerModel.getEmail());
		customerAccount.setPhoneNumber(customerModel.getPhoneNumber());
		customerAccount.setAccountType(accountModel.getAccountType());
		customerAccount.setAmount(accountModel.getAmount());
		customerAccount.setOpeningTime(accountModel.getOpeningTime());

		return customerAccount;
	}

}
