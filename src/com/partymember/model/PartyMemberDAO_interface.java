package com.partymember.model;

import java.util.List;

public interface PartyMemberDAO_interface {
	
	// Function UPDATE is pending.
	
	public int insert(PartyMemberVO partyMemberVO);  //done
	
	public void update(PartyMemberVO partyMemberVO);  //pending
	
	public int updateStatus(Integer partyMemberSN, String status);  //done
	
	public PartyMemberVO findByPartyMemberSN(Integer partyMemberSN);  //done
	
	public List<PartyMemberVO> findByPartyMember(Integer partyMember);  //done
	
	public List<PartyMemberVO> findByPartySN(Integer partySN);  //done
	
	public List<PartyMemberVO> getAll(); // done
	
	public int deleteByPartyMemberSN(Integer partyMemberSN);	//done


}
