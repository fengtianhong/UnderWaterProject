package com.articleTitleOpt.model;

import java.util.*;

public interface ArticleTitleOptDAO_interface {
	public void insert(ArticleTitleOptVO articleTitleOptVO);
    public void update(ArticleTitleOptVO articleTitleOptVO);
    public ArticleTitleOptVO findByPrimaryKey(Integer articleTitleOptSN);
    public List<ArticleTitleOptVO> getAll();   //查詢所有標題
}
