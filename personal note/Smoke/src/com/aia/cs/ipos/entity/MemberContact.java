package com.aia.cs.ipos.entity;

import com.google.gson.annotations.Expose;

public class MemberContact {

	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getContactid() {
		return contactid;
	}
	public void setContactid(String contactid) {
		this.contactid = contactid;
	}
	public String getBankaccount() {
		return bankaccount;
	}
	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEappInfoid() {
		return eappInfoid;
	}
	public void setEappInfoid(String eappInfoid) {
		this.eappInfoid = eappInfoid;
	}
	public String getMainplan() {
		return mainplan;
	}
	public void setMainplan(String mainplan) {
		this.mainplan = mainplan;
	}
	public String getIndexno() {
		return indexno;
	}
	public void setIndexno(String indexno) {
		this.indexno = indexno;
	}
	public String getMembercontactid() {
		return membercontactid;
	}
	public void setMembercontactid(String membercontactid) {
		this.membercontactid = membercontactid;
	}
	
	public SubMember[] getSubMember() {
		return SubMember;
	}
	public void setSubMember(SubMember[] subMember) {
		SubMember = subMember;
	}
	
	public int getDependentCount()
	{
		return this.dependentCount;
	}
	public void setDependentCount(int dependentCount)
	{
		this.dependentCount=dependentCount;
	}
	
	private String bankcode;
	private String contactid;
	private String bankaccount;
	private String position;
	private String eappInfoid;
	private String mainplan;
	private String indexno;
	private String membercontactid;
	private SubMember[] SubMember;
	
	private transient int dependentCount;
}
