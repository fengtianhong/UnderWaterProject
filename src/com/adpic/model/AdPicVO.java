package com.adpic.model;

import java.sql.Blob;

public class AdPicVO {
	
	private Integer adPicSN;
	private Integer orderSN;
	private byte[] pic;
	
	public Integer getAdPicSN() {
		return adPicSN;
	}
	public void setAdPicSN(Integer adPicSN) {
		this.adPicSN = adPicSN;
	}
	public Integer getOrderSN() {
		return orderSN;
	}
	public void setOrderSN(Integer orderSN) {
		this.orderSN = orderSN;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
}
