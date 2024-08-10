package com.aalperen.service;

import org.json.JSONObject;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aalperen.entity.PaymentOrder;
import com.aalperen.entity.User;
import com.aalperen.enums.PaymentMethod;
import com.aalperen.enums.PaymentOrderStatus;
import com.aalperen.repository.PaymentOrderRepository;
import com.aalperen.response.PaymentResponse;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;





@Service
public class PaymentServiceImp implements PaymentService{

	@Autowired
	private PaymentOrderRepository paymentOrderRepository;
	
	@Value("${stripe.api.key}")
	private String stripeSecretKey;
	
	
	
	@Override
	public PaymentOrder createOrder(User user, Long amount, PaymentMethod method) {
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setUser(user);
		paymentOrder.setAmount(amount);
		paymentOrder.setPaymentMethod(method);
		paymentOrder.setStatus(PaymentOrderStatus.PENDING);
		
		return paymentOrderRepository.save(paymentOrder);
	}

	@Override
	public PaymentOrder getOrderById(Long id) throws Exception {
		
		return paymentOrderRepository.findById(id).orElseThrow(()->new Exception("Payment order not found"));
	}

	@Override
	public Boolean proccedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException {
		
		if(paymentOrder.getStatus()== null) {
			paymentOrder.setStatus(PaymentOrderStatus.PENDING);
		}
		if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {
			
			paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
			paymentOrderRepository.save(paymentOrder);
			return true;
			
		}
		return false;
	}
	
	
	@Override
	public PaymentResponse createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException {
		Stripe.apiKey = stripeSecretKey;
		
		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl("http://localhost:8081/wallet/?order_id=" + orderId + "&payment_id={CHECKOUT_SESSION_ID}")
				.setCancelUrl("http://localhost:8081/payment/cancel")
				.addLineItem(SessionCreateParams.LineItem.builder()
						.setQuantity(1L)
						.setPriceData(SessionCreateParams.LineItem.PriceData.builder()
								.setCurrency("usd").setUnitAmount(amount*100).setProductData(SessionCreateParams
										.LineItem
										.PriceData
										.ProductData
										.builder()
										.setName("Top up wallet")
										.build()
										
										).build()
								).build()
						).build();
		
		
		Session session = Session.create(params);
		System.out.println("SESSION: "+session);
		
		PaymentResponse res = new PaymentResponse();
		res.setPaymentUrl(session.getUrl());
		
		return res;
		
				
				
		
	}
	

	
	


	
	

}
