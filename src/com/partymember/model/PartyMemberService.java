package com.partymember.model;

import java.util.List;

public class PartyMemberService {
	
	private PartyMemberDAO_interface dao;
	
	public PartyMemberService() {
		dao = new PartyMemberJNDIDAO();
	}
	
	public List<PartyMemberVO> findByPartySN(Integer partySN) {
		return dao.findByPartySN(partySN);
	}
	
}
