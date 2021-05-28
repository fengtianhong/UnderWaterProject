package com.news.model;
import java.sql.Date;

public class NewsVO implements java.io.Serializable{
	private Integer newsSN;
	private String title;
	private String content;
	private byte[] image;
	private Date newsDate;
	private String newsFrom;
	private String newsType;
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}
	public String getNewsFrom() {
		return newsFrom;
	}
	public void setNewsFrom(String newsFrom) {
		this.newsFrom = newsFrom;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	
}
