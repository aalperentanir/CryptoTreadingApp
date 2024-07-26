package com.aalperen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aalperen.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{

	Wallet findByUserId(Long userId);
}
