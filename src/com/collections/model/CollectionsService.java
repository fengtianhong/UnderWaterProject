package com.collections.model;

import java.util.List;

public class CollectionsService {
	
	private CollectionsDAO_interface dao;
	
	public CollectionsService() {
		dao = new CollectionsDAO();
	}
	public CollectionsVO addCollections(Integer groupTourSN, Integer userID) {
		
		CollectionsVO vo = new CollectionsVO();
		vo.setGroupTourSN(groupTourSN);
		vo.setUserID(userID);
		dao.insert(vo);
		
		return vo;
	}
	public void deleteCollections(Integer groupTourSN, Integer userID) {
		
		CollectionsVO vo = new CollectionsVO();
		vo.setGroupTourSN(groupTourSN);
		vo.setUserID(userID);
		dao.delete(vo);
	}
	public List<Integer> getCollectionsByUserid(Integer userID) {
		return dao.findByUserID(userID);
	}
	
}
