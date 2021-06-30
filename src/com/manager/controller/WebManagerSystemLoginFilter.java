package com.manager.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebManagerSystemLoginFilter implements Filter{

	private FilterConfig config;
	
	public void init(FilterConfig config) {
		this.config = config;
	}
	
	public void destory() {
		config = null;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		
		Object account = session.getAttribute("account");
		
		if(account == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/member/webManagerSystemLogin.jsp");
			return;
		}else {
			chain.doFilter(req, res);
		}
		
	}

}
