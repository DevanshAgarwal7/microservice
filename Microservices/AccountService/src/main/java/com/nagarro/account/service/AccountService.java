package com.nagarro.account.service;

import com.nagarro.account.model.AccountModel;
import com.nagarro.account.model.CustomerAccountWrapperModel;
import com.nagarro.account.model.TransactionModel;

public interface AccountService {

	TransactionModel addMoneyToAccount(TransactionModel transactionModel) throws Exception;

	AccountModel create(int customerId);

	TransactionModel withdrawMoneyFromoAccount(TransactionModel transactionModel) throws Exception;

	CustomerAccountWrapperModel fetchAccountDetails(TransactionModel transactionModel) throws Exception;

	AccountModel delete(int customerId) throws Exception;

}
