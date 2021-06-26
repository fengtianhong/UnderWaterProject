package com.customerreply.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customerreply/customerservice.do")
public class CustomerServiceServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("CSmanager".equals(action)) {
			
			// 後台管理訊息
			try {
				String userID = req.getParameter("userID");
				req.setAttribute("userID", "Manager");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/customerreply/CSmanager.jsp");	//
				dispatcher.forward(req, res);
				
			}catch(Exception e) {
				System.out.println("update failure"+ e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/customerreply/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("CSchat".equals(action)) {
			
			// 使用者點擊聊天室視窗 確認UserID
			try {
				String userID = req.getParameter("userID");
				req.setAttribute("userID", userID);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/customerreply/CSchat.jsp");
				dispatcher.forward(req, res);
				
			}catch(Exception e) {
				System.out.println("update failure"+ e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/customerreply/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
	}
}
