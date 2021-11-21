package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.service.HappyHouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = {"*"},maxAge = 6000)
@Api("Map 컨트롤러 API V1")  // Controller에 대한 설명 Swagger Annotation
@RequestMapping("/map")
public class HappyHouseMapController {

	@Autowired
	private HappyHouseMapService happyHouseMapService;
	
	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception{
		return new ResponseEntity<List<SidoGugunCodeDto>>(happyHouseMapService.getSido(), HttpStatus.OK); 
	}
	
	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception{
		return new ResponseEntity<List<SidoGugunCodeDto>>(happyHouseMapService.getGugunInSido(sido), HttpStatus.OK); 
	}
	
	
	@ApiOperation(value = "동 정보", notes = "전국의 동을 반환한다.", response = List.class)
	@GetMapping("/dong")
	ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception{
		return new ResponseEntity<List<HouseInfoDto>>(happyHouseMapService.getDongInGugun(gugun), HttpStatus.OK); 
	}
	
	
	@ApiOperation(value = "동 아파트 정보", notes = "동 내의 아파트를 반환한다.", response = Map.class)
	@GetMapping("/apt/dong")
	ResponseEntity<Map<String, Object>> aptInDong(@RequestParam("dong") String dong) throws Exception{
		Map<String, Object> responseMap = new HashMap<>();
		List<HouseInfoDto> houseList = happyHouseMapService.getAptInDong(dong);
		responseMap.put("houseList", houseList);
		int avg = happyHouseMapService.getAptAvgPrice(houseList); 
		responseMap.put("avgPrice", avg);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK); 
	}
	
	@ApiOperation(value = "구군 아파트 정보", notes = "구군 내의 아파트를 반환한다.", response = Map.class)
	@GetMapping("/apt/gugun")
	ResponseEntity<Map<String, Object>> aptInGugun(@RequestParam("gugun") String gugun) throws Exception{
		Map<String, Object> responseMap = new HashMap<>();
		List<HouseInfoDto> houseList = happyHouseMapService.getAptInGugun(gugun);
		responseMap.put("houseList", houseList);
		int avg = happyHouseMapService.getAptAvgPrice(houseList); 
		responseMap.put("avgPrice", avg);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK); 
	}
	

	@ApiOperation(value = "아파트 검색", notes = "아파트를 아파트명으로 검색한다.", response = Map.class)
	@GetMapping("/apt/name")
	ResponseEntity<Map<String, Object>> getAptByName(@RequestParam("aptName") String aptName) throws Exception{
		Map<String, Object> responseMap = new HashMap<>();
		List<HouseInfoDto> houseList = happyHouseMapService.getAptByName(aptName);
		responseMap.put("houseList", houseList);
		int avg = happyHouseMapService.getAptAvgPrice(houseList); 
		responseMap.put("avgPrice", avg);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK); 
	}
	
	@ApiOperation(value = "아파트 코드 검색", notes = "아파트를 코드번호로 검색한다.", response = Map.class)
	@GetMapping("/apt/code")
	ResponseEntity<Map<String, Object>> getAptByCode(@RequestParam("aptCode") String aptCode) throws Exception{
		Map<String, Object> responseMap = new HashMap<>();
		List<HouseInfoDto> houseList = happyHouseMapService.getAptByCode(aptCode);
		responseMap.put("houseList", houseList);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK); 
	}
	
	// TODO 아파트 정렬: 다르게 표현할 방법 약간 고려
	@GetMapping("/apt/dong/year")
	ResponseEntity<List<HouseInfoDto>> aptInDongYear(@RequestParam("dong") String dong) throws Exception{
		List<HouseInfoDto> lists= happyHouseMapService.getAptInDong(dong);
		Collections.sort(lists);
		return new ResponseEntity<List<HouseInfoDto>>(lists, HttpStatus.OK);  
	}
	
	@GetMapping("/apt/gugun/year")
	ResponseEntity<List<HouseInfoDto>> aptInGugunYear(@RequestParam("gugun") String gugun) throws Exception{
		List<HouseInfoDto> lists= happyHouseMapService.getAptInGugun(gugun);
		Collections.sort(lists);
		return new ResponseEntity<List<HouseInfoDto>>(lists, HttpStatus.OK);  
	}
	
	@GetMapping("/apt/name/year")
	ResponseEntity<List<HouseInfoDto>> getAptByNameYear(@RequestParam("aptName") String aptName) throws Exception{
		List<HouseInfoDto> lists= happyHouseMapService.getAptByName(aptName);
		Collections.sort(lists);
		return new ResponseEntity<List<HouseInfoDto>>(lists, HttpStatus.OK);   
	}
	
}
