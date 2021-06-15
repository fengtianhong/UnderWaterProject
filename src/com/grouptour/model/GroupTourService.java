package com.grouptour.model;

import java.sql.Date;
import java.util.List;

import com.collections.model.CollectionsDAO_interface;

public class GroupTourService {

	private GroupTourDAO_interface dao;
	
	public GroupTourService() {
		dao = new GroupTourDAO();
	}
	public GroupTourVO insertGroupTour(String tourName, byte[] tourPic, Date startTime, Date endTime, 
									   Date regTime, Date closeTime, Integer pointSN, 
									   Integer price, Integer limitNumder, 
									   String certificationLimit, String status, String content) {
		
		GroupTourVO vo = new GroupTourVO();
		vo.setTourName(tourName);
		vo.setTourPic(tourPic);
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		vo.setRegTime(regTime);
		vo.setCloseTime(closeTime);
		vo.setPointSN(pointSN);
		vo.setPrice(price);
		vo.setLimitNumder(limitNumder);
		vo.setCertificationLimit(certificationLimit);
		vo.setStatus(status);
		vo.setContent(content);
		dao.insert(vo);
		
		return vo;
	}
	public GroupTourVO updateGroupTour(Integer groupTourSN, String tourName, byte[] tourPic, Date startTime, Date endTime, 
									   Date regTime, Date closeTime, Integer pointSN, 
									   Integer price, Integer attendNumber, Integer limitNumder, 
									   String certificationLimit, String status, String content) {
		
		GroupTourVO vo = new GroupTourVO();
		vo.setGroupTourSN(groupTourSN);
		vo.setTourName(tourName);
		vo.setTourPic(tourPic);
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		vo.setRegTime(regTime);
		vo.setCloseTime(closeTime);
		vo.setPointSN(pointSN);
		vo.setPrice(price);
		vo.setAttendNumber(attendNumber);
		vo.setLimitNumder(limitNumder);
		vo.setCertificationLimit(certificationLimit);
		vo.setStatus(status);
		vo.setContent(content);
		dao.update(vo);
		
		return vo;
	}
	public GroupTourVO getOne(Integer groupTourSN) {
		return dao.findByPrimaryKey(groupTourSN);
	}
	public List<GroupTourVO> getAll() {
		return dao.getAll();
	}
}
