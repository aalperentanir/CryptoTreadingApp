package com.aalperen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.aalperen.entity.Coin;
import com.aalperen.entity.User;
import com.aalperen.entity.WatchList;
import com.aalperen.service.CoinService;
import com.aalperen.service.UserService;
import com.aalperen.service.WatchListService;

@RestController
public class WatchListController {
	
	@Autowired
	private WatchListService watchListService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CoinService coinService;
	
	
	@GetMapping("/api/watchlist/user")
	public ResponseEntity<WatchList> getUserWatchList(@RequestHeader("Authorization")String jwt) throws Exception{
		User user = userService.findUserProfileByJwt(jwt);
		
		WatchList watchList = watchListService.findUserWatchList(user.getId());
		
		return ResponseEntity.ok(watchList);
	}
	
//	@PostMapping("/api/watchlist/create")
//	public ResponseEntity<WatchList> createWatchList(@RequestHeader("Authorization")String jwt) throws Exception{
//		User user = userService.findUserProfileByJwt(jwt);
//		
//		WatchList createdWatchList = watchListService.createWatchList(user);
//		
//		return ResponseEntity.status(HttpStatus.CREATED).body(createdWatchList);
//	}
	
	@GetMapping("/api/watchlist/{watchListId}")
	public ResponseEntity<WatchList> getWatchListById(@PathVariable Long watchListId) throws Exception{
		
		
		WatchList watchList = watchListService.findById(watchListId);
		
		return ResponseEntity.ok(watchList);
	}
	
	
	@PatchMapping("/api/watchlist/add/coin/{coinId}")
	public ResponseEntity<Coin> addItemToWatchList(@RequestHeader("Authorization")String jwt, String coinId) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Coin coin = coinService.findBYId(coinId);
		
		Coin addedCoin = watchListService.addItemWatchlist(coin, user);
		
		return ResponseEntity.ok(addedCoin);
	}
	
	

}
