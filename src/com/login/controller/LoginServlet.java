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

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
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
		String check = session.getAttribute("randomString").toString();
		String checknum = req.getParameter("checknum");
		
		if (vo == true) {
			if(check.equals(checknum)){//驗證碼判斷
				session = req.getSession();			
				req.setAttribute("msg", "驗證碼正確");
				req.setAttribute("account", account);
				MemberVO memberVO = membersvc.findByAccount(account);
				Integer userID =  memberVO.getUserID();
				session.setAttribute("userID", userID);
				
				try {
					String location = (String) session.getAttribute("location");
					if(location != null) {
						session.removeAttribute("location");//有無來源網頁，若有導回原網頁
						res.sendRedirect(location);
						return;
					}
				}catch(Exception e) {//若沒有導回index頁面
					req.getRequestDispatcher("index.jsp").forward(req, res);
				}
			}else {
				req.setAttribute("msg", "驗證碼不正確");
				req.getRequestDispatcher("login.jsp").forward(req, res);
			}
		} else {
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
		

	}

}
