package com.forumArticle.model;

import java.util.*;

public interface ForumArticleDAO_interface {
	public void insert(ForumArticleVO forumArticleVO);
	
	public void userUpdate(ForumArticleVO forumArticleVO);	//	使用者update
	public void hiddenAtricle(Integer articleSN);	//	假裝刪除文章
	
	public ForumArticleVO findByPrimaryKey(Integer articleSN);
	public List<ForumArticleVO> getAll();

}
