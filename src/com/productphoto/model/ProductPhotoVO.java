package com.productphoto.model;

import java.io.Serializable;

public class ProductPhotoVO implements Serializable {
	private Integer PhotoSN;
	private Integer ProductSN;
	private byte[] productimages;
	
	public ProductPhotoVO() {
		super();
	}

	public Integer getPhotoSN() {
		return PhotoSN;
	}

	public void setPhotoSN(Integer photoSN) {
		PhotoSN = photoSN;
	}

	public Integer getProductSN() {
		return ProductSN;
	}

	public void setProductSN(Integer productSN) {
		ProductSN = productSN;
	}

	public byte[] getProductimages() {
		return productimages;
	}

	public void setProductimages(byte[] productimages) {
		this.productimages = productimages;
	}
	
}
