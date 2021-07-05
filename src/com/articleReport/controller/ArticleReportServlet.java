package com.articleReport.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.articleReport.model.ArticleReportService;
import com.articleReport.model.ArticleReportVO;

public class ArticleReportServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				接收參數
				String str = req.getParameter("rptSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("哪個.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer rptSN = null;
				try {
					rptSN = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉編號不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("哪個.jsp");
					failureView.forward(req, res);
					return;
				}
//				查詢
				ArticleReportService articleReportSvc = new ArticleReportService();
				ArticleReportVO articleReportVO = articleReportSvc.getOneArticleReport(rptSN);
				if (rptSN == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("某個.jsp");
					failureView.forward(req, res);
					return;
				}
//				轉交
				req.setAttribute("articleReportVO", articleReportVO);
				String url = "某個.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("是哪個.jsp");
				failureView.forward(req, res);
			}
		}
		
//		查詢後轉交到更新資訊頁面
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				接收參數
				Integer rptSN = new Integer(req.getParameter("rptSN"));
				
//				查詢
				ArticleReportService articleReportSvc = new ArticleReportService();
				ArticleReportVO articleReportVO = articleReportSvc.getOneArticleReport(rptSN);
				
//				成功後轉交更新
				req.setAttribute("articleReportVO", articleReportVO);
				String url = "/forumArticle/uwRptReply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/uwRptManage.jsp");
				failureView.forward(req, res);
			}
		}
		
//		更新(管理員更新檢舉回覆文字)
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("1");
			try {
//				接收參數
				Integer rptSN = new Integer(req.getParameter("rptSN"));
				Boolean rptStatus = new Boolean(req.getParameter("rptStatus"));
				String reRptResult = req.getParameter("reRptResult");
				
				if (reRptResult == null || reRptResult.trim().length() == 0) {
					errorMsgs.add("更新檢舉處理之文字不得為空");
				} 
				System.out.println("11");
				ArticleReportVO articleReportVO = new ArticleReportVO();
				articleReportVO.setRptSN(rptSN);
				articleReportVO.setRptStatus(true);
				articleReportVO.setReRptResult(reRptResult);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleReportVO", articleReportVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("");
					failureView.forward(req, res);
					return; //程式中斷
				}
//				更新
				ArticleReportService articleReportSvc = new ArticleReportService();
				articleReportVO = articleReportSvc.updateArticleReport(rptSN, rptStatus, reRptResult);
				System.out.println("111");	
//				成功後轉交
				req.setAttribute("articleReportVO", articleReportVO);
				String url = "/forumArticle/uwRptManage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/uwRptReply.jsp");
				failureView.forward(req, res);
			}	
		}
		
//		使用者新增檢舉
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			HttpSession session = req.getSession();

			try {
//				接收參數
				
				Integer userID =  (Integer)session.getAttribute("userID");
				Integer articleSN = new Integer(req.getParameter("articleSN"));
				
//				新增
				req.setAttribute("articleSN", articleSN);
				req.setAttribute("userID", userID);
								
//				成功後轉交畫面
				String url = "/forumArticle/rptForm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);						
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/rptForm.jsp");
				failureView.forward(req, res);
			}	
		}
		
//		使用者新增檢舉
		if ("insertrpt".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			HttpSession session = req.getSession();

			try {
//				接收參數
				
				Integer userID =  (Integer)session.getAttribute("userID");
				Integer articleSN = new Integer(req.getParameter("articleSN"));

				String rptReason = req.getParameter("rptReason");
				
				if (rptReason == null || rptReason.trim().length() == 0) {
					errorMsgs.add("檢舉內容請勿空白");
				} 
				
				ArticleReportVO articleReportVO = new ArticleReportVO();
				articleReportVO.setUserID(userID);
				articleReportVO.setArticleSN(articleSN);
				articleReportVO.setRptReason(rptReason);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleReportVO", articleReportVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/rptForm.jsp");
					failureView.forward(req, res);
					return;
				}
				
//				新增
				ArticleReportService articleReportSvc = new ArticleReportService();
				articleReportVO = articleReportSvc.addArticleReport(userID, articleSN, rptReason);
								
//				成功後轉交畫面
				String url = "/forumArticle/forumArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);						
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/rptForm.jsp");
				failureView.forward(req, res);
			}	
		}
	}
}
