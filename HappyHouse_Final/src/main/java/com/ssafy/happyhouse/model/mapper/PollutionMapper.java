package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.PollutionDto;

@Mapper
public interface PollutionMapper {
	List<PollutionDto> getPollutionByGugun(String gugun) throws SQLException;	
	List<PollutionDto> getPollutionByDong(String dong) throws SQLException;	
}
