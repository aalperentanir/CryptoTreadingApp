package com.aalperen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aalperen.entity.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long>{
	
	VerificationCode findByUserId(Long userId);
	

}
