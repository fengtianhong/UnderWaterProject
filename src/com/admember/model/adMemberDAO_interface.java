package com.admember.model;

public interface adMemberDAO_interface {
	
	public void insert(adMemberVO adMemberVO);
	
	public int update(String adUserID,String pwd ,Integer status);
	
	public adMemberVO findBYaccount(Integer account);
}
