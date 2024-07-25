package com.aalperen.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aalperen.entity.ForgetPasswordToken;
import com.aalperen.entity.User;
import com.aalperen.entity.VerificationCode;
import com.aalperen.enums.VerificationType;
import com.aalperen.request.ForgetPasswordTokenRequest;
import com.aalperen.request.ResetPasswordRequest;
import com.aalperen.response.ApiResponse;
import com.aalperen.response.AuthResponse;
import com.aalperen.service.EmailService;
import com.aalperen.service.ForgetPasswordService;
import com.aalperen.service.UserService;
import com.aalperen.service.VerificationCodeService;
import com.aalperen.utils.OtpUtils;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private VerificationCodeService verificationCodeService;
	
	@Autowired
	private ForgetPasswordService forgetPasswordService;
	
	
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserProfileByJwt(jwt); 
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/verification/{verificationType}/send-otp")
	public ResponseEntity<String> sendVerificationOtp(@RequestHeader("Authorization") String jwt, @PathVariable VerificationType verificationType) throws Exception{
		User user = userService.findUserProfileByJwt(jwt); 
		
		VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user.getId());
		
		if(verificationCode == null) {
			verificationCode = verificationCodeService.sendVerificationCode(user, verificationType);
		}
		
		if(verificationType.equals(VerificationType.EMAIL)) {
			emailService.sendVerificationOtpEmail(user.getEmail(), verificationCode.getOtp());
		}
		
		
		return new ResponseEntity<>("Verification otp sent successfully", HttpStatus.OK);
	}
	
	@PatchMapping("/enable-two-factor/verify-otp/{otp}")
	public ResponseEntity<User> enableTwoFactorAuth(@PathVariable String otp, @RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserProfileByJwt(jwt); 
		
		VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user.getId());
		
		String sendTo = verificationCode.getVerificationType().equals(VerificationType.EMAIL)?
				      verificationCode.getEmail():verificationCode.getMobile();
		
		boolean isVerified = verificationCode.getOtp().equals(otp);
		
		if(isVerified) {
			User updatedUser = userService.enableTwoFactorAuthentication(verificationCode.getVerificationType(), sendTo, user);
			
			verificationCodeService.deleteVerificationCodeById(verificationCode);
			
			return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		}
		
		throw new Exception("Wrong otp");
	}
	
	
	@PostMapping("/auth/reset-password/send-otp")
	public ResponseEntity<AuthResponse> sendForgetPasswordOtp(String jwt, @RequestBody ForgetPasswordTokenRequest request) throws Exception{
		
		User user = userService.findUserByEmail(request.getSendTo());
		
		String otp = OtpUtils.generateOtp();
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		
		ForgetPasswordToken token = forgetPasswordService.findByUser(user.getId());
		
		if(token == null) {
			token = forgetPasswordService.createToken(user, id, otp, request.getVerificationType(), request.getSendTo());
		}
		
		if(request.getVerificationType().equals(VerificationType.EMAIL)) {
			emailService.sendVerificationOtpEmail(user.getEmail(), token.getOtp());
		}
		
		AuthResponse res = new AuthResponse();
		res.setSession(token.getId());
		res.setMessage("Password reset otp sent successfully");
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PatchMapping("/auth/reset-password/verify-otp")
	public ResponseEntity<ApiResponse> resetPassword(@RequestParam String id,@RequestBody ResetPasswordRequest req, @RequestHeader("Authorization") String jwt) throws Exception{
		
		ForgetPasswordToken token = forgetPasswordService.findById(id);
		
		boolean isVerified = token.getOtp().equals(req.getOtp());
		
		if(isVerified) {
			userService.updatePassword(token.getUser(), req.getPassword());
			ApiResponse res = new ApiResponse();
			res.setMessage("Password updated successfully");
			
			return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
		}
		throw new Exception("Wrong otp");

	}
	
	
}
