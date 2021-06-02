package com.articleReport.model;

import java.util.*;

public interface ArticleReportDAO_interface {
	public void insert(ArticleReportVO articleReportVO);
    public void update(ArticleReportVO articleReportVO);
    public ArticleReportVO findByPrimaryKey(Integer rptSN);
    public List<ArticleReportVO> getAll();
//	查詢所有文章檢舉(1對多，回傳set)
//	萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ArticleReportVO> getAll(Map<String, String[]> map); 
}
