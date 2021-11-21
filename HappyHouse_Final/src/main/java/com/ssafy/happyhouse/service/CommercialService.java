package com.ssafy.happyhouse.service;


import java.util.List;

import com.ssafy.happyhouse.model.CommercialCodeDto;
import com.ssafy.happyhouse.model.CommercialDto;

public interface CommercialService {
	List<CommercialCodeDto> getCode1() throws Exception;
	List<CommercialCodeDto> getCode2(String code1) throws Exception;
	List<CommercialCodeDto> getCode3(String code2) throws Exception;

	List<CommercialDto> getCommercialByGugun(CommercialCodeDto codes) throws Exception;	
	List<CommercialDto> getCommercialByDong(CommercialCodeDto codes) throws Exception;	
	List<CommercialDto> getCommercialByPos(CommercialCodeDto codes) throws Exception;	
}
