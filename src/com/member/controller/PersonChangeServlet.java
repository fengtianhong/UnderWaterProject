package com.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@MultipartConfig//讀取照片黳定需要該行 form表單內也要加enctype="multipart/form-data"
@WebServlet("/PersonChangeServlet")
public class PersonChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PersonChangeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		String action = req.getParameter("action");
		List<String> successMsgs = new LinkedList<String>();
		req.setAttribute("successMsgs", successMsgs);
//		System.out.println(action);
		
		//個人網頁更新
		if("update_info".equals(action)) {
			
			try {
				//抓到personinfochange form送出的userID
				Integer userID = new Integer(req.getParameter("userID"));
//				System.out.println(userID);
				String account = req.getParameter("account");
				if(account == null || account.trim().length() == 0) {
					errorMsgs.add("帳號有誤");
				}
				
				String phone = null;
				phone = req.getParameter(phone);
				
				String nickName = req.getParameter("nickName");
//				System.out.println(nickName);
				if(nickName == null || nickName.trim().length() == 0) {
					errorMsgs.add("暱稱請勿空白");
				}
				
				String userName = req.getParameter("userName");
				if(userName == null || nickName.trim().length() == 0) {
					errorMsgs.add("姓名請勿空白");
				}
				
				//性別選項  不需再篩選
				String gender = req.getParameter("gender");
//				System.out.println();
				
				java.sql.Date birthDate = null;
				try {
					birthDate = Date.valueOf(req.getParameter("birthdate"));
				}catch(IllegalArgumentException e) {
					birthDate = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				//證照選項 不須再篩選
				String certification = req.getParameter("certification");
				
				//選填 則不需篩選
				String personID = null;
				personID = req.getParameter("personID");
				
				String address = null;
				address = req.getParameter("address");
				
				
				byte[] certificationPic = null;
				InputStream in = null;
				
				try {
					Part part = req.getPart("certificationPic");
					in = part.getInputStream();
					certificationPic = new byte[in.available()];
					in.read(certificationPic);
					
					if(certificationPic.length == 0) {
						MemberService memberSvc = new MemberService();
						MemberVO originalVO = memberSvc.getone(userID);
						certificationPic = originalVO.getCertificationPic();
					}
				}catch(Exception e){
					e.printStackTrace();
					errorMsgs.add("證照讀取錯誤" + e.getMessage());
				}finally{
					in.close();
				}
				
				
				byte[] personPhoto = null;
				
				try {
					Part part = req.getPart("personPhoto");
					in = part.getInputStream();
					personPhoto = new byte[in.available()];
					in.read(personPhoto);
					
					if(personPhoto.length == 0) {
						MemberService memberSvc = new MemberService();
						MemberVO originalVO = memberSvc.getone(userID);
						personPhoto = originalVO.getPersonPhoto();
					}
				}catch(Exception e){
					e.printStackTrace();
					errorMsgs.add("個人照片讀取錯誤" + e.getMessage());
				}finally{
					in.close();
				}
				
				MemberVO memberVO = new MemberVO();
				memberVO.setAccount(account);
				memberVO.setAccount(account);
				memberVO.setNickName(nickName);
				memberVO.setUserName(userName);
				memberVO.setGender(gender);
				memberVO.setPhone(phone);
				memberVO.setBirthDate(birthDate);
				memberVO.setCertification(certification);
				memberVO.setPersonID(personID);
				memberVO.setAddress(address);
				memberVO.setCertificationPic(certificationPic);
				memberVO.setPersonPhoto(personPhoto);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					String url = "/member/personinfochange.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.persoInfoUpdateMember(userID, nickName, userName, gender, birthDate, phone, certification, certificationPic, personID, address, personPhoto);
				req.setAttribute("memberVO", memberVO);
//				System.out.println("修改成功之前");
				successMsgs.add("修改成功!!");
				String url = "/member/personinfochange.jsp";
				// 成功轉交給personInfo.jsp
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			}catch(Exception e) {
				System.out.println("error");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/personinfochange.jsp");
				failureView.forward(req, res);
			}
		}
		//============修改密碼
		if("update_pwd".equals(action)) {
			
			try {
			
			MemberService memberSvc = new MemberService();
			Integer userID = new Integer(req.getParameter("userID"));
			MemberVO memberVO = memberSvc.getone(userID);
			String originalpwd = memberVO.getPwd();
//			System.out.println("update_pwd有跑到");
			
			String pwd = req.getParameter("pwd");
//			System.out.println(pwd);
			String newpwd1 = req.getParameter("newpwd1");
//			System.out.println(newpwd1);
			String newpwd2 = req.getParameter("newpwd2");
//			System.out.println(newpwd2);
			if(pwd == null || pwd.trim().length() == 0) {
				errorMsgs.add("原密碼不得空白");
			} 
			if(newpwd1 == null || newpwd1.trim().length() == 0 || newpwd2 == null || newpwd2.trim().length() == 0) {
				errorMsgs.add("新密碼不得空白");
			}
//			System.out.println(newpwd1 + newpwd2);
			if(!(newpwd1.equals(newpwd2))) {
				errorMsgs.add("兩次新密碼輸入有誤");
			}
			
			if(!originalpwd.equals(pwd)) {
				errorMsgs.add("與原始密碼有出入");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/personchangepwd.jsp");
				failureView.forward(req, res);
				return;
			}
			
			memberVO = memberSvc.pwdUpdateMember(userID, newpwd2);
			
			req.setAttribute("memberVO", memberVO);
			String url = "/member/personchangepwd.jsp";
			successMsgs.add("修改成功!!");
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/personchangepwd.jsp");
				failureView.forward(req, res);
			}
		}
			
		
		
		
		
	}
		
}
