package com.orderlist.model;

import java.io.Serializable;

public class OrderListVO implements Serializable {

	private Integer orderListSN;
	private Integer productSN;
	private Integer orderSN;
	private Integer purchaseQuantity;
	private Integer productPrice;
	private Integer rating;

	public OrderListVO() {
		super();
	}

	public Integer getOrderListSN() {
		return orderListSN;
	}

	public void setOrderListSN(Integer orderListSN) {
		this.orderListSN = orderListSN;
	}

	public Integer getProductSN() {
		return productSN;
	}

	public void setProductSN(Integer productSN) {
		this.productSN = productSN;
	}

	public Integer getOrderSN() {
		return orderSN;
	}

	public void setOrderSN(Integer orderSN) {
		this.orderSN = orderSN;
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
