package com.forumArticle.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.forumArticle.model.ForumArticleService;
import com.forumArticle.model.ForumArticleVO;



public class ForumArticleServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		論壇首頁 - 閱讀更多
		//	forumArticle.jsp的請求
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//	接收參數+錯誤處理
			try {
				String str = req.getParameter("articleSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/error.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer articleSN = null;
				try {
					articleSN = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/error.jsp");
					failureView.forward(req, res);
					return;
				}
				//	開始查詢
				ForumArticleService forumArticleSvc = new ForumArticleService();
				ForumArticleVO forumArticleVO = forumArticleSvc.getOneForumArticle(articleSN);
				
				if (forumArticleVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/forumArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				//	查詢完成轉交 - 閱讀文章內文
				req.setAttribute("forumArticleVO", forumArticleVO); // 資料庫取出的forumArticleVO物件,存入req
				req.setAttribute("articleSN", articleSN);
				String url = "/forumArticle/fAListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 fAListOne.jsp
				successView.forward(req, res);
				//	其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/forumArticle.jsp");
				failureView.forward(req, res);
			}			
		}
		
		//	後台使用者刪除文章
		//	
		if ("deleteArticle".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				//	接收參數+錯誤處理
				Integer articleSN = new Integer(req.getParameter("articleSN"));
				//	開始修改
				ForumArticleService forumArticleSvc = new ForumArticleService();
				forumArticleSvc.deleteForumArticle(articleSN);
				//	轉交首頁
				req.setAttribute("articleSN", articleSN);
				String url = "/forumArticle/bFAManage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				//	其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/bFAManage.jsp");
				failureView.forward(req, res);
			}
		}
		//	****************************** 文章閱讀頁面下架文章 ******************************
		//	
		//	文章內頁刪除文章
		if ("hiddenAtricle".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//	接收參數+錯誤處理
			try {
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				Boolean articleStatus = new Boolean(req.getParameter("articleStatus"));

				ForumArticleVO forumArticleVO = new ForumArticleVO();
				forumArticleVO.setArticleSN(articleSN);
				forumArticleVO.setArticleStatus(articleStatus);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumArticleVO", forumArticleVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/bFAManage.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				//	開始修改
					ForumArticleService forumArticleSvc = new ForumArticleService();
					forumArticleSvc.deleteForumArticle(articleSN);
					
				//	修改完成後轉交
					req.setAttribute("forumArticleVO", forumArticleVO);
					String url = "/forumArticle/forumArticle.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
				//	其他錯誤處理	
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/bFAManage.jsp");
				failureView.forward(req, res);
			}
		}
		
		//	****************************** 3-1.使用者查詢單一個後更新 (getOne_For_Update)******************************
		//	
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				//	接收參數+錯誤處理
				Integer articleSN = new Integer(req.getParameter("articleSN"));
				//	開始查詢
				ForumArticleService forumArticleSvc = new ForumArticleService();
				ForumArticleVO forumArticleVO = forumArticleSvc.getOneForumArticle(articleSN);
				//	查詢後轉交update
				req.setAttribute("forumArticleVO", forumArticleVO); // 資料庫取出的forumArticleVO物件,存入req
				String url = "/forumArticle/fAUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 
				successView.forward(req, res);
				//	其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/forumArticle.jsp");
				failureView.forward(req, res);
			}
		}		
		
//		****************************** 3-2.(使用者)更新 (update)******************************	
		if ("userUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//接收參數+錯誤處理
			try {
				Integer articleSN = new Integer(req.getParameter("articleSN"));
				
				String articleTitle = req.getParameter("articleTitle");
				
				if (articleTitle == null || articleTitle.trim().length() == 0) {
					errorMsgs.add("文章標題: 請勿空白");
				} 
								
				String articleText = req.getParameter("articleText").trim();
				if (articleText == null || articleText.trim().length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				}
				
				ForumArticleVO forumArticleVO = new ForumArticleVO();
				forumArticleVO.setArticleSN(articleSN);
				forumArticleVO.setArticleTitle(articleTitle);
				forumArticleVO.setArticleText(articleText);
				

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumArticleVO", forumArticleVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAUpdate.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				//	修改資料
				ForumArticleService forumArticleSvc = new ForumArticleService();
				forumArticleVO = forumArticleSvc.userUpdateForumArticle(articleSN, articleTitle, articleText);
				
				//	修改完成後轉交
				req.setAttribute("forumArticleVO", forumArticleVO);
				String url = "/forumArticle/forumArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAUpdate.jsp");
				failureView.forward(req, res);
			}
		}
//		****************************** 4.新增 (insert)******************************			
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			HttpSession session = req.getSession();
			
			try {
				//接收請求參數 以及 錯誤格式處理
				Integer userID =  (Integer)session.getAttribute("userID");
				String articleTitle = req.getParameter("articleTitle");
				String articleText = req.getParameter("articleText");

				if (articleTitle == null || articleTitle.trim().length() == 0) {
					errorMsgs.add("文章標題：請勿空白");
				} 

				if (articleText == null || articleText.trim().length() == 0) {
					errorMsgs.add("文章內容：請勿空白");
				} 
				
				Integer articleTitleOptSN = new Integer(req.getParameter("articleTitleOptSN").trim());
				
				ForumArticleVO forumArticleVO = new ForumArticleVO();
				forumArticleVO.setArticleTitle(articleTitle);
				forumArticleVO.setArticleText(articleText);
				forumArticleVO.setUserID(userID);
				forumArticleVO.setArticleTitleOptSN(articleTitleOptSN);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumArticleVO", forumArticleVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/forumArticleInsert.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//	新增
				ForumArticleService forumArticleSvc = new ForumArticleService();
				forumArticleVO = forumArticleSvc.addForumArticle(articleTitle, articleText, userID, articleTitleOptSN);
				
				//	新增完成後轉交到forumArticle.jsp(文章列表)
				String url = "/forumArticle/forumArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				//	其他錯誤處理
			} catch  (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/forumArticleInsert.jsp");
				failureView.forward(req, res);
			}
		}	
	}
}
