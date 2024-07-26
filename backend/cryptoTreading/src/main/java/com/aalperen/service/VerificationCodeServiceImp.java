package com.aalperen.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aalperen.entity.User;
import com.aalperen.entity.VerificationCode;
import com.aalperen.enums.VerificationType;
import com.aalperen.repository.VerificationCodeRepository;
import com.aalperen.utils.OtpUtils;

@Service
public class VerificationCodeServiceImp implements VerificationCodeService{

	@Autowired
	private VerificationCodeRepository verificationCodeRepository;
	
	
	@Override
	public VerificationCode sendVerificationCode(User user, VerificationType verificationType) {
		VerificationCode verificationCode = new VerificationCode();
		verificationCode.setOtp(OtpUtils.generateOtp());
		verificationCode.setVerificationType(verificationType);
		verificationCode.setUser(user);
		
		return verificationCodeRepository.save(verificationCode);
	}

	@Override
	public VerificationCode getVerificationCodeById(Long id) throws Exception {
		Optional<VerificationCode> opt = verificationCodeRepository.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("Verification Code not found");
	}

	@Override
	public VerificationCode getVerificationCodeByUser(Long userId) {
		// TODO Auto-generated method stub
		return verificationCodeRepository.findByUserId(userId);
	}

	@Override
	public void deleteVerificationCodeById(VerificationCode verificationCode) {
		verificationCodeRepository.delete(verificationCode);
		
	}

}
