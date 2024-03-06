package com.nagarro.account.service.impl;

import org.springframework.stereotype.Service;

import com.nagarro.account.exception.CustomException;
import com.nagarro.account.service.CustomExceptionInterface;

@Service
public class CustomExceptionImpl implements CustomExceptionInterface {

	@Override
	public CustomException generateException(String message) {
		return new CustomException(message);
	}

}
