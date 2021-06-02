package com.product.model;

import java.util.List;

public interface ProductDAO_interface {
	public void insert(ProductVO productVO);

	public void offShelf(ProductVO productVO); // 下架 : update 更新 productStatus

	public void update(ProductVO productVO);

	public ProductVO getOneByProductSN(Integer productSN);

	public List<ProductVO> getAll();

}
