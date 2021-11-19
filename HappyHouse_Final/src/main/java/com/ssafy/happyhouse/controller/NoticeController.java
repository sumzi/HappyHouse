package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.PageBean;
import com.ssafy.happyhouse.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = {"*"},maxAge = 6000)
@Api("공지사항 Controller API V1")  // Controller에 대한 설명 Swagger Annotation
@RequestMapping("/notice")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService service;
	private static final String SUCCESS="success";
	
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		logger.debug("ErrorHandler.............................");
		logger.debug("errorMassage............................."+e.getMessage());
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
	}
	
	@ApiOperation(value="공지사항 목록", notes = "공지사항을 검색 조건에 맞게 검색한 결과")
	@GetMapping("")
	public ResponseEntity<List<NoticeDto>> searchAll(PageBean bean) {
		logger.debug("searchAll............................");
		List<NoticeDto> notices = service.searchAll(bean);
		logger.debug("notices:{}",notices);
		if(notices!=null && !notices.isEmpty()) {
			return new ResponseEntity<List<NoticeDto>>(notices, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<NoticeDto>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="공지사항 정보 조회", notes = "공지사항 번호에 해당하는 공지 정보를 조회")
	@GetMapping("/{no}")
	public ResponseEntity<NoticeDto> search(@PathVariable String no){
		logger.debug("search............................");
		service.updateHit(no);
		NoticeDto notice = service.search(no);
		if(notice!=null ) {
			return new ResponseEntity<NoticeDto>(notice, HttpStatus.OK);
		}else {
			return new ResponseEntity<NoticeDto>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="공지 정보 등록", notes = "공지 정보를 전달받아 등록시킨다.")
	@PostMapping("")
	public ResponseEntity<String> regist(@RequestBody NoticeDto notice){
		logger.debug("regist............................");
		service.insert(notice);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="공지 정보 수정", notes = "공지 정보를 전달받아 수정시킨다.")
	@PutMapping("")
	public ResponseEntity<String> update(@RequestBody NoticeDto notice){
		logger.debug("update............................");
		service.update(notice);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	
	@ApiOperation(value="공지 정보 삭제", notes = "공지 번호에 해당하는 공지 정보를 삭제한다.")
	@DeleteMapping("/{no}")
	public ResponseEntity<String> remove(@PathVariable String no){
		logger.debug("remove............................");
		service.delete(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
