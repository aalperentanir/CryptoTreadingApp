package com.aalperen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aalperen.entity.PaymentOrder;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long>{

}
