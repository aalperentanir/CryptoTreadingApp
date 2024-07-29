package com.aalperen.service;

import java.util.List;

import com.aalperen.entity.User;
import com.aalperen.entity.Withdrawal;

public interface WithdrawalService {

	Withdrawal requestWithdrawal(Long amount, User user);
	
	Withdrawal procedWithWithdrawal(Long withdrawalId, boolean accept) throws Exception;
	
	List<Withdrawal> getUserWithdrawalHistory(User user);
	
	List<Withdrawal> getAllWithdrawalRequest();
	
	
}
