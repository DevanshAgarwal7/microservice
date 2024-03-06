package com.nagarro.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.account.constant.AccountType;
import com.nagarro.account.constant.Constant;
import com.nagarro.account.convertor.Convertor;
import com.nagarro.account.entity.AccountEntity;
import com.nagarro.account.exception.NotEnoughBalanceException;
import com.nagarro.account.exception.ProvideDetailsException;
import com.nagarro.account.model.AccountModel;
import com.nagarro.account.model.CustomerAccountWrapperModel;
import com.nagarro.account.model.CustomerModel;
import com.nagarro.account.model.TransactionModel;
import com.nagarro.account.repository.AccountRepository;
import com.nagarro.account.service.AccountService;
import com.nagarro.account.service.CustomerClientService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerClientService customerClientService;

	@Override
	public AccountModel create(int customerId) {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setCustomerId(customerId);
		accountEntity.setAccountType(AccountType.SAVING.toString());
		accountEntity.setAmount(Constant.getMinimumBalance());
		accountEntity.setOpeningTime(CurrentDateTimeService.getCurrentDateTime());
		return Convertor.AccountEntityToAccountModel(this.accountRepository.save(accountEntity));
	}

	@Override
	public TransactionModel addMoneyToAccount(TransactionModel transactionModel) throws Exception {
		if (transactionModel.getAccountNumber() > 0 && transactionModel.getCustomerId() > 0 && transactionModel.getAmount() > 0) {
			AccountEntity accountEntity = this.accountRepository.findById(transactionModel.getAccountNumber())
					.orElseThrow();
			getCustomerDetails(transactionModel.getCustomerId());
			accountEntity.setAmount(accountEntity.getAmount() + transactionModel.getAmount());
			accountEntity.setLastTransactionTime(CurrentDateTimeService.getCurrentDateTime());
			accountEntity = this.accountRepository.save(accountEntity);
			return Convertor.AccountEntityToTransactionModel(accountEntity);
		} else {
			throw new ProvideDetailsException();
		}
	}

	@Override
	public TransactionModel withdrawMoneyFromoAccount(TransactionModel transactionModel) throws Exception {
		if (transactionModel.getAccountNumber() > 0 && transactionModel.getCustomerId() > 0 && transactionModel.getAmount() > 0) {
			AccountEntity accountEntity = this.accountRepository.findById(transactionModel.getAccountNumber())
					.orElseThrow();
			getCustomerDetails(transactionModel.getCustomerId());
			if (accountEntity.getAmount() >= transactionModel.getAmount() + Constant.getMinimumBalance()) {
				accountEntity.setAmount(accountEntity.getAmount() - transactionModel.getAmount());
				accountEntity.setLastTransactionTime(CurrentDateTimeService.getCurrentDateTime());
				accountEntity = this.accountRepository.save(accountEntity);
				return Convertor.AccountEntityToTransactionModel(accountEntity);
			} else {
				throw new NotEnoughBalanceException();
			}
		} else {
			throw new ProvideDetailsException();
		}
	}

	@Override
	public CustomerAccountWrapperModel fetchAccountDetails(TransactionModel transactionModel) throws Exception {
		if (transactionModel.getAccountNumber() > 0 && transactionModel.getCustomerId() > 0) {
			AccountEntity accountEntity = this.accountRepository.findById(transactionModel.getAccountNumber())
					.orElseThrow();
			ObjectMapper objectMapper = new ObjectMapper();
			CustomerModel customerModel = objectMapper.convertValue(
					getCustomerDetails(transactionModel.getCustomerId()), new TypeReference<CustomerModel>() {
					});
			return Convertor.generateCustomerAccountDetails(accountEntity, customerModel);
		} else {
			throw new ProvideDetailsException();
		}
	}

	@Override
	public AccountModel delete(int customerId) throws Exception {
		AccountEntity accountEntity = this.accountRepository.findByCustomerId(customerId);
		if (accountEntity == null) {
			throw new Exception();
		}
		this.accountRepository.deleteById(accountEntity.getAccountNumber());
		return Convertor.AccountEntityToAccountModel(accountEntity);
	}

	private Object getCustomerDetails(int customerId) throws Exception {
		return this.customerClientService.getCustomer(customerId).getBody();
	}

}
