package com.party.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.partymember.model.PartyMemberService;

@WebServlet("/party/partyPic.do")
public class PartyPic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
					throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String action = req.getParameter("action");
		PartyMemberService partyMemberSvc = new PartyMemberService();
		
		
//  ============== 會員審核報名資格 =============
		
		if ("certificationPic".equals(action)) {
			Integer sn = Integer.parseInt(req.getParameter("sn"));
			byte[] pic = (partyMemberSvc.findByPartyMemberSN(sn).getCertificationPic());
			
			if (pic != null) {
				out.write(pic);
			} else {
//可放張代替的圖~
				out.write('a');
			}
		}
	}

}
