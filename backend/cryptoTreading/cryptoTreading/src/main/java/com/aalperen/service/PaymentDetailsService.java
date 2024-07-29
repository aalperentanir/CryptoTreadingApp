package com.aalperen.service;

import com.aalperen.entity.PaymentDetails;
import com.aalperen.entity.User;

public interface PaymentDetailsService {

	PaymentDetails addPaymentDetails(String accountNumber, String accountHolderName, String ifsc, String bankName, User user);
	
	PaymentDetails getUserPaymentDetails(User user);
	
}
