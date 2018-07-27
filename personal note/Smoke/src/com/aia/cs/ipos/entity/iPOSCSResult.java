package com.aia.cs.ipos.entity;

import util.JsonUtil;

public class iPOSCSResult {
	private String subReult;
	public String getSubReult() {
		return subReult;
	}


	public void setSubReult(String subReult) {
		this.subReult = subReult;
	}


	public String getPolicyNo() {
		return policyNo;
	}


	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}


	public String getSubmitDate() {
		return submitDate;
	}


	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getReferenceNo() {
		return referenceNo;
	}


	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}


	private String policyNo;
	private String submitDate;
	private String message;
	private String referenceNo;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ss="{\"subReult\":\"1\",\"policyNo\":\"0000106109\",\"submitDate\":\"01\\/09\\/2017 20:38:45\",\"message\":\"Submission success!\",\"referenceNo\":\"152495|AppList|2017010921362148\"}";
		iPOSCSResult re=(iPOSCSResult) JsonUtil.formatJsonToObject(ss, iPOSCSResult.class);
		System.out.println(re.getPolicyNo());
	}

}
