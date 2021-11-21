package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.UserDto;

@Mapper
public interface UserMapper {
	void insert(UserDto user) throws SQLException ;

	void update(UserDto user) throws SQLException ;

	void delete(String id) throws SQLException ;

	String findPass(UserDto user) throws SQLException ;

	UserDto login(UserDto user) throws SQLException ;
	UserDto userInfo(String userid) throws SQLException;
	
	List<UserDto> searchAll() throws SQLException ;
	UserDto search(String id) throws SQLException ;
}
