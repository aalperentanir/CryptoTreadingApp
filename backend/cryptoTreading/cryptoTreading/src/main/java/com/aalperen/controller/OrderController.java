package com.aalperen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aalperen.entity.Coin;
import com.aalperen.entity.Order;
import com.aalperen.entity.User;
import com.aalperen.enums.OrderType;
import com.aalperen.request.CreateOrderRequest;
import com.aalperen.service.CoinService;
import com.aalperen.service.OrderService;
import com.aalperen.service.UserService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CoinService coinService;
	
//	@Autowired
//	private WalletTransactionService walletTransactionService;
	
	@PostMapping("/orders/pay")
	public ResponseEntity<Order> payOrderPayment(@RequestHeader("Authorization") String jwt,@RequestBody CreateOrderRequest req) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		Coin coin = coinService.findBYId(req.getCoinId());
		
		Order order = orderService.processOrder(coin, req.getQuantity(), req.getOrderType(), user);
		
		return ResponseEntity.ok(order);
	}
	
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Order> getOrderById(@RequestHeader("Authorization") String jwt, @PathVariable Long orderId) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Order order = orderService.getOrderById(orderId);
		
		if(order.getUser().getId().equals(user.getId())) {
			return ResponseEntity.ok(order);
			
		}else {
			throw new Exception("Token missing...");
		}
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrdersForUser(@RequestHeader("Authorization") String jwt, 
			                                               @RequestParam(required = false) OrderType orderType,
			                                               @RequestParam(required = false) String assetSymbol) throws Exception{
		
		Long userId = userService.findUserProfileByJwt(jwt).getId();
		
		List<Order> userOrders = orderService.getAllOrdersOfUser(userId, assetSymbol, orderType);
		
		return ResponseEntity.ok(userOrders);
	}

}
