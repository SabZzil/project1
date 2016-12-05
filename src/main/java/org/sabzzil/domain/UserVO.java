package org.sabzzil.domain;

import java.util.Date;

public class UserVO {

	private String uid;
	private String upw;
	private String uname;
	private String email;
	private Date regdate;
	private Date updatedate;
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setUpw(String upw) {
		this.upw = upw;
	}
	
	public String getUpw() {
		return upw;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public String getUname() {
		return uname;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public Date getRegdate() {
		return regdate;
	}
	
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	public Date getUpdatedate() {
		return updatedate;
	}
	
	@Override
	public String toString() {
		return "UserVO [uid=" + uid + ", uname=" + uname + ", email="
				+ email + ", regdate=" + regdate + ", updatedate=" + updatedate
				+ "]";
	}
}
