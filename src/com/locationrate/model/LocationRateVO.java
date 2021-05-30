package com.locationrate.model;

import java.sql.Timestamp;

public class LocationRateVO implements java.io.Serializable{
	
	private Integer SN;
	private Integer pointSN;
	private Integer userID;
	private Integer rate;
	private String rateDetail;
	private Timestamp createTime;
	
	public Integer getSN() {
		return SN;
	}
	public void setSN(Integer sN) {
		SN = sN;
	}
	public Integer getPointSN() {
		return pointSN;
	}
	public void setPointSN(Integer pointSN) {
		this.pointSN = pointSN;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public String getRateDetail() {
		return rateDetail;
	}
	public void setRateDetail(String rateDetail) {
		this.rateDetail = rateDetail;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}	
	

}
