package com.forumArticle.model;

import java.util.*;

public interface ForumArticleDAO_interface {
	public void insert(ForumArticleVO forumArticleVO);
	
	public void userUpdate(ForumArticleVO forumArticleVO);	//	使用者update
	public void mUpdate(ForumArticleVO forumArticleVO);	//	管理員update
	
	public ForumArticleVO findByPrimaryKey(Integer articleSN);
	public List<ForumArticleVO> getAll();
//	查詢所有文章(1對多，回傳set)
//	萬用複合查詢(傳入參數型態Map)(回傳 List)
//	public List<ForumArticleVO> getAll(Map<String, String[]> map); 
}
