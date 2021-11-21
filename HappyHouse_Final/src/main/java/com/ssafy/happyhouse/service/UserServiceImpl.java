package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.UserException;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Transactional
	public void insert(UserDto user) throws Exception {
		UserDto find = userMapper.search(user.getUserId());
		if (find != null)
			throw new UserException("이미 등록된 id입니다.");
		userMapper.insert(user);
	}

	@Transactional
	public void update(UserDto user) throws Exception {
		UserDto find = userMapper.search(user.getUserId());
		if (find == null)
			throw new UserException("등록되지 않은 id입니다. 회원 정보를 수정할 수 없습니다.");
		if (user.getUserPw() == null || user.getUserPw().isEmpty()) {
			user.setUserPw(find.getUserPw());
		}
		if (user.getUserName() == null || user.getUserName().isEmpty()) {
			user.setUserName(find.getUserName());
		}
		if (user.getUserEmail() == null || user.getUserEmail().isEmpty()) {
			user.setUserEmail(find.getUserEmail());
		}
		if (user.getUserPhone() == null || user.getUserPhone().isEmpty()) {
			user.setUserPhone(find.getUserPhone());
		}
		userMapper.update(user);
	}

	@Transactional
	public void delete(String userId) throws Exception {
		UserDto find = userMapper.search(userId);
		if (find == null)
			throw new UserException("등록되지 않은 id입니다. 회원 정보를 삭제할 수 없습니다.");
		userMapper.delete(userId);
	}

	@Transactional
	public String findPass(UserDto user) throws Exception {
		return userMapper.findPass(user);
	}

	@Transactional
	public UserDto login(UserDto user) throws Exception {
		if (user.getUserId() == null || user.getUserPw() == null) {
			throw new UserException("값을 제대로 입력해야 합니다.");
		}
		return userMapper.login(user);
	}

	@Transactional
	public UserDto userInfo(String userid) throws Exception {
		return userMapper.userInfo(userid);
	}

	@Transactional
	public UserDto search(String userId) throws Exception {
		return userMapper.search(userId);
	}

	@Transactional
	public List<UserDto> searchAll() throws Exception {
		return userMapper.searchAll();
	}
}
