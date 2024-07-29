package com.aalperen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aalperen.entity.WatchList;

public interface WatchListRepository extends JpaRepository<WatchList, Long>{
	
	WatchList findByUserId(Long userId);
	

}
