package com.member.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class MemberVO implements Serializable{
	private Integer userID;//會員標號
	private String account;//帳號
	private String pwd;//密碼
	private String nickName;//暱稱
	private String gender;//性別
	private Date birthDate;//會員生日
	private String phone;//聯絡電話
	private String certification;//證照
	private byte[] certificationPic;//證照圖片
	private String personID;//身分證字號
	private String address;//地址
	private Timestamp createTime;//帳號建立時間
	private String status;//帳號狀態
	private Integer ratePeople;//被評價總人數
	private Integer ratePoint;//被評價總分
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public byte[] getCertificationPic() {
		return certificationPic;
	}
	public void setCertificationPic(byte[] certificationPic) {
		this.certificationPic = certificationPic;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getRatePeople() {
		return ratePeople;
	}
	public void setRatePeople(Integer ratePeople) {
		this.ratePeople = ratePeople;
	}
	public Integer getRatePoint() {
		return ratePoint;
	}
	public void setRatePoint(Integer ratePoint) {
		this.ratePoint = ratePoint;
	}
}
