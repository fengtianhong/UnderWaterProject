package com.orderlist.model;

import java.sql.Connection;
import java.util.List;

public interface OrderListDAO_interface {

	public void insert(OrderListVO orderListVO);

	public void delete(Integer orderListSN);

	public void update(OrderListVO orderListVO);

	public OrderListVO getOneByOrderListSN(Integer orderListSN);

	public List<OrderListVO> getAll();
	
	// 同時新增訂單主檔與明細
	public void insertWithOrderForProduct(OrderListVO orderListVO, Connection con);

}
