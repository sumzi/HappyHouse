package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "CommercialDto : 상권 가게 정보 dto", description = "상권의 가게 정보를 표현하는데 사용한다.")
public class CommercialDto {
	private String no;
	private String shopname;
	private String localname;
	private String code1;
	private String code2;
	private String gucode;
	private String dongcode;
	private String address;
	private String jibuaddress;
	private String lat; 
	private String lng;
	
//	public CommercialDto(String no, String shopname, String code1, String code2, String gucode, String dongcode,
//			String address, String jibuaddress, String lat, String lng) {
//		super();
//		this.no = no;
//		this.shopname = shopname;
//		this.code1 = code1;
//		this.code2 = code2;
//		this.gucode = gucode;
//		this.dongcode = dongcode;
//		this.address = address;
//		this.jibuaddress = jibuaddress;
//		this.lat = lat;
//		this.lng = lng;
//	}
	

	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getShopname() {
		return shopname;
	}


	public void setShopname(String shopname) {
		this.shopname = shopname;
	}


	public String getLocalname() {
		return localname;
	}


	public void setLocalname(String localname) {
		this.localname = localname;
	}


	public String getCode1() {
		return code1;
	}


	public void setCode1(String code1) {
		this.code1 = code1;
	}


	public String getCode2() {
		return code2;
	}


	public void setCode2(String code2) {
		this.code2 = code2;
	}


	public String getGucode() {
		return gucode;
	}


	public void setGucode(String gucode) {
		this.gucode = gucode;
	}


	public String getDongcode() {
		return dongcode;
	}


	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getJibuaddress() {
		return jibuaddress;
	}


	public void setJibuaddress(String jibuaddress) {
		this.jibuaddress = jibuaddress;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}


	public String getLng() {
		return lng;
	}


	public void setLng(String lng) {
		this.lng = lng;
	}


	@Override
	public String toString() {
		return "CommercialDto [no=" + no + ", shopname=" + shopname + ", localname=" + localname + ", code1=" + code1
				+ ", code2=" + code2 + ", gucode=" + gucode + ", dongcode=" + dongcode + ", address=" + address
				+ ", jibuaddress=" + jibuaddress + ", lat=" + lat + ", lng=" + lng + "]";
	}	
	
}
