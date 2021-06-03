package com.customerreply.model;

import java.sql.Timestamp;

public class CustomerReplyVO implements java.io.Serializable{
	private Integer customerReplySN;
	private Integer userID;
	private String type;
	private String content;
	private Timestamp sendTime;
	
	public Integer getCustomerReplySN() {
		return customerReplySN;
	}
	public void setCustomerReplySN(Integer customerReplySN) {
		this.customerReplySN = customerReplySN;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	
}
