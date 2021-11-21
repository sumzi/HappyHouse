package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.PollutionDto;
import com.ssafy.happyhouse.model.mapper.PollutionMapper;

@Service
public class PollutionServiceImpl implements PollutionService {
	@Autowired
	private SqlSession sqlSession;
	
	@Transactional
	public List<PollutionDto> getPollutionByGugun(String gugun) throws Exception {
		return sqlSession.getMapper(PollutionMapper.class).getPollutionByGugun(gugun);
	}

	@Transactional
	public List<PollutionDto> getPollutionByDong(String dong) throws Exception {
		return sqlSession.getMapper(PollutionMapper.class).getPollutionByDong(dong);
	}

}
