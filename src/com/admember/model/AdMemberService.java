package com.admember.model;

import java.sql.Timestamp;

public class AdMemberService {
	
	private AdMemberDAO dao;
	
	AdMemberService(){
		dao = new AdMemberDAO();
	}
	
	public AdMemberVO insertAdMemberVO(String account, String pwd, Integer status) {
		
		AdMemberVO vo = new AdMemberVO();
		vo.setAccount(account);
		vo.setPwd(pwd);
		vo.setStatus(status);
		return vo;
	}
	
	public AdMemberVO updateAdMemberVO(Integer adUserID, String account, String pwd, Timestamp createTime, Integer status) {
		
		AdMemberVO vo = new AdMemberVO();
		vo.setAdUserID(adUserID);
		vo.setAccount(account);
		vo.setPwd(pwd);
		vo.setCreateTime(createTime);
		vo.setStatus(status);
		return vo;
	}
	

	public AdMemberVO  findBYaccount(Integer account) {
		return dao.findBYaccount(account);
	}
}
