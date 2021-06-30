package com.forumComment.model;

import java.sql.Timestamp;
import java.util.List;

public class ForumCommentService {
	private ForumCommentDAO_interface dao;
	
	public ForumCommentService() {
		dao = new ForumCommentDAO();
	}
	
	public ForumCommentVO addForumComment(Integer articleSN, Integer userID, String cmtText) {
		ForumCommentVO forumCommentvo = new ForumCommentVO();
		
		forumCommentvo.setArticleSN(articleSN);
		forumCommentvo.setUserID(userID);
//		forumCommentvo.setCmtDate(cmtDate);
		forumCommentvo.setCmtText(cmtText);
		dao.insert(forumCommentvo);
		
		return forumCommentvo;
	}
	
	public ForumCommentVO updateForumComment(Integer cmtSN, Integer articleSN, Integer userID, String cmtText) {
		ForumCommentVO forumCommentvo = new ForumCommentVO();
		
		forumCommentvo.setCmtSN(cmtSN);
		forumCommentvo.setArticleSN(articleSN);
		forumCommentvo.setUserID(userID);
		forumCommentvo.setCmtText(cmtText);
		dao.update(forumCommentvo);
		
		return forumCommentvo;
	}
	
	public void deleteForumComment(Integer cmtSN) {
		dao.delete(cmtSN);
	}
	
	public ForumCommentVO getOneForumComment(Integer articleSN) {
		return dao.findByPrimaryKey(articleSN);
	}
	
	public List<ForumCommentVO> getAll(){
		return dao.getAll();
	}
	
}
