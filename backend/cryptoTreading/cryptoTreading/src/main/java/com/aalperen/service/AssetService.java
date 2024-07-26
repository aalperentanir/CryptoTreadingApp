package com.aalperen.service;

import java.util.List;

import com.aalperen.entity.Asset;
import com.aalperen.entity.Coin;
import com.aalperen.entity.User;

public interface AssetService {
	
	Asset createAsset(User user, Coin coin, double quantity);
	
	Asset getAssetById(Long assetId) throws Exception;
	
	Asset getAssetByUserId(Long userId, Long assetId);
	
	List<Asset> getUsersAssets(Long userId);
	
	Asset updateAsset(Long assetId, double quantity) throws Exception;
	
	Asset findAssetByUserIdAndCoinId(Long userId, String coinId);
	
	void deleteAsset(Long assetId);
	

}
