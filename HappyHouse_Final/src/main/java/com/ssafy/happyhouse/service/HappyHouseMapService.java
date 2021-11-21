package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;


public interface HappyHouseMapService {
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptByName(String aptName) throws Exception;
	List<HouseInfoDto> getAptByCode(String aptCode) throws Exception;
	int getAptAvgPrice(List<HouseInfoDto> houseList);
}
