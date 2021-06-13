package com.party.model;

import java.util.List;

public interface PartyDAO_interface {
	
	// Functions including PARTYDETAIL(CLOB) need to be updated.
	// Function UPDATE is pending.
	
	public PartyVO insert(PartyVO partyVO);  //done
	
	public void update(PartyVO partyVO);  //pending
	
	public PartyVO updateStatus(Integer partySN, String status);  //done
	
	public PartyVO findByPartySN(Integer partySN);  //done
	
	public List<PartyVO> findByPartyHost(Integer partyHost);  //done

	public List<PartyVO> findByPartyLocation(Integer partyLocation);  //done
	
	public List<PartyVO> getAll();	//done
	
	public int deleteByPartySN(Integer partySN);  //done

	// 0606 updated
	public List<PartyVO> findBySearch(String keyword, Integer pointSN, Integer partyMinSize);
}
