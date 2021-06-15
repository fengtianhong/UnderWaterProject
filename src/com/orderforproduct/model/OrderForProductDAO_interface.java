package com.orderforproduct.model;

import java.util.List;

public interface OrderForProductDAO_interface {

	public void insert(OrderForProductVO orderForProductVO);

	public void changeStatus(OrderForProductVO orderForProductVO);

	public void update(OrderForProductVO orderForProductVO);

	public OrderForProductVO getOneByOrderSN(Integer orderSN);

	public List<OrderForProductVO> getAll();

}
