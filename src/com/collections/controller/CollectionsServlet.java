package com.collections.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.collections.model.CollectionsService;

//@WebServlet("/CollectionsServlet")
public class CollectionsServlet extends HttpServlet {
//	test servlet
//	http://localhost:8081/UnderWaterProject/CollectionsServlet?action=favorite&userID=1&GroupTourSN=6001
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
// 載入網站時 要抓取愛心是否存在 > 寫在JSP但好像有點失敗
		
		
		// 前端按了<3 
		if ("favorite".equals(action)) {
			
			// 傳入 UserID, GroupTourSN
			Integer userID = new Integer(req.getParameter("userID"));
			Integer groupTourSN = new Integer(req.getParameter("GroupTourSN"));
// 請求參數CHECK?
			
			// 查詢資料
			CollectionsService colSvc = new CollectionsService();
			List<Integer> list = colSvc.getCollectionsByUserid(userID);
			
			if(list.contains(groupTourSN)) {	// 套裝行程存在該使用者收藏時刪除資料
				colSvc.deleteCollections(groupTourSN, userID);
				req.setAttribute("Msg", "移除收藏!");
			}else {
				colSvc.addCollections(groupTourSN, userID);	// 或者加入收藏
				req.setAttribute("Msg", "成功加入收藏!");
			}
			RequestDispatcher successView = req.getRequestDispatcher("/collections/test_collections.jsp");
			successView.forward(req, res);
			System.out.println("CollectionsServlet DONE");

		}
		
	}
}
