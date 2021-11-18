package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.HouseInfoDto;

@Mapper
public interface HouseMapper {
	
//	// insert
//		void register(HouseDto dealDto)   throws SQLException ;
		
		// select
		List<HouseInfoDto> searchAll() throws SQLException ;
		List<HouseInfoDto> searchByAptName(String aptName) throws SQLException ;
		List<HouseInfoDto> searchByDong(String dong) throws SQLException ;
		HouseInfoDto searchByNo(String no) throws SQLException ;
		
//		// update
//		void updateAptName(int no, String aptName)   throws SQLException ;
//		void updateApt(int no, HouseDto dto)   throws SQLException ;
//		
//		// delete
//		void delete(int no)   throws SQLException ;
		
		
}
