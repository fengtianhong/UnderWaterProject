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

import com.forumArticle.model.ForumArticleService;
import com.forumArticle.model.ForumArticleVO;

public class ForumArticleServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//	****************************** 1.查詢單一個 (getOne_For_Display)******************************
		//	可用在查詢檢舉文章的時候
		//	fASelect.jsp的請求
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
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fASelect.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fASelect.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fASelect.jsp");
					failureView.forward(req, res);
					return;
				}
				//	查詢完成轉交
				req.setAttribute("forumArticleVO", forumArticleVO); // 資料庫取出的forumArticleVO物件,存入req
				String url = "/forumArticle/fAListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 fAListOne.jsp
				successView.forward(req, res);
				//	其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fASelect.jsp");
				failureView.forward(req, res);
			}			
		}
		
		//	****************************** 2-1.查詢單一個後更新 (getOne_For_Update)******************************
		//	fAListAll.jsp的請求
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
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 fAUpdate.jsp
				successView.forward(req, res);
				//	其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAListAll.jsp");
				failureView.forward(req, res);
			}
		}
		//	****************************** 2-2.(管理員)更新 (update)******************************
		//	想法：管理員的更新，應該不是要去更改使用者發表的內容
		//	fAUpdate.jsp的請求
		if ("mUpdate".equals(action)) {
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
					req.setAttribute("forumArticleVO", forumArticleVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAUpdate.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				//	開始修改
					ForumArticleService forumArticleSvc = new ForumArticleService();
					forumArticleVO = forumArticleSvc.mUpdateForumArticle(articleSN, articleStatus);
					
				//	修改完成後轉交
					req.setAttribute("forumArticleVO", forumArticleVO);
					String url = "/forumArticle/fAListOne.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
				//	其他錯誤處理	
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/fAUpdate.jsp");
				failureView.forward(req, res);
			}
		}
		
		//	****************************** 3-1.使用者查詢單一個後更新 (getOne_For_Update)******************************
		//	forumArticle.jsp的請求
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
				String url = "/forumArticle/forumArticleUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 forumArticleUpdate.jsp
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
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				
				String articleTitle = req.getParameter("articleTitle");
				String articleTitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				
				if (articleTitle == null || articleTitle.trim().length() == 0) {
					errorMsgs.add("文章標題: 請勿空白");
				} else if(!articleTitle.trim().matches(articleTitleReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("文章標題: 只能是中、英文字母、數字和_ , 且長度必需在2到15之間");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/forumArticleUpdate.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/forumArticle/forumArticleUpdate.jsp");
				failureView.forward(req, res);
			}
		}
//		****************************** 4.新增 (insert)******************************			
		//	來自forumArticle.jsp的新增發文請求
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接收請求參數 以及 錯誤格式處理
				
				String articleTitle = req.getParameter("articleTitle");
				String articleTitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if (articleTitle == null || articleTitle.trim().length() == 0) {
					errorMsgs.add("文章標題：請勿空白");
				} else if(!articleTitle.trim().matches(articleTitleReg)) {
					errorMsgs.add("文章標題：只能是中、英文字母、數字和_ , 且長度必需在2到15之間");
	            }
				
				String articleText = req.getParameter("articleText");
				String articleTextReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
				if (articleText == null || articleText.trim().length() == 0) {
					errorMsgs.add("文章內容：請勿空白");
				} else if(!articleText.trim().matches(articleTextReg)) {
					errorMsgs.add("文章內容：只能是中、英文字母、數字和_。");
	            }
				
				Integer userID = new Integer(req.getParameter("userID").trim());
				Integer articleTitleOptSN = new Integer(req.getParameter("articleTitleOptSN").trim());
				
				ForumArticleVO forumArticleVO = new ForumArticleVO();
				forumArticleVO.setArticleTitle(articleTitle);
				forumArticleVO.setArticleText(articleText);
				
//				會員先用寫死的
				forumArticleVO.setUserID(1);
//				forumArticleVO.setUserID(userID);
				
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
