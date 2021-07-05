package com.member.controller;

import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
@MultipartConfig
public class MemberServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	//登入帳號使用
	public void doPost(HttpServletRequest req, HttpServletResponse res)	throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
//			Integer userID = new Integer(req.getParameter("userid").trim());
			
			//帳號
			String account = null;
			account = req.getParameter("account").trim();
			if(account == null || account.length() == 0) {
				account = "";
				errorMsgs.add("請輸入帳號");
			}
			
			
//			System.out.println(account);
			
			//密碼
			String pwd = null;
			pwd = req.getParameter("pwd").trim();
			if(pwd == null || pwd.length() == 0) {
				pwd = "";
				errorMsgs.add("請輸入密碼");
			}
//			System.out.println(pwd);			
			
			//姓名
			String userName = null;
			userName = req.getParameter("userName").trim();
			if(userName == null || userName.length() == 0) {
				userName = "";
				errorMsgs.add("請輸入姓名");
			}
//			System.out.println(userName);
			
			//暱稱
			String nickName = null;
			nickName = req.getParameter("nickName").trim();
			if(nickName == null || nickName.length() == 0) {
				nickName = "";
				errorMsgs.add("請輸入暱稱");
			}
//			System.out.println(nickName);
			
			//身分證字號
			String personID = null;
			personID = req.getParameter("personID").trim();
			//性別
			String gender = null;
			gender = req.getParameter("gender").trim();
//			System.out.println(gender);
			
			
//			SimpleDateFormat sdf = new SimpleDateFormat("E YYYY/MM/dd");
//			DateFormat df = DateFormat.getDateInstance();
//			Date birthDate = (Date) df.parse("1970/1/1");
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(date);
//			System.out.println(df.format(calendar.getTime()));
		
			//生日
			java.sql.Date birthDate = null;
			try {
				birthDate = Date.valueOf(req.getParameter("birthDate").trim());
				System.out.println(birthDate);
			}catch(IllegalArgumentException e) {
				birthDate = Date.valueOf("1970-01-01");
				
			}
			
			
			//電話
			String phone = req.getParameter("phone").trim();
			
			//地址
			String  address = null;
			address = req.getParameter("address").trim();
			
			//證照
			String certification = null;
			certification = req.getParameter("certification").trim();
			
			
			
			
			
			//證照圖片===========================
			byte[] certificationPic = null;
			InputStream in = null;
			try {
				in = null;
				Part part = req.getPart("certificationPic"); 
				in = part.getInputStream();	
				certificationPic = new byte[in.available()];
				in.read(certificationPic);
				
				if(certificationPic.length == 0) {
					in = getServletContext().getResourceAsStream("/member/images/noimage.PNG");
					certificationPic = new byte[in.available()];
					in.read(certificationPic);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
				errorMsgs.add("證照圖片讀取錯誤" + e.getLocalizedMessage());
			}finally {
				in.close();
			}
			
			//個人照片
			in = null;
			byte[] personPhoto = null;
			try {
				
				Part part = req.getPart("personPhoto"); 
				in = part.getInputStream();	
				personPhoto = new byte[in.available()];
				in.read(personPhoto);
				
				if(personPhoto.length == 0) {
					in = getServletContext().getResourceAsStream("/member/images/noimage.PNG");
					personPhoto = new byte[in.available()];
					in.read(personPhoto);
				}
			}catch(Exception e) {
				e.printStackTrace();
				errorMsgs.add("個人照片讀取錯誤" + e.getLocalizedMessage());
			}finally {
				in.close();
			}
						
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
			memberVO.setPersonPhoto(personPhoto);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("MemberVO", memberVO);
				req.getRequestDispatcher("/member/register.jsp").forward(req, res);
//				res.sendRedirect(req.getContextPath()+"/member/register.jsp");
				return;
			}
//			System.out.println();
			String registersuccessMsgs = "註冊成功";//未實現註冊成功alert在畫面上
			req.getSession().setAttribute("registersuccessMsgs", registersuccessMsgs);
			MemberService memberService = new MemberService();
			memberVO = memberService.insertMember(account, pwd, nickName, userName, gender, birthDate, phone, certification, certificationPic, personID, address, personPhoto);
//			req.getRequestDispatcher("/index.jsp").forward(req, res);
			res.sendRedirect(req.getContextPath()+ "/index.jsp");
			
			
			
		}catch(Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			e.printStackTrace();
			req.getRequestDispatcher("register.jsp").forward(req, res);
//			res.sendRedirect(req.getContextPath()+"/member/register.jsp");
			
		}
	}
}
