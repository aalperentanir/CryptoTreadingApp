package com.aalperen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aalperen.entity.PaymentDetails;
import com.aalperen.entity.User;
import com.aalperen.repository.PaymentDetailsRepository;

@Service
public class PaymentDetailsServiceImp implements PaymentDetailsService{

	@Autowired
	private PaymentDetailsRepository paymentDetailsRepository;
	
	
	@Override
	public PaymentDetails addPaymentDetails(String accountNumber, String accountHolderName, String ifsc,
			String bankName, User user) {
		
		PaymentDetails paymentDetails = new PaymentDetails();
		
		paymentDetails.setAccountDumber(accountNumber);
		paymentDetails.setAccountHolderName(accountHolderName);
		paymentDetails.setBankName(bankName);
		paymentDetails.setIfsc(ifsc);
		paymentDetails.setUser(user);
		
		return paymentDetailsRepository.save(paymentDetails);
	}

	@Override
	public PaymentDetails getUserPaymentDetails(User user) {
		// TODO Auto-generated method stub
		return paymentDetailsRepository.findByUserId(user.getId());
	}

}
