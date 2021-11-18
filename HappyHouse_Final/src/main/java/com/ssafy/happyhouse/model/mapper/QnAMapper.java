package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.PageBean;
import com.ssafy.happyhouse.model.QnADto;

@Mapper
public interface QnAMapper extends BoardMapper {
	void insertQnA(QnADto qna);
	
	void reply(QnADto qna);
	void clearReply(String no);

	QnADto searchQnA(String no);
	List<QnADto> searchAllQnA(PageBean bean);
	int totalCount(PageBean bean);
}
