package com.productphoto.model;

import java.io.Serializable;

public class ProductPhotoVO implements Serializable {

	private Integer PhotoSN;
	private Integer ProductSN;
	private byte[] productImages;

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

	public byte[] getProductImages() {
		return productImages;
	}

	public void setProductImages(byte[] productimages) {
		this.productImages = productimages;
	}

}
