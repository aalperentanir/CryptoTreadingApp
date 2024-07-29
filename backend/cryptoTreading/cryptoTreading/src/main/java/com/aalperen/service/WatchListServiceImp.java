package com.aalperen.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aalperen.entity.Coin;
import com.aalperen.entity.User;
import com.aalperen.entity.WatchList;
import com.aalperen.repository.WatchListRepository;

@Service
public class WatchListServiceImp implements WatchListService{

	@Autowired
	private WatchListRepository watchListRepository;

	@Override
	public WatchList findUserWatchList(Long userId) throws Exception {
		WatchList watchList = watchListRepository.findByUserId(userId);
		
		if(watchList == null) {
			throw new Exception("WatchList not found");
		}
		return watchList;
	}

	@Override
	public WatchList createWatchList(User user) {
		WatchList watchList = new WatchList();
		watchList.setUser(user);
		
		return watchListRepository.save(watchList);
	}

	@Override
	public WatchList findById(Long id) throws Exception {
		Optional<WatchList> opt = watchListRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new Exception("WatchList not found");
		}
		return opt.get();
	}

	@Override
	public Coin addItemWatchlist(Coin coin, User user) throws Exception {
		
		WatchList watchList = findUserWatchList(user.getId());
		
		if(watchList.getCoins().contains(coin)) {
			watchList.getCoins().remove(coin);
		
		}
		else watchList.getCoins().add(coin);
		watchListRepository.save(watchList);
		
		return coin;
	}
}
