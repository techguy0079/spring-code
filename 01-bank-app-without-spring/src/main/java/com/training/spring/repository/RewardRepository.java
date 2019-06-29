package com.training.spring.repository;

import java.sql.SQLException;
import java.util.List;

import com.training.spring.model.Reward;



public interface RewardRepository {
	
	public void addReward(Reward reward) throws SQLException;
	public int getTotalRewardAmount(Long accountNumber) throws SQLException;
	public List<Reward> getAllRewardsForAccount(Long accountNUmber)throws SQLException;
	
	

}
