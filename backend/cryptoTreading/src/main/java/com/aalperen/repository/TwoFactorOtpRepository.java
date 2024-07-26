package com.aalperen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aalperen.entity.TwoFactorOTP;

public interface TwoFactorOtpRepository extends JpaRepository<TwoFactorOTP, String>{
	
	TwoFactorOTP findByUserId(Long userId);

}
