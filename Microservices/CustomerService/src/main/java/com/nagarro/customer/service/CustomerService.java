package com.nagarro.customer.service;

import java.util.List;

import com.nagarro.customer.model.CustomerAccountWrapperModel;
import com.nagarro.customer.model.CustomerModel;
import com.nagarro.customer.model.CustomerUpdateDetailsModel;

public interface CustomerService {

	CustomerAccountWrapperModel save(CustomerModel customerModel) throws Exception;

	List<CustomerModel> getAll();

	CustomerModel getCustomer(int customerId) throws Exception;

	CustomerModel updateCustomerDetails(CustomerUpdateDetailsModel customerUpdatedDeatils) throws Exception;

	CustomerModel delete(int customerId) throws Exception;

}
