package com.diveinfo.model;

public class DiveInfoVO implements java.io.Serializable{
	private Integer pointSN;
	private String pointName;
	private Double latitude;
	private Double longitude;
	private String view;
	private String introduction;
	private String season;
	private String local;
	private Byte[] pic;
	private Integer ratePoint;
	private Integer ratePeople;
	private String status;
	public Integer getPointSN() {
		return pointSN;
	}
	public void setPointSN(Integer pointSN) {
		this.pointSN = pointSN;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Byte[] getPic() {
		return pic;
	}
	public void setPic(Byte[] pic) {
		this.pic = pic;
	}
	public Integer getRatePoint() {
		return ratePoint;
	}
	public void setRatePoint(Integer ratePoint) {
		this.ratePoint = ratePoint;
	}
	public Integer getRatePeople() {
		return ratePeople;
	}
	public void setRatePeople(Integer ratePeople) {
		this.ratePeople = ratePeople;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
}
