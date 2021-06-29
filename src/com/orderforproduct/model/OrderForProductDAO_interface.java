package com.orderforproduct.model;

import java.util.List;

import com.orderlist.model.OrderListVO;

public interface OrderForProductDAO_interface {

	public void insert(OrderForProductVO orderForProductVO);

	public void changeStatus(OrderForProductVO orderForProductVO);

	public void update(OrderForProductVO orderForProductVO);

	public OrderForProductVO getOneByOrderSN(Integer orderSN);

	public List<OrderForProductVO> getAll();
	
	// 同時新增訂單主檔與明細
	public void insertWithOrderList(OrderForProductVO orderForProductVO, List<OrderListVO> list);

}
