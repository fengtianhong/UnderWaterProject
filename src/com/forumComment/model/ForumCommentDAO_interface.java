package com.forumComment.model;

import java.util.*;

public interface ForumCommentDAO_interface {
	public void insert(ForumCommentVO forumCommentVO);
    public void update(ForumCommentVO forumCommentVO);
    public void delete(Integer cmtSN);
    public List<ForumCommentVO> findByPrimaryKey(Integer articleSN);
    public List<ForumCommentVO> getAll();
//	查詢所有文章評論(1對多，回傳set)
//	萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ForumCommentVO> getAll(Map<String, String[]> map); 
}
