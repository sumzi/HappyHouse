package com.ssafy.happyhouse.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.CommercialCodeDto;
import com.ssafy.happyhouse.model.CommercialDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.PollutionDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.service.CommercialService;
import com.ssafy.happyhouse.service.HappyHouseMapService;
import com.ssafy.happyhouse.service.PollutionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Api("Map 컨트롤러 API V1") // Controller에 대한 설명 Swagger Annotation
@RequestMapping("/map")
public class HappyHouseMapController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private HappyHouseMapService happyHouseMapService;
	@Autowired
	private CommercialService commercialService;
	
	@Autowired
	private PollutionService pollutionService;
	
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		logger.debug("ErrorHandler.............................");
		logger.debug("errorMassage............................."+e.getMessage());
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
	}

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(happyHouseMapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(happyHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@ApiOperation(value = "동 정보", notes = "전국의 동을 반환한다.", response = List.class)
	@GetMapping("/dong")
	ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(happyHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}

	@ApiOperation(value = "동 아파트 정보", notes = "동 내의 아파트를 반환한다.", response = Map.class)
	@GetMapping("/apt/dong")
	ResponseEntity<Map<String, Object>> aptInDong(@RequestParam("dong") String dong) throws Exception {
		Map<String, Object> responseMap = new HashMap<>();
		List<HouseInfoDto> houseList = happyHouseMapService.getAptInDong(dong);
		responseMap.put("houseList", houseList);
		int avg = happyHouseMapService.getAptAvgPrice(houseList);
		responseMap.put("avgPrice", avg);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@ApiOperation(value = "구군 아파트 정보", notes = "구군 내의 아파트를 반환한다.", response = Map.class)
	@GetMapping("/apt/gugun")
	ResponseEntity<Map<String, Object>> aptInGugun(@RequestParam("gugun") String gugun) throws Exception {
		Map<String, Object> responseMap = new HashMap<>();
		List<HouseInfoDto> houseList = happyHouseMapService.getAptInGugun(gugun);
		responseMap.put("houseList", houseList);
		int avg = happyHouseMapService.getAptAvgPrice(houseList);
		responseMap.put("avgPrice", avg);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@ApiOperation(value = "아파트 검색", notes = "아파트를 아파트명으로 검색한다.", response = Map.class)
	@GetMapping("/apt/name")
	ResponseEntity<Map<String, Object>> getAptByName(@RequestParam("aptName") String aptName) throws Exception {
		Map<String, Object> responseMap = new HashMap<>();
		List<HouseInfoDto> houseList = happyHouseMapService.getAptByName(aptName);
		responseMap.put("houseList", houseList);
		int avg = happyHouseMapService.getAptAvgPrice(houseList);
		responseMap.put("avgPrice", avg);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@ApiOperation(value = "아파트 코드 검색", notes = "아파트를 코드번호로 검색한다.", response = Map.class)
	@GetMapping("/apt/code")
	ResponseEntity<Map<String, Object>> getAptByCode(@RequestParam("aptCode") String aptCode) throws Exception {
		Map<String, Object> responseMap = new HashMap<>();
		List<HouseInfoDto> houseList = happyHouseMapService.getAptByCode(aptCode);
		responseMap.put("houseList", houseList);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	// TODO 아파트 정렬: 다르게 표현할 방법 약간 고려
	@GetMapping("/apt/dong/year")
	ResponseEntity<List<HouseInfoDto>> aptInDongYear(@RequestParam("dong") String dong) throws Exception {
		List<HouseInfoDto> lists = happyHouseMapService.getAptInDong(dong);
		Collections.sort(lists);
		return new ResponseEntity<List<HouseInfoDto>>(lists, HttpStatus.OK);
	}

	@GetMapping("/apt/gugun/year")
	ResponseEntity<List<HouseInfoDto>> aptInGugunYear(@RequestParam("gugun") String gugun) throws Exception {
		List<HouseInfoDto> lists = happyHouseMapService.getAptInGugun(gugun);
		Collections.sort(lists);
		return new ResponseEntity<List<HouseInfoDto>>(lists, HttpStatus.OK);
	}

	@GetMapping("/apt/name/year")
	ResponseEntity<List<HouseInfoDto>> getAptByNameYear(@RequestParam("aptName") String aptName) throws Exception {
		List<HouseInfoDto> lists = happyHouseMapService.getAptByName(aptName);
		Collections.sort(lists);
		return new ResponseEntity<List<HouseInfoDto>>(lists, HttpStatus.OK);
	}

	@ApiOperation(value = "동 상권 정보", notes = "동 내의 분류에 맞는 상권 정보 반환한다.", response = Map.class)
	@PostMapping("/commercial/dong")
	ResponseEntity<Map<String, Object>> commercialInDong(
			@RequestBody @ApiParam(value = "동코드와 분류코드가 담긴 정보", required = true) CommercialCodeDto codes)
			throws Exception {
		Map<String, Object> responseMap = new HashMap<>();
		List<CommercialDto> commercialList = commercialService.getCommercialByDong(codes);
		responseMap.put("commercialList", commercialList);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@ApiOperation(value = "구군 상권 정보", notes = "구군 내의 분류에 맞는 상권 정보 반환한다.", response = Map.class)
	@PostMapping("/commercial/gugun")
	ResponseEntity<Map<String, Object>> commercialInGugun(
			@RequestBody @ApiParam(value = "구코드와 분류코드가 담긴 정보", required = true) CommercialCodeDto codes)
			throws Exception {
		Map<String, Object> responseMap = new HashMap<>();
		List<CommercialDto> commercialList = commercialService.getCommercialByGugun(codes);
		responseMap.put("commercialList", commercialList);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "동 환경 정보", notes = "동 내의 분류에 맞는 상권 정보 반환한다.", response = Map.class)
	@GetMapping("/pollution/dong")
	ResponseEntity<Map<String, Object>> pollutionInDong(
			@RequestBody @ApiParam(value = "동코드", required = true) String dong)
			throws Exception {
		Map<String, Object> responseMap = new HashMap<>();
		List<PollutionDto> pollutionList = pollutionService.getPollutionByDong(dong);
		responseMap.put("pollutionList", pollutionList);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "동 환경 정보", notes = "동 내의 분류에 맞는 상권 정보 반환한다.", response = Map.class)
	@GetMapping("/pollution/gugun")
	ResponseEntity<Map<String, Object>> pollutionInGugun(
			@RequestBody @ApiParam(value = "동코드", required = true) String gugun)
			throws Exception {
		Map<String, Object> responseMap = new HashMap<>();
		List<PollutionDto> pollutionList = pollutionService.getPollutionByGugun(gugun);
		responseMap.put("pollutionList", pollutionList);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}


}
