package com.articleReport.model;

public class ArticleReportVO implements java.io.Serializable{
	
	private Integer rptSN;
	private Integer userID;
	private Integer articleSN;
	private String rptReason;
	private Boolean rptStatus;
	private String reRptResult;
	
	
	
	public Integer getRptSN() {
		return rptSN;
	}
	public void setRptSN(Integer rptSN) {
		this.rptSN = rptSN;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getArticleSN() {
		return articleSN;
	}
	public void setArticleSN(Integer articleSN) {
		this.articleSN = articleSN;
	}
	public String getRptReason() {
		return rptReason;
	}
	public void setRptReason(String rptReason) {
		this.rptReason = rptReason;
	}
	public String getReRptResult() {
		return reRptResult;
	}
	public void setReRptResult(String reRptResult) {
		this.reRptResult = reRptResult;
	}
	public Boolean getRptStatus() {
		return rptStatus;
	}
	public void setRptStatus(Boolean rptStatus) {
		this.rptStatus = rptStatus;
	}
}	