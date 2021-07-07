package com.forumComment.model;

import java.util.*;

public interface ForumCommentDAO_interface {
	public void insert(ForumCommentVO forumCommentVO);
    public void update(ForumCommentVO forumCommentVO);
    public void delete(Integer cmtSN);
    
    public List<ForumCommentVO> findByPrimaryKey(Integer cmtSN);
    
    public List<ForumCommentVO> getAll();

}
