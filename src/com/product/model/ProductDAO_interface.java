package com.product.model;

import java.util.List;

public interface ProductDAO_interface {
	public void insert(ProductVO productVO);

	public void offShelf(Integer productSN);

	public void update(ProductVO productVO);

	public ProductVO getOneByProductSN(Integer productSN);

	public List<ProductVO> getAll();

}
