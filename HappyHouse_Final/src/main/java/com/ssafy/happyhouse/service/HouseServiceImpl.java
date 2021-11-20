//package com.ssafy.happyhouse.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ssafy.happyhouse.model.HouseInfoDto;
//import com.ssafy.happyhouse.model.HouseException;
//import com.ssafy.happyhouse.model.mapper.HouseMapper;
//
//@Service
//public class HouseServiceImpl implements HouseService{
//	
//	
//	@Autowired
//	private HouseMapper houseMapper;
//
////	@Override
////	public void register(HouseDto house) {
////		try {
////			houseMapper.register(house);
////		} catch (Exception e) {
////			e.printStackTrace();
////			throw new HouseException("House 생성 중 오류");
////		}
////	}
//
//	@Transactional
//	public List<HouseInfoDto> searchAll() {
//		try {
//			return houseMapper.searchAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new HouseException("House 목록 조회 중 오류");
//		}
//	}
//
//	@Transactional
//	public List<HouseInfoDto> searchByAptName(String aptName) {
//		try {
//			return houseMapper.searchByAptName(aptName);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new HouseException("House 아파트별 조회 중 오류");
//		}
//	}
//
//	@Transactional
//	public List<HouseInfoDto> searchByDong(String dong) {
//		try {
//			return houseMapper.searchByDong(dong);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new HouseException("House 동별 조회 중 오류");
//		}
//	}
//
////	@Override
////	public void updateAptName(int no, String aptName) {
////		try {
////			houseMapper.updateAptName(no, aptName);
////		} catch (Exception e) {
////			e.printStackTrace();
////			throw new HouseException("House 수정 중 오류");
////		}
////	}
////
////	@Override
////	public void delete(int no) {
////		try {
////			houseMapper.delete(no);
////		} catch (Exception e) {
////			e.printStackTrace();
////			throw new HouseException("House 삭제 중 오류");
////		}
////		
////	}
//
//	@Transactional
//	public HouseInfoDto searchByNo(String no) {
//		try {
//			return houseMapper.searchByNo(no);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new HouseException("House 거래 상세 조회 중 오류");
//		}
//	}
//
//}
