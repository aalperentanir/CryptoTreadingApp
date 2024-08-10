package com.aalperen.service;

import com.aalperen.entity.PaymentOrder;
import com.aalperen.entity.User;
import com.aalperen.enums.PaymentMethod;
import com.aalperen.response.PaymentResponse;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {
	
	PaymentOrder createOrder(User user, Long amount, PaymentMethod method);
	
	PaymentOrder getOrderById(Long id) throws Exception;
	
	Boolean proccedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException;
	
	PaymentResponse createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException;
	

}
