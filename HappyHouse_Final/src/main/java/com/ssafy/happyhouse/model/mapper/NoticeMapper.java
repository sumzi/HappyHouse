package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.PageBean;

@Mapper
public interface NoticeMapper extends BoardMapper {
	void insertNotice(NoticeDto notice);

	void updateNotice(NoticeDto notice);

	NoticeDto searchNotice(String no);
	List<NoticeDto> searchAllNotice(PageBean page);
	int totalCount(PageBean bean);
}
