package com.articleReport.model;

import java.util.*;

public interface ArticleReportDAO_interface {
	public void insert(ArticleReportVO articleReportVO);
    public void update(ArticleReportVO articleReportVO);
    public ArticleReportVO findByPrimaryKey(Integer rptSN);
    public List<ArticleReportVO> getAll();
}
