package com.diveinfo.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.diveinfo.model.*;

public class DiveinfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// insert資料
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			// 檢查錯誤
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String pointname = req.getParameter("pointname");
				if (pointname == null || (pointname.trim().length() == 0)) {
					pointname="";
					errorMsgs.add("請輸入潛點名稱");
				}
				Double latitude = null;
				try {
					latitude = new Double(req.getParameter("latitue").trim());
				} catch (Exception e) {
					latitude = 23.24975;
					errorMsgs.add("請輸入緯度");
				}
				Double longitude = null;
				try {
					longitude = new Double(req.getParameter("longitude").trim());
				} catch (Exception e) {
					longitude = 23.24975;
					errorMsgs.add("請輸入經度");
				}
				String view = req.getParameter("view");
				String introduction = req.getParameter("introduction");
				if (introduction == null || (pointname.trim().length() == 0)) {
					errorMsgs.add("請輸入詳細介紹");
					
				}
			
				StringBuilder season = new StringBuilder();
				
				if(req.getParameterValues("season")!=null) {
				for(String s:req.getParameterValues("season")){
					season.append(s);
				}
				}
				
				if (season == null || (season.toString().trim().length() == 0)) {
					errorMsgs.add("請選填季節");
				}
				String local = req.getParameter("local");
				byte[] pic = null;
				
				
				DiveInfoVO diveinfoVO = new DiveInfoVO();
				diveinfoVO.setPointName(pointname);
				diveinfoVO.setLatitude(latitude);
				diveinfoVO.setLongitude(longitude);
				diveinfoVO.setView(view);
				diveinfoVO.setIntroduction(introduction);
				diveinfoVO.setSeason(season.toString());
				diveinfoVO.setPic(pic);
				diveinfoVO.setLocal(local);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("diveinfoVO", diveinfoVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/diveinfo/addDiveinfo.jsp");
					failureView.forward(req, res);
					return;
				}
				//檢查完畢
				DiveInfoService diveinfoSvc = new DiveInfoService();
				diveinfoVO=diveinfoSvc.addDiveInfo(pointname, latitude, longitude, view, introduction, season.toString(), local, pic, 0, 0, "0");
				RequestDispatcher failureView = req.getRequestDispatcher("/diveinfo/Diveinfo.jsp");
				failureView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/diveinfo/addDiveinfo.jsp");
				failureView.forward(req, res);
			}
			

		}

	}

}
