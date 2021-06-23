package com.forumArticle.model;

import java.util.List;

public class ForumArticleService {
	private ForumArticleDAO_interface dao;
	
	public ForumArticleService() {
		dao = new ForumArticleDAO();
	}
	
	public ForumArticleVO addForumArticle(String articleTitle, String articleText, Integer userID, Integer articleTitleOptSN) {
		ForumArticleVO forumArticlevo = new ForumArticleVO();
		
		forumArticlevo.setArticleTitle(articleTitle);
//		forumArticlevo.setPublishedDate(publishedDate);
		forumArticlevo.setArticleText(articleText);
//		forumArticlevo.setArticleStatus(articleStatus);
		forumArticlevo.setUserID(userID);
		forumArticlevo.setArticleTitleOptSN(articleTitleOptSN);
//		forumArticlevo.setRateGCount(rateGCount);
//		forumArticlevo.setRateNGCount(rateNGCount);
		dao.insert(forumArticlevo);
		
		return forumArticlevo;
	}
	
	//	管理者更新
//	public ForumArticleVO mUpdateForumArticle(Integer articleSN, String articleTitle, Timestamp publishedDate, String articleText, Integer articleStatus, Integer userID, Integer articleTitleOptSN, Integer rateGCount, Integer rateNGCount) {
	public ForumArticleVO mUpdateForumArticle(Integer articleSN, Boolean articleStatus) {		
		
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
	public ForumArticleVO userUpdateForumArticle(Integer articleSN, String articleTitle, String articleText) {		
		
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
		dao.userUpdate(forumArticlevo);
		
		return forumArticlevo;
	}
	
	
	
	
	public ForumArticleVO getOneForumArticle(Integer articleSN) {
		return dao.findByPrimaryKey(articleSN);
	}
	
	public List<ForumArticleVO> getAll(){
		return dao.getAll();
	}
	
}
