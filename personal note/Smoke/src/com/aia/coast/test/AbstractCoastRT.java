package com.aia.coast.test;

import util.Checker;

import com.aia.coast.testcase.CoastCase;

public abstract class AbstractCoastRT implements CoastRT{
	
	protected String policyNum;
	protected String pmAccount;
	protected String mmAccount;
	protected String url;
	protected CoastCase testcase;
	boolean go=true;
	
	public abstract void setTestcase(CoastCase testcase);

	
	public void initialize(String url, String pmAccount, String mmAccount)
	{
		this.url=url;
		this.pmAccount=pmAccount;
		this.mmAccount=mmAccount;
	}
	
	public void initialize(String url, String policyNum, String pmAccount, String mmAccount)
	{
		this.url=url;
		this.policyNum=policyNum;
		this.pmAccount=pmAccount;
		this.mmAccount=mmAccount;
	}
	
	@Override
	public void executeTest(CoastCase testcase)
	{
		this.testcase=testcase;
		
		
		if(!isIPos())
		{
			go=policyMaintenance();
		}else
		{
			go=policyManitenance(policyNum);
		}
		
		
		
		if(go && isMasterQC())
		{
			go=policyMasterQC(policyNum);
		}
		
		if(go)
		{
			go=memberMaintenance(policyNum);
		}
		
		
		
		if(go && isMemberQC())
		{
			go=memberQC(policyNum);
		}
		
		
		if(go && isMemberUW())
		{
			memberLetterUW(policyNum);
			memberPendingUW(policyNum);
			memberUW(policyNum);
		}
		
		if(go)
		{	
			go=billGeneration(policyNum);
		}
		
		if(go && Checker.isNotNull(testcase.getPmChange()) && Checker.isNotNull(testcase.getPmChange().getChangeSection()))
		{
			go=createChangeJob(policyNum);
			
		}
		
		if(go && Checker.isNotNull(testcase.getPmChange()) && Checker.isNotNull(testcase.getPmChange().getChangeSection()))
		{
			go=PMChange(policyNum);
		}
		
		if(go && Checker.isNotNull(testcase.getPmChange()) && Checker.isNotNull(testcase.getPmChange().getChangeSection()))
		{
			go=billGenerationForChange(policyNum);
		}
	}
	
	
	@Override
	public void smokeTest(CoastCase testcase)
	{
		this.testcase=testcase;
		boolean go=true;
		
		if(!isIPos())
		{
			go=policyMaintenance();
		}else
		{
			go=policyManitenance(policyNum);
		}
		
		
		
		if(go && isMasterQC())
		{
			go=policyMasterQC(policyNum);
		}
		
		if(go)
		{
			go=memberMaintenance(policyNum);
		}	
		
		
		if(go)
		{
			System.out.println("Finish Smoke testing and the result passed.");
		}else
		{
			System.out.println("Finish Smoke testing and the result failed.");	
		}
	}



	
}
