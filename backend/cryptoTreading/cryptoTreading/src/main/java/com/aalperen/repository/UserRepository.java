package com.aalperen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aalperen.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	User findByEmail(String email);
	
	
}
