package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.PageBean;
import com.ssafy.happyhouse.model.QnADto;


public interface QnAService {
	void insert(QnADto qna);
	void update(QnADto qna);
	void delete(String no);
	
	void reply(QnADto qna);
	void clearReply(String no);
	
	QnADto search(String no);
	List<QnADto> searchAll(PageBean bean);
}
