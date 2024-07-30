package com.aalperen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aalperen.entity.PaymentOrder;
import com.aalperen.entity.User;
import com.aalperen.enums.PaymentMethod;
import com.aalperen.response.PaymentResponse;
import com.aalperen.service.PaymentService;
import com.aalperen.service.UserService;

@RestController
@RequestMapping("/api")
public class PaymentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/payment/{paymentMethod}/amount/{amount}")
	public ResponseEntity<PaymentResponse> paymentHandler(@PathVariable PaymentMethod paymentMethod, 
			@PathVariable Long amount, 
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		PaymentResponse paymentResponse;
		
		PaymentOrder order = paymentService.createOrder(user, amount, paymentMethod);
		
		if(paymentMethod.equals(PaymentMethod.RAZORPAY)) {
			paymentResponse = paymentService.createRazorpayPaymentLink(user, amount);
		
		}else {
			paymentResponse = paymentService.createStripePaymentLink(user, amount, order.getId());
		}
		
		return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
		
		
	}
	

}
