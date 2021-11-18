package com.ssafy.happyhouse.model;

import java.io.Serializable;
import java.util.Arrays;

public class PageBean implements Serializable {
	private String 	pageNo="1";
	/**한 페이지에 표시할 데이타 수*/
	private int 	spp = 20;
	/**한 페이지에 표시할 시작 번호*/
	private int 	start=0;
	/**한 페이지에 표시할 끝 번호 */
	private int 	end=spp;
	private String 	pagelink;
	private String 	pageQuery;
	private String  key;
	private String  word;
	public PageBean() {}
	public PageBean(String pageNo, String key, String word) {
		setPageNo(pageNo);
		setKey(key);
		setWord(word);
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		if(key==null) {
			key="all";
		}else {
			this.key = key;
		}
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		if(word==null) {
			word="";
		}else {
			this.word = word;
		}
	}
	public String getPageQuery() {
		return new StringBuilder(50)
		.append("pageNo=").append(pageNo)
		.append("&word=").append(word)
		.append("&key=").append(key).toString();
	}
	public void setPageQuery(String pageQuery) {
		this.pageQuery = pageQuery;
	}
	public String getPagelink() {
		return pagelink;
	}
	public void setPagelink(String pagelink) {
		this.pagelink = pagelink;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		if(pageNo==null) {
			this.pageNo="1";
		}else {
			this.pageNo = pageNo;
		}
	}
	public int getSpp() {
		return spp;
	}
	public void setSpp(int spp) {
		this.spp = spp;
	}
	public int getStart() {
		int page = Integer.parseInt(pageNo);
		if(page>1) {
			start = (page-1)*spp;
		}
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "PageBean [pageNo=" + pageNo + ", key=" + key + ", word=" + word + "]";
	}
}
