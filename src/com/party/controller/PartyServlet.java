package com.party.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.party.model.PartyService;
import com.party.model.PartyVO;
import com.partymember.model.PartyMemberService;
import com.partymember.model.PartyMemberVO;

public class PartyServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
		
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		PartyService partySvc = new PartyService();
		PartyMemberService partyMemberSvc = new PartyMemberService();
//		PrintWriter out = res.getWriter();
		
		if ("getAllBy".equals(action)) {
			String keyword = req.getParameter("keyword");
			Integer pointSN = Integer.parseInt(req.getParameter("pointSN"));
			Integer partyMinSize = Integer.parseInt(req.getParameter("partyMinSize"));
			List<PartyVO> listBySearch = partySvc.findBySearch(keyword, pointSN, partyMinSize);
			
			HttpSession session = req.getSession();
			session.setAttribute("listBySearch", listBySearch);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyList.jsp");
			successView.forward(req, res);
		}
		
		if ("partyDetail".equals(action)) {
			Integer partySN = Integer.parseInt(req.getParameter("partySN"));
			PartyVO partyVO = partySvc.findByPartySN(partySN);
			
			List<PartyMemberVO> partyMemberList = partyMemberSvc.findByPartySN(partyVO.getPartySN());
			req.setAttribute("partyVO", partyVO);
			req.setAttribute("partyMemberList", partyMemberList);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyDetail.jsp");
			successView.forward(req, res);
		}
		
		if ("goBack".equals(action)) {
			RequestDispatcher successView = req.getRequestDispatcher("/party/partyList.jsp");
			successView.forward(req, res);
		}
		
		if ("goRegister".equals(action)) {
			Integer partySN = Integer.parseInt(req.getParameter("partySN"));
			PartyVO partyVO = partySvc.findByPartySN(partySN);
			req.setAttribute("partyVO", partyVO);
			
			RequestDispatcher successView = req.getRequestDispatcher("/party/goRegister.jsp");
			successView.forward(req, res);
		}
		
		
		// 跳出Party相關記得把session.invalidate();
		
	}

}
