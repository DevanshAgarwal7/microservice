package com.nagarro.account.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateTimeService {

	public static String getCurrentDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
		Date date = new Date();
		return formatter.format(date);
	}
}
