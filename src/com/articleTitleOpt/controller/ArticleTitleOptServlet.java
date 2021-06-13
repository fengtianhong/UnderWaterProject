package com.articleTitleOpt.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.articleTitleOpt.model.*;

public class ArticleTitleOptServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // 來自articleTitleOpt.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//	輸入的錯誤處理
				String str = req.getParameter("articleTitleOptSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入選項編號後，再重試一次。");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/articleTitleOpt.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/articleTitleOpt.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/articleTitleOpt.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//	查詢完轉交
				req.setAttribute("articleTitleOptVO", articleTitleOptVO);
				String url = "/articleTitleOpt/listOnearticleTitleOpt.jsp";
				
				
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/articleTitleOpt/articleTitleOpt.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
