package com.memberrate.model;

import java.sql.Timestamp;

public class MemberRateVO {
	
	private Integer SN;
	private Integer partySN;
	private Integer orderSN;
	private Integer rateMaker;
	private Integer rateRecipiant;
	private Integer rate;
	private String rateDetail;
	private Timestamp createTime;
	
	public MemberRateVO() {
		super();
	}

	public MemberRateVO(Integer sN, Integer partySN, Integer orderSN, Integer rateMaker, Integer rateRecipiant,
			Integer rate, String rateDetail, Timestamp createTime) {
		super();
		SN = sN;
		this.partySN = partySN;
		this.orderSN = orderSN;
		this.rateMaker = rateMaker;
		this.rateRecipiant = rateRecipiant;
		this.rate = rate;
		this.rateDetail = rateDetail;
		this.createTime = createTime;
	}

	public Integer getSN() {
		return SN;
	}

	public void setSN(Integer sN) {
		SN = sN;
	}

	public Integer getPartySN() {
		return partySN;
	}

	public void setPartySN(Integer partySN) {
		this.partySN = partySN;
	}

	public Integer getOrderSN() {
		return orderSN;
	}

	public void setOrderSN(Integer orderSN) {
		this.orderSN = orderSN;
	}

	public Integer getRateMaker() {
		return rateMaker;
	}

	public void setRateMaker(Integer rateMaker) {
		this.rateMaker = rateMaker;
	}

	public Integer getRateRecipiant() {
		return rateRecipiant;
	}

	public void setRateRecipiant(Integer rateRecipiant) {
		this.rateRecipiant = rateRecipiant;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getRateDetail() {
		return rateDetail;
	}

	public void setRateDetail(String rateDetail) {
		this.rateDetail = rateDetail;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	
	
}
