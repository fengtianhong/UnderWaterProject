package com.member.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class MemberVO implements Serializable{
	private Integer userID;//會員編號X
	private String account;//帳號
	private String pwd;//密碼ˇ
	private String nickName;//暱稱ˇ
	private String userName;//名字ˇ
	private String gender;//性別 helloˇ
	private Date birthDate;//會員生日ˇ
	private String phone;//聯絡電話ˇ
	private String certification;//證照ˇ
	private byte[] certificationPic;//證照圖片ˇ
	private String personID;//身分證字號
	private String address;//地址ˇ
	private Timestamp createTime;//帳號建立時間X
	private Integer status;//帳號狀態ˇ
	private Timestamp upDateTime;//更新時間ˇX
	private Integer ratePeople;//被評價總人數ˇ
	private Integer ratePoint;//被評價總分ˇ
	
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Timestamp getUpDateTime() {
		return upDateTime;
	}
	public void setUpDateTime(Timestamp upDateTime) {
		this.upDateTime = upDateTime;
	}
}
