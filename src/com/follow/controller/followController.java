package com.follow.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.collections.model.CollectionsService;


@WebServlet("/follow/follow.do")
public class followController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		if ("favorite".equals(action)) {
//			
//			PrintWriter out = res.getWriter();
//			
//			try {
//				// 傳入 UserID, GroupTourSN
//				Integer userID = new Integer(req.getParameter("userID").trim());
//				Integer groupTourSN = new Integer(req.getParameter("groupTourSN").trim());
//				
//				// 查詢資料
//				CollectionsService colSvc = new CollectionsService();
//				List<Integer> list = colSvc.getCollectionsByUserid(userID);
//				
//				if(list.contains(groupTourSN)) {				// 套裝行程存在該使用者收藏時刪除資料
//					colSvc.deleteCollections(groupTourSN, userID);
//					out.print("delete");
//				}else {
//					colSvc.addCollections(groupTourSN, userID);	// 或者加入收藏
//					out.print("add");
//				}
//				return;
//				
//			}catch(Exception e) {
//				e.printStackTrace();	//
//				System.out.println("add collection failure"+ e.getMessage());
//				out.print("fail");
//			}
//			
//
//		}
		
	}
}
