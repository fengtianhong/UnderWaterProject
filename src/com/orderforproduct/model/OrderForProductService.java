package com.orderforproduct.model;

import java.sql.Timestamp;
import java.util.List;

import com.orderlist.model.OrderListVO;

public class OrderForProductService {

	private OrderForProductDAO_interface dao;

	public OrderForProductService() {
		dao = new OrderForProductDAO();
	}
	
	public OrderForProductVO insertOrder(Integer userID, Timestamp purchaseDate, Integer totalPrice,
			String orderStatus, List<OrderListVO> list) {
		
		OrderForProductVO orderForProductVO = new OrderForProductVO();
		
		orderForProductVO.setUserID(userID);
		orderForProductVO.setPurchaseDate(purchaseDate);
		orderForProductVO.setTotalPrice(totalPrice);
		orderForProductVO.setOrderStatus(orderStatus);
		dao.insertWithOrderList(orderForProductVO, list);
		
		return orderForProductVO;
	}
	
	public OrderForProductVO changeOrderStatus(Integer orderSN, Integer userID, Timestamp purchaseDate, Integer totalPrice,
			String orderStatus) {
		
		OrderForProductVO orderForProductVO = new OrderForProductVO();
		
		orderForProductVO.setOrderSN(orderSN);
		orderForProductVO.setUserID(userID);
		orderForProductVO.setPurchaseDate(purchaseDate);
		orderForProductVO.setTotalPrice(totalPrice);
		orderForProductVO.setOrderStatus(orderStatus);
		dao.changeStatus(orderForProductVO);
		
		return orderForProductVO;
	}
	
	public OrderForProductVO updateOrder(Integer orderSN, Integer userID, Timestamp purchaseDate, Integer totalPrice,
			String orderStatus) {
		
		OrderForProductVO orderForProductVO = new OrderForProductVO();
		
		orderForProductVO.setOrderSN(orderSN);
		orderForProductVO.setUserID(userID);
		orderForProductVO.setPurchaseDate(purchaseDate);
		orderForProductVO.setTotalPrice(totalPrice);
		orderForProductVO.setOrderStatus(orderStatus);
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
