package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;

/**
 * Servlet implementation class CheckAccountServlet
 */
@WebServlet("/CheckAccountServlet")
public class CheckAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CheckAccountServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String btn_account = req.getParameter("btn_account");
		MemberService membersvc = new MemberService();
		Boolean vo = membersvc.checkAccountMember(btn_account);
		String canuse = "可以使用";
		String used = "已經註冊過";
		System.out.println("到檢查驗證碼這裡");
		if(vo == true) {
			System.out.println("上");
			req.setAttribute("used", used);
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}else {
			System.out.println("下");
			req.setAttribute("canuse", canuse);
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
		
	}

}
