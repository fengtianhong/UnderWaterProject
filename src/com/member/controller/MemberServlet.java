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
		try {//帳號
			String account = req.getParameter("account").trim();
			String pwd = req.getParameter("pwd").trim();
			String nickName = req.getParameter("nickName").trim();
			String userName = req.getParameter("userName").trim();
			String gender = req.getParameter("gender").trim();
			
			Date birthDate = null;
			birthDate = Date.valueOf(req.getParameter("birthDate").trim());
			String phone = req.getParameter("phone").trim();
			String certification = req.getParameter("certification").trim();
			Part part = req.getPart("certificationPic");
			
			byte[] certificationPic = null;
			InputStream in = part.getInputStream();
			certificationPic = new byte[in.available()];
			in.read(certificationPic);
			in.close();
			String personID = req.getParameter("personID").trim();
			String address = req.getParameter("address").trim();
			
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
			
			MemberService memberService = new MemberService();
			memberVO = memberService.insertMember(account, pwd, nickName, userName, gender, birthDate, phone, certification, certificationPic, personID, address);
			req.setAttribute("Msg", "新增成功");
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}
