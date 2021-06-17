package com.productphoto.model;

import java.util.List;

public class ProductPhotoService {

	private ProductPhotoDAO_interface dao;

	public ProductPhotoService() {
		dao = new ProductPhotoDAO();
	}
	
	public ProductPhotoVO insertPhoto(Integer productSN, byte[] productImages) {
		
		ProductPhotoVO productPhotoVO = new ProductPhotoVO();
		
		productPhotoVO.setProductSN(productSN);
		productPhotoVO.setProductImages(productImages);
		dao.insert(productPhotoVO);
		
		return productPhotoVO;
	}
	
	public void deletePhoto(Integer photoSN) {
		dao.delete(photoSN);
	}
	
	public ProductPhotoVO updatePhoto(Integer photoSN, Integer productSN, byte[] productImages) {
		
		ProductPhotoVO productPhotoVO = new ProductPhotoVO();
		
		productPhotoVO.setPhotoSN(photoSN);
		productPhotoVO.setProductSN(productSN);
		productPhotoVO.setProductImages(productImages);
		dao.update(productPhotoVO);
		
		return productPhotoVO;
	}
	
	public ProductPhotoVO getOnePhoto(Integer photoSN) {
		return dao.getOneByPhotoSN(photoSN);
	}
	
	public List<ProductPhotoVO> getAll(){
		return dao.getAll();
	}
	
}
