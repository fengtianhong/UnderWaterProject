package com.party.model;

import java.util.List;

public interface PartyDAO_interface {
	
	// Functions including PARTYDETAIL(CLOB) need to be updated.
	
	public PartyVO insert(PartyVO partyVO);  //done
	
	public PartyVO update(PartyVO partyVO);  //0617 done
	
	public PartyVO updateStatus(Integer partySN, String status);  //done
	
	public PartyVO findByPartySN(Integer partySN);  //done
	
	public List<PartyVO> findByPartyHost(Integer partyHost);  //done

	public List<PartyVO> findByPartyLocation(Integer partyLocation);  //done
	
	public List<PartyVO> getAll();	//done
	
	public int deleteByPartySN(Integer partySN);  //done

	// 0606 updated
	public List<PartyVO> findBySearch(String keyword, Integer pointSN, Integer partyMinSize);
	
	// 0617 updated
	public List<PartyVO> findByPartySNLike(Integer partySN);
}
