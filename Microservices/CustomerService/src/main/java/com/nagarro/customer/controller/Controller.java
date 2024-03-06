package com.nagarro.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.customer.constant.ExceptionMessage;
import com.nagarro.customer.exception.CustomerNotFoundException;
import com.nagarro.customer.model.CustomerModel;
import com.nagarro.customer.model.CustomerUpdateDetailsModel;
import com.nagarro.customer.service.CustomExceptionInterface;
import com.nagarro.customer.service.CustomerService;

import feign.FeignException;

@RestController
@RequestMapping(value = "/customer")
public class Controller {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomExceptionInterface customExceptionService;

	@PostMapping(value = "/add")
	public ResponseEntity<Object> addCustomer(@RequestBody CustomerModel customerModel) {
		try {
			return new ResponseEntity<Object>(this.customerService.save(customerModel), HttpStatus.CREATED);
		} catch (Exception e) {

			return new ResponseEntity<Object>(
					this.customExceptionService.generateEcxeption(ExceptionMessage.Customer_Already_Exists.toString()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/fetchall")
	public ResponseEntity<Object> getAllCustomer() {
		return new ResponseEntity<Object>(this.customerService.getAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/fetch")
	public ResponseEntity<Object> getCustomer(@RequestParam int customerId) {
		try {
			return new ResponseEntity<Object>(this.customerService.getCustomer(customerId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(
					this.customExceptionService.generateEcxeption(ExceptionMessage.Customer_Not_Found.toString()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PatchMapping(value = "/update")
	public ResponseEntity<Object> updateCustomer(@RequestBody CustomerUpdateDetailsModel customerUpdatedDeatils) {
		try {
			return new ResponseEntity<Object>(this.customerService.updateCustomerDetails(customerUpdatedDeatils),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(this.customExceptionService.generateEcxeption(
					ExceptionMessage.Cannot_Update_Customer_Not_Found.toString()), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Object> deleteCustomer(@RequestParam int customerId) {
		try {
			return new ResponseEntity<Object>(this.customerService.delete(customerId), HttpStatus.OK);
		} catch(FeignException e) {
			return new ResponseEntity<Object>(this.customExceptionService.generateEcxeption(
					ExceptionMessage.Your_Details_Are_Deleted_But_Your_Account_is_Already_Deleted.toString()), HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(this.customExceptionService.generateEcxeption(
					ExceptionMessage.Cannot_Delete_Customer_Not_Found.toString()), HttpStatus.BAD_REQUEST);
		}
	}
}
