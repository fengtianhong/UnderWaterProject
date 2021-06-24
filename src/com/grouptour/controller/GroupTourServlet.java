package com.grouptour.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.grouptour.model.*;

@MultipartConfig
@WebServlet("/grouptour/grouptour.do")
public class GroupTourServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		// GP BackEndlistAll.jsp (For 商品列表跟後台) > 這個好像不用
		
		// 套裝行程頁面 顯示單一商品
		if ("getOne_ForDisplay".equals(action)) {
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			try {
				Integer groupTourSN =  new Integer(req.getParameter("groupTourSN"));
				GroupTourService groupTourSvc = new GroupTourService();
				GroupTourVO groupTourVO = groupTourSvc.getOne(groupTourSN);
				
				req.setAttribute("groupTourVO", groupTourVO);
				RequestDispatcher successView = req.getRequestDispatcher("/grouptour/frontendListOne.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				System.out.println("update failure"+ e.getMessage());
				errMsg.add("取得資料失敗，"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/grouptour/frontendListAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 後台新增頁面的請求 	1. addGT.jsp
		if ("insert".equals(action)) {	// 
			List<String> errMsg = new LinkedList<String>();
			
			//================================Parameter===================================
			try {
				String tourName = req.getParameter("tourName").trim();
				if(tourName==null || tourName.length()==0) {
					errMsg.add("請輸入行程名稱");
				}
				
				byte[] tourPic = null;
				try {
					Part part = req.getPart("tourPic");
					InputStream in = part.getInputStream();
					tourPic = new byte[in.available()];
					in.read(tourPic);
					in.close();
					
				}catch(Exception e) {
					e.printStackTrace();
					errMsg.add("圖片讀取錯誤"+e.getMessage());
				}
			
				
				Date startTime = null;
				try {
					startTime = Date.valueOf(req.getParameter("startTime").trim());
				}catch(IllegalArgumentException ie) {
					startTime = new Date(System.currentTimeMillis());
					errMsg.add("請輸入行程開始日期");
				}
				
				
				Date endTime = null;
				try {
					endTime = Date.valueOf(req.getParameter("endTime").trim());
				}catch(IllegalArgumentException e) {
					endTime = new Date(System.currentTimeMillis());
					errMsg.add("請輸入行程結束日期");
				}
				
				
				Date regTime = null;
				try {
					regTime = Date.valueOf(req.getParameter("regTime").trim());
				}catch(IllegalArgumentException e) {
					regTime = new Date(System.currentTimeMillis());
					errMsg.add("請輸入報名開始日期");
				}
				
				
				Date closeTime = null;
				try {
					closeTime = Date.valueOf(req.getParameter("closeTime").trim());
				}catch(IllegalArgumentException e) {
					closeTime = new Date(System.currentTimeMillis());
					errMsg.add("請輸入報名結束日期");
				}
				
				
				Integer pointSN = new Integer (req.getParameter("pointSN").trim());
				
				
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
					if(price == null || price < 0) {
						errMsg.add("請輸入正確價格");
					}
				} catch (NumberFormatException e) {
					price = 0;
					errMsg.add("請輸入正確價格");
				}
				
				
				Integer limitNumder = null;
				try {
					limitNumder = new Integer (req.getParameter("limitNumder").trim());
					if(limitNumder == null || limitNumder < 0) {
						limitNumder = 1;
						errMsg.add("請確認人數限制");
					}
				} catch (NumberFormatException e) {
					limitNumder = 1;
					errMsg.add("請確認人數限制");
				}
				
				
				String certificationLimit = req.getParameter("certificationLimit").trim();
				String certificationLimitReg = "^\\d$";
				if(certificationLimit == null || certificationLimit.trim().length() == 0) {
					errMsg.add("請確認證照資訊");
				}else if(!certificationLimit.trim().matches(certificationLimitReg)) {
					errMsg.add("證照資訊輸入錯誤");
				}
				
				
				String status = req.getParameter("status").trim();
				String statusReg = "^\\d$";
				if(status == null || certificationLimit.trim().length() == 0) {
					errMsg.add("請確認行程狀態");
				}else if(!status.trim().matches(statusReg)) {
					errMsg.add("行程狀態輸入錯誤");
				}
				
				
				// ckeditor  NOY YET
				String content = req.getParameter("content").trim();
				if(content==null || content.length()==0) {
					errMsg.add("請輸入行程內容");
				}
				
				//===================================================================
				GroupTourVO groupTourVO = new GroupTourVO();
				groupTourVO.setTourName(tourName);
				groupTourVO.setTourPic(tourPic);
				groupTourVO.setStartTime(startTime);
				groupTourVO.setEndTime(endTime);
				groupTourVO.setRegTime(regTime);
				groupTourVO.setCloseTime(closeTime);
				groupTourVO.setPointSN(pointSN);
				groupTourVO.setPrice(price);
				groupTourVO.setLimitNumder(limitNumder);
				groupTourVO.setCertificationLimit(certificationLimit);
				groupTourVO.setStatus(status);
				groupTourVO.setContent(content);
				
				// 有錯則中斷
				if(!errMsg.isEmpty()) {	
					req.setAttribute("errMsg", errMsg);
					req.setAttribute("groupTourVO", groupTourVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/grouptour/addGT.jsp");
					failureView.forward(req, res);
					return;
				}
				
				GroupTourService groupTourSvc = new GroupTourService();
				groupTourVO = groupTourSvc.insertGroupTour(tourName, tourPic, startTime, endTime, regTime, closeTime, pointSN, price, limitNumder, certificationLimit, status, content);
				req.setAttribute("Msg", "新增成功");
//				System.out.println(groupTourVO);

				RequestDispatcher successView = req.getRequestDispatcher("/grouptour/addGT.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("Exception occured");
				errMsg.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/grouptour/addGT.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		// 後台更新頁面的請求     1. 來自 backendListGT  2. 轉至 updateGT
		if ("getOne_ForUpdate".equals(action)) {
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			try {
				Integer groupTourSN =  new Integer(req.getParameter("groupTourSN"));
				GroupTourService groupTourSvc = new GroupTourService();
				GroupTourVO groupTourVO = groupTourSvc.getOne(groupTourSN);
				
				req.setAttribute("groupTourVO", groupTourVO);
				RequestDispatcher successView = req.getRequestDispatcher("/grouptour/updateGT.jsp");
				successView.forward(req, res);
				return;
				
			}catch(Exception e) {
				e.printStackTrace();   //
				System.out.println("update failure");
				errMsg.add("取得資料失敗，"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/grouptour/backendListGT.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {	
			List<String> errMsg = new LinkedList<String>();
			req.setAttribute("errMsg", errMsg);
			
			try {
				Integer groupTourSN = new Integer(req.getParameter("groupTourSN").trim());
				
				String tourName = req.getParameter("tourName").trim();
				if(tourName==null || tourName.length()==0) {
					errMsg.add("請輸入行程名稱");
				}
				
			
				byte[] tourPic = null;
				InputStream in = null;
				try {
					Part part = req.getPart("tourPic");	// 讀取新圖
					in = part.getInputStream();
					tourPic = new byte[in.available()];
					in.read(tourPic);
					
					if(tourPic.length == 0) {			// 未修正圖片則存取原圖
						GroupTourService groupTourSvc = new GroupTourService();
						GroupTourVO originalVO = groupTourSvc.getOne(groupTourSN);
						tourPic = originalVO.getTourPic();
					}
					
				}catch(Exception e) {
					e.printStackTrace();
					errMsg.add("圖片讀取錯誤"+e.getMessage());
				}finally {
					in.close();
				}
			
				Date startTime = null;
				try {
					startTime = Date.valueOf(req.getParameter("startTime").trim());
				}catch(IllegalArgumentException ie) {
					startTime = new Date(System.currentTimeMillis());
					errMsg.add("請輸入行程開始日期");
				}
				
				
				Date endTime = null;
				try {
					endTime = Date.valueOf(req.getParameter("endTime").trim());
				}catch(IllegalArgumentException e) {
					endTime = new Date(System.currentTimeMillis());
					errMsg.add("請輸入行程結束日期");
				}
				
				
				Date regTime = null;
				try {
					regTime = Date.valueOf(req.getParameter("regTime").trim());
				}catch(IllegalArgumentException e) {
					regTime = new Date(System.currentTimeMillis());
					errMsg.add("請輸入報名開始日期");
				}
				
				
				Date closeTime = null;
				try {
					closeTime = Date.valueOf(req.getParameter("closeTime").trim());
				}catch(IllegalArgumentException e) {
					closeTime = new Date(System.currentTimeMillis());
					errMsg.add("請輸入報名結束日期");
				}
				
				
				Integer pointSN = new Integer (req.getParameter("pointSN").trim());
				
				
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
					if(price == null || price < 0) {
						errMsg.add("請輸入正確價格");
					}
				} catch (NumberFormatException e) {
					price = 0;
					errMsg.add("請輸入正確價格");
				}
				
				
				Integer limitNumder = null;
				try {
					limitNumder = new Integer (req.getParameter("limitNumder").trim());
					if(limitNumder == null || limitNumder < 0) {
						limitNumder = 1;
						errMsg.add("請確認人數限制");
					}
				} catch (NumberFormatException e) {
					errMsg.add("請確認人數限制");
				}
				
				
				Integer attendNumber = null;
				try {
					attendNumber = new Integer (req.getParameter("attendNumber").trim());
					if(attendNumber == null || attendNumber < 0) {
						attendNumber = 0;
						errMsg.add("請確認報名人數");
					}
				} catch (NumberFormatException e) {
					errMsg.add("請確認報名人數");
				}
				
				
				String certificationLimit = req.getParameter("certificationLimit").trim();
				String certificationLimitReg = "^\\d$";
				if(certificationLimit == null || certificationLimit.trim().length() == 0) {
					errMsg.add("請確認證照資訊");
				}else if(!certificationLimit.trim().matches(certificationLimitReg)) {
					errMsg.add("證照資訊輸入錯誤");
				}
				
				
				String status = req.getParameter("status").trim();
				String statusReg = "^\\d$";
				if(status == null || certificationLimit.trim().length() == 0) {
					errMsg.add("請確認行程狀態");
				}else if(!status.trim().matches(statusReg)) {
					errMsg.add("行程狀態輸入錯誤");
				}
				
				
				String content = req.getParameter("content").trim();
				if(content==null || content.length()==0) {
					errMsg.add("請輸入行程內容");
				}
				//===================================================================
				GroupTourVO groupTourVO = new GroupTourVO();
				groupTourVO.setGroupTourSN(groupTourSN);
				groupTourVO.setTourName(tourName);
				groupTourVO.setTourPic(tourPic);
				groupTourVO.setStartTime(startTime);
				groupTourVO.setEndTime(endTime);
				groupTourVO.setRegTime(regTime);
				groupTourVO.setCloseTime(closeTime);
				groupTourVO.setPointSN(pointSN);
				groupTourVO.setPrice(price);
				groupTourVO.setAttendNumber(attendNumber);
				groupTourVO.setLimitNumder(limitNumder);
				groupTourVO.setCertificationLimit(certificationLimit);
				groupTourVO.setStatus(status);
				groupTourVO.setContent(content);

				// 有錯則中斷
				if(!errMsg.isEmpty()) {	
					req.setAttribute("errMsg", errMsg);
					req.setAttribute("groupTourVO", groupTourVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/grouptour/updateGT.jsp");
					failureView.forward(req, res);
					return;
				}
				
				GroupTourService groupTourSvc = new GroupTourService();
				GroupTourVO originalVO = groupTourSvc.getOne(groupTourSN);	
				if(groupTourVO.equals(originalVO)) {
					errMsg.add("未修改任何內容，請再確認");
				}
				
				// 判斷是否有改過資料
				if(!errMsg.isEmpty()) {	
					req.setAttribute("errMsg", errMsg);
					req.setAttribute("groupTourVO", groupTourVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/grouptour/updateGT.jsp");
					failureView.forward(req, res);
					return;
				}
				
				groupTourVO = groupTourSvc.updateGroupTour(groupTourSN, tourName, tourPic, startTime, endTime, regTime, closeTime, pointSN, price, attendNumber, limitNumder, certificationLimit, status, content);
				req.setAttribute("Msg", "修改成功");
				RequestDispatcher successView = req.getRequestDispatcher("/grouptour/backendListGT.jsp");
				successView.forward(req, res);
				
			}catch(Exception e) {
				e.printStackTrace();
				errMsg.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/grouptour/updateGT.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
