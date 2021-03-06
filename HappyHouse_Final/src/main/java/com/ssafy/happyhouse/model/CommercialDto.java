package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "CommercialDto : 상권 가게 정보 dto", description = "상권의 가게 정보를 표현하는데 사용한다.")
public class CommercialDto {
	private String no;
	private String shopname;
	private String localname;
	private String code1;
	private String codename1;
	private String code2;
	private String codename2;
	private String code3;
	private String codename3;
	private String codename4;
	private String gucode;
	private String dongcode;
	private String address;
	private String jibuaddress;
	private String postcode;
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

	

	public String getCode3() {
		return code3;
	}


	public void setCode3(String code3) {
		this.code3 = code3;
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

	

	public String getCodename1() {
		return codename1;
	}


	public void setCodename1(String codename1) {
		this.codename1 = codename1;
	}


	public String getCodename2() {
		return codename2;
	}


	public void setCodename2(String codename2) {
		this.codename2 = codename2;
	}


	public String getCodename3() {
		return codename3;
	}


	public void setCodename3(String codename3) {
		this.codename3 = codename3;
	}
	
	


	public String getCodename4() {
		return codename4;
	}


	public void setCodename4(String codename4) {
		this.codename4 = codename4;
	}

	

	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	@Override
	public String toString() {
		return "CommercialDto [no=" + no + ", shopname=" + shopname + ", localname=" + localname + ", code1=" + code1
				+ ", codename1=" + codename1 + ", code2=" + code2 + ", codename2=" + codename2 + ", code3=" + code3
				+ ", codename3=" + codename3 + ", codename4=" + codename4 + ", gucode=" + gucode + ", dongcode="
				+ dongcode + ", address=" + address + ", jibuaddress=" + jibuaddress + ", postcode=" + postcode
				+ ", lat=" + lat + ", lng=" + lng + "]";
	}
	
}
