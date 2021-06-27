package com.orderforgroup.model;

import java.util.List;

public interface OrderForGroupDAO_interface {
	public void insert(OrderForGroupVO oderForGroupVO);
	public void update(OrderForGroupVO oderForGroupVO);
	public OrderForGroupVO findByPrimaryKey(Integer orderSN);
	public List<OrderForGroupVO> getOrderByUserID(Integer userID);
	public List<OrderForGroupVO> getAll();
	public List<Integer> checkRepeatOrder(Integer userID);	// 確認是否已報名用
	public List<Integer> getMember(Integer groupTourSN);	// 列出已報名之團員用

}
