package com.locationrate.model;

import java.util.List;

public class LocationrateService {
	
	private LocationRateDAO_interface dao;
	
	public LocationrateService() {
		dao = new LocationRateDAO();
	}
	public LocationRateVO addLocationRate(Integer pointSN, Integer userID, Integer rate, String rateDetail) {
		LocationRateVO vo = new LocationRateVO();
		vo.setPointSN(pointSN);
		vo.setUserID(userID);
		vo.setRate(rate);
		vo.setRateDetail(rateDetail);
		dao.insert(vo);
		return vo;
	}
	public LocationRateVO updateLocationRate(Integer pointSN, Integer rate, String rateDetail) {
		LocationRateVO vo = new LocationRateVO();
		vo.setPointSN(pointSN);
		vo.setRate(rate);
		vo.setRateDetail(rateDetail);
		dao.insert(vo);
		return vo;
	}
	public void deleteLocationRate(Integer SN) {
		dao.delete(SN);
	}
	public List<LocationRateVO> getByPointSN(Integer pointSN) {
		return dao.getByPointSN(pointSN);
	}
	public LocationRateVO getByUser(Integer userID) {
		return dao.getByUser(userID);
	}
}
