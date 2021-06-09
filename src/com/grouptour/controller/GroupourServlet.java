package com.grouptour.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GroupourServlet")
public class GroupourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupourServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		// GP listAll.jsp (For 商品列表跟後台)
		
		// 套裝行程頁面 顯示單一商品
		if ("getOne_ForDisplay".equals(action)) {
			
		}
		
		// 後台新增頁面的請求
		if ("insert".equals(action)) {	
			
		}
		
		// 後台更新頁面的請求     1. getOne_ForUpdate  2. update
		if ("getOne_ForUpdate".equals(action)) {
			
		}
		
		if ("update".equals(action)) {	
			
		}
	
	}

}
