package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;

public class LoginServlet extends HttpServlet {

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
		String check = session.getAttribute("randomString").toString();
		String checknum = req.getParameter("checknum");
		
		if (vo == true) {
			if(check.equals(checknum)){//驗證碼判斷
				session = req.getSession();			
//				req.setAttribute("msg", "驗證碼正確");
//				req.setAttribute("account", account);
				MemberVO memberVO = membersvc.findByAccount(account);
				Integer userID =  memberVO.getUserID();
				session.setAttribute("userID", userID);
//				System.out.println("驗證碼正確");
				try {
					String location = (String) session.getAttribute("location");
//					System.out.println(location);
					if(location != null) {
//						System.out.println("有無來源網頁，若有導回原網頁");
						res.sendRedirect(location);
						session.removeAttribute("location");//有無來源網頁，若有導回原網頁
						return;
					}else {
//						System.out.println("若沒有來源網頁,導回index頁面");
//						req.getRequestDispatcher(req.getContextPath() + "/index.jsp").forward(req, res);
						res.sendRedirect(req.getContextPath());
						return;
					}
				}catch(Exception e) {//若沒有,導回index頁面
//					System.out.println("沒有,導回index頁面");
					res.sendRedirect(req.getContextPath());
				}
			}else {
				System.out.println("驗證碼不正確");
				req.setAttribute("msg", "驗證碼不正確");
				req.getRequestDispatcher("login.jsp").forward(req, res);
			}
		} else {
			System.out.println("帳號密碼錯誤");
			req.setAttribute("msg", "帳號密碼錯誤");
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
		

	}

}
