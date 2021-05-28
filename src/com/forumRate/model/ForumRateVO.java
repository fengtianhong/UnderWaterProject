package com.forumRate.model;

public class ForumRateVO implements java.io.Serializable{
	
	private Integer articleRateSN;
	private Integer userID;
	private Integer articleSN;
	private Boolean articleRate;
	
	public Integer getArticleRateSN() {
		return articleRateSN;
	}
	public void setArticleRateSN(Integer articleRateSN) {
		this.articleRateSN = articleRateSN;
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
	public Boolean getArticleRate() {
		return articleRate;
	}
	public void setArticleRate(Boolean articleRate) {
		this.articleRate = articleRate;
	}
	
	
}
