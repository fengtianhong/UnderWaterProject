package com.grouptour.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grouptour.model.GroupTourService;
import com.grouptour.model.GroupTourVO;

@WebServlet("/grouptour/GetImage.do")
public class GetImage extends HttpServlet {
       
    public GetImage() {
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			Integer groupTourSN = new Integer (req.getParameter("id"));
			GroupTourService groupTourSvc = new GroupTourService();
			GroupTourVO groupTourVO = groupTourSvc.getOne(groupTourSN);
			byte[] b = groupTourVO.getTourPic();
			out.write(b);
			
		} catch (Exception e) {
//			InputStream in = getServletContext().getResourceAsStream("404_urchin&reef.PNG");
//			byte[] b = new byte[in.available()];
//			in.read(b);
//			out.write(b);
//			in.close();
		}finally {
			out.close();
		}
	}

}
