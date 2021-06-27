package com.login.controller;

import javax.servlet.http.*;

import java.io.IOException;

import javax.servlet.*;

public class LoginFilter implements Filter{

	private FilterConfig config;
	
	public void init(FilterConfig config) {
		System.out.println("init");
		this.config = config;
	}
	
	public void destory() {
		config = null;
	}
	
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		System.out.println("doFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		Object userID = session.getAttribute("userID");
		
		if(userID == null) {
			//若帳號是空值，則導回登入畫面
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/member/login.jsp");
			return;
		}else {
			chain.doFilter(request, response);
		}
	}
	
}
