package com.locationrate.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/locationrate/locationrate.do")
public class LocationrateServlet extends HttpServlet {

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
				Integer pointSN = new Integer(req.getParameter("pointSN").trim());
				Integer userID = new Integer(req.getParameter("userID").trim());
				Integer rate = new Integer(req.getParameter("rate").trim());
				String rateDetail = req.getParameter("rateDetail").trim();
				
				
				
			}catch(Exception e) {
				e.printStackTrace();   //
				System.out.println("insert failure");
				errMsg.add("更新資料失敗，"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/qa/");
				failureView.forward(req, res);
			}
		}
		if("update".equals(action)) {
			
			
			Integer SN = new Integer(req.getParameter("SN").trim());

		}
		if("delete".equals(action)) {
			
		}
		if("getOne_ForUpdate".equals(action)) {
			
		}
		if("getOne_ForDisplay".equals(action)) {
			
		}
		if("get_ByUserID".equals(action)) {
			
		}
		if("get_ByPointSN".equals(action)) {
			
		}
		
	}

}
