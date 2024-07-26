package com.aalperen.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aalperen.entity.TwoFactorOTP;
import com.aalperen.entity.User;
import com.aalperen.repository.TwoFactorOtpRepository;

@Service
public class TwoFactorOtpServiceImpl implements TwoFactorOtpService{
	
	@Autowired
	private TwoFactorOtpRepository twoFactorOtpRepository;

	@Override
	public TwoFactorOTP createTwoFactorOtp(User user, String otp, String jwt) {
		UUID uuid = UUID.randomUUID();
		
		String id = uuid.toString();
		
		TwoFactorOTP twoFactorOtp = new TwoFactorOTP();
		twoFactorOtp.setOtp(otp);
		twoFactorOtp.setJwt(jwt);
		twoFactorOtp.setId(id);
		twoFactorOtp.setUser(user);
		
		return twoFactorOtpRepository.save(twoFactorOtp);
	}

	@Override
	public TwoFactorOTP findByUser(Long userId) {
		
		return twoFactorOtpRepository.findByUserId(userId) ;
	}

	@Override
	public TwoFactorOTP findById(String id) {
		Optional<TwoFactorOTP> otp = twoFactorOtpRepository.findById(id);
		
		return otp.orElse(null);
	}

	@Override
	public boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP, String otp) {
		// TODO Auto-generated method stub
		return twoFactorOTP.getOtp().equals(otp);
	}

	@Override
	public void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP) {
		// TODO Auto-generated method stub
		twoFactorOtpRepository.delete(twoFactorOTP);
	}

}
