package com.articleReport.model;

import java.util.List;

public class ArticleReportService {
	private ArticleReportDAO_interface dao;
	
	public ArticleReportService() {
		dao = new ArticleReportDAO();
	}
	
	public ArticleReportVO addArticleReport(Integer userID, Integer articleSN, String rptReason) {
		ArticleReportVO articleReportvo = new ArticleReportVO();
		articleReportvo.setUserID(userID);
		articleReportvo.setArticleSN(articleSN);
		articleReportvo.setRptReason(rptReason);
		dao.insert(articleReportvo);
		
		return articleReportvo;
	}
	
	public ArticleReportVO updateArticleReport(Integer rptSN, Integer userID, Integer articleSN, String rptReason, String rptResult, String reRptResult) {
		ArticleReportVO articleReportvo = new ArticleReportVO();
		
		articleReportvo.setRptSN(rptSN);
		articleReportvo.setUserID(userID);
		articleReportvo.setArticleSN(articleSN);
		articleReportvo.setRptReason(rptReason);
		articleReportvo.setRptResult(reRptResult);
		articleReportvo.setReRptResult(reRptResult);
		dao.update(articleReportvo);
		
		return articleReportvo;	
	}
	public ArticleReportVO getOneArticleReport(Integer rptSN) {
		return dao.findByPrimaryKey(rptSN);
	}
	
	public List<ArticleReportVO> getAll(){
		return dao.getAll();
	}
	
}
