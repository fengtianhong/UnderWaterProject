package com.diveinfo.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.diveinfo.model.*;

@WebServlet("/diveinfo/ShowPic")
public class ShowPic extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		
		try {
			Integer pointSN = new Integer(req.getParameter("pointSN"));
			DiveInfoService  diveinfoSvc = new DiveInfoService();
			DiveInfoVO diveinfoVo = diveinfoSvc.getOneDiveInfo(pointSN);
			byte[] pic = diveinfoVo.getPic();
			 	
			out.write(pic);
		}catch(Exception e){
			InputStream in = getServletContext().getResourceAsStream("/diveinfo/images/404_urchin&reef.PNG");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}finally {
			out.close();
		}
	}

}
