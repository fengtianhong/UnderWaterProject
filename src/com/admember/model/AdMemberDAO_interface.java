package com.admember.model;

public interface AdMemberDAO_interface {
	
	public void insert(AdMemberVO adMemberVO);
	
	public int update(String adUserID,String pwd ,Integer status);
	
	public AdMemberVO findBYaccount(Integer account);
}
