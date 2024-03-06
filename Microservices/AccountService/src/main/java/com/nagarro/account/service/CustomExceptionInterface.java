package com.nagarro.account.service;

import com.nagarro.account.exception.CustomException;

public interface CustomExceptionInterface {

	CustomException generateException(String message);

}
