package com.aia.coast.testcase;

public class Policy {
	public String getPolicyType() {
		return PolicyType;
	}
	public void setPolicyType(String policyType) {
		PolicyType = policyType;
	}
	public String getTeamCode() {
		return TeamCode;
	}
	public void setTeamCode(String teamCode) {
		TeamCode = teamCode;
	}
	public String getTeamLeader() {
		return TeamLeader;
	}
	public void setTeamLeader(String teamLeader) {
		TeamLeader = teamLeader;
	}
	public String getTeamMember() {
		return TeamMember;
	}
	public void setTeamMember(String teamMember) {
		TeamMember = teamMember;
	}
	public String getFirstYearSaleRep1() {
		return FirstYearSaleRep1;
	}
	public void setFirstYearSaleRep1(String firstYearSaleRep1) {
		FirstYearSaleRep1 = firstYearSaleRep1;
	}
	public String getRenewalYearSaleRep1() {
		return RenewalYearSaleRep1;
	}
	public void setRenewalYearSaleRep1(String renewalYearSaleRep1) {
		RenewalYearSaleRep1 = renewalYearSaleRep1;
	}
	public String isIncludeVoluntaryPolicy() {
		return IncludeVoluntaryPolicy;
	}
	public void setIncludeVoluntaryPolicy(String includeVoluntaryPolicy) {
		IncludeVoluntaryPolicy = includeVoluntaryPolicy;
	}
	public String getPolicyCategory() {
		return PolicyCategory;
	}
	public void setPolicyCategory(String policyCategory) {
		PolicyCategory = policyCategory;
	}
	public String getMarketingProgrammingType() {
		return MarketingProgrammingType;
	}
	public void setMarketingProgrammingType(String marketingProgrammingType) {
		MarketingProgrammingType = marketingProgrammingType;
	}
	public String getPolicyDesc() {
		return PolicyDesc;
	}
	public void setPolicyDesc(String policyDesc) {
		PolicyDesc = policyDesc;
	}

	public String getInitEffectiveDate() {
		return InitEffectiveDate;
	}
	public void setInitEffectiveDate(String initEffectiveDate) {
		InitEffectiveDate = initEffectiveDate;
	}

	public String getEnrollDate() {
		return EnrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		EnrollDate = enrollDate;
	}
	
	private String PolicyDesc;
	private String InitEffectiveDate;
	private String PolicyCategory;
	private String MarketingProgrammingType;
	private String PolicyType;
	private String TeamCode;
	private String TeamLeader;
	private String TeamMember;
	private String FirstYearSaleRep1;
	private String RenewalYearSaleRep1;
	private String EnrollDate;
	private String IncludeVoluntaryPolicy="false";
	
}
