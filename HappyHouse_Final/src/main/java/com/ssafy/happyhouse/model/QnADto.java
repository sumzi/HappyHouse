package com.ssafy.happyhouse.model;

import java.io.Serializable;


public class QnADto extends BoardDto implements Serializable{
	String reply;

	public QnADto(String no, String title, String content, String ndate, String userName, String type, String reply) {
		super(no, title, content, ndate, userName, "qna");
		this.reply = reply;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "[" + super.toString() + "QnADto: reply=" + reply + "]";
	}	
}
