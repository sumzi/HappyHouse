package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.CommercialCodeDto;
import com.ssafy.happyhouse.model.CommercialDto;

@Mapper
public interface CommercialMapper {
	List<CommercialCodeDto> getCode1() throws SQLException;
	List<CommercialCodeDto> getCode2(String code1) throws SQLException;
	List<CommercialCodeDto> getCode3(String code2) throws SQLException;
	
	List<CommercialDto> getCommercialByGugun(CommercialCodeDto codes) throws SQLException;	
	List<CommercialDto> getCommercialByDong(CommercialCodeDto codes) throws SQLException;	
	List<CommercialDto> getCommercialByPos(CommercialCodeDto codes) throws SQLException;	
}
