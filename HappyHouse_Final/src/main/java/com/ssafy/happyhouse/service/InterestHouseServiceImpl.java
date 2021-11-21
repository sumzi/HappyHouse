package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.HouseException;
import com.ssafy.happyhouse.model.InterestHouseDto;
import com.ssafy.happyhouse.model.mapper.InterestHouseMapper;
@Service
public class InterestHouseServiceImpl implements InterestHouseService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Transactional
	public void likeHouse(InterestHouseDto interestHouse) throws Exception {
		InterestHouseDto find=sqlSession.getMapper(InterestHouseMapper.class).search(interestHouse);
		if(find!=null) {
			throw new HouseException("이미 존재한 찜");
		}
		sqlSession.getMapper(InterestHouseMapper.class).likeHouse(interestHouse);
	}

	@Transactional
	public void unlikeHouse(InterestHouseDto interestHouse) throws Exception {
		sqlSession.getMapper(InterestHouseMapper.class).unlikeHouse(interestHouse);
	}

	@Transactional
	public List<InterestHouseDto> searchAll() throws Exception {
		return sqlSession.getMapper(InterestHouseMapper.class).searchAll();
	}
	@Transactional
	public List<InterestHouseDto> searchIdAll(String userId) throws Exception {
		return sqlSession.getMapper(InterestHouseMapper.class).searchIdAll(userId);
	}

	@Transactional
	public InterestHouseDto search(InterestHouseDto interestHouse) throws Exception {
		return sqlSession.getMapper(InterestHouseMapper.class).search(interestHouse);
	}

}
