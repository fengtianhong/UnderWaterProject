package com.orderlist.model;

import java.util.List;

public interface OrderListDAO_interface {
	public void insert(OrderListVO orderListVO);

	public void delete(Integer orderListSN);

	public void update(OrderListVO orderListVO);

	public OrderListVO getOneByOrderListSN(Integer orderListSN);

	public List<OrderListVO> getAll();

}
