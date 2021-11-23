package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "PollutionDto : 환경 정보 dto", description = "환경 정보를 표현하는데 사용한다.")
public class PollutionDto {
	private String no;
	private String name;
	private String dongcode;
	private String sidoName;
	private String gugunName;
	
	private String bizcode;
	
	private String lng;
	private String lat;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getBizcode() {
		return bizcode;
	}
	public void setBizcode(String bizcode) {
		this.bizcode = bizcode;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	@Override
	public String toString() {
		return "PollutionDto [no=" + no + ", name=" + name + ", dongcode=" + dongcode + ", sidoName=" + sidoName
				+ ", gugunName=" + gugunName + ", bizcode=" + bizcode + ", lng=" + lng + ", lat=" + lat + "]";
	}
		
}
