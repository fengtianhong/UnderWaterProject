package com.oderforgroup.model;

import java.sql.Date;
import java.util.List;

public class OderForGroupService {
	
	private OderForGroupDAO_interface dao;
	
	public OderForGroupService() {
		dao = new OderForGroupDAO();
	}
	public OderForGroupVO insertOderForGroup(Integer userID, Integer groupTourSN, Integer totalPrice, Date purchaseDate, String phone, String personID, Date birthdate) {
		OderForGroupVO vo = new OderForGroupVO();
		vo.setUserID(userID);
		vo.setGroupTourSN(groupTourSN);
		vo.setTotalPrice(totalPrice);
		vo.setPurchaseDate(purchaseDate);
		vo.setPhone(phone);
		vo.setPersonID(personID);
		vo.setBirthdate(birthdate);
		return vo;
	}
	public OderForGroupVO updateOderForGroup(Integer orderSN, Integer userID, Integer groupTourSN, Integer totalPrice, Date purchaseDate, String phone, String personID, Date birthdate) {
		
		OderForGroupVO vo = new OderForGroupVO();
		vo.setOrderSN(orderSN);
		vo.setUserID(userID);
		vo.setGroupTourSN(groupTourSN);
		vo.setTotalPrice(totalPrice);
		vo.setPurchaseDate(purchaseDate);
		vo.setPhone(phone);
		vo.setPersonID(personID);
		vo.setBirthdate(birthdate);
		return vo;
		
	}
	public OderForGroupVO findByPrimaryKey(Integer orderSN) {
		return dao.findByPrimaryKey(orderSN);
	}
	public List<OderForGroupVO> getOrderByUserID(Integer userID) {
		return dao.getOrderByUserID(userID);
	}

}
