package com.aalperen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.aalperen.entity.PaymentDetails;
import com.aalperen.entity.User;
import com.aalperen.service.PaymentDetailsService;
import com.aalperen.service.UserService;

@RestController
public class PaymentDetailsController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PaymentDetailsService paymentDetailsService;
	
	
	@PostMapping("/api/payment-details")
	public ResponseEntity<PaymentDetails> addPaymentDetails(@RequestHeader("Authorization") String jwt, @RequestBody PaymentDetails req) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		PaymentDetails paymentDetails = paymentDetailsService.addPaymentDetails(req.getAccountDumber(),
				                                                                req.getAccountHolderName(),
				                                                                req.getIfsc(),
				                                                                req.getBankName(),
				                                                                user
				                                                                );
		
		return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/api/payment-details")
	public ResponseEntity<PaymentDetails> getUserPaymentDetails(@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		PaymentDetails paymentDetails = paymentDetailsService.getUserPaymentDetails(user);
		
		return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
	}
	
}
