package com.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MailService;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/member/Heisenberg.do")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		List<String> errMsg = new LinkedList<String>();
		req.setAttribute("errMsg", errMsg);
		
		try {
			// 驗證帳號是否正確
//			System.out.println(req.getContextPath());
//			System.out.println("forgetpassword doPost有收到");
			String account = req.getParameter("account");
			System.out.println(account);
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.findByAccount(account);
			System.out.println(memberVO);
			if(memberVO == null) {
				errMsg.add("請確認帳號是否正確");
				RequestDispatcher failureView = req.getRequestDispatcher("/member/forgetpassword.jsp");
				failureView.forward(req, res);
				return;
			}
			
			String to = account;
			String ch_name = memberVO.getUserName();
			System.out.println(ch_name);
			
			// 產生隨機密碼
			MailService mailService = new MailService();
			String pwd = mailService.genRandomNum();	
			// 更新 DB 密碼
			memberSvc.ForgetPwd(account, pwd);
			System.out.println(account+ pwd);
			// 寄送 Email
			String messageText = ch_name + "您好，\n " + " 您的密碼如下: " + pwd + "\n" + "您的密碼已經變更";
			mailService.sendMail(to, messageText);
			System.out.println("success");
			
			res.sendRedirect(req.getContextPath() + "/member/passwordreset.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("失敗"+ e.getMessage());
			res.sendRedirect(req.getContextPath()+"/member/forgetpassword.jsp");		// back to index
		}

	}

}



