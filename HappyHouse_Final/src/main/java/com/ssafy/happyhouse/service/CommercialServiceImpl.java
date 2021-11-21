package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.CommercialCodeDto;
import com.ssafy.happyhouse.model.CommercialDto;
import com.ssafy.happyhouse.model.mapper.CommercialMapper;

@Service
public class CommercialServiceImpl implements CommercialService {

	@Autowired
	private SqlSession sqlSession;
	@Transactional
	public List<CommercialCodeDto> getCode1() throws Exception {
		return sqlSession.getMapper(CommercialMapper.class).getCode1();
	}

	@Transactional
	public List<CommercialCodeDto> getCode2(String code1) throws Exception {
		return sqlSession.getMapper(CommercialMapper.class).getCode2(code1);
	}

	@Transactional
	public List<CommercialCodeDto> getCode3(String code2) throws Exception {
		 return sqlSession.getMapper(CommercialMapper.class).getCode2(code2);
	}

	@Transactional
	public List<CommercialDto> getCommercialByGugun(CommercialCodeDto codes) throws Exception {
		return sqlSession.getMapper(CommercialMapper.class).getCommercialByGugun(codes);
	}

	@Transactional
	public List<CommercialDto> getCommercialByDong(CommercialCodeDto codes) throws Exception {
		return sqlSession.getMapper(CommercialMapper.class).getCommercialByDong(codes);
	}

	//미완성
	@Transactional
	public List<CommercialDto> getCommercialByPos(CommercialCodeDto codes) throws Exception {
		return sqlSession.getMapper(CommercialMapper.class).getCommercialByPos(codes);
	}
}
