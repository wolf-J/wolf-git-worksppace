package com.aia.coast.testcase;

public class BillResult {
	public String getPremiums() {
		return premiums;
	}
	public void setPremiums(String premiums) {
		this.premiums = premiums;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getCommissionAmounts() {
		return commissionAmounts;
	}
	public void setCommissionAmounts(String commissionAmounts) {
		this.commissionAmounts = commissionAmounts;
	}
	private String premiums;
	private String fees;
	private String commissionAmounts;
	
	
}
