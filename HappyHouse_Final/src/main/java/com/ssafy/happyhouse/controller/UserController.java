package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@CrossOrigin(origins = {"*"},maxAge = 6000)
@Api("User Controller API V1")  // Controller에 대한 설명 Swagger Annotation
@RequestMapping("/user")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService service;

	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		logger.debug("ErrorHandler.............................");
		logger.debug("errorMassage............................." + e.getMessage());
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
	}

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = service.login(user);
			if (loginUser != null) {
//				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
//				logger.debug("로그인 토큰정보 : {}", token);
//				resultMap.put("access-token", token);
				resultMap.put("user", loginUser);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원정보 조회", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
//		if (jwtService.isUsable(request.getHeader("access-token"))) {
		logger.info("사용 가능한 토큰!!!");
		try {
//				로그인 사용자 정보.
			UserDto user = service.userInfo(userid);
			resultMap.put("userInfo", user);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
//		} else {
//			logger.error("사용 불가능 토큰!!!");
//			resultMap.put("message", FAIL);
//			status = HttpStatus.ACCEPTED;
//		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "비밀번호 찾기", notes = "비밀번호를 찾는다.", response = Map.class)
	@PostMapping("/find")
	public ResponseEntity<Map<String, Object>> find(
			@ApiParam(value = "로그인 시 필요한 회원정보(아이디, 이메일 이름).", required = true) String userId, String userEmail,
			String userName) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			String pass = service.findPass(userId, userEmail, userName);
			resultMap.put("message", SUCCESS);
			resultMap.put("pass", pass);
			System.out.println(pass);
		} catch (Exception e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원가입", notes = "아이디, 비밀번호, 이름, 이메일, 전화번호를 받아 회원가입한다.", response = Map.class)
	@PostMapping("")
	public ResponseEntity<Map<String, Object>> regist(
			@RequestBody @ApiParam(value = "회원가입시 필요한 회원정보", required = true) UserDto user) {
		logger.debug("regist............................");
		logger.debug("user:{}", user);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			service.insert(user);
			resultMap.put("message", SUCCESS);
			System.out.println(user);
		} catch (Exception e) {
			logger.error("회원정보 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원정보 변경", notes = "비밀번호, 이름, 이메일, 전화번호를 업데이트 한다. (선택적 업데이트 가능)", response = Map.class)
	@PutMapping("")
	public ResponseEntity<Map<String, Object>> update(
			@RequestBody @ApiParam(value = "회원 정보 변경에 필요한 회원정보", required = true) UserDto user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			service.update(user);
			resultMap.put("message", SUCCESS);
			System.out.println(user);
		} catch (Exception e) {
			logger.error("회원정보 업데이트 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원정보 리스트 조회", notes = "관리자용 회원 리스트 조회 기능", response = Map.class)
	@PostMapping("/list")
	public ResponseEntity<Map<String, Object>> list() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
//			if (user.getRole().equals("admin")) {
			List<UserDto> list = service.searchAll();
			resultMap.put("userList", list);
			resultMap.put("message", SUCCESS);
//			} else {
//				resultMap.put("message", FAIL);
//				status = HttpStatus.ACCEPTED;
//			}
		} catch (Exception e) {
			logger.error("회원정보 업데이트 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원정보 삭제", notes = "회원정보 삭제 기능", response = Map.class)
	@DeleteMapping("/delete")
	public ResponseEntity<Map<String, Object>> delete(
			@RequestBody @ApiParam(value = "회원정보 삭제용 회원정보", required = true) UserDto user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			service.delete(user.getUserId());
			resultMap.put("userList", SUCCESS);
			resultMap.put("message", SUCCESS);

		} catch (Exception e) {
			logger.error("회원정보 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
