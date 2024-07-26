package com.aalperen.entity;

import java.time.LocalDate;

import com.aalperen.enums.WalletTransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class WalletTranscation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Wallet wallet;
	
	private WalletTransactionType type;
	
	private LocalDate date;
	
	private String transferId;
	
	private String purpose;
	
	private Long amount;
	
	
	
}
