package com.nagarro.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.customer.model.AccountModel;

@FeignClient(url = "http://localhost:8083/", value = "ACCOUNT-SERVICE")
public interface AccountClientService {

	@PostMapping(value = "account/create")
	public ResponseEntity<AccountModel> createAccount(@RequestParam int customerId);
	
	@DeleteMapping(value = "account/delete")
	public ResponseEntity<Object> deleteAccount(@RequestParam int customerId);
}
