package com.aalperen.request;

import com.aalperen.enums.OrderType;

import lombok.Data;

@Data
public class CreateOrderRequest {
	
	private String coinId;
	
	private double quantity;
	
	private OrderType orderType;

}
