package com.aalperen.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aalperen.entity.ForgetPasswordToken;
import com.aalperen.entity.User;
import com.aalperen.enums.VerificationType;
import com.aalperen.repository.ForgetPasswordRepository;

@Service
public class ForgetPasswordServiceImp implements ForgetPasswordService{

	@Autowired
	private ForgetPasswordRepository forgetPasswordRepository;

	@Override
	public ForgetPasswordToken createToken(User user, String id, String otp, VerificationType verificationType,
			String sendTo) {
		ForgetPasswordToken token = new ForgetPasswordToken();
		token.setUser(user);
		token.setSendTo(sendTo);
		token.setVerificationType(verificationType);
		token.setOtp(otp);
		token.setId(id);
		
		
		return forgetPasswordRepository.save(token);
	}

	@Override
	public ForgetPasswordToken findById(String id) {
		Optional<ForgetPasswordToken> opt = forgetPasswordRepository.findById(id);

		return opt.orElse(null);
	}

	@Override
	public ForgetPasswordToken findByUser(Long userId) {
		// TODO Auto-generated method stub
		return forgetPasswordRepository.findByUserId(userId);
	}

	@Override
	public void deleteToken(ForgetPasswordToken token) {
		forgetPasswordRepository.delete(token);
		
	}
}
