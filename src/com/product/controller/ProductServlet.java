package com.product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("ProductServlet")
public class ProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("getOne_For_DisPlay".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = request.getParameter("productSN");
				if (str.trim().length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = request.getRequestDispatcher("/product/product_select.jsp");
					failView.forward(request, response);
					return;
				}

				Integer productSN = null;

				try {
					productSN = new Integer(str);
				} catch (NumberFormatException e) {
					errorMsgs.add("格式輸入不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = request.getRequestDispatcher("/product/product_select.jsp");
					failView.forward(request, response);
					return;
				}

				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(productSN);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = request.getRequestDispatcher("/product/product_select.jsp");
					failView.forward(request, response);
					return;
				}
				
				request.setAttribute("productVO", productVO);
				String url = "/product/listOneProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);			

			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/product/product_select.jsp");
				failView.forward(request, response);				
			} 
		}
	}

}
