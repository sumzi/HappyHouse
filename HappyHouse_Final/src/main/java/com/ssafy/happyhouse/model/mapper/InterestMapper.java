package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.InterestAreaDto;
import com.ssafy.happyhouse.model.InterestHouseDto;

public interface InterestMapper {
	void likeHouse(InterestHouseDto interestHouse) throws SQLException;
	void unlikeHouse(InterestHouseDto interestHouse) throws SQLException;
	
	InterestHouseDto search(InterestHouseDto interestHouse) throws SQLException;
	List<String> searchIdAll(String userId) throws SQLException;
	List<InterestHouseDto> searchAll() throws SQLException;
	
	void insertArea(InterestAreaDto interestArea) throws SQLException;
	void updateArea(InterestAreaDto interestArea) throws SQLException;
	void deleteArea(String userId) throws SQLException;
	String searchArea(String userId) throws SQLException;
	
	List<String> searchHouseRank() throws SQLException;
	String searchAreaRank() throws SQLException;
	InterestAreaDto searchAreaDongCode(String dongCode) throws SQLException;
}
