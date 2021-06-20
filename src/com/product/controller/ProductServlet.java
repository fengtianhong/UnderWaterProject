package com.product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.product.model.ProductService;
import com.product.model.ProductVO;

public class ProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("getOneProduct".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = request.getParameter("productSN");
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("請輸入查詢資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = request.getRequestDispatcher("/product/selectProduct.jsp");
					failView.forward(request, response);
					return;
				}

				Integer productSN = null;

				try {
					productSN = new Integer(str);
				} catch (NumberFormatException e) {
					errorMsgs.add("格式輸入錯誤");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = request.getRequestDispatcher("/product/selectProduct.jsp");
					failView.forward(request, response);
					return;
				}

				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(productSN);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = request.getRequestDispatcher("/product/selectProduct.jsp");
					failView.forward(request, response);
					return;
				}

				request.setAttribute("productVO", productVO);
				String url = "/product/listOneProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("查無資料" + e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/product/selectProduct.jsp");
				failView.forward(request, response);
			}
		}

		if ("UpdateProduct".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer productSN = new Integer(request.getParameter("productSN"));

				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(productSN);

				request.setAttribute("productVO", productVO);
				String url = "/product/updateProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/product/listAllProduct.jsp");
				failView.forward(request, response);
			}
		}

		if ("getOne_To_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer productSN = new Integer(request.getParameter("productSN").trim());

				String productClass = request.getParameter("productClass");
				if (productClass == null || productClass.trim().length() == 0) {
					errorMsgs.add("商品類別請勿空白");
				}

				String productName = request.getParameter("productName").trim();
				if (productName == null || productName.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				}

				Integer productPrice = null;
				try {
					productPrice = new Integer(request.getParameter("productPrice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("價格請填數字");
				}

				Integer productQuantity = null;
				try {
					productQuantity = new Integer(request.getParameter("productQuantity").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("數量請填數字");
				}

				String productDetail = request.getParameter("productDetail").trim();
				if (productDetail == null || productDetail.trim().length() == 0) {
					errorMsgs.add("商品說明請勿空白");
				}

				java.sql.Timestamp productCreateTime = null;
				try {
					productCreateTime = java.sql.Timestamp.valueOf(request.getParameter("productCreateTime").trim());
				} catch (IllegalArgumentException e) {
					productCreateTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Boolean productDiscount = null;
				try {
					productDiscount = new Boolean(request.getParameter("productDiscount").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請填true/false");
				}

				Boolean productPrime = null;
				try {
					productPrime = new Boolean(request.getParameter("productPrime").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請填true/false");
				}
				
				Integer ratingPoint = null;
				try {
					ratingPoint = new Integer(request.getParameter("ratingPoint").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("評價總分數請填數字");
				}

				Integer ratingNumber = null;
				try {
					ratingNumber = new Integer(request.getParameter("ratingNumber").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("評價總人數請填數字");
				}

				ProductVO productVO = new ProductVO();
				productVO.setProductSN(productSN);
				productVO.setProductClass(productClass);
				productVO.setProductName(productName);
				productVO.setProductPrice(productPrice);
				productVO.setProductQuantity(productQuantity);
//				productVO.setProductStatus(productStatus);
				productVO.setProductDetail(productDetail);
				productVO.setProductCreateTime(productCreateTime);
				productVO.setProductDiscount(productDiscount);
				productVO.setProductPrime(productPrime);
				productVO.setRatingPoint(ratingPoint);
				productVO.setRatingNumber(ratingNumber);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("productVO", productVO);
					RequestDispatcher failView = request.getRequestDispatcher("/product/updateProduct.jsp");
					failView.forward(request, response);
					return;
				}

				ProductService empSvc = new ProductService();
				productVO = empSvc.updateProduct(productSN, productClass, productName, productPrice, productQuantity,
						productDetail, productCreateTime, productDiscount, productPrime, ratingPoint, ratingNumber);

				request.setAttribute("productVO", productVO);
				String url = "/product/listOneProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/product/updateProduct.jsp");
				failView.forward(request, response);
			}
		}

		if ("insertProduct".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String productClass = request.getParameter("productClass");
				if (productClass == null || productClass.trim().length() == 0) {
					errorMsgs.add("商品類別請勿空白");
				}

				String productName = request.getParameter("productName").trim();
				if (productName == null || productName.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				}

				Integer productPrice = null;
				try {
					productPrice = new Integer(request.getParameter("productPrice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("價格請填數字");
				}

				Integer productQuantity = null;
				try {
					productQuantity = new Integer(request.getParameter("productQuantity").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("數量請填數字");
				}

				String productStatus = request.getParameter("productStatus").trim();
				if (productStatus == null) {
					errorMsgs.add("商品狀態請勿空白");
				}

				String productDetail = request.getParameter("productDetail").trim();
				if (productDetail == null || productDetail.trim().length() == 0) {
					errorMsgs.add("商品說明請勿空白");
				}

				Boolean productDiscount = null;
				try {
					productDiscount = new Boolean(request.getParameter("productDiscount").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("格式輸入錯誤");
				}

				Boolean productPrime = null;
				try {
					productPrime = new Boolean(request.getParameter("productPrime").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("格式輸入錯誤");
				}

				Integer ratingPoint = null;
				try {
					ratingPoint = new Integer(request.getParameter("ratingPoint").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("評價總分數請填數字");
				}

				Integer ratingNumber = null;
				try {
					ratingNumber = new Integer(request.getParameter("ratingNumber").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("評價總人數請填數字");
				}

				ProductVO productVO = new ProductVO();
				productVO.setProductClass(productClass);
				productVO.setProductName(productName);
				productVO.setProductPrice(productPrice);
				productVO.setProductQuantity(productQuantity);
				productVO.setProductStatus(productStatus);
				productVO.setProductDetail(productDetail);
				productVO.setProductDiscount(productDiscount);
				productVO.setProductPrime(productPrime);
				productVO.setRatingPoint(ratingPoint);
				productVO.setRatingNumber(ratingNumber);

				request.setAttribute("productVO", productVO);	// 不管成功與否都要setAttr
//				295行 = 298行 + 304行
				if (!errorMsgs.isEmpty()) {
//					request.setAttribute("productVO", productVO);	// 失敗
					RequestDispatcher failView = request.getRequestDispatcher("/product/insertProduct.jsp");
					failView.forward(request, response);
					return;
				}

//				request.setAttribute("productVO", productVO);	// 成功

				ProductService productSvc = new ProductService();
				productVO = productSvc.insertProduct(productClass, productName, productPrice, productQuantity,
						productStatus, productDetail, productDiscount, productPrime, ratingPoint, ratingNumber);
			
				String url = "/product/listOneProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/product/insertProduct.jsp");
				failView.forward(request, response);
			}
		}

	}

}
