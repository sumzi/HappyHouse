package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.InterestAreaDto;
import com.ssafy.happyhouse.model.InterestHouseDto;

public interface InterestService {
	void likeHouse(InterestHouseDto interestHouse) throws Exception;
	void unlikeHouse(InterestHouseDto interestHouse) throws Exception;
	List<InterestHouseDto> searchAll() throws Exception;
	List<String> searchIdAll(String userId) throws Exception;
	InterestHouseDto search(InterestHouseDto interestHouse) throws Exception;
	
	
	void insertArea(InterestAreaDto interestArea) throws Exception;
	void updateArea(InterestAreaDto interestArea) throws Exception;
	void deleteArea(String userId) throws Exception;
	String searchArea(String userId) throws Exception;
	
	List<String> searchHouseRank() throws SQLException;
	String searchAreaRank() throws SQLException;
	
	InterestAreaDto searchAreaDongCode(String dongCode) throws SQLException;
}
