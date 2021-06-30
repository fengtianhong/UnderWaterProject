package com.forumRate.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forumArticle.model.ForumArticleService;
import com.forumRate.model.*;

public class ForumRateServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//	接收參數及錯誤處理
			try {
				String str = req.getParameter("articleRateSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入評價編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("評價查詢網頁.jsp");
					failureView.forward(req, res);
					return;
				}				
				
				Integer articleRateSN = null;
				
				try {
					articleRateSN = new Integer(str);
				} catch (Exception e){
					errorMsgs.add("評價編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("評價查詢網頁.jsp");
					failureView.forward(req, res);
					return;
				}
//				開始查詢資料
				ForumRateService forumRateSvc = new ForumRateService();
				ForumRateVO forumRateVO = forumRateSvc.getOneForumRate(articleRateSN);
				
				if (articleRateSN == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("評價查詢網頁.jsp");
					failureView.forward(req, res);
					return;
				}			
//				查詢後轉交到列表
				req.setAttribute("forumRateVO", forumRateVO);
				String url = "列出該筆查詢.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
//				其他例外處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("評價查詢網頁.jsp");
				failureView.forward(req, res);
			}
		}
		
//		從評價列表來更新
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				接收參數
				Integer articleRateSN = new Integer(req.getParameter("articleRateSN"));
//				查詢該筆資料
				ForumRateService forumRateSvc = new ForumRateService();
				ForumRateVO forumRateVO = forumRateSvc.getOneForumRate(articleRateSN);
//				成功後轉交評價列表
				req.setAttribute("forumRateVO", forumRateVO);
				String url = "列出該筆查詢.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("評價列表網頁.jsp");
				failureView.forward(req, res);
			}	
		}
		
//		更新評價(來自該文章頁面的請求)
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
//				接收參數
				Integer articleRateSN = new Integer(req.getParameter("articleRateSN").trim());
				Integer userID = new Integer(req.getParameter("userID").trim());
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				Boolean articleRate = new Boolean(req.getParameter("articleRate"));
				
				ForumRateVO forumRateVO = new ForumRateVO();
				forumRateVO.setArticleRateSN(articleRateSN);
				forumRateVO.setUserID(userID);
				forumRateVO.setArticleSN(articleSN);
				forumRateVO.setArticleRate(articleRate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumRateVO", forumRateVO);
					RequestDispatcher failureView = req.getRequestDispatcher("文章更新.jsp");
					failureView.forward(req, res);
					return; 
				}
//				更改評價
				ForumRateService forumRateSvc = new ForumRateService();
				forumRateVO = forumRateSvc.updateForumRate(articleRateSN, userID, articleSN, articleRate);
//				送出新的評價
				req.setAttribute("forumRateVO", forumRateVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "該放到哪.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
//				其他錯誤
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("在文章頁面的按鈕按評價，應該回到文章頁面.jsp");
				failureView.forward(req, res);
			}
		}

//		新增評價
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				接收參數
				Integer userID = new Integer(req.getParameter("userID").trim());
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				Boolean articleRate = new Boolean(req.getParameter("articleRate"));
				
				ForumRateVO forumRateVO = new ForumRateVO();
				forumRateVO.setUserID(userID);
				forumRateVO.setArticleSN(articleSN);
				forumRateVO.setArticleRate(articleRate);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumRateVO", forumRateVO);
					RequestDispatcher failureView = req.getRequestDispatcher("文章更新.jsp");
					failureView.forward(req, res);
					return; 
				}
//				新增資料
				ForumRateService forumRateSvc = new ForumRateService();
				forumRateVO = forumRateSvc.addForumRate(userID, articleSN, articleRate);
//				轉交
				String url = "放到哪邊呢.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("要放哪個.jsp");
				failureView.forward(req, res);
			}
		}
		
//		刪除有需要嗎？
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				接收參數
				Integer articleRateSN = new Integer(req.getParameter("articleRateSN"));
//				刪除
				ForumRateService forumRateSvc = new ForumRateService();
				forumRateSvc.deleteForumRate(articleRateSN);
//				轉交
				String url = "放到哪邊呢.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);						
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("要放哪個.jsp");
				failureView.forward(req, res);
			}	
		}		
	}
}
