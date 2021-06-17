package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
		System.out.println("有跑到");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String account = req.getParameter("account");
		String pwd = req.getParameter("pwd");
//		MemberVO vo = new MemberService().loginMember(account, pwd);
		MemberService membersvc = new MemberService();
		Boolean  vo = membersvc.loginMember(account, pwd);
		System.out.println(vo);
		if (vo == true) {
			req.getRequestDispatcher("success.jsp").forward(req, res);
		} else {
			req.getRequestDispatcher("error.jsp").forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		String account = req.getParameter("account");
		String password = req.getParameter("password");

	}

}
