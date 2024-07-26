package com.aalperen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aalperen.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
