package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.InterestAreaDto;
import com.ssafy.happyhouse.model.InterestHouseDto;
import com.ssafy.happyhouse.service.InterestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = {"*"},maxAge = 6000)
@Api("InterestHouse Controller API V1")  // Controller에 대한 설명 Swagger Annotation
@RequestMapping("/interest")
public class InterestController {
	public static final Logger logger = LoggerFactory.getLogger(InterestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private InterestService service;
	
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		logger.debug("ErrorHandler.............................");
		logger.debug("errorMassage............................." + e.getMessage());
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
	}
	
	@ApiOperation(value="관심집 등록", notes = "아파트 정보를 전달받아 등록시킨다.")
	@PostMapping("/house/like")
	public ResponseEntity<Map<String, Object>> likeHouse(@RequestBody @ApiParam(value = "회원 찜 등록", required = true) InterestHouseDto interestHouse ){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			service.likeHouse(interestHouse);
			resultMap.put("message",SUCCESS);
		} catch (Exception e) {
			logger.error("회원찜 등록실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value="관심집 삭제", notes = "아파트 정보를 전달받아 삭제시킨다.")
	@DeleteMapping("/house/unlike")
	public ResponseEntity<Map<String, Object>> unlikeHouse(@RequestBody @ApiParam(value = "회원 찜 삭제", required = true) InterestHouseDto interestHouse ){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			service.unlikeHouse(interestHouse);
			resultMap.put("message",SUCCESS);
		} catch (Exception e) {
			logger.error("회원찜 삭제실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원찜 조회", notes = "회원 찜 목록을 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/house/search/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<String> list = service.searchIdAll(userId);
			resultMap.put("interestHouseList", list);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value="관심지역 등록", notes = "동코드를 전달받아 등록시킨다.")
	@PostMapping("/area/insert")
	public ResponseEntity<Map<String, Object>> insertArea(@RequestBody @ApiParam(value = "관심지역 등록", required = true) InterestAreaDto interestArea ){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			service.insertArea(interestArea);
			resultMap.put("message",SUCCESS);
		} catch (Exception e) {
			logger.error("관심지역 등록실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	@ApiOperation(value="관심지역 수정", notes = "동코드,회원아이디를 전달받아 수정시킨다.")
	@PostMapping("/area/update")
	public ResponseEntity<Map<String, Object>> updateArea(@RequestBody @ApiParam(value = "관심지역 수정", required = true) InterestAreaDto interestArea ){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			service.updateArea(interestArea);
			resultMap.put("message",SUCCESS);
		} catch (Exception e) {
			logger.error("관심지역 수정실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	@ApiOperation(value="관심지역 삭제", notes = "회원아이디를 전달받아 삭제시킨다.")
	@DeleteMapping("/area/delete/{userId}")
	public ResponseEntity<Map<String, Object>> deleteArea(@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			service.deleteArea(userId);
			resultMap.put("message",SUCCESS);
		} catch (Exception e) {
			logger.error("회원찜 삭제실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원 관심지역 조회", notes = "회원 관심지역 동코드를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/area/search/{userId}")
	public ResponseEntity<Map<String, Object>> searchArea(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			String area = service.searchArea(userId);
			resultMap.put("interestAreaDongCode", area);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
