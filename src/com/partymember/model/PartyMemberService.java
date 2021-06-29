package com.partymember.model;

import java.util.List;

public class PartyMemberService {
	
	private PartyMemberDAO_interface dao;
	
	public PartyMemberService() {
		dao = new PartyMemberJNDIDAO();
	}
	
	public int insert(PartyMemberVO partyMemberVO) {
		return dao.insert(partyMemberVO);
	}
	
	public List<PartyMemberVO> findByPartySN(Integer partySN) {
		return dao.findByPartySN(partySN);
	}
	
	public int updateStatus(Integer partyMemberSN, String status) {
		return dao.updateStatus(partyMemberSN, status);
	}
	
	public List<PartyMemberVO> findByPartyMember(Integer partyMember) {
		return dao.findByPartyMember(partyMember);
	}
	
	public PartyMemberVO findByPartyMemberSN(Integer partyMemberSN) {
		return dao.findByPartyMemberSN(partyMemberSN);
	}
	
	// 0626 added
	public List<PartyMemberVO> findByPartySNAndStatus(Integer partySN, String status) {
		return dao.findByPartySNAndStatus(partySN, status);
	}
	
}
