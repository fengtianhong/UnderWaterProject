package com.productphoto.model;

import java.io.Serializable;

public class ProductPhotoVO implements Serializable {

	private Integer photoSN;
	private Integer productSN;
	private byte[] productImages;

	public ProductPhotoVO() {
		super();
	}

	public Integer getPhotoSN() {
		return photoSN;
	}

	public void setPhotoSN(Integer photoSN) {
		this.photoSN = photoSN;
	}

	public Integer getProductSN() {
		return productSN;
	}

	public void setProductSN(Integer productSN) {
		this.productSN = productSN;
	}

	public byte[] getProductImages() {
		return productImages;
	}

	public void setProductImages(byte[] productImages) {
		this.productImages = productImages;
	}

}
