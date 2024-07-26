package com.aalperen.entity;

import com.aalperen.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String email;
	
	private String fullName;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@Embedded
	private TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
	
	private Role role = Role.USER;
	
	
}
