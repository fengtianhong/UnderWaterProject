package com.orderforgroup.model;

import java.sql.Date;
import java.util.List;

public class OrderForGroupService {
	
	private OrderForGroupDAO_interface dao;
	
	public OrderForGroupService() {
		dao = new OrderForGroupDAO();
	}
	public OrderForGroupVO insertOrderForGroup(Integer userID, Integer groupTourSN, Integer totalPrice, Date purchaseDate, String phone, String personID, Date birthdate) {
		OrderForGroupVO vo = new OrderForGroupVO();
		vo.setUserID(userID);
		vo.setGroupTourSN(groupTourSN);
		vo.setTotalPrice(totalPrice);
		vo.setPurchaseDate(purchaseDate);
		vo.setPhone(phone);
		vo.setPersonID(personID);
		vo.setBirthdate(birthdate);
		dao.insert(vo);
		return vo;
	}
	public OrderForGroupVO updateOderForGroup(Integer orderSN, Integer userID, Integer groupTourSN, Integer totalPrice, Date purchaseDate, String phone, String personID, Date birthdate) {
		
		OrderForGroupVO vo = new OrderForGroupVO();
		vo.setOrderSN(orderSN);
		vo.setUserID(userID);
		vo.setGroupTourSN(groupTourSN);
		vo.setTotalPrice(totalPrice);
		vo.setPurchaseDate(purchaseDate);
		vo.setPhone(phone);
		vo.setPersonID(personID);
		vo.setBirthdate(birthdate);
		dao.update(vo);
		return vo;
		
	}
	public OrderForGroupVO findByPrimaryKey(Integer orderSN) {
		return dao.findByPrimaryKey(orderSN);
	}
	public List<OrderForGroupVO> getOrderByUserID(Integer userID) {
		return dao.getOrderByUserID(userID);
	}
	public List<OrderForGroupVO> getAll() {
		return dao.getAll();
	}
	
	// 列出會員已參加行程
	public List<Integer> checkRepeatOrder(Integer userID) {
		return dao.checkRepeatOrder(userID);
	}
	// 列出行程已報名團員
	public List<Integer> getMember(Integer groupTourSN) {
		return dao.getMember(groupTourSN);
	}
}
