package com.adorder.model;

import java.sql.Timestamp;

public class AdOrderVO {
	
	private Integer orderSN;
	private Integer adUserID;
	private Integer block;
	private Timestamp time;
	private Timestamp showTime;
	private Timestamp expiredTime;
	private Integer status;
	
	public Integer getOrderSN() {
		return orderSN;
	}
	public void setOrderSN(Integer orderSN) {
		this.orderSN = orderSN;
	}
	public Integer getAdUserID() {
		return adUserID;
	}
	public void setAdUserID(Integer adUserID) {
		this.adUserID = adUserID;
	}
	public Integer getBlock() {
		return block;
	}
	public void setBlock(Integer block) {
		this.block = block;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Timestamp getShowTime() {
		return showTime;
	}
	public void setShowTime(Timestamp showTime) {
		this.showTime = showTime;
	}
	public Timestamp getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Timestamp expiredTime) {
		this.expiredTime = expiredTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
