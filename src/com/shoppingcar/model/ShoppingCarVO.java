package com.shoppingcar.model;

import java.io.Serializable;

public class ShoppingCarVO implements Serializable {
	private Integer shoppingCarSN;
	private Integer userID;
	private Integer productSN;
	private Integer purchaseQuantity;
	private Integer productPrice;
	private Integer totalPrice;

	public ShoppingCarVO() {
		super();
	}

	public ShoppingCarVO(Integer shoppingCarSN, Integer userID, Integer productSN, Integer purchaseQuantity,
			Integer productPrice, Integer totalPrice) {
		super();
		this.shoppingCarSN = shoppingCarSN;
		this.userID = userID;
		this.productSN = productSN;
		this.purchaseQuantity = purchaseQuantity;
		this.productPrice = productPrice;
		this.totalPrice = totalPrice;
	}

	public Integer getShoppingCarSN() {
		return shoppingCarSN;
	}

	public void setShoppingCarSN(Integer shoppingCarSN) {
		this.shoppingCarSN = shoppingCarSN;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getProductSN() {
		return productSN;
	}

	public void setProductSN(Integer productSN) {
		this.productSN = productSN;
	}

	public Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
