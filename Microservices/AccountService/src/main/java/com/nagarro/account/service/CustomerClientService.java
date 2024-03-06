package com.nagarro.account.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8081/", value = "CUSTOMER-SERVICE")
public interface CustomerClientService {

	@GetMapping(value = "customer/fetch")
	public ResponseEntity<Object> getCustomer(@RequestParam int customerId);
}
