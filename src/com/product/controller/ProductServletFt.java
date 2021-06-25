package com.product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("/ProductServletFt")
@MultipartConfig
public class ProductServletFt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		HttpSession session = request.getSession();
		
		if ("getProductByClass".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = request.getParameter("productClass");
				if (str.trim().length() == 0) {
					errorMsgs.add("請輸入查詢資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = request.getRequestDispatcher("/product/ft_searchProduct.jsp");
					failView.forward(request, response);
					return;
				}
				System.out.println(str);
				ProductService productSvc = new ProductService();
				List<ProductVO> list = productSvc.getOneClassProduct(str);

				request.setAttribute("list", list);	
				String url = "/product/ft_listClassProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("查無資料" + e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/product/ft_searchProduct.jsp");
				failView.forward(request, response);
			}
		}
	}

}
