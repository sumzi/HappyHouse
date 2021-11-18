package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.UserDto;

public interface UserService {

	void insert(UserDto user);

	void update(UserDto user);

	void delete(String userId);

	String findPass(String userId, String userEmail, String userName);

	UserDto login(String userId, String userPw);

	List<UserDto> searchAll();

	UserDto search(String id);
}
