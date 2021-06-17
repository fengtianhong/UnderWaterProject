package com.orderlist.model;

import java.util.List;

public class OrderListService {
	
	private OrderListDAO_interface dao;

	public OrderListService() {
		dao = new OrderListDAO();
	}
	
	public OrderListVO insertOrderList(Integer productSN, Integer orderSN, Integer purchaseQuantity,
			Integer productPrice, Integer rating) {
		
		OrderListVO orderListVO = new OrderListVO();
		
		orderListVO.setProductSN(productSN);
		orderListVO.setOrderSN(orderSN);
		orderListVO.setPurchaseQuantity(purchaseQuantity);
		orderListVO.setProductPrice(productPrice);
		orderListVO.setRating(rating);
		dao.insert(orderListVO);
		
		return orderListVO;
	}
	
	public void deleteOrderList(Integer orderListSN) {
		dao.delete(orderListSN);
	}

	public OrderListVO updateOrderList(Integer orderListSN, Integer productSN, Integer orderSN,
			Integer purchaseQuantity, Integer productPrice, Integer rating) {
		
		OrderListVO orderListVO = new OrderListVO();
		
		orderListVO.setOrderListSN(orderListSN);
		orderListVO.setProductSN(productSN);
		orderListVO.setOrderSN(orderSN);
		orderListVO.setPurchaseQuantity(purchaseQuantity);
		orderListVO.setProductPrice(productPrice);
		orderListVO.setRating(rating);
		dao.update(orderListVO);
		
		return orderListVO;
	}
	
	public OrderListVO getOneOrderList(Integer orderListSN) {
		return dao.getOneByOrderListSN(orderListSN);
	}
	
	public List<OrderListVO> getAll(){
		return dao.getAll();
	}
	
}
