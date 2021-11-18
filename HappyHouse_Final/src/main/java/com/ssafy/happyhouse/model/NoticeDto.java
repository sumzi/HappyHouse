package com.ssafy.happyhouse.model;

import java.io.Serializable;


public class NoticeDto extends BoardDto implements Serializable {
	private int likeCount;
	private String isReply;
	public NoticeDto(String no, String title, String content, String ndate, String userName, String type,
			int heartCount, String isReply) {
		super(no, title, content, ndate, userName, "notice");
		this.likeCount = heartCount;
		this.isReply = isReply;
	}

	
	public int getLikeCount() {
		return likeCount;
	}



	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}



	public String getIsReply() {
		return isReply;
	}



	public void setIsReply(String isReply) {
		this.isReply = isReply;
	}



	@Override
	public String toString() {
		return "[" + super.toString() + "NoticeDto: heartCount=" + likeCount + ", isReply=" + isReply + "]";
	}
}
