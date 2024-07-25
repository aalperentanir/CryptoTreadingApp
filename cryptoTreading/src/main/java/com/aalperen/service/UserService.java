package com.aalperen.service;

import com.aalperen.entity.User;
import com.aalperen.enums.VerificationType;

public interface UserService {

	User findUserProfileByJwt(String jwt) throws Exception;
	
	User findUserByEmail(String email) throws Exception;
	
	User findUserById(Long userId) throws Exception;
	
	User enableTwoFactorAuthentication(VerificationType verfiType,String sendTo, User user);
	
	User updatePassword(User user, String newPassword);	
}
