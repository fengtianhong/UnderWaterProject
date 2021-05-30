package com.partymember.model;

import java.sql.Date;

public class PartyMemberVO {
	
	private Integer partyMemberSN;
	private Integer partySN;
	private Integer partyMember;
	private String gender;
	private String email;
	private String phone;
	private Date birthDate;
	private String personID;
	private String certification;
	private byte[] certificationPic;
	private Date appliedDate;
	private String comment;
	private String status;
	
	public PartyMemberVO() {
		super();
	}

	public PartyMemberVO(Integer partyMemberSN, Integer partySN, Integer partyMember, String gender, String email,
			String phone, Date birthDate, String personID, String certification, byte[] certificationPic,
			Date appliedDate, String comment, String status) {
		super();
		this.partyMemberSN = partyMemberSN;
		this.partySN = partySN;
		this.partyMember = partyMember;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthDate;
		this.personID = personID;
		this.certification = certification;
		this.certificationPic = certificationPic;
		this.appliedDate = appliedDate;
		this.comment = comment;
		this.status = status;
	}

	public Integer getPartyMemberSN() {
		return partyMemberSN;
	}

	public void setPartyMemberSN(Integer partyMemberSN) {
		this.partyMemberSN = partyMemberSN;
	}

	public Integer getPartySN() {
		return partySN;
	}

	public void setPartySN(Integer partySN) {
		this.partySN = partySN;
	}

	public Integer getPartyMember() {
		return partyMember;
	}

	public void setPartyMember(Integer partyMember) {
		this.partyMember = partyMember;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
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

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
