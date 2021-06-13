package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class MemberService {
	
	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public MemberVO insertMember(String account, String pwd, String nickName, String userName, String gender, Date birthDate, String phone, 
					String certification, byte[] certificationPic, String personID, String address, Integer status, Integer ratePeople, 
					Integer ratePoint ) {
		MemberVO vo = new MemberVO();
		vo.setAccount(account);
		vo.setPwd(pwd);
		vo.setNickName(nickName);
		vo.setUserName(userName);
		vo.setGender(gender);
		vo.setBirthDate(birthDate);
		vo.setPhone(phone);
		vo.setCertification(certification);
		vo.setCertificationPic(certificationPic);
		vo.setPersonID(personID);
		vo.setAddress(address);
		vo.setStatus(status);
		vo.setRatePeople(ratePeople);
		vo.setRatePoint(ratePoint);
		
		return vo;
	}
	
	public MemberVO updateMember(Integer userID, String account, String pwd, String nickName, String userName, 
								String gender, Date birthDate, String phone, String certification, byte[] certificationPic, 
								String personID, String address, Timestamp createTime, Integer status, Timestamp upDateTime, Integer ratePeople, Integer ratePoint) {
		MemberVO vo = new MemberVO();
		vo.setUserID(userID);
		vo.setAccount(account);
		vo.setPwd(pwd);
		vo.setNickName(nickName);
		vo.setUserName(userName);
		vo.setGender(gender);
		vo.setBirthDate(birthDate);
		vo.setPhone(phone);
		vo.setCertification(certification);
		vo.setCertificationPic(certificationPic);
		vo.setPersonID(personID);
		vo.setAddress(address);
		vo.setCreateTime(createTime);
		vo.setStatus(status);
		vo.setUpDateTime(upDateTime);
		vo.setRatePeople(ratePeople);
		vo.setRatePoint(ratePoint);
		return vo;
	}
	
	public MemberVO getone(Integer userID) {
		return dao.findByPrimaryKey(userID);
	}
	
	public List<MemberVO> findByAccount(String account){
		return dao.findByAccount(account);
	}
	
	public List<MemberVO> getAll(){
		return dao.getAll();
	}
}
