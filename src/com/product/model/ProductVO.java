package com.product.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductVO implements Serializable {
	private Integer productSN;
	private String productClass;
	private String productName;
	private Integer productPrice;
	private Integer productQuantity;
	private String productStatus;
	private String productDetail;
	private Timestamp productCreate;
	private Byte productDiscount;
	private Byte productPrime;
	private Integer ratingPoint;
	private Integer ratingNumber;

	public ProductVO() {
		super();
	}

	public ProductVO(Integer productSN, String productClass, String productName, Integer productPrice,
			Integer productQuantity, String productStatus, String productDetail, Timestamp productCreate,
			Byte productDiscount, Byte productPrime, Integer ratingPoint, Integer ratingNumber) {
		super();
		this.productSN = productSN;
		this.productClass = productClass;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.productStatus = productStatus;
		this.productDetail = productDetail;
		this.productCreate = productCreate;
		this.productDiscount = productDiscount;
		this.productPrime = productPrime;
		this.ratingPoint = ratingPoint;
		this.ratingNumber = ratingNumber;
	}

	public Integer getProductSN() {
		return productSN;
	}

	public void setProductSN(Integer productSN) {
		this.productSN = productSN;
	}

	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public Timestamp getProductCreate() {
		return productCreate;
	}

	public void setProductCreate(Timestamp productCreate) {
		this.productCreate = productCreate;
	}

	public Byte getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(Byte productDiscount) {
		this.productDiscount = productDiscount;
	}

	public Byte getProductPrime() {
		return productPrime;
	}

	public void setProductPrime(Byte productPrime) {
		this.productPrime = productPrime;
	}

	public Integer getRatingPoint() {
		return ratingPoint;
	}

	public void setRatingPoint(Integer ratingPoint) {
		this.ratingPoint = ratingPoint;
	}

	public Integer getRatingNumber() {
		return ratingNumber;
	}

	public void setRatingNumber(Integer ratingNumber) {
		this.ratingNumber = ratingNumber;
	}

}
