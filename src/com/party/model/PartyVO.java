package com.party.model;

import java.sql.Date;
import java.sql.Timestamp;

public class PartyVO {
	
	private Integer partySN;
	private Integer partyHost;
	private String partyTitle;
	private Date regDate;
	private Date closeDate;
	private Date startDate;
	private Date endDate;
	private Integer partyMinSize;
	private Integer partyLocation;
	private String partyDetail;
	private Timestamp createTime;
	private String status;
	
	public PartyVO() {
		super();
	}
	
	public PartyVO(Integer partySN, Integer partyHost, String partyTitle, Date regDate, Date closeDate, Date startDate,
			Date endDate, Integer partyMinSize, Integer partyLocation, String partyDetail, Timestamp createTime,
			String status) {
		super();
		this.partySN = partySN;
		this.partyHost = partyHost;
		this.partyTitle = partyTitle;
		this.regDate = regDate;
		this.closeDate = closeDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.partyMinSize = partyMinSize;
		this.partyLocation = partyLocation;
		this.partyDetail = partyDetail;
		this.createTime = createTime;
		this.status = status;
	}

	public Integer getPartySN() {
		return partySN;
	}

	public void setPartySN(Integer partySN) {
		this.partySN = partySN;
	}

	public Integer getPartyHost() {
		return partyHost;
	}

	public void setPartyHost(Integer partyHost) {
		this.partyHost = partyHost;
	}

	public String getPartyTitle() {
		return partyTitle;
	}

	public void setPartyTitle(String partyTitle) {
		this.partyTitle = partyTitle;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getPartyMinSize() {
		return partyMinSize;
	}

	public void setPartyMinSize(Integer partyMinSize) {
		this.partyMinSize = partyMinSize;
	}

	public Integer getPartyLocation() {
		return partyLocation;
	}

	public void setPartyLocation(Integer partyLocation) {
		this.partyLocation = partyLocation;
	}

	public String getPartyDetail() {
		return partyDetail;
	}

	public void setPartyDetail(String partyDetail) {
		this.partyDetail = partyDetail;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
