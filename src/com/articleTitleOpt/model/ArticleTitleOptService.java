package com.articleTitleOpt.model;

import java.util.List;

public class ArticleTitleOptService {
	private ArticleTitleOptDAO_interface dao;
	
	public ArticleTitleOptService() {
		dao = new ArticleTitleOptDAO();
	}
	
	public ArticleTitleOptVO addArticleTitleOpt(String articleTitleOptText) {
		ArticleTitleOptVO articleTitleOptvo = new ArticleTitleOptVO();
		
		articleTitleOptvo.setArticleTitleOptText(articleTitleOptText);
		dao.insert(articleTitleOptvo);
		
		return articleTitleOptvo;
	}
	
	public ArticleTitleOptVO updateArticleTitleOpt(Integer articleTitleOptSN, String articleTitleOptText) {
		ArticleTitleOptVO articleTitleOptvo = new ArticleTitleOptVO();
		
		articleTitleOptvo.setArticleTitleOptSN(articleTitleOptSN);
		articleTitleOptvo.setArticleTitleOptText(articleTitleOptText);
		dao.update(articleTitleOptvo);
		
		return articleTitleOptvo;
	}
	
	public ArticleTitleOptVO getOneArticleTitleOpt(Integer articleTitleOptSN) {
		return dao.findByPrimaryKey(articleTitleOptSN);
	}
	
	public List<ArticleTitleOptVO> getAll(){
		return dao.getAll();
	}
	
}
