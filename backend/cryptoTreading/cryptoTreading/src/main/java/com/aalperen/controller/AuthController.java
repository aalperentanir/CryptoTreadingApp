package com.aalperen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aalperen.config.JWTProvider;
import com.aalperen.entity.TwoFactorOTP;
import com.aalperen.entity.User;
import com.aalperen.repository.UserRepository;
import com.aalperen.response.AuthResponse;
import com.aalperen.service.CustomeUserDetailsService;
import com.aalperen.service.EmailService;
import com.aalperen.service.TwoFactorOtpService;
import com.aalperen.utils.OtpUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomeUserDetailsService userDetailsService;
	
	@Autowired
	private TwoFactorOtpService twoFactorOtpService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception{		
		
		User isEmailExist = userRepository.findByEmail(user.getEmail());
		
		if(isEmailExist != null) {
			throw new Exception("Email is already exist ");
			
		}
		
		User newUser = new User();
		
		newUser.setEmail(user.getEmail());
		newUser.setFullName(user.getFullName());
		newUser.setPassword(user.getPassword());
		
		User savedUser = userRepository.save(newUser);
		
		Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String jwt = JWTProvider.generateToken(auth);
		
		AuthResponse res = new AuthResponse();
		
		res.setJwt(jwt);
		res.setStatus(true);
		res.setMessage("Signup request success");
		
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception{		
		
		String username= user.getEmail();
		String password = user.getPassword();
		
		Authentication auth = authenticate(username, password);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String jwt = JWTProvider.generateToken(auth);
		
		User authUser = userRepository.findByEmail(username);
		
		if(user.getTwoFactorAuth().isEnabled()) {
			AuthResponse res = new AuthResponse();
			res.setMessage("Two factor auth is enabled");
			res.setTwoFactorAuthEnabled(true);
			
			String otp = OtpUtils.generateOtp();
			
			TwoFactorOTP oldTwoFactorOTP = twoFactorOtpService.findByUser(authUser.getId());
			
			if(oldTwoFactorOTP != null) {
				twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOTP);
				
			}
			
			TwoFactorOTP newTwoFactorOtp = twoFactorOtpService.createTwoFactorOtp(authUser, otp, jwt);
			
			emailService.sendVerificationOtpEmail(username, otp);
			
			res.setSession(newTwoFactorOtp.getId());
			
			return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		}
		
		AuthResponse res = new AuthResponse();
		
		res.setJwt(jwt);
		res.setStatus(true);
		res.setMessage("Signin request success");
		
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}

	private Authentication authenticate(String username, String password) {
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid username");
			
		}
		
		if(!password.equals(userDetails.getPassword())) {
			throw new BadCredentialsException("Wrong password");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
	}
	
	
	@PostMapping("/two-factor/otp/{otp}")
	public ResponseEntity<AuthResponse> verifySigninOtp(@PathVariable String otp, @RequestParam String id) throws Exception{
		
		TwoFactorOTP twoFactorOTP = twoFactorOtpService.findById(id);
		
		if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP, otp)) {
			AuthResponse res = new AuthResponse();
			res.setMessage("Two factor authentication verified");
			res.setTwoFactorAuthEnabled(true);
			res.setJwt(twoFactorOTP.getJwt());
			
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		throw new Exception("Invalid otp");
	}
}
