package com.oderforgroup.model;

import java.sql.Date;

public class OderForGroupVO implements java.io.Serializable{
	private Integer orderSN;
	private Integer userID;
	private Integer groupTourSN;
	private Integer totalPrice;
	private Date purchaseDate;
	private String phone;
	private String personID;
	private Date birthdate;
	
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
	public Integer getGroupTourSN() {
		return groupTourSN;
	}
	public void setGroupTourSN(Integer groupTourSN) {
		this.groupTourSN = groupTourSN;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	

}
