package com.product.model;

import java.util.List;

public interface ProductDAO_interface {

	public void insert(ProductVO productVO);

	public void offShelf(ProductVO productVO); // 下架 : update 更新 productStatus

	public void update(ProductVO productVO);

	public ProductVO getOneByProductSN(Integer productSN);

	public List<ProductVO> getProductByClass(String productClass); // 依商品類別查詢

	public List<ProductVO> getProductByDiscount(Boolean productDiscount); // 找優惠商品

	public List<ProductVO> getProductByPrime(Boolean productPrime); // 找精選商品

	public List<ProductVO> getAll();

}
