package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;

public class LoginServlet extends HttpServlet {
	
	public LoginServlet() {
		super();
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		System.out.println("有跑到");
		
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String account = req.getParameter("account");
		String pwd = req.getParameter("pwd");
		MemberService membersvc = new MemberService();
		Boolean  vo = membersvc.loginMember(account, pwd);
//		System.out.println(vo);
		PrintWriter out = res.getWriter();
		String check = session.getAttribute("randomString").toString();
		String checknum = req.getParameter("checknum");
		
		if (vo == true) {
			if(check.equals(checknum)){
//				System.out.println(check);
//				System.out.println(checknum);
				req.setAttribute("msg", "驗證碼正確");
				req.setAttribute("account", account);
				req.getRequestDispatcher("cookie.jsp").forward(req, res);
			}else {
//				System.out.println(check);
//				System.out.println(checknum);
				req.setAttribute("msg", "驗證碼不正確");
				req.getRequestDispatcher("login.jsp").forward(req, res);
			}
		} else {
//			System.out.println(check);
//			System.out.println(checknum);
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
		

	}

}
