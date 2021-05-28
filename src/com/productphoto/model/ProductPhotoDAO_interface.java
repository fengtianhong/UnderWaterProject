package com.productphoto.model;

import java.util.List;

public interface ProductPhotoDAO_interface {
	public void insert(ProductPhotoVO productPhotoVO);
	public void delete(Integer photoSN);
	public void update(ProductPhotoVO productPhotoVO);
	public ProductPhotoVO getOneByPhotoSN(Integer photoSN);
	public List<ProductPhotoVO> getAll();
	
}
