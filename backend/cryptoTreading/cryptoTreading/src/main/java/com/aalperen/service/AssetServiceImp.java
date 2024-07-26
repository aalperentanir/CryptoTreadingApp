package com.aalperen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aalperen.entity.Asset;
import com.aalperen.entity.Coin;
import com.aalperen.entity.User;
import com.aalperen.repository.AssetRepository;

@Service
public class AssetServiceImp implements AssetService{
	
	@Autowired
	private AssetRepository assetRepository;

	@Override
	public Asset createAsset(User user, Coin coin, double quantity) {
		Asset asset = new Asset();
		asset.setUser(user);
		asset.setCoin(coin);
		asset.setQuantity(quantity);
		asset.setBuyPrice(coin.getCurrentPrice());
		
		
		return assetRepository.save(asset);
	}

	@Override
	public Asset getAssetById(Long assetId) throws Exception {
		// TODO Auto-generated method stub
		return assetRepository.findById(assetId).orElseThrow(()-> new Exception("Asset not found"));
	}

	@Override
	public Asset getAssetByUserId(Long userId, Long assetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Asset> getUsersAssets(Long userId) {
		// TODO Auto-generated method stub
		return assetRepository.findByUserId(userId);
	}

	@Override
	public Asset updateAsset(Long assetId, double quantity) throws Exception {
		
		Asset oldAsset = getAssetById(assetId);
		
		oldAsset.setQuantity(quantity)
		;
		return assetRepository.save(oldAsset);
	}
	
	@Override
	public Asset findAssetByUserIdAndCoinId(Long userId, String coinId) {
		// TODO Auto-generated method stub
		return assetRepository.findByUserIdAndCoinId(userId, coinId);
	}

	@Override
	public void deleteAsset(Long assetId) {
		
		assetRepository.deleteById(assetId);
		
	}


	
	
	

}
