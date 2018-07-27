package com.aia.coast.testcase;

import java.util.List;

public class PolicyProduct {
	public String getProduct() {
		return Product;
	}

	public void setProduct(String product) {
		Product = product;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getModeOfPayment() {
		return ModeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		ModeOfPayment = modeOfPayment;
	}

	public String getBusinessSource() {
		return BusinessSource;
	}

	public void setBusinessSource(String businessSource) {
		BusinessSource = businessSource;
	}

	public String getChannelIndicator() {
		return ChannelIndicator;
	}

	public void setChannelIndicator(String channelIndicator) {
		ChannelIndicator = channelIndicator;
	}

	public String getCommissionCode() {
		return CommissionCode;
	}

	public void setCommissionCode(String commissionCode) {
		CommissionCode = commissionCode;
	}

	public String getBillType() {
		return BillType;
	}

	public void setBillType(String billType) {
		BillType = billType;
	}

	public String getBillPlan() {
		return BillPlan;
	}

	public void setBillPlan(String billPlan) {
		BillPlan = billPlan;
	}

	public String getNelAmount() {
		return NelAmount;
	}

	public void setNelAmount(String nelAmount) {
		NelAmount = nelAmount;
	}

	public String getNelAge() {
		return NelAge;
	}

	public void setNelAge(String nelAge) {
		NelAge = nelAge;
	}

	public String getNMLAmount() {
		return NMLAmount;
	}

	public void setNMLAmount(String nMLAmount) {
		NMLAmount = nMLAmount;
	}

	public String getNMLAge() {
		return NMLAge;
	}

	public void setNMLAge(String nMLAge) {
		NMLAge = nMLAge;
	}

	public String getAgeReference() {
		return AgeReference;
	}

	public void setAgeReference(String ageReference) {
		AgeReference = ageReference;
	}

	public List<DistributionChannel> getDistributionChannel() {
		return distributionChannel;
	}

	public void setDistributionChannel(List<DistributionChannel> distributionChannel) {
		this.distributionChannel = distributionChannel;
	}
	public String getBusinessType() {
		return BusinessType;
	}

	public void setBusinessType(String businessType) {
		BusinessType = businessType;
	}

	public String getHIVLimitAmount() {
		return HIVLimitAmount;
	}

	public void setHIVLimitAmount(String hIVLimitAmount) {
		HIVLimitAmount = hIVLimitAmount;
	}
	
	public String getMemberShipTypeAge() {
		return memberShipTypeAge;
	}

	public void setMemberShipTypeAge(String memberShipTypeAge) {
		this.memberShipTypeAge = memberShipTypeAge;
	}
	
	private String Product;
	private String Currency;
	private String ModeOfPayment;
	private String BusinessSource;
	private String ChannelIndicator;
	private String CommissionCode;
	private String BillType;
	private String BillPlan;
	private String NelAmount;
	private String NelAge;
	private String NMLAmount;
	private String NMLAge;
	private String HIVLimitAmount;
	private String AgeReference;
	private String BusinessType;
	private String memberShipTypeAge;
	private List<DistributionChannel> distributionChannel;
	
}
