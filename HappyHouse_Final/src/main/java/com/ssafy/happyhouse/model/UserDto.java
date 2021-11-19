package com.ssafy.happyhouse.model;

import java.io.Serializable;

public class UserDto implements Serializable {
	private String userId;
	private String userPw;
	private String userEmail;
	private String userPhone;
	private String userName;
	private String joinDate;
	private String role;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userPw=" + userPw + ", userEmail=" + userEmail + ", userPhone="
				+ userPhone + ", userName=" + userName + ", joinDate=" + joinDate + ", role=" + role + "]";
	}
}
