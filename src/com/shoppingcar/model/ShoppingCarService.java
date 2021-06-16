package com.shoppingcar.model;

import java.util.List;

public class ShoppingCarService {
	
	private ShoppingCarDAO_interface dao;

	public ShoppingCarService() {
		dao = new ShoppingCarDAO();
	}
	
	public ShoppingCarVO insertShoppingCar(Integer userID, Integer productSN, Integer purchaseQuantity,
			Integer productPrice, Integer totalPrice) {
		
		ShoppingCarVO shoppingCarVO = new ShoppingCarVO();
		
		shoppingCarVO.setUserID(userID);
		shoppingCarVO.setProductSN(productSN);
		shoppingCarVO.setPurchaseQuantity(purchaseQuantity);
		shoppingCarVO.setProductPrice(productPrice);
		shoppingCarVO.setTotalPrice(totalPrice);
		dao.insert(shoppingCarVO);
		
		return shoppingCarVO;
	}
	
	public void deleteShoppingCar(Integer shoppingCarSN) {
		dao.delete(shoppingCarSN);
	}
	
	public ShoppingCarVO updateShoppingCar(Integer shoppingCarSN, Integer userID, Integer productSN,
			Integer purchaseQuantity, Integer productPrice, Integer totalPrice) {
		
		ShoppingCarVO shoppingCarVO = new ShoppingCarVO();
		
		shoppingCarVO.setShoppingCarSN(shoppingCarSN);
		shoppingCarVO.setUserID(userID);
		shoppingCarVO.setProductSN(productSN);
		shoppingCarVO.setPurchaseQuantity(purchaseQuantity);
		shoppingCarVO.setProductPrice(productPrice);
		shoppingCarVO.setTotalPrice(totalPrice);
		dao.update(shoppingCarVO);
		
		return shoppingCarVO;
	}
	
	public ShoppingCarVO getOneShoppingCar(Integer shoppingCarSN) {		
		return dao.getOneByShoppingCarSN(shoppingCarSN);
	}
	
	public List<ShoppingCarVO> getAll(){
		return dao.getAll();
	}
	
}
