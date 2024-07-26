package com.aalperen.service;

import com.aalperen.entity.TwoFactorOTP;
import com.aalperen.entity.User;

public interface TwoFactorOtpService {
	
	TwoFactorOTP createTwoFactorOtp(User user, String otp, String jwt);
		
	TwoFactorOTP findByUser(Long userId);
	
	TwoFactorOTP findById(String id);
	
	boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP, String otp);
	
	void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP);
	

}
