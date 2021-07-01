package com.shoppingcar.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("/mall/shoppingCar.html")
public class ShoppingCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
		String action = request.getParameter("action");
		
		if (!action.equals("CHECKOUT")) {

			// 刪除購物車中的商品
			if (action.equals("DELETE")) {
				String del = request.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);
			}
			// 新增商品至購物車中
			else if (action.equals("ADD")) {
				boolean match = false;

				// 取得後來新增的商品
				ProductVO newproductVO = getProductVO(request);

				// 新增第一項商品至購物車時
				if (buylist == null) {
					buylist = new Vector<ProductVO>();
					buylist.add(newproductVO);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						ProductVO productVO = buylist.get(i);

						// 假若新增的商品和購物車的商品一樣時
						if (productVO.getProductName().equals(newproductVO.getProductName())) {
							productVO.setProductQuantity(productVO.getProductQuantity()
									+ newproductVO.getProductQuantity());
							buylist.setElementAt(productVO, i);
							match = true;
						}
					}

					// 假若新增的商品和購物車的商品不一樣時
					if (!match)
						buylist.add(newproductVO);
				}
			}

			session.setAttribute("shoppingcart", buylist);
			String url = "/product/ft_listAllProduct.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}

		// 結帳，計算購物車商品價錢總數
		else if (action.equals("CHECKOUT")) {
			float total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO order = buylist.get(i);
				float price = order.getProductPrice();
				int quantity = order.getProductQuantity();
				total += (price * quantity);
			}

			String amount = String.valueOf(total);
			request.setAttribute("amount", amount);
			String url = "/shoppingCar/Checkout.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

	private ProductVO getProductVO(HttpServletRequest request) {
		
		ProductService proSvc = new ProductService();
		
		Integer productSN = new Integer (request.getParameter("productSN").trim());
		Integer productQuantity = new Integer (request.getParameter("productQuantity").trim());
		
		ProductVO productVO = proSvc.getOneProduct(productSN);		
		productVO.setProductQuantity(productQuantity);
				
		return productVO;
	}

}
