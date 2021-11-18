package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.HouseException;
import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.PageBean;
import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.mapper.HouseMapper;
import com.ssafy.happyhouse.model.mapper.QnAMapper;
import com.ssafy.happyhouse.model.mapper.QnAMapper;

@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	private QnAMapper qnaMapper;
	
	@Transactional
	public void insert(QnADto qna) {
		try {
			qnaMapper.insert(qna);
			qna.setNo(qnaMapper.lastInsertNo());
			qnaMapper.insertQnA(qna);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("qna 글 등록 중 오류");
		}
	}

	@Transactional
	public void update(QnADto qna) {
		try {
			qnaMapper.update(qna);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("qna 글 업데이트 중 오류");
		}
	}

	@Transactional
	public void delete(String no) {
		try {
			qnaMapper.delete(no);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("qna 글 삭제 중 오류");
		}
	}
	
	@Transactional
	public void reply(QnADto qna) {
		try {
			qnaMapper.reply(qna);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("qna 답변 업데이트 중 오류");
		}
	}

	@Transactional
	public void clearReply(String no) {
		try {
			qnaMapper.clearReply(no);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("qna 답변 삭제 중 오류");
		}
	}
	
	
	@Transactional
	public QnADto search(String no) {
		try {
			return qnaMapper.searchQnA(no);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("qna 글 검색 중 오류");
		}
	}

	@Transactional
	public List<QnADto> searchAll(PageBean bean) {
		try {
			return qnaMapper.searchAllQnA(bean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("qna 글 전체 검색 중 오류");
		}
	}




}
