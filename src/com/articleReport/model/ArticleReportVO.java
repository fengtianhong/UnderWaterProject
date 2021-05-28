package com.articleReport.model;

public class ArticleReportVO implements java.io.Serializable{
	
	private Integer reportSN;
	private Integer userID;
	private Integer articleSN;
	private String reportReason;
	
	public Integer getReportSN() {
		return reportSN;
	}
	public void setReportSN(Integer reportSN) {
		this.reportSN = reportSN;
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
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}

	
}
