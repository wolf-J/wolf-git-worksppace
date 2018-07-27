package com.aia.coast.testcase;

public class DistributionChannel {
	public String getFeeType() {
		return FeeType;
	}
	public void setFeeType(String feeType) {
		FeeType = feeType;
	}
	public String getProducerCode() {
		return ProducerCode;
	}
	public void setProducerCode(String producerCode) {
		ProducerCode = producerCode;
	}
	public String getFeeShare() {
		return FeeShare;
	}
	public void setFeeShare(String feeShare) {
		FeeShare = feeShare;
	}
	public String getCaseCountShare() {
		return CaseCountShare;
	}
	public void setCaseCountShare(String caseCountShare) {
		CaseCountShare = caseCountShare;
	}
	public String isSubOfficeProducer() {
		return SubOfficeProducer;
	}
	public void setSubOfficeProducer(String subOfficeProducer) {
		SubOfficeProducer = subOfficeProducer;
	}
	
	private String SubOfficeProducer;
	private String FeeType;
	private String ProducerCode;
	private String FeeShare;
	private String CaseCountShare;
}
