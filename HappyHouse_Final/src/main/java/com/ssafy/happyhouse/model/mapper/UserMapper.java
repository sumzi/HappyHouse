package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.UserDto;

@Mapper
public interface UserMapper {
	void insert(UserDto user);

	void update(UserDto user);

	void delete(String id);

	String findPass(String id, String email, String name);

	UserDto login(String id, String pw);
	List<UserDto> searchAll();
	UserDto search(String id);
}
