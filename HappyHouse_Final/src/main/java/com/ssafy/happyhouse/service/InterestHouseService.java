package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.InterestHouseDto;

public interface InterestHouseService {
	void likeHouse(InterestHouseDto interestHouse) throws Exception;
	void unlikeHouse(InterestHouseDto interestHouse) throws Exception;
	List<InterestHouseDto> searchAll() throws Exception;
	List<String> searchIdAll(String userId) throws Exception;
	InterestHouseDto search(InterestHouseDto interestHouse) throws Exception;
}
