package com.aalperen.entity;

import com.aalperen.enums.VerificationType;

import lombok.Data;

@Data
public class TwoFactorAuth {

	private boolean isEnabled = false;
	
	private VerificationType sendTo;
	
}
