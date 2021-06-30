package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/MemberInfoServlet")
public class MemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;chartset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
//		System.out.println(keyword);
		
		//查詢開始
		if("search_account".equals(action)) {
			System.out.println("有跑到");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String account = req.getParameter("account");
				if(account == null || (account.trim().length() ==0)) {
					errorMsgs.add("請輸入email帳號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/personinfo.jsp");
					return;
				}
				
				//老師這版有需要嗎
//				Integer empno = null;
//				try {
//					empno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
				
				//開查資料
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.findByAccount(account);
				if(memberVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/personinfo.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("memberVO", memberVO);
				String url = "/member/personinfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得資訊" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/personinfo.jsp");
				failureView.forward(req, res);
			}
		}
		//========查詢結束========
		if("update".equals(action)) {
			
		}
		
		
	}

}
