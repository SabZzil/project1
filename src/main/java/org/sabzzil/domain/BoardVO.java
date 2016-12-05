package org.sabzzil.domain;

import java.util.Date;

public class BoardVO {

	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
	private int replycnt;
	
	public void setBno(int bno) {
		this.bno = bno;
	}
	
	public int getBno() {
		return bno;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public Date getRegdate() {
		return regdate;
	}
	
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	
	public int getViewcnt() {
		return viewcnt;
	}
	
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	
	public int getReplycnt() {
		return replycnt;
	}
	
}
