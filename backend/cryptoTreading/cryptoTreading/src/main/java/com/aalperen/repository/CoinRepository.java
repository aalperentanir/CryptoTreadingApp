package com.aalperen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aalperen.entity.Coin;

public interface CoinRepository extends JpaRepository<Coin, String> {

}
