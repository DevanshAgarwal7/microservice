package com.nagarro.account.constant;

public class Constant {

	private static int MINIMUM_BALANCE = 1000;
	private static String NO_MINIMUM_BALANCE_STATEMENT = "FAILED, Your account doest not contains minimum balance after transaction.";
	private static String ACCOUNT_NOT_EXISTS = "Account does not exists.";
	private static String CUSTOMER_NOT_EXISTS = "Customer does not exists.";
	private static String NOT_ENOUGH_BALANCE = "Account does not have enough balance. Minimum balance 1000 rupees should maintained.";
	private static String PROVIDE_DETAILS = "Please provide details.";

	public static String getProvideDetailsMessage() {
		return PROVIDE_DETAILS;
	}
	public static String getNotEnoughBalanceMessage() {
		return NOT_ENOUGH_BALANCE;
	}

	public static String getCustomerNotExistsMessage() {
		return CUSTOMER_NOT_EXISTS;
	}

	public static String getAccountStatusMessage() {
		return ACCOUNT_NOT_EXISTS;
	}

	public static int getMinimumBalance() {
		return MINIMUM_BALANCE;
	}

	public static String getNoMinimumBalanceStatementMessage() {
		return NO_MINIMUM_BALANCE_STATEMENT;
	}

}
