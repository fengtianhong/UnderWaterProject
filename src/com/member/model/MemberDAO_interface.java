package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	public void insert(MemberVO MemberVO);
	public void update(MemberVO MemberVO);
	public MemberVO findBYPrimaryKey(Integer userID);
	public List<MemberVO> getAll();
}
