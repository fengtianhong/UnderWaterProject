package com.articleTitleOpt.model;

import java.util.*;

public interface ArticleTitleOptDAO_interface {
	public void insert(ArticleTitleOptVO articleTitleOptVO);
    public void update(ArticleTitleOptVO articleTitleOptVO);
    public ArticleTitleOptVO findByPrimaryKey(Integer articleTitleOptSN);
    public List<ArticleTitleOptVO> getAll();   
//	查詢所有文章標題(1對多，回傳set)
//	萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ArticleTitleOptVO> getAll(Map<String, String[]> map); 
}
