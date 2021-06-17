package com.orderforproduct.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderForProductService {

	private OrderForProductDAO_interface dao;

	public OrderForProductService() {
		dao = new OrderForProductDAO();
	}
	
	public OrderForProductVO insertOrder(Integer userID, Timestamp purchaseDate, Integer totalPrice,
			String orderStatus, Timestamp clearDate) {
		
		OrderForProductVO orderForProductVO = new OrderForProductVO();
		
		orderForProductVO.setUserID(userID);
		orderForProductVO.setPurchaseDate(purchaseDate);
		orderForProductVO.setTotalPrice(totalPrice);
		orderForProductVO.setOrderStatus(orderStatus);
		orderForProductVO.setClearDate(clearDate);
		dao.insert(orderForProductVO);
		
		return orderForProductVO;
	}
	
	public OrderForProductVO changeOrderStatus(Integer orderSN, Integer userID, Timestamp purchaseDate, Integer totalPrice,
			String orderStatus, Timestamp clearDate) {
		
		OrderForProductVO orderForProductVO = new OrderForProductVO();
		
		orderForProductVO.setOrderSN(orderSN);
		orderForProductVO.setUserID(userID);
		orderForProductVO.setPurchaseDate(purchaseDate);
		orderForProductVO.setTotalPrice(totalPrice);
		orderForProductVO.setOrderStatus(orderStatus);
		orderForProductVO.setClearDate(clearDate);
		dao.changeStatus(orderForProductVO);
		
		return orderForProductVO;
	}
	
	public OrderForProductVO updateOrder(Integer orderSN, Integer userID, Timestamp purchaseDate, Integer totalPrice,
			String orderStatus, Timestamp clearDate) {
		
		OrderForProductVO orderForProductVO = new OrderForProductVO();
		
		orderForProductVO.setOrderSN(orderSN);
		orderForProductVO.setUserID(userID);
		orderForProductVO.setPurchaseDate(purchaseDate);
		orderForProductVO.setTotalPrice(totalPrice);
		orderForProductVO.setOrderStatus(orderStatus);
		orderForProductVO.setClearDate(clearDate);
		dao.update(orderForProductVO);
		
		return orderForProductVO;
	}
	
	public OrderForProductVO getOneOrder(Integer orderSN) {
		return dao.getOneByOrderSN(orderSN);
	}
	
	public List<OrderForProductVO> getAll(){
		return dao.getAll();
	}
	
}
