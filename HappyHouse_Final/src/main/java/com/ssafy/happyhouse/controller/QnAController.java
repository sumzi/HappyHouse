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

import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.PageBean;
import com.ssafy.happyhouse.service.QnAService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = {"*"},maxAge = 6000)
@Api("QnA Controller API V1")  // Controller에 대한 설명 Swagger Annotation
@RequestMapping("/qna")
public class QnAController {
	private static final Logger logger = LoggerFactory.getLogger(QnAController.class);
	
	@Autowired
	private QnAService service;
	private static final String SUCCESS="success";
	
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		logger.debug("ErrorHandler.............................");
		logger.debug("errorMassage............................."+e.getMessage());
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
	}
	
	@ApiOperation(value="QnA 목록", notes = "id와 일치하는 QnA를 검색한 결과")
	@GetMapping("")
	public ResponseEntity<List<QnADto>> searchAll(PageBean bean) {
		logger.debug("searchAll............................");
		List<QnADto> qnaList = service.searchAll(bean);
		logger.debug("qnaList:{}",qnaList);
		if(qnaList!=null && !qnaList.isEmpty()) {
			return new ResponseEntity<List<QnADto>>(qnaList, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<QnADto>>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	@ApiOperation(value="QnA 정보 조회", notes = "QnA 번호에 해당하는 QnA 정보를 조회")
	@GetMapping("/{no}")
	public ResponseEntity<QnADto> search(@PathVariable String no){
		logger.debug("search............................");
		QnADto qna = service.search(no);
		if(qna!=null ) {
			return new ResponseEntity<QnADto>(qna, HttpStatus.OK);
		}else {
			return new ResponseEntity<QnADto>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="QnA 정보 등록", notes = "QnA 정보를 전달받아 등록시킨다.")
	@PostMapping("")
	public ResponseEntity<String> regist(@RequestBody QnADto qna){
		logger.debug("regist............................");
		service.insert(qna);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="QnA 정보 수정", notes = "QnA 정보를 전달받아 수정시킨다.")
	@PutMapping("")
	public ResponseEntity<String> update(@RequestBody QnADto qna){
		logger.debug("update............................");
		service.update(qna);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	
	@ApiOperation(value="QnA 정보 삭제", notes = "QnA 번호에 해당하는 QnA 정보를 삭제한다.")
	@DeleteMapping("/{no}")
	public ResponseEntity<String> remove(@PathVariable String no){
		logger.debug("remove............................");
		service.delete(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	
	@ApiOperation(value="QnA 답변 업데이트", notes = "QnA 번호에 해당하는 QnA에 답변을 업데이트함.")
	@PutMapping("reply")
	public ResponseEntity<String> reply(@RequestBody QnADto qna){
		logger.debug("reply............................");
		service.reply(qna);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="QnA 답변 비우기", notes = "QnA 번호에 해당하는 답변 정보를 비웁니다. ")
	@PutMapping("reply/{no}")
	public ResponseEntity<String> clearReply(@PathVariable String no){
		logger.debug("clearReply............................");
		service.clearReply(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
