package com.aalperen.service;

import com.aalperen.entity.ForgetPasswordToken;
import com.aalperen.entity.User;
import com.aalperen.enums.VerificationType;

public interface ForgetPasswordService {
	
	ForgetPasswordToken createToken(User user, String id, String otp, VerificationType verificationType, String sendTo);
	
	ForgetPasswordToken findById(String id);
	
	ForgetPasswordToken findByUser(Long userId);
	
	void deleteToken(ForgetPasswordToken token);
	

}
