package com.grouptour.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grouptour.model.*;

@WebServlet("/grouptour/GroupTourFilter.do")
public class GroupTourFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("filter".equals(action)) {
			System.out.println("do2");
			try {
				GroupTourService groupTourSvc = new GroupTourService();			
				List<Integer> listK = null;
				List<Integer> listL = null;
//				List<Integer> listD = null;
				List<GroupTourVO> listNew = new ArrayList<GroupTourVO>();
				
				// keyword
				String keyword = req.getParameter("keyword").trim();
				if(keyword != null && keyword.length() != 0) {
					listK = groupTourSvc.SearchKeyword(keyword);
				}
				
				// location
				String location = req.getParameter("location").trim();
				if(location != null && location.length() != 0) {
					listL = groupTourSvc.SearchLocation(location);
				}
				
//				// date 先不做
//				Date dateStart = null;
//				Date dateEnd = null;
//				try {
//					dateStart = Date.valueOf(req.getParameter("dateStart"));
//					dateStart = Date.valueOf(req.getParameter("dateEnd"));
//					listD = groupTourSvc.SearchDate(dateStart, dateEnd);
//				}catch(IllegalArgumentException e) {
//				}
				
				// 比對list
				if(listK == null && listL != null) {
					for(Integer groupTourSN : listL) {
						listNew.add(groupTourSvc.getOne(groupTourSN));
					}
				}else if(listK != null && listL == null) {
					for(Integer groupTourSN : listK) {
						listNew.add(groupTourSvc.getOne(groupTourSN));
					}
				}else if(listK != null && listL != null) {
					for(Integer groupTourSN : listK) {
						if(listL.contains(groupTourSN)) {
							listNew.add(groupTourSvc.getOne(groupTourSN));
						}
					}
				}
				
				System.out.println(listNew);
				if(listNew.size() == 0) {
					String msg = "查無行程";
					req.setAttribute("msg", msg);
					RequestDispatcher failureView = req.getRequestDispatcher("/grouptour/frontendListAll.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("listNew", listNew);
				RequestDispatcher successView = req.getRequestDispatcher("/grouptour/frontendListAll.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				System.out.println("update failure"+ e.getMessage());
				e.printStackTrace();
				String msg = "查詢錯誤";
				req.setAttribute("msg", msg);
				RequestDispatcher failureView = req.getRequestDispatcher("/grouptour/frontendListAll.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
