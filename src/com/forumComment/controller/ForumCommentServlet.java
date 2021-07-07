package com.forumComment.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.forumComment.model.*;


public class ForumCommentServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		String actionDelete = req.getParameter("actionDelete");
		
		
//		查詢單筆
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				接收參數
				String str = req.getParameter("articleSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAListOne.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer articleSN = null;
				
				try {
					articleSN = new Integer(str);
				} catch (Exception e){
					errorMsgs.add("留言編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAListOne.jsp");
					failureView.forward(req, res);
					return;
				}
				
//				查詢資料
				ForumCommentService forumCommentSvc = new ForumCommentService();
				List<ForumCommentVO> forumCommentVO = forumCommentSvc.getOneForumComment(articleSN);
				
				if (articleSN == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAListOne.jsp");
					failureView.forward(req, res);
					return;
				}
				
//				查詢轉交
				req.setAttribute("forumCommentVO", forumCommentVO);
//				req.setAttribute("articleSN", articleSN);
				String url = "/forumArticle/fAListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				

//				其他例外處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("留言查詢網頁.jsp");
				failureView.forward(req, res);
			}
		}
		
//		查詢後的更新
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);			
			
			HttpSession session = req.getSession();
			
			try {
//				接收
//				Integer cmtSN =  (Integer)session.getAttribute("cmtSN");
//				Integer articleSN = (Integer)session.getAttribute("articleSN");
//				Integer userID =  (Integer)session.getAttribute("userID");
				
				Integer articleSN = new Integer(req.getParameter("articleSN"));
//				查詢
				ForumCommentService forumCommentSvc = new ForumCommentService();
				List<ForumCommentVO> forumCommentVO = forumCommentSvc.getOneForumComment(articleSN);
//				List<ForumCommentVO> forumCommentVO = forumCommentSvc.getOneForumComment(cmtSN);
				
//				成功後轉交
				req.setAttribute("forumCommentVO", forumCommentVO);
				String url = "/forumArticle/fAListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAListOne.jsp");
				failureView.forward(req, res);
			}
		}
//		更新評論
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			HttpSession session = req.getSession();
			
			try {
//				接收
				
				Integer cmtSN = new Integer(req.getParameter("cmtSN").trim());
				String cmtText = req.getParameter("cmtText");
		
				if (cmtText == null || cmtText.trim().length() == 0) {
					errorMsgs.add("評論內容請勿空白");
				} 			
				Integer userID =  (Integer)session.getAttribute("userID");
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				
				ForumCommentVO forumCommentVO = new ForumCommentVO();
				forumCommentVO.setCmtSN(cmtSN);
				forumCommentVO.setCmtText(cmtText);
				forumCommentVO.setUserID(userID);
				forumCommentVO.setArticleSN(articleSN);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumCommentVO", forumCommentVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/forumComment.jsp");
					failureView.forward(req, res);
					return; 
				}
//				更改
				ForumCommentService forumCommentSvc = new ForumCommentService();
				forumCommentVO = forumCommentSvc.updateForumComment(cmtSN, articleSN, userID, cmtText);
//				轉交
				req.setAttribute("forumCommentVO", forumCommentVO);
				req.setAttribute("articleSN", articleSN);
				String url = "/forumArticle/fAListOne.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAListOne.jsp");

				failureView.forward(req, res);
			}				
		}
		
//		新增評價
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			HttpSession session = req.getSession();
			
			try {
//				接收請求
				
				String cmtText = req.getParameter("cmtText");
				if (cmtText == null || cmtText.trim().length() == 0) {
					errorMsgs.add("評論請勿空白");
				} 
				
				Integer userID =  (Integer)session.getAttribute("userID");
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				
				ForumCommentVO forumCommentVO = new ForumCommentVO();
				forumCommentVO.setCmtText(cmtText);
				forumCommentVO.setUserID(userID);

				forumCommentVO.setArticleSN(articleSN);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumCommentVO", forumCommentVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAListOne.jsp");
					failureView.forward(req, res);
					return; 
				}
				
//				新增
				ForumCommentService forumCommentSvc = new ForumCommentService();
				forumCommentVO = forumCommentSvc.addForumComment(articleSN, userID, cmtText);
//				轉交
				req.setAttribute("articleSN", articleSN);
				String url = "/forumArticle/fAListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAListOne.jsp");
				failureView.forward(req, res);
			}				
		}
		
//		刪除留言
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				接收
				Integer cmtSN = new Integer(req.getParameter("cmtSN"));
				Integer articleSN = new Integer(req.getParameter("articleSN"));
				
//				刪除
				ForumCommentService forumCommentSvc = new ForumCommentService();
				forumCommentSvc.deleteForumComment(cmtSN);
				req.setAttribute("articleSN", articleSN);
//				成功後轉交
				String url = "/forumArticle/fAListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
//				其他錯誤處理	
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAListOne.jsp");
				failureView.forward(req, res);
			}		
		}	
	}
}
