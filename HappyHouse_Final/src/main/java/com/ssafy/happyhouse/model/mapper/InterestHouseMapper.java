package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.InterestHouseDto;


public interface InterestHouseMapper {
	void likeHouse(InterestHouseDto interestHouse) throws SQLException;
	void unlikeHouse(InterestHouseDto interestHouse) throws SQLException;
	
	InterestHouseDto search(InterestHouseDto interestHouse) throws SQLException;
	List<InterestHouseDto> searchIdAll(String userId) throws SQLException;
	List<InterestHouseDto> searchAll() throws SQLException;
}
