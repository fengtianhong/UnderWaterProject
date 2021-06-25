package com.product.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("/product/GetPhoto.do")
public class ShowPhoto extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
			
		try {
			Integer productSN = new Integer(req.getParameter("productSN"));
			ProductService  productSvc = new ProductService();
			ProductVO productVO = productSvc.getOneProduct(productSN);
			byte[] productPhoto = productVO.getProductPhoto();		 	
			out.write(productPhoto);
			
		}catch(Exception e){
			System.out.println(e);	// 給錯時錯誤會印在 console > 不優

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

