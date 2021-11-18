package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.HouseException;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.PageBean;
import com.ssafy.happyhouse.model.mapper.HouseMapper;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Transactional
	public void insert(NoticeDto notice) {
		try {
			noticeMapper.insert(notice);
			notice.setNo(noticeMapper.lastInsertNo());
			noticeMapper.insertNotice(notice);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("notice 글 등록 중 오류");
		}
	}

	@Transactional
	public void update(NoticeDto notice) {
		try {
			noticeMapper.update(notice);
			noticeMapper.updateNotice(notice);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("notice 글 업데이트 중 오류");
		}
	}

	@Transactional
	public void delete(String no) {
		try {
			noticeMapper.delete(no);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("notice 글 삭제 중 오류");
		}
	}

	@Transactional
	public NoticeDto search(String no) {
		try {
			return noticeMapper.searchNotice(no);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("notice 글 검색 중 오류");
		}
	}

	@Transactional
	public List<NoticeDto> searchAll(PageBean bean) {
		try {
			return noticeMapper.searchAllNotice(bean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("notice 글 전체 검색 중 오류");
		}
	}


}
