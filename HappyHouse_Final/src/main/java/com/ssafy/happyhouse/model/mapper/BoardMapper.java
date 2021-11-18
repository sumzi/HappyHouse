package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.BoardDto;

@Mapper
public interface BoardMapper {
	void insert(BoardDto board);

	void update(BoardDto board);

	void delete(String no);

	BoardDto search(String no);
	List<BoardDto> searchAll();
	
	String lastInsertNo();
}
