package com.ssafy.happyhouse.model;

import java.io.Serializable;


public class BoardDto implements Serializable {
	private String no;
	private String title;
	private String content;
	private String ndate;
	private String userId;
	private String type;
	
	public BoardDto(String no, String title, String content, String ndate, String userName, String type) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.ndate = ndate;
		this.userId = userName;
		this.type = type;
	}
	
	public String getNo() {
		return no;
	}



	public void setNo(String no) {
		this.no = no;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getNdate() {
		return ndate;
	}



	public void setNdate(String ndate) {
		this.ndate = ndate;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	@Override
	public String toString() {
		return "BoardDto [no=" + no + ", title=" + title + ", content=" + content + ", ndate=" + ndate + ", userName="
				+ userId + ", type=" + type + "]";
	}
}
