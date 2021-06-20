package com.forumArticle.model;

import java.sql.Timestamp;
import java.util.List;

public class ForumArticleService {
	private ForumArticleDAO_interface dao;
	
	public ForumArticleService() {
		dao = new ForumArticleDAO();
	}
	
	public ForumArticleVO addForumArticle(String articleTitle, Timestamp publishedDate, String articleText, Integer articleStatus, Integer userID, Integer articleTitleOptSN, Integer rateGCount, Integer rateNGCount) {
		ForumArticleVO forumArticlevo = new ForumArticleVO();
		
		forumArticlevo.setArticleTitle(articleTitle);
		forumArticlevo.setPublishedDate(publishedDate);
		forumArticlevo.setArticleText(articleText);
		forumArticlevo.setArticleStatus(articleStatus);
		forumArticlevo.setUserID(userID);
		forumArticlevo.setArticleTitleOptSN(articleTitleOptSN);
		forumArticlevo.setRateGCount(rateGCount);
		forumArticlevo.setRateNGCount(rateNGCount);
		dao.insert(forumArticlevo);
		
		return forumArticlevo;
	}
	
	//	管理者更新
//	public ForumArticleVO mUpdateForumArticle(Integer articleSN, String articleTitle, Timestamp publishedDate, String articleText, Integer articleStatus, Integer userID, Integer articleTitleOptSN, Integer rateGCount, Integer rateNGCount) {
	public ForumArticleVO mUpdateForumArticle(Integer articleSN, Integer articleStatus) {		
		
		ForumArticleVO forumArticlevo = new ForumArticleVO();
		
		forumArticlevo.setArticleSN(articleSN);
//		forumArticlevo.setArticleTitle(articleTitle);
//		forumArticlevo.setPublishedDate(publishedDate);
//		forumArticlevo.setArticleText(articleText);
		forumArticlevo.setArticleStatus(articleStatus);
//		forumArticlevo.setUserID(userID);
//		forumArticlevo.setArticleTitleOptSN(articleTitleOptSN);
//		forumArticlevo.setRateGCount(rateGCount);
//		forumArticlevo.setRateNGCount(rateNGCount);
		dao.mUpdate(forumArticlevo);
		
		return forumArticlevo;
	}
	//	使用者更新
//	public ForumArticleVO uUpdateForumArticle(Integer articleSN, String articleTitle, Timestamp publishedDate, String articleText, Integer articleStatus, Integer userID, Integer articleTitleOptSN, Integer rateGCount, Integer rateNGCount) {
	public ForumArticleVO uUpdateForumArticle(Integer articleSN, String articleTitle, String articleText) {		
		
		ForumArticleVO forumArticlevo = new ForumArticleVO();
		
		forumArticlevo.setArticleSN(articleSN);
		forumArticlevo.setArticleTitle(articleTitle);
//		forumArticlevo.setPublishedDate(publishedDate);
		forumArticlevo.setArticleText(articleText);
//		forumArticlevo.setArticleStatus(articleStatus);
//		forumArticlevo.setUserID(userID);
//		forumArticlevo.setArticleTitleOptSN(articleTitleOptSN);
//		forumArticlevo.setRateGCount(rateGCount);
//		forumArticlevo.setRateNGCount(rateNGCount);
		dao.uUpdate(forumArticlevo);
		
		return forumArticlevo;
	}
	
	
	
	
	public ForumArticleVO getOneForumArticle(Integer articleSN) {
		return dao.findByPrimaryKey(articleSN);
	}
	
	public List<ForumArticleVO> getAll(){
		return dao.getAll();
	}
	
}
