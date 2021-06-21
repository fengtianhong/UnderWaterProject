package com.collections.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.collections.model.CollectionsService;

@WebServlet("/collections/collections.do")
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
			
			
			try {
				// 傳入 UserID, GroupTourSN
				Integer userID = new Integer(req.getParameter("userID").trim());
				Integer groupTourSN = new Integer(req.getParameter("groupTourSN").trim());
				
				// 查詢資料
				CollectionsService colSvc = new CollectionsService();
				List<Integer> list = colSvc.getCollectionsByUserid(userID);
				PrintWriter out = res.getWriter();
				
				if(list.contains(groupTourSN)) {	// 套裝行程存在該使用者收藏時刪除資料
					colSvc.deleteCollections(groupTourSN, userID);
					out.print("delete");
				}else {
					colSvc.addCollections(groupTourSN, userID);	// 或者加入收藏
					out.print("add");
				}
				return;
				
			}catch(Exception e) {
				e.printStackTrace();	//
				System.out.println("failure"+ e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/qa");	// 重新insert
				failureView.forward(req, res);
			}
			

		}
		
	}
}
