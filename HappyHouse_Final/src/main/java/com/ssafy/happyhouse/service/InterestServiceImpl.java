package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.HouseException;
import com.ssafy.happyhouse.model.InterestAreaDto;
import com.ssafy.happyhouse.model.InterestHouseDto;
import com.ssafy.happyhouse.model.mapper.InterestMapper;

@Service
public class InterestServiceImpl implements InterestService {

	@Autowired
	private SqlSession sqlSession;

	@Transactional
	public void likeHouse(InterestHouseDto interestHouse) throws Exception {
		InterestHouseDto find = sqlSession.getMapper(InterestMapper.class).search(interestHouse);
		if (find != null) {
			throw new HouseException("이미 존재한 찜");
		}
		sqlSession.getMapper(InterestMapper.class).likeHouse(interestHouse);
	}

	@Transactional
	public void unlikeHouse(InterestHouseDto interestHouse) throws Exception {
		sqlSession.getMapper(InterestMapper.class).unlikeHouse(interestHouse);
	}

	@Transactional
	public List<InterestHouseDto> searchAll() throws Exception {
		return sqlSession.getMapper(InterestMapper.class).searchAll();
	}

	@Transactional
	public List<String> searchIdAll(String userId) throws Exception {
		return sqlSession.getMapper(InterestMapper.class).searchIdAll(userId);
	}

	@Transactional
	public InterestHouseDto search(InterestHouseDto interestHouse) throws Exception {
		return sqlSession.getMapper(InterestMapper.class).search(interestHouse);
	}

	@Transactional
	public void insertArea(InterestAreaDto interestArea) throws Exception {
		String find = sqlSession.getMapper(InterestMapper.class).searchArea(interestArea.getUserId());
		if (find != null) {
			throw new HouseException("이미 존재한 찜");
		}
		sqlSession.getMapper(InterestMapper.class).insertArea(interestArea);
	}

	@Transactional
	public void updateArea(InterestAreaDto interestArea) throws Exception {
		sqlSession.getMapper(InterestMapper.class).updateArea(interestArea);
	}

	@Transactional
	public void deleteArea(String userId) throws Exception {
		sqlSession.getMapper(InterestMapper.class).deleteArea(userId);
	}

	@Transactional
	public String searchArea(String userId) throws Exception {
		return sqlSession.getMapper(InterestMapper.class).searchArea(userId);
	}
}
