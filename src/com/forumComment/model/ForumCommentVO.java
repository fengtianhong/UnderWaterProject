package com.forumComment.model;

import java.sql.Timestamp;

public class ForumCommentVO implements java.io.Serializable{
	
	private Integer cmtSN;
	private Integer articleSN;
	private Integer userID;
	private Timestamp cmtDate;
	private String cmtText;

	public Integer getCmtSN() {
		return cmtSN;
	}
	public void setCmtSN(Integer cmtSN) {
		this.cmtSN = cmtSN;
	}
	public Integer getArticleSN() {
		return articleSN;
	}
	public void setArticleSN(Integer articleSN) {
		this.articleSN = articleSN;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Timestamp getCmtDate() {
		return cmtDate;
	}
	public void setCmtDate(Timestamp cmtDate) {
		this.cmtDate = cmtDate;
	}
	public String getCmtText() {
		return cmtText;
	}
	public void setCmtText(String cmtText) {
		this.cmtText = cmtText;
	}	
}
