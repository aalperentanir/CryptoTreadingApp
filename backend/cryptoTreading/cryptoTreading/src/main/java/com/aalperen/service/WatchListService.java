package com.aalperen.service;

import com.aalperen.entity.Coin;
import com.aalperen.entity.User;
import com.aalperen.entity.WatchList;

public interface WatchListService {
	
	WatchList findUserWatchList(Long userId) throws Exception;
	
	WatchList createWatchList(User user);
	
	WatchList findById(Long id) throws Exception;
	
	Coin addItemWatchlist(Coin coin, User user) throws Exception;
	

}
