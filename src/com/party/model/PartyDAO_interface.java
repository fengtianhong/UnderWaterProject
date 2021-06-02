package com.party.model;

import java.util.List;

public interface PartyDAO_interface {
	
	// Functions including PARTYDETAIL(CLOB) need to be updated.
	// Function UPDATE is pending.
	
	public int insert(PartyVO partyVO);  //done
	
	public void update(PartyVO partyVO);  //pending
	
	public int updateStatus(Integer partySN, String status);  //done
	
	public PartyVO findByPartySN(Integer partySN);  //done
	
	public List<PartyVO> findByPartyHost(Integer partyHost);  //done

	public List<PartyVO> findByPartyLocation(Integer partyLocation);  //done
	
	public List<PartyVO> getAll();	//done
	
	public int deleteByPartySN(Integer partySN);  //done

}
