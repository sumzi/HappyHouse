package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.HouseException;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.UserException;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Transactional
	public void insert(UserDto user)  {
		UserDto find = userMapper.search(user.getUserId());
		if(find !=null) throw new HouseException("이미 등록된 id입니다.");
		userMapper.insert(user);
	}

	@Transactional
	public void update(UserDto user)  {
		UserDto find = userMapper.search(user.getUserId());
		if(find ==null) throw new UserException("등록되지 않은 id입니다. 회원 정보를 수정할 수 없습니다.");
		userMapper.update(user);
	}

	@Transactional
	public void delete(String userId) {
		UserDto find = userMapper.search(userId);
		if(find ==null) throw new UserException("등록되지 않은 id입니다. 회원 정보를 삭제할 수 없습니다.");
		userMapper.delete(userId);
	}

	@Transactional
	public String findPass(String userId, String userEmail, String userName) {
		return userMapper.findPass(userId, userEmail, userName);
	}

	@Transactional
	public UserDto login(String userId, String userPw)  {
		UserDto user = userMapper.search(userId);
		if(user==null) throw new UserException("등록되지 않은 아이디입니다.");
		if(!userPw.equals(user.getUserPw())) throw new UserException("비밀번호 오류!!!!");
		return user;
	}

	@Override
	public UserDto search(String userId)  {
		return userMapper.search(userId);
	}

	@Override
	public List<UserDto> searchAll() {
		return userMapper.searchAll();
	}
}
