package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.PageBean;

@Mapper
public interface NoticeMapper extends BoardMapper {
	void insertNotice(NoticeDto notice) throws SQLException ;

	void updateNotice(NoticeDto notice) throws SQLException ;

	NoticeDto searchNotice(String no) throws SQLException ;
	List<NoticeDto> searchAllNotice(PageBean page) throws SQLException ;
	int totalCount(PageBean bean) throws SQLException ;
	
	void updateHit(String no) throws SQLException;
}
