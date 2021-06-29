package com.manager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/WebManagerSystemLogoutServlet")
public class WebManagerSystemLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public WebManagerSystemLogoutServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.getSession().removeAttribute("account");
		res.sendRedirect(req.getContextPath()+"/member/webManagerSystemLogin.jsp");
		
	}

}
