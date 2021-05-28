package com.forumArticle.model;

import java.util.*;

public interface ForumArticleDAO_interface {
	public void insert(ForumArticleVO forumArticleVO);
	public void update(ForumArticleVO forumArticleVO);
	public ForumArticleVO findByPrimaryKey(Integer articleSN);
	public List<ForumArticleVO> getAll();
	
//	查詢所有文章標題(1對多，回傳set)

}
