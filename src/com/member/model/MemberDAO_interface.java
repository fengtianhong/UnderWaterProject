package com.member.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface MemberDAO_interface {
	public void insert(MemberVO MemberVO);
	public void update(MemberVO MemberVO);
	public MemberVO findByPrimaryKey(Integer userID);
	public MemberVO findByAccount(String account);
	public List<MemberVO> getAll();
	public Boolean login(MemberVO MemberVO);
	public Boolean checkAccount(MemberVO MemberVO);
	public void personInfoUpdate(MemberVO MemberVO);
	public void pwdUpdate(MemberVO MemberVO);
	public List<MemberVO> findBySearch(String account, String nickName, String userName);
	public void updatePassword(String account, String pwd);
}
