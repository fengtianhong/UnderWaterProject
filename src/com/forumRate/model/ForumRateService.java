package com.forumRate.model;

import java.util.List;

public class ForumRateService {
	
	private ForumRateDAO_interface dao;
	
	public ForumRateService() {
		dao = new ForumRateDAO();
	}
	
	public ForumRateVO addForumRate(Integer userID, Integer articleSN, Boolean articleRate) {
		ForumRateVO forumRatevo = new ForumRateVO();
		
		forumRatevo.setUserID(userID);
		forumRatevo.setArticleSN(articleSN);
		forumRatevo.setArticleRate(articleRate);
		dao.insert(forumRatevo);
		
		return forumRatevo;
	}
	
	public ForumRateVO updateForumRate(Integer articleRateSN, Integer userID, Integer articleSN, Boolean articleRate) {
		ForumRateVO forumRatevo = new ForumRateVO();
		
		forumRatevo.setArticleRateSN(articleRateSN);
		forumRatevo.setUserID(userID);
		forumRatevo.setArticleSN(articleSN);
		forumRatevo.setArticleRate(articleRate);
		dao.update(forumRatevo);
		
		return forumRatevo;
	}
	
	public void deleteForumRate(Integer articleRateSN) {
		dao.delete(articleRateSN);
	}
	
	public ForumRateVO getOneForumRate(Integer articleRateSN) {
		return dao.findByPrimaryKey(articleRateSN);
	}
	
	public List<ForumRateVO> getAll(){
		return dao.getAll();
	}
	
}
