package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommercialCodeDto : 업종겅색 또는 검색조건 설정", description = "업종리스트를 받아오거나 검색조건을 설정하는데 사용함.")
public class CommercialCodeDto {
	@ApiModelProperty(value = "업종 대분류")
	private String code1;
	@ApiModelProperty(value = "대분류 이름")
	private String codename1;
	@ApiModelProperty(value = "업종 중분류")
	private String code2;
	@ApiModelProperty(value = "중분류 이름")
	private String codename2;
	@ApiModelProperty(value = "업종 소분류")
	private String code3;
	@ApiModelProperty(value = "소분류 이름")
	private String codename3;	
	
	@ApiModelProperty(value = "구코드")
	private String guguncode;
	
	@ApiModelProperty(value = "동코드")
	private String dongcode;
	
	@ApiModelProperty(value = "경도 좌표")
	private String lng;
	@ApiModelProperty(value = "위도 좌표")
	private String lat;
	@ApiModelProperty(value = "거리차이")
	private String dist;
	
	@ApiModelProperty(value = "사용 안해도 되는 것(선택사항)")
	private String codetype;

	public String getCode1() {
		return code1;
	}
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	public String getCodename1() {
		return codename1;
	}
	public void setCodename1(String codename1) {
		this.codename1 = codename1;
	}
	public String getCode2() {
		return code2;
	}
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public String getCodename2() {
		return codename2;
	}
	public void setCodename2(String codename2) {
		this.codename2 = codename2;
	}
	
	public String getCode3() {
		return code3;
	}
	public void setCode3(String code3) {
		this.code3 = code3;
	}
	public String getCodename3() {
		return codename3;
	}
	public void setCodename3(String codename3) {
		this.codename3 = codename3;
	}
	public String getCodetype() {
		return codetype;
	}
	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}
	
	public String getGuguncode() {
		return guguncode;
	}
	public void setGuguncode(String guguncode) {
		this.guguncode = guguncode;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
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
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	@Override
	public String toString() {
		return "CommercialCodeDto [code1=" + code1 + ", codename1=" + codename1 + ", code2=" + code2 + ", codename2="
				+ codename2 + ", code3=" + code3 + ", codename3=" + codename3 + ", guguncode=" + guguncode + ", dongcode="
				+ dongcode + ", lng=" + lng + ", lat=" + lat + ", dist=" + dist + ", codetype=" + codetype + "]";
	}
	
	
	
	
}
