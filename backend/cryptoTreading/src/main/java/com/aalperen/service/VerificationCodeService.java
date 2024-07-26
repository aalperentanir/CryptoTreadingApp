package com.aalperen.service;

import com.aalperen.entity.User;
import com.aalperen.entity.VerificationCode;
import com.aalperen.enums.VerificationType;

public interface VerificationCodeService {
	
	VerificationCode sendVerificationCode(User user, VerificationType verificationType);
	
	VerificationCode getVerificationCodeById(Long id) throws Exception;
	
	VerificationCode getVerificationCodeByUser(Long userId);
	
	void deleteVerificationCodeById(VerificationCode verificationCode);

}
