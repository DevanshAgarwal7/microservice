package com.nagarro.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.account.constant.Constant;
import com.nagarro.account.exception.NotEnoughBalanceException;
import com.nagarro.account.exception.ProvideDetailsException;
import com.nagarro.account.model.AccountModel;
import com.nagarro.account.model.TransactionModel;
import com.nagarro.account.service.AccountService;
import com.nagarro.account.service.CustomExceptionInterface;

import feign.FeignException;

@RestController
@RequestMapping(value = "/account")
public class Controller {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CustomExceptionInterface customExceptionService;

	@PostMapping(value = "/create")
	public ResponseEntity<AccountModel> createAccount(@RequestParam int customerId) {
		return new ResponseEntity<AccountModel>(this.accountService.create(customerId), HttpStatus.CREATED);
	}

	@PostMapping(value = "/add/money")
	public ResponseEntity<Object> addMoney(@RequestBody TransactionModel transactionModel) {
		try {
			return new ResponseEntity<Object>(this.accountService.addMoneyToAccount(transactionModel), HttpStatus.OK);
		} catch (FeignException e) {
			return new ResponseEntity<Object>(
					this.customExceptionService.generateException(Constant.getCustomerNotExistsMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (ProvideDetailsException e) {
			return new ResponseEntity<Object>(
					this.customExceptionService.generateException(Constant.getProvideDetailsMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>(
					this.customExceptionService.generateException(Constant.getAccountStatusMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/withdraw/money")
	public ResponseEntity<Object> withdrawMoney(@RequestBody TransactionModel transactionModel) {
		try {
			return new ResponseEntity<Object>(this.accountService.withdrawMoneyFromoAccount(transactionModel),
					HttpStatus.OK);
		} catch (FeignException e) {
			return new ResponseEntity<Object>(
					this.customExceptionService.generateException(Constant.getCustomerNotExistsMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (ProvideDetailsException e) {
			return new ResponseEntity<Object>(
					this.customExceptionService.generateException(Constant.getProvideDetailsMessage()),
					HttpStatus.BAD_REQUEST);
		} catch(NotEnoughBalanceException e) {
			return new ResponseEntity<Object>(
					this.customExceptionService.generateException(Constant.getNotEnoughBalanceMessage()),
					HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<Object>(
					this.customExceptionService.generateException(Constant.getAccountStatusMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/fetch")
	public ResponseEntity<Object> accountDetail(@RequestBody TransactionModel transactionModel) {
		try {
			return new ResponseEntity<Object>(this.accountService.fetchAccountDetails(transactionModel), HttpStatus.OK);
		} catch (ProvideDetailsException e) {
			return new ResponseEntity<Object>(
					this.customExceptionService.generateException(Constant.getProvideDetailsMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (FeignException e) {
			return new ResponseEntity<Object>(
					this.customExceptionService.generateException(Constant.getCustomerNotExistsMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(
					this.customExceptionService.generateException(Constant.getAccountStatusMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Object> deleteAccount(@RequestParam int customerId) {
		try {
			return new ResponseEntity<Object>(this.accountService.delete(customerId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(
					this.customExceptionService.generateException(Constant.getAccountStatusMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
}
