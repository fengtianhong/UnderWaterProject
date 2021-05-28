package com.orderlist.model;

import java.io.Serializable;

public class OrderListVO implements Serializable {
	private Integer orderListSN;
	private Integer productSN;
	private Integer purchaseQuantity;
	private Integer rating;
	private Integer productPrice;

	public OrderListVO() {
		super();
	}

	public OrderListVO(Integer orderListSN, Integer productSN, Integer purchaseQuantity, Integer rating,
			Integer productPrice) {
		super();
		this.orderListSN = orderListSN;
		this.productSN = productSN;
		this.purchaseQuantity = purchaseQuantity;
		this.rating = rating;
		this.productPrice = productPrice;
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

	public Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

}
