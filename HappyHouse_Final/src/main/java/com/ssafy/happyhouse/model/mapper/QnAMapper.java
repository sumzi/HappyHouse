package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.PageBean;
import com.ssafy.happyhouse.model.QnADto;

@Mapper
public interface QnAMapper extends BoardMapper {
	void insertQnA(QnADto qna) throws SQLException ;
	
	void reply(QnADto qna) throws SQLException ;
	void clearReply(String no) throws SQLException ;

	QnADto searchQnA(String no) throws SQLException ;
	List<QnADto> searchAllQnA(PageBean bean) throws SQLException ;
	int totalCount(PageBean bean) throws SQLException ;
	
	void updateHit(String no) throws SQLException;
}
