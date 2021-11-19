package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.PageBean;


public interface NoticeService {
	void insert(NoticeDto notice);
	void update(NoticeDto notice);
	void delete(String no);
	NoticeDto search(String no);
	List<NoticeDto> searchAll(PageBean bean);
	void updateHit(String no);
}
