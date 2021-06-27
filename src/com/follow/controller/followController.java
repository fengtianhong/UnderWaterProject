package com.follow.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.follow.model.FollowService;

@WebServlet("/follow/follow.do")
public class followController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("friend".equals(action)) {

			PrintWriter out = res.getWriter();

			try {
				// 傳入 UserID, GroupTourSN
				Integer userID = new Integer(req.getParameter("userID").trim());
				Integer followed = new Integer(req.getParameter("followed").trim());// 抓使用者名稱

				// 查詢資料
				FollowService folSvc = new FollowService();
				List<Integer> list = folSvc.findFollowed(userID);
				System.out.println(list);
				if (list.contains(followed)) {
					System.out.println("????");
					folSvc.deleteFollow(userID, followed);
					out.print("delete");
				} else {
					System.out.println("!!!!");
					folSvc.addFollow(userID, followed);
					out.print("add");
				}
				return;

			} catch (Exception e) {
				e.printStackTrace(); //
				System.out.println("add collection failure" + e.getMessage());
				out.print("fail");
			}

		}

	}
}
