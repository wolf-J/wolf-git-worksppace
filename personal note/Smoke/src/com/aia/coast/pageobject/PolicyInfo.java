package com.aia.coast.pageobject;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import util.Checker;

import com.aia.coast.entity.Client;
import com.aia.coast.entity.Policy;
import com.aia.coast.entity.individualPolicy;
import com.aia.coast.entity.uatIndividualPolicy;
import com.aia.coast.util.ConvertPropertyToPojo;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;

import static util.Checker.isNotNull;

public class PolicyInfo extends BasePage{

	protected PolicyInfo(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	Locator policyInitEffectiveDate=new Locator("policyInitEffectiveDate");
	Locator policyDesc=new Locator("policyDesc",3);
	Locator policyCategory=new Locator("policyCategory",3);
	Locator marketingProgramType=new Locator("marketingProgramType",3);
	Locator marketingProgramTypeArrowDone=new Locator("marketingProgramTypeArrowDone");
	Locator marketingProgramTypeList=new Locator("marketingProgramTypeList");
	
	Locator policyType=new Locator("policyType",3);
	Locator teamCode=new Locator("teamCode",3);
	Locator servicingOfficer=new Locator("servicingOfficer",3);
	Locator serviceAE=new Locator("serviceAE",3);
	Locator firstYearSaleRep1=new Locator("firstYearSaleRep1",3);
	Locator renewalYearSaleRep1=new Locator("renewalYearSaleRep1",3);
	
	Locator teamCodeDropDown=new Locator("teamCodeDropDown");
	Locator defaultTeamCodeItem=new Locator("defaultTeamCodeItem");
	//Voluntary area
	Locator includeVoluntaryPolicy=new Locator("includeVoluntaryPolicy",3);
	Locator allTeamCode=new Locator("allTeamCode");
	
	Locator teamLeaderDropDown=new Locator("teamLeaderDropDown");
	Locator allTeamLeader=new Locator("allTeamLeader");
	
	Locator teamMemerDropDown=new Locator("teamMemerDropDown");
	Locator allTeamMember=new Locator("allTeamMember");
	
	Locator firstYearDropDown=new Locator("firstYearDropDown");
	Locator allFirstYear=new Locator("allFirstYear");
	
	Locator renewalYearDropDown=new Locator("renewalYearDropDown");
	Locator allRenewalYear=new Locator("allRenewalYear");
	
	Locator enrollDate=new Locator("enrollDate");
	Locator enrollDateBox=new Locator("enrollDateBox");
	
	Locator loadingImg=new Locator("loadingImg");
	Locator loading=new Locator("loading");
	
	Locator quickApprove=new Locator("quickApprove");
	Locator quickApproveBtn=new Locator("quickApproveBtn");
	
	public void fillPolicy(com.aia.coast.testcase.Policy policy) throws Exception
	{
		//Type Initial date
		if(isNotNull(policy.getInitEffectiveDate()))
			clearType(policyInitEffectiveDate,policy.getInitEffectiveDate());
		
		//Type Policy Description
		if(isNotNull(policy.getPolicyDesc()))
			clearType(policyDesc,policy.getPolicyDesc());
		
		//Set Policy Category
		if(isNotNull(policy.getPolicyCategory()))
		{
			click(policyCategory);
			clearType(policyCategory,policy.getPolicyCategory());
			click(policyCategory);
		}
		//Set Policy Marketing Programming Type
		
		TimeUnit.SECONDS.sleep(1);
		
		if(isNotNull(policy.getMarketingProgrammingType()))
		{
			click(marketingProgramType);
			selectValue(marketingProgramTypeArrowDone, marketingProgramTypeList, policy.getMarketingProgrammingType());
			//clearType(marketingProgramType,policy.getMarketingProgrammingType());
			//click(marketingProgramType);

			//performEnter();
		}
		TimeUnit.MILLISECONDS.sleep(200);
		//Set Policy Type
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		if(isNotNull(policy.getPolicyType()))
		{
			click(policyType);
			clearType(policyType,policy.getPolicyType());
			click(policyType);
		}
		
		
		TimeUnit.SECONDS.sleep(1);
		
		WebElement qApprove=findHideElement(quickApprove);
		String quickApproveVal=qApprove.getAttribute("value");
		
		System.out.println("Quick Approve: "+quickApproveVal);
		while(quickApproveVal.equalsIgnoreCase("N"))
		{
			qApprove=findHideElement(quickApprove);
			quickApproveVal=qApprove.getAttribute("value");
			if(quickApproveVal.equalsIgnoreCase("N"))
				click(quickApproveBtn);
		}
		
		//Set Team Code
		if(isNotNull(policy.getTeamCode()))
		{
			pageDown();
			TimeUnit.MILLISECONDS.sleep(100);
			clear(teamCode);
			TimeUnit.MILLISECONDS.sleep(500);
			selectValue(teamCodeDropDown,allTeamCode,policy.getTeamCode());
			
		}
		
		
		TimeUnit.MILLISECONDS.sleep(300);
		//Set Servicing Officer 
		if(isNotNull(policy.getTeamLeader()))
		{
			clear(serviceAE);
			
			selectValue(teamLeaderDropDown, allTeamLeader,policy.getTeamLeader());
		}
		
		//Set Service AE
		if(isNotNull(policy.getTeamMember()))
		{
			//clearType(serviceAE,policy.getTeamMember());
			clear(servicingOfficer);
			selectValue(teamMemerDropDown,allTeamMember,policy.getTeamMember());
		}
		//Set First Year Sale Rep1
		if(isNotNull(policy.getFirstYearSaleRep1()))
		{
			//clearType(firstYearSaleRep1,policy.getFirstYearSaleRep1());
			//click(firstYearSaleRep1);
			//click(firstYearDropDown);
			selectValue(firstYearDropDown,allFirstYear,policy.getFirstYearSaleRep1());
			
		}
		//Set Renewal Year Sale Rep1
		if(isNotNull(policy.getRenewalYearSaleRep1()))
		{
			//clearType(renewalYearSaleRep1,policy.getRenewalYearSaleRep1());
			//click(renewalYearSaleRep1);
			selectValue(renewalYearDropDown,allRenewalYear, policy.getRenewalYearSaleRep1());
		
			
		}
		click(teamCode);
		
		if(Boolean.valueOf(policy.isIncludeVoluntaryPolicy()))
		{
			System.out.println("Individual Policy...");
			scrollToElement(includeVoluntaryPolicy);
			click(includeVoluntaryPolicy);
		}
		
		if(Checker.isNotNull(policy.getEnrollDate()))
		{
			pageDown();
			TimeUnit.MILLISECONDS.sleep(100);
			typeQuickJS(enrollDateBox,policy.getEnrollDate());
			click(enrollDate);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
