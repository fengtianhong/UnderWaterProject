package com.forumRate.model;

import java.util.*;

public interface ForumRateDAO_interface {
	public void insert(ForumRateVO forumRateVO);
	public void update(ForumRateVO forumRateVO);
	public ForumRateVO findByPrimaryKey(Integer articleSN);
	public List<ForumRateVO> getAll();	
//	查詢所有文章評價(1對多，回傳set)
//	萬用複合查詢(傳入參數型態Map)(回傳 List)
//	public List<ForumRateVO> getAll(Map<String, String[]> map); 
}
