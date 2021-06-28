package com.forumArticle.model;

import java.sql.Timestamp;

public class ForumArticleVO implements java.io.Serializable{
	
	private Integer articleSN;
	private String articleTitle;
	private Timestamp publishedDate;
	private String articleText;
//	private Integer articleStatus;
	private Boolean articleStatus;
	private Integer userID;
	private Integer articleTitleOptSN;
	private Integer rateGCount;
	private Integer rateNGCount;
	
	public Integer getArticleSN() {
		return articleSN;
	}
	public void setArticleSN(Integer articleSN) {
		this.articleSN = articleSN;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public Timestamp getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Timestamp publishedDate) {
		this.publishedDate = publishedDate;
	}
	public String getArticleText() {
		return articleText;
	}
	public void setArticleText(String articleText) {
		this.articleText = articleText;
	}
	public Boolean getArticleStatus() {
		return articleStatus;
	}
	public void setArticleStatus(Boolean articleStatus) {
		this.articleStatus = articleStatus;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getArticleTitleOptSN() {
		return articleTitleOptSN;
	}
	public void setArticleTitleOptSN(Integer articleTitleOptSN) {
		this.articleTitleOptSN = articleTitleOptSN;
	}
	public Integer getRateGCount() {
		return rateGCount;
	}
	public void setRateGCount(Integer rateGCount) {
		this.rateGCount = rateGCount;
	}
	public Integer getRateNGCount() {
		return rateNGCount;
	}
	public void setRateNGCount(Integer rateNGCount) {
		this.rateNGCount = rateNGCount;
	}
	
}

