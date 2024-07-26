package com.aalperen.service;

import java.util.List;

import com.aalperen.entity.Coin;
import com.aalperen.entity.Order;
import com.aalperen.entity.OrderItem;
import com.aalperen.entity.User;
import com.aalperen.enums.OrderType;

public interface OrderService {
	
	Order createOrder(User user, OrderItem orderItem, OrderType orderType);
	
	Order getOrderById(Long orderId) throws Exception;
	
	List<Order> getAllOrdersOfUser(Long userId, String assetSymbol, OrderType orderType);
	
	Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception;
	
	

}
