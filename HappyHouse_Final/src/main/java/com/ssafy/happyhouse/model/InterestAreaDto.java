package com.ssafy.happyhouse.model;

public class InterestAreaDto {
	private String userId;
	private String dongCode;
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
	@Override
	public String toString() {
		return "InterestAreaDto [userId=" + userId + ", dongCode=" + dongCode + "]";
	}
	
	
	
}
