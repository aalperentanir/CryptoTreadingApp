package com.aalperen.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aalperen.entity.Asset;
import com.aalperen.entity.Coin;
import com.aalperen.entity.Order;
import com.aalperen.entity.OrderItem;
import com.aalperen.entity.User;
import com.aalperen.enums.OrderStatus;
import com.aalperen.enums.OrderType;
import com.aalperen.repository.OrderItemRepository;
import com.aalperen.repository.OrderRepository;

@Service
public class OrderServiceImp implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AssetService assetService;
	

	@Override
	public Order createOrder(User user, OrderItem orderItem, OrderType orderType) {
		double price = orderItem.getCoin().getCurrentPrice()*orderItem.getQuantity();
		
		Order order = new Order();
		order.setUser(user);
		order.setOrderItem(orderItem);
		order.setOrderType(orderType);
		order.setPrice(BigDecimal.valueOf(price));
		order.setTimeStamp(LocalDateTime.now());
		order.setStatus(OrderStatus.PENDING);
		
		return orderRepository.save(order);
	}

	@Override
	public Order getOrderById(Long orderId) throws Exception {
		// TODO Auto-generated method stub
		return orderRepository.findById(orderId).orElseThrow(()-> new Exception("Order not found"));
	}

	@Override
	public List<Order> getAllOrdersOfUser(Long userId, String assetSymbol, OrderType orderType) {
		// TODO Auto-generated method stub
		return orderRepository.findByUserId(userId);
	}

	
	private OrderItem createOrderItem(Coin coin, double quantity, double buyPrice, double sellPrice) {
		OrderItem orderItem = new OrderItem();
		orderItem.setCoin(coin);
		orderItem.setBuyPrice(buyPrice);
		orderItem.setSellPrice(sellPrice);
		orderItem.setQuantity(quantity);
		
		return orderItemRepository.save(orderItem);
	}
	
	@Transactional
	public Order buyAsset(Coin coin, double quantity, User user) throws Exception {
	    if (quantity <= 0) {
	        throw new Exception("Quantity should be positive");
	    }
	    double buyPrice = coin.getCurrentPrice();
	    OrderItem orderItem = createOrderItem(coin, quantity, buyPrice, 0);

	    Order order = createOrder(user, orderItem, OrderType.BUY);
	    orderItem.setOrder(order);

	    walletService.payOrderPayment(order, user);

	    order.setStatus(OrderStatus.SUCCESS);
	    order.setOrderType(OrderType.BUY);

	    Order savedOrder = orderRepository.save(order);

	    Asset oldAsset = assetService.findAssetByUserIdAndCoinId(order.getUser().getId(), order.getOrderItem().getCoin().getId());

	    if (oldAsset == null) {
	        assetService.createAsset(user, orderItem.getCoin(), orderItem.getQuantity());
	    } else {
	        assetService.updateAsset(oldAsset.getId(), oldAsset.getQuantity() + quantity);
	    }

	    return savedOrder;
	}

	@Transactional
	public Order sellAsset(Coin coin, double quantity, User user) throws Exception {
	    if (quantity <= 0) {
	        throw new Exception("Quantity should be positive");
	    }
	    double sellPrice = coin.getCurrentPrice();

	    Asset assetToSell = assetService.findAssetByUserIdAndCoinId(user.getId(), coin.getId());

	    if (assetToSell == null) {
	        throw new Exception("Asset not found");
	    }

	    double buyPrice = assetToSell.getBuyPrice();

	    if (assetToSell.getQuantity() < quantity) {
	        throw new Exception("Insufficient quantity to sell");
	    }

	    OrderItem orderItem = createOrderItem(coin, quantity, buyPrice, sellPrice);

	    Order order = createOrder(user, orderItem, OrderType.SELL);
	    orderItem.setOrder(order);

	    walletService.payOrderPayment(order, user);

	    Order savedOrder = orderRepository.save(order);

	    assetService.updateAsset(assetToSell.getId(), assetToSell.getQuantity() - quantity);

	    if (assetToSell.getQuantity() <= 0) {
	        assetService.deleteAsset(assetToSell.getId());
	    }

	    return savedOrder;
	}

	
	
	@Override
	@Transactional
	public Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception {
		
		if(orderType.equals(OrderType.BUY)) {
			return buyAsset(coin, quantity, user);
			
		}else if(orderType.equals(OrderType.SELL)) {
			return sellAsset(coin, quantity, user);
		}
		 throw new Exception("Invalid order type");
	}
	

}
