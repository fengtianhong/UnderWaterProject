package com.articleTitleOpt.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.articleTitleOpt.model.*;

@MultipartConfig
public class ArticleTitleOptServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


		if ("getOne_For_Display".equals(action)) {	// from select
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("123");
			try {
				//	接收參數，輸入的錯誤處理
				String str = req.getParameter("articleTitleOptSN");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入選項編號後，再重試一次。");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/articleTitleOptSelect.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer articleTitleOptSN = null;
				try {
					articleTitleOptSN = new Integer(str);
				} catch (Exception e){
					errorMsgs.add("發文選項編號輸入不正確，請重新輸入後再重試一次。");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/articleTitleOptSelect.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//	查詢的部分
				ArticleTitleOptService articleTitleOptSvc = new ArticleTitleOptService();
				ArticleTitleOptVO articleTitleOptVO = articleTitleOptSvc.getOneArticleTitleOpt(articleTitleOptSN);
				if (articleTitleOptVO == null) {
					errorMsgs.add("無此筆資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/articleTitleOptSelect.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//	查詢完轉交
				req.setAttribute("articleTitleOptVO", articleTitleOptVO);
				String url = "/articleTitleOpt/listOneArticleTitleOpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
				//	其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/articleTitleOptSelect.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) {	//	來自listAll的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer articleTitleOptSN = new Integer(req.getParameter("articleTitleOptSN"));	//	接收
				
				ArticleTitleOptService articleTitleOptSvc = new ArticleTitleOptService();
				ArticleTitleOptVO articleTitleOptVO = articleTitleOptSvc.getOneArticleTitleOpt(articleTitleOptSN);	//	查詢

				req.setAttribute("articleTitleOptVO", articleTitleOptVO);
				String url = "/articleTitleOpt/updateArticleTitleOpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {		//錯誤處理
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/listAllArticleTitleOpt.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//	接收請求參數
			try {
				Integer articleTitleOptSN = new Integer(req.getParameter("articleTitleOptSN").trim());
				
				String articleTitleOptText = req.getParameter("articleTitleOptText");
				String articleTitleOptTextReg = "^(\u4e00-\u9fa5){4}$";
				
				if (articleTitleOptText == null || articleTitleOptText.trim().length() == 0) {
					errorMsgs.add("文章選項：請勿空白");
				} else if (!articleTitleOptText.trim().matches(articleTitleOptTextReg)){
					errorMsgs.add("文章選項只能是中文，且文字長度應為4。");
				}
				
				ArticleTitleOptVO articleTitleOptVO = new ArticleTitleOptVO();
				articleTitleOptVO.setArticleTitleOptSN(articleTitleOptSN);
				articleTitleOptVO.setArticleTitleOptText(articleTitleOptText);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute(articleTitleOptTextReg, articleTitleOptVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/listAllArticleTitleOpt.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//	開始修改
				ArticleTitleOptService articleTitleOptSvc = new ArticleTitleOptService();
				articleTitleOptVO = articleTitleOptSvc.updateArticleTitleOpt(articleTitleOptSN, articleTitleOptTextReg);
				
				//	修改完成的轉交
				req.setAttribute("articleTicleOptVO", articleTitleOptVO);
				String url = "/articleTitleOpt/listAllArticleTitleOpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交
				successView.forward(req, res);
				
			} catch (Exception e) {		//	錯誤處理
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/updateArticleTitleOpt.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) {		//	來自add的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//	接收
				String articleTitleOptText = req.getParameter("articleTitleOptText");
				String articleTitleOptTextReg = "^(\u4e00-\u9fa5){4}$";
				
				if (articleTitleOptText == null || articleTitleOptText.trim().length() == 0) {
					errorMsgs.add("文章選項請勿空白");
				} else if (!articleTitleOptText.trim().matches(articleTitleOptTextReg)) {
					errorMsgs.add("文章選項只能是中文，且文字長度應為4。");
				}
				
				ArticleTitleOptVO articleTitleOptVO = new ArticleTitleOptVO();
				articleTitleOptVO.setArticleTitleOptText(articleTitleOptText);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleTitleOptVO", articleTitleOptVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/addArticleTitleOpt.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//	新增
				ArticleTitleOptService articleTitleOptSvc = new ArticleTitleOptService();
				articleTitleOptVO = articleTitleOptSvc.addArticleTitleOpt(articleTitleOptText);
				
				//	完成後轉交
				String url = "/articleTitleOpt/listAllArticleTitleOpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				//	錯誤處理
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/addArticleTitleOpt.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
