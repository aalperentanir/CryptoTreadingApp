package com.aalperen.request;

import com.aalperen.enums.VerificationType;

import lombok.Data;

@Data
public class ForgetPasswordTokenRequest {
	
	private String sendTo;
	
	private VerificationType verificationType;
	
	
}
