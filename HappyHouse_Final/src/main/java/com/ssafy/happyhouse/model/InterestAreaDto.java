package com.ssafy.happyhouse.model;

public class InterestAreaDto {
	private String userId;
	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	@Override
	public String toString() {
		return "InterestAreaDto [userId=" + userId + ", dongCode=" + dongCode + ", sidoName=" + sidoName
				+ ", gugunName=" + gugunName + ", dongName=" + dongName + "]";
	}
	
	
	
}
