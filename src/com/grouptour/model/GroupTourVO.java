package com.grouptour.model;

import java.sql.Date;
import java.sql.Timestamp;

public class GroupTourVO implements java.io.Serializable{
	
	private Integer groupTourSN;
	private String tourName;
	private byte[] tourPic;
	private Date startTime;
	private Date endTime;
	private Date regTime;
	private Date closeTime;
	private Timestamp createTime;
	private Integer pointSN;
	private Integer price;
	private Integer attendNumber;
	private Integer limitNumder;
	private String certificationLimit;
	private String status;
	private String content;
	
	@Override
	public String toString() {
		return "GroupTourVO [groupTourSN=" + groupTourSN + ", tourName=" + tourName + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", regTime=" + regTime + ", closeTime=" + closeTime + ", createTime="
				+ createTime + ", pointSN=" + pointSN + ", price=" + price + ", attendNumber=" + attendNumber
				+ ", limitNumder=" + limitNumder + ", certificationLimit=" + certificationLimit + ", status=" + status
				+ ", content=" + content + "]";
	}
	public Integer getGroupTourSN() {
		return groupTourSN;
	}
	public void setGroupTourSN(Integer groupTourSN) {
		this.groupTourSN = groupTourSN;
	}
	public String getTourName() {
		return tourName;
	}
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}
	public byte[] getTourPic() {
		return tourPic;
	}
	public void setTourPic(byte[] tourPic) {
		this.tourPic = tourPic;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getPointSN() {
		return pointSN;
	}
	public void setPointSN(Integer pointSN) {
		this.pointSN = pointSN;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getAttendNumber() {
		return attendNumber;
	}
	public void setAttendNumber(Integer attendNumber) {
		this.attendNumber = attendNumber;
	}
	public Integer getLimitNumder() {
		return limitNumder;
	}
	public void setLimitNumder(Integer limitNumder) {
		this.limitNumder = limitNumder;
	}
	public String getCertificationLimit() {
		return certificationLimit;
	}
	public void setCertificationLimit(String certificationLimit) {
		this.certificationLimit = certificationLimit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj != null && getClass() == obj.getClass()) {	// 同 class
			if (obj instanceof GroupTourVO) {
				GroupTourVO e = (GroupTourVO) obj;
				if (groupTourSN.equals(e.groupTourSN) && tourName.equals(e.tourName)
				 && startTime.equals(e.startTime) && endTime.equals(e.endTime)
				 && regTime.equals(e.regTime) && closeTime.equals(e.closeTime)
				 && pointSN.equals(e.pointSN) && price.equals(e.price)
				 && attendNumber.equals(e.attendNumber) && limitNumder.equals(e.limitNumder)
				 && certificationLimit.equals(e.certificationLimit) && status.equals(e.status)
				 && content.equals(e.content)						
					) {
					return true;
				}
			}
		}
		return false;
	}
//    @Override
//    public int hashCode() {
//        return 41 * (41 + groupTourSN) + groupTourSN;
//    }
}
