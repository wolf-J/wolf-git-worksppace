package com.aia.coast.test;

import com.aia.coast.testcase.CoastCase;

public interface CoastRT {

	public void executeTest(CoastCase testcase);
	
	public void smokeTest(CoastCase testcase);
	
	public boolean policyMaintenance();
	
	public boolean policyManitenance(String policyNum);
	
	public boolean policyMasterQC(String policyNum);
	
	public boolean memberMaintenance(String policyNum);
	
	public boolean memberQC(String policyNum);
	
	public boolean memberLetterUW(String policyNum);
	
	public boolean memberPendingUW(String policyNum);
	
	public boolean memberUW(String policyNum);
	
	public boolean billGeneration(String policyNum);
	
	public boolean receivablePending(String policyNum);
	
	public boolean receivable(String policyNum);
	
	public boolean isMasterQC();
	
	public boolean isMemberQC();
	
	public boolean isMemberUW();
	
	public boolean isIPos();
	
	public boolean contractCheck(String policyNum);
	
	public boolean createChangeJob(String policyNum);
	
	public boolean PMChange(String policyNum);
	
	public boolean billGenerationForChange(String policyNum);

	boolean MemberBillGenerationChange(String policyNum);

	boolean CreateReceivableJob(String policyNum);

	boolean InReceivableRefund(String policyNum);

	boolean IndividualReceivable(String policyNum);

	boolean InReceivablePending(String policyNum);

	boolean GroupReceivable(String policyNum);
}
