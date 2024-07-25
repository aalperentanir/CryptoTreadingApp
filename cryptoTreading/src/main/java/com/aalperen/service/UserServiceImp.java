package com.aalperen.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aalperen.config.JWTProvider;
import com.aalperen.entity.TwoFactorAuth;
import com.aalperen.entity.User;
import com.aalperen.enums.VerificationType;
import com.aalperen.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public User findUserProfileByJwt(String jwt) throws Exception {
		String email = JWTProvider.getEmailFromToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new Exception("User not found");
		}
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new Exception("User not found");
		}
		return user;
	}

	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isEmpty()) {
			throw new Exception("User not found");
		}
		return user.get();
	}

	@Override
	public User enableTwoFactorAuthentication(VerificationType verfiType, String sendTo, User user) {
		TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
		twoFactorAuth.setEnabled(true);
		twoFactorAuth.setSendTo(verfiType);
		
		user.setTwoFactorAuth(twoFactorAuth);
		
		return userRepository.save(user);
	}

	@Override
	public User updatePassword(User user, String newPassword) {
		user.setPassword(newPassword);
		
		return userRepository.save(user);
	}

}
