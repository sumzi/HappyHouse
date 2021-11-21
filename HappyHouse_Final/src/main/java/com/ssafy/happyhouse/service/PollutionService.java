package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.PollutionDto;

public interface PollutionService {
	List<PollutionDto> getPollutionByGugun(String gugun) throws Exception;	
	List<PollutionDto> getPollutionByDong(String dong) throws Exception;
}
