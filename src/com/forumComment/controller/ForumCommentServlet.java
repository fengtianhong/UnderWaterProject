package com.forumComment.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forumComment.model.*;
import com.forumRate.model.ForumRateVO;

public class ForumCommentServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		查詢單筆
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				接收參數
				String str = req.getParameter("cmtSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("留言查詢網頁.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer cmtSN = null;
				
				try {
					cmtSN = new Integer(str);
				} catch (Exception e){
					errorMsgs.add("評價編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("評論查詢網頁.jsp");
					failureView.forward(req, res);
					return;
				}
				
//				查詢資料
				ForumCommentService forumCommentSvc = new ForumCommentService();
				ForumCommentVO forumCommentVO = forumCommentSvc.getOneForumComment(cmtSN);
				
				if (cmtSN == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("評論查詢網頁.jsp");
					failureView.forward(req, res);
					return;
				}
				
//				查詢轉交
				req.setAttribute("forumCommentVO", forumCommentVO);
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
		
//		查詢後的更新
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);			
			
			try {
//				接收
				Integer cmtSN = new Integer(req.getParameter("cmtSN"));
//				查詢
				ForumCommentService forumCommentSvc = new ForumCommentService();
				ForumCommentVO forumCommentVO = forumCommentSvc.getOneForumComment(cmtSN);
//				成功後轉交
				req.setAttribute("forumCommentVO", forumCommentVO);
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
//		更新評論
		if ("update".endsWith(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				接收
				Integer cmtSN = new Integer(req.getParameter("cmtSN").trim());
				
				String cmtText = req.getParameter("cmtText");
				String cmtTextReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
				if (cmtText == null || cmtText.trim().length() == 0) {
					errorMsgs.add("評論內容請勿空白");
				} else if(!cmtText.trim().matches(cmtTextReg)) { 
					errorMsgs.add("評論內容只能是中、英文字母、數字和_ , 且長度必需在1到50之間");
	            }				
				
				Integer userID = new Integer(req.getParameter("userID").trim());
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				
				ForumCommentVO forumCommentVO = new ForumCommentVO();
				forumCommentVO.setCmtSN(cmtSN);
				forumCommentVO.setCmtText(cmtText);
				forumCommentVO.setUserID(userID);
				forumCommentVO.setArticleSN(articleSN);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumCommentVO", forumCommentVO);
					RequestDispatcher failureView = req.getRequestDispatcher("評論更新.jsp");
					failureView.forward(req, res);
					return; 
				}
//				更改
				ForumCommentService forumCommentSvc = new ForumCommentService();
				forumCommentVO = forumCommentSvc.updateForumComment(cmtSN, articleSN, userID, cmtText);
//				轉交
				req.setAttribute("forumCommentVO", forumCommentVO);
				String url = "該放到哪.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("評價列表網頁.jsp");
				failureView.forward(req, res);
			}				
		}
		
//		新增評價
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				接收
				String cmtText = req.getParameter("cmtText");
				String cmtTextReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
				if (cmtText == null || cmtText.trim().length() == 0) {
					errorMsgs.add("評論請勿空白");
				} else if(!cmtText.trim().matches(cmtTextReg)) {
					errorMsgs.add("評論只能是中、英文字母、數字和_ , 且長度必需在1到50之間");
	            }
				
				Integer userID = new Integer(req.getParameter("userID").trim());
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				
				ForumCommentVO forumCommentVO = new ForumCommentVO();
				forumCommentVO.setCmtText(cmtText);
//				forumCommentVO.setUserID(userID);
//				會員先寫死試試看功能
				forumCommentVO.setUserID(2);
				forumCommentVO.setArticleSN(articleSN);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumCommentVO", forumCommentVO);
					RequestDispatcher failureView = req.getRequestDispatcher("評論更新.jsp");
					failureView.forward(req, res);
					return; 
				}
				
//				新增
				ForumCommentService forumCommentSvc = new ForumCommentService();
				forumCommentVO = forumCommentSvc.addForumComment(articleSN, userID, cmtText);
//				轉交
				String url = "我還不知道.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
//				其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("評價列表網頁.jsp");
				failureView.forward(req, res);
			}				
		}
		
//		刪除評論
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
//				接收
				Integer cmtSN = new Integer(req.getParameter("cmtSN"));
//				刪除
				ForumCommentService forumCommentSvc = new ForumCommentService();
				forumCommentSvc.deleteForumComment(cmtSN);
//				成功後轉交
				String url = "轉交去哪呢.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
//				其他錯誤處理	
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("評價列表網頁.jsp");
				failureView.forward(req, res);
			}		
		}	
	}
}
