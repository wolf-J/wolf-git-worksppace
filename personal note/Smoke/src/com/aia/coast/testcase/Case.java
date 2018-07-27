package com.aia.coast.testcase;

public class Case {
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String isRun() {
		return run;
	}
	public void setRun(String run) {
		this.run = run;
	}
	public String getPolicyCategory() {
		return policyCategory;
	}
	public void setPolicyCategory(String policyCategory) {
		this.policyCategory = policyCategory;
	}
	
	
	public String getPolicyNum() {
		return policyNum;
	}
	public void setPolicyNum(String policyNum) {
		this.policyNum = policyNum;
	}


	private String policyNum;
	private String caseId;
	private String run;
	private String policyCategory;
}
