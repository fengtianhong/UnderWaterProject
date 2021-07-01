package com.orderforproduct.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderForProductVO implements Serializable {

	private Integer orderSN;
	private Integer userID;
	private Timestamp purchaseDate;
	private Integer totalPrice;
	private String orderStatus;

	public OrderForProductVO() {
		super();
	}

	public Integer getOrderSN() {
		return orderSN;
	}

	public void setOrderSN(Integer orderSN) {
		this.orderSN = orderSN;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
