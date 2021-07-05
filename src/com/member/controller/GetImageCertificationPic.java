package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/member/GetImageCertificationPic.do")
public class GetImageCertificationPic extends HttpServlet {

	public GetImageCertificationPic() {
		
	}
	
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		doPost(req,res);
	}
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
//			System.out.println("Getimage");
			Integer memberUserID = new Integer(req.getParameter("userid"));
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getone(memberUserID);
			byte[] b = memberVO.getCertificationPic();
			out.write(b);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
		
	}
}
