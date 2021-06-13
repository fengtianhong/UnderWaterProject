package com.adpic.model;

import java.util.List;

public class AdPicService {
	
	private AdPicDAO_interface dao;
	
	public AdPicService() {
		dao = new AdPicDAO();
	}
	
	public AdPicVO insertAdPicVO(Integer orderSN, byte[] pic) {
		AdPicVO vo = new AdPicVO();
		vo.setOrderSN(orderSN);
		vo.setPic(pic);
		return vo;
	}
	
	public AdPicVO updateAdPicVO(Integer adPicSN, Integer orderSN, byte[] pic) {
		AdPicVO vo = new AdPicVO();
		vo.setAdPicSN(adPicSN);
		vo.setOrderSN(orderSN);
		vo.setPic(pic);
		
		return vo;
	}
	
	public List<AdPicVO> getone(Integer orderSN){
		return dao.findByorderSN(orderSN);
	}
	
	public List<AdPicVO> getAll(){
		return dao.getAll();
	}
}
