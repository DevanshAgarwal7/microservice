package com.nagarro.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.customer.convertor.Convertor;
import com.nagarro.customer.entity.CustomerEntity;
import com.nagarro.customer.exception.CustomerNotFoundException;
import com.nagarro.customer.model.AccountModel;
import com.nagarro.customer.model.CustomerAccountWrapperModel;
import com.nagarro.customer.model.CustomerModel;
import com.nagarro.customer.model.CustomerUpdateDetailsModel;
import com.nagarro.customer.repository.CustomerRepository;
import com.nagarro.customer.service.AccountClientService;
import com.nagarro.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountClientService accountClientService;

	@Override
	public CustomerAccountWrapperModel save(CustomerModel customerModel) throws Exception {
		if(isCustomerPresent(customerModel)!= null) {
			throw new Exception();
		}
		String currentDateTime = CurrentDateTimeService.getCurrentDateTime();
		customerModel.setAddingTime(currentDateTime);
		customerModel.setUpdatingTime(currentDateTime);
		CustomerEntity customerEntity = Convertor.ModelToEntity(customerModel);
		customerModel = Convertor.EntityToModel(this.customerRepository.save(customerEntity));
		AccountModel accountModel = this.accountClientService.createAccount(customerModel.getCustomerId()).getBody();
		return Convertor.generateCustomerAccount(customerModel, accountModel);
		
	}

	@Override
	public List<CustomerModel> getAll() {
		return Convertor.EntityListToModelList(this.customerRepository.findAll());
	}

	@Override
	public CustomerModel getCustomer(int customerId) throws Exception{
		return Convertor.EntityToModel(this.customerRepository.findById(customerId).orElseThrow());	
	}

	@Override
	public CustomerModel updateCustomerDetails(CustomerUpdateDetailsModel customerUpdatedDeatils) throws Exception {
		CustomerModel customerModel = getCustomer(customerUpdatedDeatils.getCustomerId());
		if(customerModel != null) {
			if(customerUpdatedDeatils.getEmail().trim().length() > 0) {
				customerModel.setEmail(customerUpdatedDeatils.getEmail());
			}
			if(customerUpdatedDeatils.getPhoneNumber().trim().length() > 0) {
				customerModel.setPhoneNumber(customerUpdatedDeatils.getPhoneNumber());
			}
			customerModel.setUpdatingTime(CurrentDateTimeService.getCurrentDateTime());
			this.customerRepository.save(Convertor.ModelToEntity(customerModel));
			return customerModel;
		}
		return null;
	}

	@Override
	public CustomerModel delete(int customerId) throws Exception {
		CustomerModel customerModel = getCustomer(customerId);
		this.customerRepository.deleteById(customerId);
		this.accountClientService.deleteAccount(customerId);
		return customerModel;
	}
	
	private CustomerEntity isCustomerPresent(CustomerModel customerModel) {
		CustomerEntity entity = this.customerRepository.findByEmail(customerModel.getEmail());
		if(entity != null) {
			return entity;
		}
		entity = this.customerRepository.findByPhoneNumber(customerModel.getPhoneNumber());
		if(entity != null) {
			return entity;
		}
		return null;
	}

}
