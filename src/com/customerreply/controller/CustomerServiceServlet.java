package com.customerreply.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/customerreply/customerservice.do")
public class CustomerServiceServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("CSmanager".equals(action)) {
			
			// 後台管理訊息
			try {
				req.setAttribute("userID", "Manager");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/customerreply/CSmanager.jsp");	//
				dispatcher.forward(req, res);
				
			}catch(Exception e) {
				System.out.println("update failure"+ e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/customerreply/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if("CSchat".equals(action)) {
//			
//			// 使用者點擊聊天室視窗 確認UserID
//			try {
//				HttpSession session = req.getSession();
//				Integer userID = (Integer)session.getAttribute("userID");
//				if(userID == null) {
//					RequestDispatcher dispatcher = req.getRequestDispatcher("/member/login.jsp");
//					dispatcher.forward(req, res);
//					return;
//				}
//				RequestDispatcher dispatcher = req.getRequestDispatcher("/customerreply/CSchat.jsp");
//				dispatcher.forward(req, res);
//				
//			}catch(Exception e) {
//				System.out.println("update failure"+ e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/customerreply/index.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		
		
	}
}
