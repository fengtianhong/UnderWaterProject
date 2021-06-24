package com.locationrate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.locationrate.model.LocationrateService;

@WebServlet("/locationrate/locationrate.do")
public class LocationrateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		// 用 ajax
		if("insert".equals(action)) {
			
			PrintWriter out = res.getWriter();
			
			try {
				Integer pointSN = new Integer(req.getParameter("pointSN").trim());
				
				
				Integer userID = null;
				try {
					userID = new Integer(req.getParameter("userID").trim());	// 更新不需要 只是要確認登入
				}catch(NumberFormatException e) {
					out.print("checkLogin");
					return;
				}
						
				Integer rate =  null;
				try {
					rate = new Integer(req.getParameter("rate").trim());
				}catch(NumberFormatException e) {
					out.print("checkRate");
					return;
				}
				
				String rateDetail = req.getParameter("rateDetail").trim();
				if(rateDetail == null || rateDetail.trim().length() == 0) {
					rateDetail = null;
				}
				
				LocationrateService locationrateSvc = new LocationrateService();
				locationrateSvc.addLocationRate(pointSN, userID, rate, rateDetail);
				
				
			}catch(Exception e) {
				e.printStackTrace();   //
				System.out.println("insert fail"+ e.getMessage());
				out.print("fail");
				return;
			}
		}
		if("update".equals(action)) {		// 需要確認是否登入?

			PrintWriter out = res.getWriter();
			
			try {
				
				Integer SN = new Integer(req.getParameter("SN").trim());
				
				
				Integer userID = null;
				try {
					userID = new Integer(req.getParameter("userID").trim());	// 更新不需要 只是要確認登入
				}catch(NumberFormatException e) {
					out.print("checkLogin");
					return;
				}
						
				Integer rate =  null;
				try {
					rate = new Integer(req.getParameter("rate").trim());
				}catch(NumberFormatException e) {
					out.print("checkRate");
					return;
				}
				
				String rateDetail = req.getParameter("rateDetail").trim();
				if(rateDetail == null || rateDetail.trim().length() == 0) {
					rateDetail = null;
				}
				
				LocationrateService locationrateSvc = new LocationrateService();
				locationrateSvc.updateLocationRate(SN, rate, rateDetail);
				
				
			}catch(Exception e) {
				e.printStackTrace();   //
				System.out.println("update fail"+ e.getMessage());
				out.print("fail");
				return;
			}

		}
		if("delete".equals(action)) {		// 需要確認是否登入?
			PrintWriter out = res.getWriter();
			
			try {
				
				Integer SN = new Integer(req.getParameter("SN").trim());
				
				Integer userID = null;
				try {
					userID = new Integer(req.getParameter("userID").trim());	// 更新不需要 只是要確認登入
				}catch(NumberFormatException e) {
					out.print("checkLogin");
					return;
				}
				
				LocationrateService locationrateSvc = new LocationrateService();
				locationrateSvc.deleteLocationRate(SN);
				
				out.print("delete");
				
			}catch(Exception e) {
				e.printStackTrace();   //
				System.out.println("delete fail"+ e.getMessage());
				out.print("fail");
				return;
			}

			
			
		}
		
		// 這個要用 form?  還是不需要? 
		if("getOne_ForUpdate".equals(action)) {
			
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			
			try{
				
			}catch(Exception e) {
				
			}
			Integer userID = null;
			try {
				userID = new Integer(req.getParameter("userID").trim());
			}catch(NumberFormatException e) {
				// 為登入則不帶出評價
				
			}
			
		}
		
		// 另外跳一個潛點評價的頁面
		if("getOne_ForDisplay".equals(action)) {	// <c:if> 抓UserID符合地放上面
			
		}
		if("get_ByPointSN".equals(action)) {	// getByPointSN
			
		}
		
		// 個人資訊 > 評價頁面> 寫在jsp  getByUser
		if("get_ByUserID".equals(action)) {		// getByUserID
			
		}

		
	}

}
