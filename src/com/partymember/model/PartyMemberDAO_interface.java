package com.partymember.model;

import java.util.List;

public interface PartyMemberDAO_interface {
	
	public int insert(PartyMemberVO partyMemberVO);  //done
	
	public void update(PartyMemberVO partyMemberVO);
	
	public int updateStatus(Integer partyMemberSN, String status);  //done
	
	public PartyMemberVO findByPartyMemberSN(Integer partyMemberSN);  //done
	
	public List<PartyMemberVO> findByPartyMember(Integer partyMemebr);
	
	public List<PartyMemberVO> findByPartySN(Integer partySN);
	
	public List<PartyMemberVO> getAll(); 
	
	public void deleteByPartyMemberSN(Integer partyMemberSN);


}
