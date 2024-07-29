package com.aalperen.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aalperen.entity.User;
import com.aalperen.entity.Withdrawal;
import com.aalperen.enums.WithdrawalStatus;
import com.aalperen.repository.WithdrawalRepository;

@Service
public class WithdrawalServiceImp implements WithdrawalService{
	
	@Autowired
	private WithdrawalRepository withdrawalRepository;

	@Override
	public Withdrawal requestWithdrawal(Long amount, User user) {
		Withdrawal withdrawal = new Withdrawal();
		withdrawal.setAmount(amount);
		withdrawal.setUser(user);
		withdrawal.setStatus(WithdrawalStatus.PENDING);
		
		
		return withdrawalRepository.save(withdrawal);
	}

	@Override
	public Withdrawal procedWithWithdrawal(Long withdrawalId, boolean accept) throws Exception {
		Optional<Withdrawal> opt = withdrawalRepository.findById(withdrawalId);
		
		if(opt.isEmpty()) {
			throw new Exception("Withdrawal not found");
		}
		
		Withdrawal withdrawal1 = opt.get();
		withdrawal1.setDate(LocalDateTime.now());
		
		if(accept) {
			withdrawal1.setStatus(WithdrawalStatus.SUCCESS);
		
		}else {
			withdrawal1.setStatus(WithdrawalStatus.PENDING);
		}
		return withdrawalRepository.save(withdrawal1);
	}

	@Override
	public List<Withdrawal> getUserWithdrawalHistory(User user) {
		// TODO Auto-generated method stub
		return withdrawalRepository.findByUserId(user.getId());
	}

	@Override
	public List<Withdrawal> getAllWithdrawalRequest() {
		// TODO Auto-generated method stub
		return withdrawalRepository.findAll();
	}

}
