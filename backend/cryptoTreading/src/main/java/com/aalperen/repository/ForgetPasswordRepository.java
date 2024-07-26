package com.aalperen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aalperen.entity.ForgetPasswordToken;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPasswordToken, String>{
	
	ForgetPasswordToken findByUserId(Long userId);
	
	

}
