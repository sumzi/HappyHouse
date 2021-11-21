package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.UserDto;

public interface UserService {

	void insert(UserDto user)  throws Exception;

	void update(UserDto user)  throws Exception;

	void delete(String userId) throws Exception;

	String findPass(UserDto user) throws Exception;

	UserDto login(UserDto user) throws Exception;
	
	UserDto userInfo(String userid) throws Exception;

	List<UserDto> searchAll() throws Exception;

	UserDto search(String id) throws Exception;
}
