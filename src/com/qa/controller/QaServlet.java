package com.qa.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qa.model.QaService;
import com.qa.model.QaVO;

@WebServlet("/qa/qa.do")
public class QaServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			
			try {
				
				String menu = req.getParameter("menu").trim();
				if(menu == null || menu.length() == 0) {
					errMsg.add("請確認選單分類");
				}
				
				
				String submenu = req.getParameter("submenu").trim();
				if(submenu == null || submenu.length() == 0) {
					errMsg.add("請確認子選單分類");
				}
				
				
				String system = req.getParameter("system").trim();
				if(system == null || system.length() == 0) {
					errMsg.add("請確認系統分類");
				}
				
				
				String question = req.getParameter("question").trim();
				if(question == null || question.length() == 0) {
					errMsg.add("請確認問題敘述");
				}
				
				
				String answer = req.getParameter("answer").trim();
				if(answer == null || answer.length() == 0) {
					errMsg.add("請確認回答敘述");
				}
				
				Boolean popularQuestion = new Boolean(req.getParameter("popularQuestion"));
//				if (popularQuestion == null) {
//					popularQuestion = false;
//					errMsg.add("popularQuestion");	// null 進資料庫會變0  神奇
//				}
				
				
				QaVO qaVO = new QaVO();
				qaVO.setMenu(menu);
				qaVO.setSubmenu(submenu);
				qaVO.setSystem(system);
				qaVO.setQuestion(question);
				qaVO.setAnswer(answer);
				qaVO.setPopularQuestion(popularQuestion);
				
				
				if(!errMsg.isEmpty()) {
					req.setAttribute("qaVO", qaVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/qa/addQA.jsp");
					failureView.forward(req, res);
					return;
				}
				
				QaService qaSvc = new QaService();
				qaSvc.addQa(menu, submenu, system, question, answer, popularQuestion);
				req.setAttribute("Msg", "新增成功");		// 記得接 Msg
				RequestDispatcher successView = req.getRequestDispatcher("/qa/addQA.jsp");	// 回到QA管理?
				successView.forward(req, res);
				
			}catch(Exception e) {
				e.printStackTrace();	//
				System.out.println("insert failure"+ e.getMessage());
				errMsg.add("Exception occured"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/qa/addQA.jsp");	// 重新insert
				failureView.forward(req, res);
			}
		}
		
		if("getOne_ForUpdate".equals(action)) {	
			
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			
			try {
				Integer questionSN = new Integer(req.getParameter("questionSN").trim());
				QaService qaSvc = new QaService();
				QaVO qaVO = qaSvc.getOne(questionSN);
				
				req.setAttribute("qaVO", qaVO);
				RequestDispatcher successView = req.getRequestDispatcher("/qa/updateQA.jsp");	// 更新頁面
				successView.forward(req, res);
				
			}catch(Exception e) {
				e.printStackTrace();   //
				System.out.println("update failure");
				errMsg.add("取得資料失敗，"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/qa/");
				failureView.forward(req, res);
			}
		}
		if("update".equals(action)) {
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			
			try {
				
				Integer questionSN = new Integer(req.getParameter("questionSN").trim());
				String menu = req.getParameter("menu").trim();
				if(menu == null || menu.length() == 0) {
					errMsg.add("請確認選單分類");
				}
				
				
				String submenu = req.getParameter("submenu").trim();
				if(submenu == null || submenu.length() == 0) {
					errMsg.add("請確認子選單分類");
				}
				
				
				String system = req.getParameter("system").trim();
				if(system == null || system.length() == 0) {
					errMsg.add("請確認系統分類");
				}
				
				
				String question = req.getParameter("question").trim();
				if(question == null || question.length() == 0) {
					errMsg.add("請確認問題敘述");
				}
				
				
				String answer = req.getParameter("answer").trim();
				if(question == null || question.length() == 0) {
					errMsg.add("請確認回答敘述");
				}
				
				
				Integer clicks = null;
				try {
					clicks = new Integer (req.getParameter("clicks").trim());
					if(clicks < 0) {
					errMsg.add("點擊次數需大於0");

					}
				}catch(NumberFormatException e) {
					clicks = null;
					errMsg.add("請確認點擊次數格式");
				}
				
				Boolean popularQuestion = new Boolean(req.getParameter("popularQuestion"));
				Integer popularQuestionSort = null;
				
				QaVO qaVO = new QaVO();
				qaVO.setMenu(menu);
				qaVO.setSubmenu(submenu);
				qaVO.setSystem(system);
				qaVO.setQuestion(question);
				qaVO.setAnswer(answer);
				qaVO.setPopularQuestion(popularQuestion);
				
				
				if(!errMsg.isEmpty()) {
					req.setAttribute("qaVO", qaVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/qa/updateQA.jsp");
					failureView.forward(req, res);
					return;
				}
				
				QaService qaSvc = new QaService();
				qaSvc.updateQa(questionSN, menu, submenu, system, question, answer, clicks, popularQuestion, popularQuestionSort);
				req.setAttribute("Msg", "更新成功");	
				RequestDispatcher successView = req.getRequestDispatcher("/qa/backendList.jsp");	// 回到QA管理
				successView.forward(req, res);
				
			}catch(Exception e) {
				e.printStackTrace();	//
				System.out.println("update failure"+ e.getMessage());
				errMsg.add("Exception occured"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/qa/updateQA.jsp");	// 重新insert
				failureView.forward(req, res);
			}
		}

		if("delete".equals(action)) {
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			
			try{
				Integer questionSN = new Integer(req.getParameter("questionSN").trim());
				QaService qaSvc = new QaService();
				qaSvc.deleteQa(questionSN);
				
				RequestDispatcher successView = req.getRequestDispatcher("/qa/backendList.jsp");	// 回原頁面
				successView.forward(req, res);
				
			}catch(Exception e) {
				e.printStackTrace();
				errMsg.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/qa/backendList.jsp");	// 回原頁面
				failureView.forward(req, res);
			}
		}

		if("getPopularQuestion".equals(action)) {	// 純JSP應該可以
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			
			try {
				QaService qaSvc = new QaService();
				List<QaVO> list = qaSvc.getPopularQuestion();
				
				req.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/qa/updateQA.jsp");	// 更新頁面
				successView.forward(req, res);
				
			}catch(Exception e) {
				e.printStackTrace();   //
				System.out.println("getPopularQuestion failure");
				errMsg.add("取得資料失敗，"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/qa/");
				failureView.forward(req, res);
			}
		}

		
		
	}

}
