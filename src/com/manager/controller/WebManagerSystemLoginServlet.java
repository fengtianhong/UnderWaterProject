package com.manager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.model.ManagerService;


@WebServlet("/WebManagerSystemLoginServlet")
public class WebManagerSystemLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public WebManagerSystemLoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String account = req.getParameter("account");
		String pwd = req.getParameter("pwd");
//		System.out.println(account);
//		System.out.println(pwd);
		ManagerService managersvc = new ManagerService();
		Boolean vo = managersvc.loginManager(account, pwd);
//		System.out.println(vo);
		
		
		if(vo == true) {
			session.setAttribute("account", account);
			String location = (String)session.getAttribute("location");
//			System.out.println("有跑到");
			if(location != null) {
				session.removeAttribute("location");
				res.sendRedirect(location);
				return;
			}else{
				System.out.println("進系統");
				req.getRequestDispatcher("/backend.jsp").forward(req, res);
			}
			
						
		}else {
//			System.out.println("錯誤");
			req.setAttribute("msg", "帳號密碼錯誤 請重新輸入");
			req.getRequestDispatcher("/member/webManagerSystemLogin.jsp").forward(req, res);
		}
	}

}
