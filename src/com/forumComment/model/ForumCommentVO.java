package com.forumComment.model;

import java.sql.Timestamp;

public class ForumCommentVO implements java.io.Serializable{

	private Integer cmtSN;
	private Integer articleSN;
	private Integer userID;
	private Timestamp cmtDate;
	private String cmtText;
	
	public Integer getcmtSN() {
		return cmtSN;
	}
	public void setcmtSN(Integer cmtSN) {
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
	public Timestamp getcmtDate() {
		return cmtDate;
	}
	public void setcmtDate(Timestamp cmtDate) {
		this.cmtDate = cmtDate;
	}
	public String getcmtText() {
		return cmtText;
	}
	public void setcmtText(String cmtText) {
		this.cmtText = cmtText;
	}
	
	
}
