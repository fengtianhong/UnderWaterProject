package com.party.model;

import java.util.List;

public class PartyService {
	
	private PartyDAO_interface dao;
	
	public PartyService() {
		dao = new PartyJNDIDAO();
	}
	
	public PartyVO findByPartySN(Integer partySN) {
		return dao.findByPartySN(partySN);
	}
	
	// not sure whether useful
	public List<PartyVO> findByPartyLocation(Integer partyLocation) {
		return dao.findByPartyLocation(partyLocation);
	}
	
	public List<PartyVO> getAll() {
		return dao.getAll();
	}

	// 0606 updated
	public List<PartyVO> findBySearch(String keyword, Integer pointSN, Integer partyMinSize) {
		return dao.findBySearch(keyword, pointSN, partyMinSize);
	};
	
	// 0617 updated
	public List<PartyVO> findByPartySNLike(Integer partySN) {
		return dao.findByPartySNLike(partySN);
	}
	
	// 0617 updated
	public PartyVO update(PartyVO partyVO) {
		return dao.update(partyVO);
	}
}
