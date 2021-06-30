package com.customerreply.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/customerreply/GetMemberInfo.do")
public class GetMemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		
		PrintWriter out = res.getWriter();
		Integer userID = null;
		try {
			userID = Integer.parseInt(req.getParameter("userID"));
			
			MemberService MemberSvc = new MemberService();
			MemberVO memberVO = MemberSvc.getone(userID);
			String nickName = memberVO.getNickName();
			System.out.println("nickName"+nickName);
			out.print(nickName);
			
		}catch(Exception e) {
			e.printStackTrace();
			out.print(userID +"號使用者");
		}
	}

}
