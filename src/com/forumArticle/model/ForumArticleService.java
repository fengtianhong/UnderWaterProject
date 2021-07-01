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
		forumArticlevo.setArticleText(articleText);
		forumArticlevo.setUserID(userID);
		forumArticlevo.setArticleTitleOptSN(articleTitleOptSN);
		dao.insert(forumArticlevo);
		
		return forumArticlevo;
	}
	
	//	文章刪除

	public void deleteForumArticle(Integer articleSN) {		
		
		dao.hiddenAtricle(articleSN);

	}
	//	使用者更新
	public ForumArticleVO userUpdateForumArticle(Integer articleSN, String articleTitle, String articleText) {		
		
		ForumArticleVO forumArticlevo = new ForumArticleVO();
		
		forumArticlevo.setArticleSN(articleSN);
		forumArticlevo.setArticleTitle(articleTitle);
		forumArticlevo.setArticleText(articleText);
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
