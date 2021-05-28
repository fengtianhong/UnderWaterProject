package com.forumComment.model;

import java.sql.Timestamp;

public class forumCommentVO implements java.io.Serializable{

	private Integer commentSN;
	private Integer articleSN;
	private Integer userID;
	private Timestamp commentDate;
	private String commentText;
	
	public Integer getCommentSN() {
		return commentSN;
	}
	public void setCommentSN(Integer commentSN) {
		this.commentSN = commentSN;
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
	public Timestamp getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	
}
