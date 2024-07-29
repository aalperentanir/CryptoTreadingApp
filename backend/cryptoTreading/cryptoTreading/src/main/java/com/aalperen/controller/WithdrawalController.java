package com.aalperen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aalperen.entity.User;
import com.aalperen.entity.Wallet;
import com.aalperen.entity.WalletTranscation;
import com.aalperen.entity.Withdrawal;
import com.aalperen.service.UserService;
import com.aalperen.service.WalletService;
import com.aalperen.service.WithdrawalService;


@RestController
@RequestMapping
public class WithdrawalController {
	
	@Autowired
	private WithdrawalService withdrawalService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private UserService userService;
	
	
	//private WalletTranscationService walletTranscationService;
	
	
	@PostMapping("/api/withdrawal/{amount}")
	public ResponseEntity<?> withDrawalRequest(@PathVariable Long amount, 
			                                  @RequestHeader("Authorization") String jwt)throws Exception{
		User user = userService.findUserProfileByJwt(jwt);
		
		Wallet userWallet = walletService.getUserWallet(user);
		
		Withdrawal withdrawal = withdrawalService.requestWithdrawal(amount, user);
		
		walletService.addBalance(userWallet, -withdrawal.getAmount());
		
//		WalletTransaction walletTranscation = walletTranscationService.createTranscation(userWallet, WalletTranscationType.WITHDRAWAL, 
//				                                                                         null, "Bank account withdrawal",
//				                                                                         withdrawal.getAmount());
		
		return new ResponseEntity<>(withdrawal,HttpStatus.OK);
		
	}
	
	@GetMapping("/api/admin/withdrawal/{id}/proceed/{accept}")
	public ResponseEntity<?> proceedWitgdrawal(@PathVariable Long id, @PathVariable boolean accept, @RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Withdrawal withdrawal = withdrawalService.procedWithWithdrawal(id, accept);
		
		Wallet userWallet = walletService.getUserWallet(user);
		
		if(!accept) {
			walletService.addBalance(userWallet, withdrawal.getAmount());
		}
		
		return new ResponseEntity<>(withdrawal, HttpStatus.OK);
	}
	
	@GetMapping("/api/withdrawal")
	public ResponseEntity<List<Withdrawal>> getWithdrawalHistory(@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		List<Withdrawal> withdrawals = withdrawalService.getUserWithdrawalHistory(user);
		 
		return new ResponseEntity<>(withdrawals, HttpStatus.OK);
		
	}
	
	@GetMapping("/api/admin/withdrawal")
	public ResponseEntity<List<Withdrawal>> getAllWithdrawals(@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		List<Withdrawal> withdrawals = withdrawalService.getAllWithdrawalRequest();
		 
		return new ResponseEntity<>(withdrawals, HttpStatus.OK);
		
	}
	
	

}
