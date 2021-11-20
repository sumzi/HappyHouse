package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class HouseMapperServiceImpl implements HappyHouseMapService {
	
	@Autowired
	private SqlSession sqlSession;

	@Transactional
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Transactional
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Transactional
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Transactional
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}
	
	@Transactional
	public List<HouseInfoDto> getAptInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInGugun(gugun);
	}
	
	@Transactional
	public List<HouseInfoDto> getAptByName(String aptName) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptByName(aptName);
	}
	
	@Transactional
	public int getAptAvgPrice(List<HouseInfoDto> houseList) {
		int sum = 0;
		if( houseList.size() == 0)
			return 0;
		for(HouseInfoDto house : houseList) {
			String temp = house.getRecentPrice();
			if(temp == null)
				continue;
			sum += Integer.parseInt(house.getRecentPrice().trim().replace(",",""));
		}
		int avg = Math.round((float)(sum / houseList.size()));
		return avg;
	}

}
