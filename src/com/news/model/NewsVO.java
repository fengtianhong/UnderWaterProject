package com.news.model;
import java.sql.Date;

public class NewsVO implements java.io.Serializable{
	private Integer newsSN;
	private String title;
	private String content;
	private Byte[] image;
	private Date date;
	private String newsFrom;
	private char newsType;
	public Integer getNewsSN() {
		return newsSN;
	}
	public void setNewsSN(Integer newsSN) {
		this.newsSN = newsSN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Byte[] getImage() {
		return image;
	}
	public void setImage(Byte[] image) {
		this.image = image;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNewsFrom() {
		return newsFrom;
	}
	public void setNewsFrom(String newsFrom) {
		this.newsFrom = newsFrom;
	}
	public char getNewsType() {
		return newsType;
	}
	public void setNewsType(char newsType) {
		this.newsType = newsType;
	}
	
}
