package com.aalperen.service;

import com.aalperen.entity.Order;
import com.aalperen.entity.User;
import com.aalperen.entity.Wallet;

public interface WalletService {
	
	Wallet getUserWallet(User user);
	
	Wallet addBalance(Wallet wallet, Long money);
	
	Wallet findWalletById(Long id) throws Exception;
	
	Wallet walletToWalletTransfer(User sender, Wallet receiverWallet, Long amount) throws Exception;
	
	Wallet payOrderPayment(Order order, User user) throws Exception;

}
