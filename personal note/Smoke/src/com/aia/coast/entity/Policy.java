package com.aia.coast.entity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.aia.coast.util.ConvertPropertyToPojo;
import com.aia.ui.utils.Locator;

public class Policy {
	
	public String getPolicyDesc() {
		return policyDesc;
	}
	public void setPolicyDesc(String policyDesc) {
		this.policyDesc = policyDesc;
	}
	public String getPolicyCategory() {
		return policyCategory;
	}
	public void setPolicyCategory(String policyCategory) {
		this.policyCategory = policyCategory;
	}
	public String getMarketingProgramType() {
		return marketingProgramType;
	}
	public void setMarketingProgramType(String marketingProgramType) {
		this.marketingProgramType = marketingProgramType;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getServicingOfficer() {
		return servicingOfficer;
	}
	public void setServicingOfficer(String servicingOfficer) {
		this.servicingOfficer = servicingOfficer;
	}
	public String getServiceAE() {
		return serviceAE;
	}
	public void setServiceAE(String serviceAE) {
		this.serviceAE = serviceAE;
	}
	public String getFirstYearSaleRep1() {
		return firstYearSaleRep1;
	}
	public void setFirstYearSaleRep1(String firstYearSaleRep1) {
		this.firstYearSaleRep1 = firstYearSaleRep1;
	}
	public String getRenewalYearSaleRep1() {
		return renewalYearSaleRep1;
	}
	public void setRenewalYearSaleRep1(String renewalYearSaleRep1) {
		this.renewalYearSaleRep1 = renewalYearSaleRep1;
	}
	
	private String policyDesc;
	private String policyCategory;
	private String marketingProgramType;
	private String policyType;
	private String teamCode;
	private String servicingOfficer;
	private String serviceAE;
	private String firstYearSaleRep1;
	private String renewalYearSaleRep1;

	//test
	public static void main(String ...args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException
	{
		Policy policy=ConvertPropertyToPojo.convertToPojo(Policy.class);
		System.out.println(policy.getPolicyCategory());
	}
	
}
