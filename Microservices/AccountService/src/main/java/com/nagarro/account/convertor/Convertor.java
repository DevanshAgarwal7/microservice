package com.nagarro.account.convertor;

import com.nagarro.account.entity.AccountEntity;
import com.nagarro.account.model.AccountModel;
import com.nagarro.account.model.CustomerModel;
import com.nagarro.account.model.TransactionModel;
import com.nagarro.account.model.CustomerAccountWrapperModel;

public class Convertor {

	public static TransactionModel AccountEntityToTransactionModel(AccountEntity accountEntity) {
		TransactionModel transactionModel = new TransactionModel();
		transactionModel.setAccountNumber(accountEntity.getAccountNumber());
		transactionModel.setCustomerId(accountEntity.getCustomerId());
		transactionModel.setAmount(accountEntity.getAmount());

		return transactionModel;
	}

	public static AccountModel AccountEntityToAccountModel(AccountEntity accountEntity) {
		AccountModel accountModel = new AccountModel();
		accountModel.setAccountNumber(accountEntity.getAccountNumber());
		accountModel.setCustomerId(accountEntity.getCustomerId());
		accountModel.setAccountType(accountEntity.getAccountType());
		accountModel.setAmount(accountEntity.getAmount());
		accountModel.setOpeningTime(accountEntity.getOpeningTime());
		accountModel.setLastTransactionTime(accountEntity.getLastTransactionTime());
		return accountModel;

	}

	public static CustomerAccountWrapperModel generateCustomerAccountDetails(AccountEntity accountEntity, CustomerModel customerModel) {
		CustomerAccountWrapperModel customerAccount = new CustomerAccountWrapperModel();
		customerAccount.setAccountNumber(accountEntity.getAccountNumber());
		customerAccount.setCustomerId(customerModel.getCustomerId());
		customerAccount.setFirstName(customerModel.getFirstName());
		customerAccount.setLastName(customerModel.getLastName());
		customerAccount.setAddress(customerModel.getAddress());
		customerAccount.setEmail(customerModel.getEmail());
		customerAccount.setPhoneNumber(customerModel.getPhoneNumber());
		customerAccount.setAccountType(accountEntity.getAccountType());
		customerAccount.setAmount(accountEntity.getAmount());
		customerAccount.setOpeningTime(accountEntity.getOpeningTime());
		
		return customerAccount;
	}

}
