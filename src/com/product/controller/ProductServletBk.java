package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.diveinfo.model.DiveInfoService;
import com.diveinfo.model.DiveInfoVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

public class ProductServletBk extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
// 圖片		
		HttpSession session = request.getSession();
		
		if ("bk_getProductByClass".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = request.getParameter("productClass");
				if (str.trim().length() == 0) {
					errorMsgs.add("請輸入查詢資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = request.getRequestDispatcher("/product/bk_updateProduct_select.jsp");
					failView.forward(request, response);
					return;
				}
				
				ProductService productSvc = new ProductService();
				List<ProductVO> list = productSvc.getOneClassProduct(str);

				request.setAttribute("list", list);	
				String url = "/product/bk_listClassProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("查無資料" + e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/product/bk_updateProduct_select.jsp");
				failView.forward(request, response);
			}
		}

		if ("bk_getOneProduct".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = request.getParameter("productSN");
				if (str.trim().length() == 0) {
					errorMsgs.add("請輸入查詢資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = request.getRequestDispatcher("/product/bk_updateProduct_select.jsp");
					failView.forward(request, response);
					return;
				}

				Integer productSN = null;
				try {
					productSN = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("格式輸入錯誤");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = request.getRequestDispatcher("/product/bk_updateProduct_select.jsp");
					failView.forward(request, response);
					return;
				}

				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(productSN);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = request.getRequestDispatcher("/product/bk_updateProduct_select.jsp");
					failView.forward(request, response);
					return;
				}

				request.setAttribute("productVO", productVO);
				String url = "/product/bk_listOneProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("查無資料" + e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/product/bk_updateProduct_select.jsp");
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
				String url = "/product/bk_updateProduct_do.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/product/bk_listAllProduct.jsp");
				failView.forward(request, response);
			}
		}

		if ("getOne_To_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer productSN = new Integer(request.getParameter("productSN"));

				String productClass = request.getParameter("productClass");
				if (productClass.trim().length() == 0) {
					errorMsgs.add("商品類別請勿空白");
				}

				String productName = request.getParameter("productName").trim();
				if (productName.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				}

				Integer productPrice = null;
				try {
					productPrice = new Integer(request.getParameter("productPrice").trim());
					if (productPrice == 0) {
						errorMsgs.add("商品數量不得為零");
					}
				} catch (Exception e) {
					productPrice = 0;
					errorMsgs.add("商品單價請填數字");
				}

				Integer productQuantity = null;
				try {
					productQuantity = new Integer(request.getParameter("productQuantity").trim());
					if (productQuantity == 0) {
						errorMsgs.add("商品數量不得為零");
					}
				} catch (Exception e) {
					productQuantity = 0;
					errorMsgs.add("商品數量請填數字");
				}
				
				String productStatus = request.getParameter("productStatus");
// 看無
				byte[] productPhoto = null;
				Part FileToPhoto = request.getPart("productPhoto");
				if (FileToPhoto.getSize() == 0) {
					productPhoto = (byte[]) session.getAttribute("photoTemp");
				} else {
					InputStream in = FileToPhoto.getInputStream();
					productPhoto = new byte[in.available()];
					in.read(productPhoto);
					in.close();
				}
				session.removeAttribute("photoTemp");

				String productDetail = request.getParameter("productDetail").trim();
				if (productDetail.trim().length() == 0) {
					errorMsgs.add("商品說明請勿空白");
				}

				java.sql.Date productCreateTime = null;
				try {
					productCreateTime = java.sql.Date.valueOf(request.getParameter("productCreateTime"));
				} catch (Exception e) {
					productCreateTime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請選擇日期");
				}

				String productDiscount = request.getParameter("productDiscount");
				if (productDiscount.trim().length() == 0) {
					errorMsgs.add("優惠狀態請勿空白");
				}				
				
				String productPrime = request.getParameter("productPrime");
				if (productPrime.trim().length() == 0) {
					errorMsgs.add("精選狀態請勿空白");
				}
								
				Integer ratingPoint = new Integer(request.getParameter("ratingPoint").trim());
				
				Integer ratingNumber = new Integer(request.getParameter("ratingNumber").trim());
				
				ProductVO productVO = new ProductVO();
				productVO.setProductSN(productSN);
				productVO.setProductClass(productClass);
				productVO.setProductName(productName);
				productVO.setProductPrice(productPrice);
				productVO.setProductQuantity(productQuantity);
				productVO.setProductStatus(productStatus);
				productVO.setProductPhoto(productPhoto);
				productVO.setProductDetail(productDetail);
				productVO.setProductCreateTime(productCreateTime);
				productVO.setProductDiscount(productDiscount);
				productVO.setProductPrime(productPrime);
				productVO.setRatingPoint(ratingPoint);
				productVO.setRatingNumber(ratingNumber);
				
//testuse
				request.setAttribute("productVO", productVO);
				
				if (!errorMsgs.isEmpty()) {
//test1				request.setAttribute("productVO", productVO);
					RequestDispatcher failView = request.getRequestDispatcher("/product/bk_updateProduct_do.jsp");
					failView.forward(request, response);
					return;
				}

				ProductService empSvc = new ProductService();
				productVO = empSvc.updateProduct(productSN, productClass, productName, productPrice, productQuantity, productStatus,
							productPhoto, productDetail, productCreateTime, productDiscount, productPrime, ratingPoint, ratingNumber);

//test1			request.setAttribute("productVO", productVO);
				String url = "/product/bk_listOneProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/product/bk_updateProduct_do.jsp");
				failView.forward(request, response);
			}
		}

		if ("insertProduct".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String productClass = request.getParameter("productClass");
				if (productClass.trim().length() == 0) {
					errorMsgs.add("商品類別請勿空白");
				}

				String productName = request.getParameter("productName").trim();
				if (productName.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				}

				Integer productPrice = null;
				try {
					productPrice = new Integer(request.getParameter("productPrice").trim());
					if (productPrice == 0) {
						errorMsgs.add("商品單價不得為零");
					}
				} catch (Exception e) {
					productPrice = 0;
					errorMsgs.add("商品單價請填數字");
				}

				Integer productQuantity = null;
				try {
					productQuantity = new Integer(request.getParameter("productQuantity").trim());
					if (productQuantity == 0) {
						errorMsgs.add("商品數量不得為零");
					}
				} catch (Exception e) {
					productQuantity = 0;
					errorMsgs.add("商品數量請填數字");
				}

				String productStatus = request.getParameter("productStatus");
				if (productStatus == null) {
					errorMsgs.add("商品狀態請勿空白");
				}
				
//看無				
				byte[] productPhoto = null;
				Part FileToPhoto = request.getPart("productPhoto");
				if (FileToPhoto.getSize() != 0) {
					InputStream in = FileToPhoto.getInputStream();
					productPhoto = new byte[in.available()];
					in.read(productPhoto);
					in.close();
				}

				String productDetail = request.getParameter("productDetail").trim();
				if (productDetail == null || productDetail.trim().length() == 0) {
					errorMsgs.add("商品說明請勿空白");
				}
				
				java.sql.Date productCreateTime = null;
				try {
					productCreateTime = java.sql.Date.valueOf(request.getParameter("productCreateTime"));
				} catch (Exception e) {
					productCreateTime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請選擇日期");
				}

				String productDiscount = request.getParameter("productDiscount");
				if (productDiscount.trim().length() == 0) {
					errorMsgs.add("優惠狀態請勿空白");
				}				
				
				String productPrime = request.getParameter("productPrime");
				if (productPrime.trim().length() == 0) {
					errorMsgs.add("精選狀態請勿空白");
				}

				Integer ratingPoint = null;
				try {
					ratingPoint = new Integer(request.getParameter("ratingPoint").trim());
				} catch (Exception e) {
					ratingPoint = 0;
					errorMsgs.add("評價總分請填數字");
				}

				Integer ratingNumber = null;
				try {
					ratingNumber = new Integer(request.getParameter("ratingNumber").trim());
				} catch (Exception e) {
					ratingNumber = 0;
					errorMsgs.add("評價人數請填數字");
				}

				ProductVO productVO = new ProductVO();
				productVO.setProductClass(productClass);
				productVO.setProductName(productName);
				productVO.setProductPrice(productPrice);
				productVO.setProductQuantity(productQuantity);
				productVO.setProductStatus(productStatus);
				productVO.setProductPhoto(productPhoto);
				productVO.setProductDetail(productDetail);
				productVO.setProductCreateTime(productCreateTime);
				productVO.setProductDiscount(productDiscount);
				productVO.setProductPrime(productPrime);
				productVO.setRatingPoint(ratingPoint);
				productVO.setRatingNumber(ratingNumber);

				request.setAttribute("productVO", productVO);	// 不管成功與否都要setAttr
//				295行 = 298行 + 304行
				if (!errorMsgs.isEmpty()) {
//					request.setAttribute("productVO", productVO);	// 失敗
					RequestDispatcher failView = request.getRequestDispatcher("/product/bk_insertProduct.jsp");
					failView.forward(request, response);
					return;
				}

//				request.setAttribute("productVO", productVO);	// 成功

				ProductService productSvc = new ProductService();
				productVO = productSvc.insertProduct(productClass, productName, productPrice, productQuantity, productStatus,
							productPhoto, productDetail, productCreateTime, productDiscount, productPrime, ratingPoint, ratingNumber);
			
				String url = "/product/bk_listAllProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/product/bk_insertProduct.jsp");
				failView.forward(request, response);
			}
		}
		
		if ("ChangeStatus".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer productSN = new Integer(request.getParameter("productN"));
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(productSN);
				if ("上架".equals(productVO.getProductStatus())) {
					productVO.setProductStatus("下架");
				} else {
					productVO.setProductStatus("上架");
				}
				
				productVO = productSvc.updateProduct(productSN, productVO.getProductClass(), productVO.getProductName(),
							productVO.getProductPrice(),productVO.getProductQuantity(), productVO.getProductStatus(),
							productVO.getProductPhoto(), productVO.getProductDetail(), productVO.getProductCreateTime(),
							productVO.getProductDiscount(), productVO.getProductPrime(), productVO.getRatingPoint(), productVO.getRatingNumber());
						
				RequestDispatcher failureView = request.getRequestDispatcher("");
				failureView.forward(request, response);
				return;
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("");
				failView.forward(request, response);
			}

		}

	}

}
