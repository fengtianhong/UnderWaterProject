package com.shoppingcar.model;

import java.util.List;

public interface ShoppingCarDAO_interface {
	public void insert(ShoppingCarVO shoppingCarVO);

	public void delete(Integer shoppingCarSN);

	public void update(ShoppingCarVO shoppingCarVO);

	public ShoppingCarVO getOneByShoppingCarSN(Integer shoppingCarSN);

	public List<ShoppingCarVO> getAll();

}
