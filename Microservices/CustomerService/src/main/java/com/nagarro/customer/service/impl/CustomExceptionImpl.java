package com.nagarro.customer.service.impl;

import org.springframework.stereotype.Service;

import com.nagarro.customer.exception.CustomException;
import com.nagarro.customer.service.CustomExceptionInterface;

@Service
public class CustomExceptionImpl implements CustomExceptionInterface {

	@Override
	public CustomException generateEcxeption(String message) {
		return new CustomException(message);
	}

}
