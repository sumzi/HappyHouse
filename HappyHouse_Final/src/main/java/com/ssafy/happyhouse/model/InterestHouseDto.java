package com.ssafy.happyhouse.model;

public class InterestHouseDto {
	private String userId;
	private String aptCode;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	@Override
	public String toString() {
		return "InterestHouseDto [userId=" + userId + ", aptCode=" + aptCode + "]";
	}
	
}
