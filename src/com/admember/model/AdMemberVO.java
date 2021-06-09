package com.admember.model;

import java.sql.Timestamp;

public class AdMemberVO {
	
	private Integer adUserID;//會員流水編號
	private String account;//帳號
	private String pwd;//密碼
	private Timestamp createTime;//帳號建立時間
	private Integer status;//帳號狀態
	
	public Integer getAdUserID() {
		return adUserID;
	}
	public void setAdUserID(Integer adUserID) {
		this.adUserID = adUserID;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
