package com.ssafy.happyhouse.model;

import java.io.Serializable;


public class NoticeDto extends BoardDto implements Serializable {
	private int hitCount;
	private String isReply;
	public NoticeDto(String no, String title, String content, String ndate, String userName, String type,
			int hitCount, String isReply) {
		super(no, title, content, ndate, userName, "notice");
		this.hitCount = hitCount;
		this.isReply = isReply;
	}

	
	public int getLikeCount() {
		return hitCount;
	}



	public void setLikeCount(int likeCount) {
		this.hitCount = likeCount;
	}



	public String getIsReply() {
		return isReply;
	}



	public void setIsReply(String isReply) {
		this.isReply = isReply;
	}



	@Override
	public String toString() {
		return "[" + super.toString() + "NoticeDto: heartCount=" + hitCount + ", isReply=" + isReply + "]";
	}
}
