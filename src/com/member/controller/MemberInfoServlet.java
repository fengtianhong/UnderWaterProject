package com.member.controller;

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

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/MemberInfoServlet")
public class MemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;chartset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		MemberService memberSvc = new MemberService();
//		System.out.println("post有跑道");
		
		//查詢開始
		if("search".equals(action)) {
			String keyword = req.getParameter("keyword");
//			System.out.println("serch有跑到");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String url = "/member/searchmember.jsp";
			String account = req.getParameter("keyword");
			String nickName = req.getParameter("keyword");
			String userName = req.getParameter("keyword");
			List<MemberVO> list = memberSvc.findBySearchMember(account, nickName, userName);
//				System.out.println(list);
			if(list.size() == 0) {
					errorMsgs.add("查無資料，請重新查詢!");
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
			}else {
				req.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}			
		}
		
		if("memberDetail".equals(action)) {
//			System.out.println("memberDetail有跑到");
//			System.out.println(req.getAttribute("userID"));
			Integer userID = new Integer(req.getParameter("userID").trim());
			MemberVO memberVO = memberSvc.getone(userID);
			
			req.setAttribute("memberVO", memberVO);
			String url = "/member/searchmemberdetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
//			System.out.println("有跑到最後");
			successView.forward(req, res);
			return;
		}
		
	}

}
