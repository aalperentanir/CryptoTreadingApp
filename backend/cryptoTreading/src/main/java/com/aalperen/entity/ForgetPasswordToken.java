package com.aalperen.entity;

import com.aalperen.enums.VerificationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class ForgetPasswordToken {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	@OneToOne
	private User user;
	
	private String otp;
	
	private VerificationType verificationType;
	
	private String sendTo;
	

}
