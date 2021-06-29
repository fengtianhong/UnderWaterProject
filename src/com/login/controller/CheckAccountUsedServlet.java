package com.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;

/**
 * Servlet implementation class CheckAccountUesdServlet
 */
@WebServlet("/CheckAccountUesdServlet")
public class CheckAccountUsedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CheckAccountUsedServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("checkaccountused".equals(action)) {
			
			try {
				HttpSession session = req.getSession();
				req.setCharacterEncoding("UTF-8");
				res.setContentType("text/html;charset=UTF-8");
				String account = req.getParameter("account");
				MemberService memberSvc = new MemberService();
				Boolean vo = memberSvc.checkAccountMember(account);
				
				if(vo == false) {
					session = req.getSession();
					req.setAttribute("msg", "帳號尚未被註冊");
					return;
				}else {
					req.setAttribute("msg", "帳號已經被使用");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
