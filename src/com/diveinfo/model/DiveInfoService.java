package com.diveinfo.model;

import java.util.List;

public class DiveInfoService {
	private DiveInfoDAO_interface dao;
	
	public DiveInfoService() {
		dao = new DiveInfoDAO();
	}
	public DiveInfoVO addDiveInfo(String pointname,Double latitude,Double longitude,String view,String introduction,
			String season,String local,byte[] pic,Integer ratepoint,Integer ratepeople,String status) {
		DiveInfoVO diveinfovo = new DiveInfoVO();
		diveinfovo.setPointName(pointname);
		diveinfovo.setLatitude(latitude);
		diveinfovo.setLongitude(longitude);
		diveinfovo.setView(view);
		diveinfovo.setIntroduction(introduction);
		diveinfovo.setSeason(season);
		diveinfovo.setLocal(local);
		diveinfovo.setPic(pic);
		diveinfovo.setRatePoint(ratepoint);
		diveinfovo.setRatePeople(ratepeople);
		diveinfovo.setStatus(status);
		dao.insert(diveinfovo);
		
		return diveinfovo;
	}
	public DiveInfoVO updateDiveInfo(Integer pointSN,String pointname,Double latitude,Double longitude,String view,String introduction,
			String season,String local,byte[] pic,Integer ratepoint,Integer ratepeople,String status) {
		DiveInfoVO diveinfovo = new DiveInfoVO();
		diveinfovo.setPointName(pointname);
		diveinfovo.setLatitude(latitude);
		diveinfovo.setLongitude(longitude);
		diveinfovo.setView(view);
		diveinfovo.setIntroduction(introduction);
		diveinfovo.setSeason(season);
		diveinfovo.setLocal(local);
		diveinfovo.setPic(pic);
		diveinfovo.setRatePoint(ratepoint);
		diveinfovo.setRatePeople(ratepeople);
		diveinfovo.setStatus(status);
		diveinfovo.setPointSN(pointSN);
		dao.update(diveinfovo);
		
		return diveinfovo;
	}
	public DiveInfoVO getOneDiveInfo(Integer pointSN) {
		return dao.findByPrimaryKey(pointSN);
	}
	public List<DiveInfoVO> getAll(){
		return dao.getAll();
	}
}
