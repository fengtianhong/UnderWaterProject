package com.member.controller;

import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
@MultipartConfig
public class MemberServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)	throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		try {//帳號
			String account = null;
			account = req.getParameter("account").trim();
			if(account == null || account.length() == 0) {
				account = "";
				errorMsgs.add("請輸入帳號");
			}
			System.out.println(account);
			String pwd = null;
			pwd = req.getParameter("pwd").trim();
			if(pwd == null || pwd.length() == 0) {
				pwd = "";
				errorMsgs.add("請輸入密碼");
			}
			System.out.println(pwd);
			String nickName = null;
			nickName = req.getParameter("nickName").trim();
			if(nickName == null || nickName.length() == 0) {
				nickName = "";
				errorMsgs.add("請輸入暱稱");
			}
			System.out.println(nickName);
			String userName = null;
			userName = req.getParameter("userName").trim();
			if(userName == null || userName.length() == 0) {
				userName = "";
				errorMsgs.add("請輸入姓名");
			}
			System.out.println(userName);
			String gender = null;
			gender = req.getParameter("gender").trim();
			System.out.println(gender);
			Date birthDate = null;
			String certification = null;
			birthDate = Date.valueOf(req.getParameter("birthDate").trim());
			String phone = req.getParameter("phone").trim();
			certification = req.getParameter("certification").trim();
			Part part = req.getPart("certificationPic");
			
			byte[] certificationPic = null;
			InputStream in = part.getInputStream();
			certificationPic = new byte[in.available()];
			in.read(certificationPic);
			in.close();
			String personID = null;
			personID = req.getParameter("personID").trim();
			String  address = null;
			address = req.getParameter("address").trim();
			
			MemberVO memberVO = new MemberVO();
			memberVO.setAccount(account);
			memberVO.setPwd(pwd);
			memberVO.setNickName(nickName);
			memberVO.setUserName(userName);
			memberVO.setGender(gender);
			memberVO.setBirthDate(birthDate);
			memberVO.setPhone(phone);
			memberVO.setCertification(certification);
			memberVO.setCertificationPic(certificationPic);
			memberVO.setPersonID(personID);
			memberVO.setAddress(address);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("MemberVO", memberVO);
				req.getRequestDispatcher("/member/register.jsp").forward(req, res);
				return;
			}
			
			MemberService memberService = new MemberService();
			memberVO = memberService.insertMember(account, pwd, nickName, userName, gender, birthDate, phone, certification, certificationPic, personID, address);
			req.getRequestDispatcher("/member/index.jsp").forward(req, res);
			
			
			
		}catch(Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			req.getRequestDispatcher("register.jsp").forward(req, res);
			
		}
	}
}
