package com.adorder.model;

import java.sql.Timestamp;
import java.util.List;

public class AdOrderService {
	
	private AdOrderDAO dao; 
	
	AdOrderService(){
		dao = new AdOrderDAO();
	}
	
	public AdOrderVO insertAdOrder(Integer adUserID, Integer block, Timestamp time , Timestamp showTime, Timestamp expiredTime, Integer status) {
		AdOrderVO vo = new AdOrderVO();
		vo.setAdUserID(adUserID);
		vo.setBlock(block);
		vo.setTime(time);
		vo.setShowTime(showTime);
		vo.setExpiredTime(expiredTime);
		vo.setStatus(status);
		
		return vo;
	}
	
	public AdOrderVO updateAdOrder(Integer orderSN, Integer adUserID, Integer block, Timestamp time, Timestamp showTime, Timestamp expiredTime, Integer status) {
		AdOrderVO vo = new AdOrderVO();
		vo.setOrderSN(orderSN);
		vo.setAdUserID(adUserID);
		vo.setBlock(block);
		vo.setTime(time);
		vo.setShowTime(showTime);
		vo.setExpiredTime(expiredTime);
		vo.setStatus(status);
		
		return vo;
	}
	
	public List<AdOrderVO> getone(Integer adUserID){
		return dao.findByadUserID(adUserID);
	}
	
	public List<AdOrderVO> getAll(){
		return dao.getAll();
	}
}
