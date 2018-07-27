package com.aia.coast.pageobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import util.Checker;
import util.ListUtil;

import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;

import static util.Checker.isNotNull;
import util.StrUtil;

public class PolicyProduct extends BasePage{

	protected PolicyProduct(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	Locator modeOfPayment=new Locator("modeOfPayment");
	Locator bizSource=new Locator("bizSource");
	
	Locator commissionCode=new Locator("commisCode");
	Locator billType=new Locator("billType");
	Locator billPlan=new Locator("billPlan");
	Locator reInsuranceTreatyCode=new Locator("reInsuranceTreatyCode");
	
	Locator bizType=new Locator("bizType");
	Locator waitingPeriodYear=new Locator("waitingPeriodYear");
	Locator yearArrowDown=new Locator("yearArrowDown");
	Locator waitingPeriodMonth=new Locator("waitingPeriodMonth");
	Locator monthArrowDown=new Locator("monthArrowDown");
	Locator waitingPeriodDay=new Locator("waitingPeriodDay");
	Locator dayArrowDown=new Locator("dayArrowDown");
	
	Locator nelAmount=new Locator("nelAmount");
	Locator nelAge=new Locator("nelAge");
	Locator nmlAmount=new Locator("nmlAmount");
	Locator nmlAge=new Locator("nmlAge");
	
	Locator HIVLimitAmount=new Locator("HIVLimitAmount");
	
	Locator ageReference=new Locator("ageReference");
	
	Locator productAgeEditList=new Locator("productAgeEditList");
	Locator productAgeMemberTypeList=new Locator("productAgeMemberTypeList");
	
	Locator minAgeInputUI=new Locator("minAgeInputUI");
	Locator memberTypeInput=new Locator("memberTypeInput");
	Locator effectiveDateInput=new Locator("effectiveDateInput");
	Locator minAgeInput=new Locator("minAgeInput");
	Locator maxAgeInput=new Locator("maxAgeInput");
	Locator maxCoverAgeInput=new Locator("maxCoverAgeInput");
	Locator childAgeFrame=new Locator("childAgeFrame");
	Locator editFirstProductAge=new Locator("editFirstProductAge");
	Locator maxAgeInputUI=new Locator("maxAgeInputUI");
	Locator maxCoverAgeInputUI=new Locator("maxCoverAgeInputUI");
	
	Locator childItem=new Locator("childItem");
	Locator deleteProductAge=new Locator("deleteProductAge");
	Locator confirmDeleteBtn=new Locator("confirmDelete");
	Locator addProductAge=new Locator("addProductAge");
	
	Locator addDisChannel=new Locator("addDischannel");
	Locator disChanlFeeType=new Locator("disChanlFeeType");
	Locator productCode=new Locator("productCode");
	
	Locator producerName=new Locator("producerName");
	
	Locator editBtn=new Locator("editBtn");
	
	Locator feeShareCell=new Locator("feeShareCell");
	Locator valuationCode=new Locator("valuationCode");
	//prodcut
	Locator product=new Locator("product");
	Locator loadingImg=new Locator("loadingImg");
	Locator loadingText=new Locator("loadingText");
	Locator loadingbg=new Locator("loadingbg");
	
	
	Locator PolicyProductFrame=new Locator("PolicyProductFrame");
	
	Locator feeShare=new Locator("feeShare");
	Locator feeShareContainer=new Locator("feeShareContainer");
	
	Locator caseCountShare=new Locator("caseCountShare");
	Locator caseCountShareContainer=new Locator("caseCountShareContainer");
	
	public void updatePolicyProduct(com.aia.coast.testcase.PolicyProduct policyProduct, int order) throws Exception
	{
		if(isNotNull(policyProduct.getModeOfPayment()))
			clearType(modeOfPayment,policyProduct.getModeOfPayment());
		if(isNotNull(policyProduct.getBusinessSource()))
			clearType(bizSource,policyProduct.getBusinessSource());
		if(isNotNull(policyProduct.getCommissionCode()))
			clearType(commissionCode,policyProduct.getCommissionCode());
		if(isNotNull(policyProduct.getBillType()))
			clearType(billType,policyProduct.getBillType());
		if(isNotNull(policyProduct.getBillPlan()))
			clearType(billPlan,policyProduct.getBillPlan());
		if(isNotNull(policyProduct.getBusinessType()))
				clearType(bizType,policyProduct.getBusinessType());
		
		String reinsuranceTreatyCodeTxt= getAttributeVal(reInsuranceTreatyCode,"value");
		System.out.println("ReInsuranceTreatyCode: "+reinsuranceTreatyCodeTxt);
		if(reinsuranceTreatyCodeTxt.length()<=5)
		{
			try{
				clear(reInsuranceTreatyCode);
			}catch(Exception e){
				//ignore
			}
		}
		
		pageDown();
		if(isNotNull(policyProduct.getNelAmount()))
		{
			click(nelAmount);
			clearSType(nelAmount,policyProduct.getNelAmount());		
		}
		TimeUnit.MILLISECONDS.sleep(500);
		if(isNotNull(policyProduct.getNelAge()))
		{
			click(nelAge);
			clearSType(nelAge,policyProduct.getNelAge());
		}
		TimeUnit.MILLISECONDS.sleep(500);
		
		if(isNotNull(policyProduct.getNMLAmount()))
		{
			click(nmlAmount);
			clearSType(nmlAmount,policyProduct.getNMLAmount());		
		}
		TimeUnit.MILLISECONDS.sleep(500);
		if(isNotNull(policyProduct.getNMLAge()))
		{
			pageDown();
			TimeUnit.MILLISECONDS.sleep(200);
			click(nmlAge);
			clearSType(nmlAge,policyProduct.getNMLAge());
		}
		TimeUnit.MILLISECONDS.sleep(500);
		
		
		if(isNotNull(policyProduct.getHIVLimitAmount()))
		{
			scrollToElement(HIVLimitAmount);
			pageDown();
			TimeUnit.MILLISECONDS.sleep(200);
			click(HIVLimitAmount);
			clearSType(HIVLimitAmount,policyProduct.getHIVLimitAmount());
		}
		TimeUnit.MILLISECONDS.sleep(500);
		
		//Remove child
		pageDown();
		
		List<String> mems=Arrays.asList(policyProduct.getMemberShipTypeAge().split(","));
		mems=mems.stream().map(item -> {return StrUtil.ManipulateBenefitCode(item);}).collect(Collectors.toList());
		List<WebElement> ageMemberList=findElements(productAgeMemberTypeList);
		List<String> currentMems=new ArrayList<>();
		for(int i=0;i<ageMemberList.size();i++)
		{
			String memType=ageMemberList.get(i).getText();
			while(Checker.isNull(memType))
			{
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				// roll down and keep the element to the center of browser
				js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", ageMemberList.get(i));
				
				memType=ageMemberList.get(i).getText();
				System.out.println("Member Type: "+memType+" Jason");
				TimeUnit.MILLISECONDS.sleep(300);
			}
			System.out.println("Member Type: "+memType+" Jason");
			currentMems.add(memType);
		}
		if(currentMems.size()>mems.size())
		{
			System.out.println("Would Remove Member Type into Product Age");
		}
		else
		{
			System.out.println("Would Add Member Type from Product Age");
		}
		
		List<String> updateMemType=ListUtil.diffSet(currentMems,mems);
		
		
		for(int j=0;j<updateMemType.size();j++)
		{
			if(j==0)
				pageDown();
			String updateItem=updateMemType.get(j);
			if(j>0)
			{
				ageMemberList=findElements(productAgeMemberTypeList);
				currentMems.clear();
				for(int i=0;i<ageMemberList.size();i++)
				{
					String memType=ageMemberList.get(i).getText();
					System.out.println("Member Type: "+memType+" Jason");
					currentMems.add(memType);
				}
			}
			int index=currentMems.indexOf(updateItem);
			System.out.println("Index: "+index);
			List<WebElement> ageEdits=findElements(productAgeEditList);
			WebElement ageCheck=ageEdits.get(index);
			ageCheck.click();
			TimeUnit.MILLISECONDS.sleep(500);
			click(deleteProductAge);
			TimeUnit.SECONDS.sleep(1);
			click(confirmDeleteBtn);
		}
		
		pageDown();
		
		if(order==0)
		{
			List<com.aia.coast.testcase.DistributionChannel> productDCs=policyProduct.getDistributionChannel();
			
			for(com.aia.coast.testcase.DistributionChannel dc : productDCs)
			{
				click(addDisChannel);
				TimeUnit.SECONDS.sleep(1);
				clearType(disChanlFeeType,dc.getFeeType());
				clearType(productCode,dc.getProducerCode());
			
				performTab();
			
				TimeUnit.SECONDS.sleep(5);
			
				rawInput(dc.getFeeShare());
			
				performTab();
				rawInput(dc.getCaseCountShare());
			
				TimeUnit.SECONDS.sleep(1);
				performEnter();
				switchBack();
				TimeUnit.SECONDS.sleep(1);
				
				while(!elementNotDisplay(loadingImg))
				{
					TimeUnit.SECONDS.sleep(1);
				}
				TimeUnit.SECONDS.sleep(1);
				while(!elementNotDisplay(loadingImg))
				{
					TimeUnit.SECONDS.sleep(1);
				}
				switchToFrame(PolicyProductFrame);
			}
			
			
		}
		
		click(save);
	}
	
	
	public void updateSMPolicyProduct(com.aia.coast.testcase.PolicyProduct policyProduct) throws Exception
	{
		System.out.println("Start update Tailor made Policy Product...");
		if(isNotNull(policyProduct.getModeOfPayment()))
			clearType(modeOfPayment,policyProduct.getModeOfPayment());
		if(isNotNull(policyProduct.getBusinessSource()))
			clearType(bizSource,policyProduct.getBusinessSource());
		if(isNotNull(policyProduct.getCommissionCode()))
			clearType(commissionCode,policyProduct.getCommissionCode());
		if(isNotNull(policyProduct.getBillType()))
		{
			WebElement bType=getElement(billType);
			String bilTypeVal=bType.getAttribute("value");
			if(bilTypeVal!=null && bilTypeVal.length()>2)
			{
				clearType(billType,policyProduct.getBillType());
			}	
			
			
		}
		//Check Valuation Code
		
		if(isNotNull(policyProduct.getBillPlan()))
			clearType(billPlan,policyProduct.getBillPlan());
		
		WebElement valCode=getElement(valuationCode);
		
		String valCodeTxt=valCode.getAttribute("value");
		System.out.println("Valuation Coed is: "+valCodeTxt+". Jason");
		if(Checker.isNull(valCodeTxt))
		{
			clearType(valuationCode,"VMD01");
		}
		
		if(isNotNull(policyProduct.getBusinessType()))
				clearType(bizType,policyProduct.getBusinessType());
		pageDown();
		if(isNotNull(policyProduct.getNelAmount()))
		{
			click(nelAmount);
			clearSType(nelAmount,policyProduct.getNelAmount());		
		}
		TimeUnit.MILLISECONDS.sleep(500);
		if(isNotNull(policyProduct.getNelAge()))
		{
			click(nelAge);
			clearSType(nelAge,policyProduct.getNelAge());
		}
		TimeUnit.MILLISECONDS.sleep(500);
		
		if(isNotNull(policyProduct.getHIVLimitAmount()))
		{
			click(HIVLimitAmount);
			clearSType(HIVLimitAmount,policyProduct.getHIVLimitAmount());
		}
		TimeUnit.MILLISECONDS.sleep(500);
		
		//Remove child
		pageDown();
		
		
		pageDown();
		
		
		click(save);
	}
	
	
	
	public void updateDirectPolicyProduct() throws Exception
	{
		clearType(bizSource,"D - Direct");
		
		clearType(billType,"D - Headrate");
		
		clearType(billPlan,"T10 - THB/NC/S+A");
		
		clearType(reInsuranceTreatyCode,"");
		//scrollToElement(waitingPeriodYear);
		//click(waitingPeriodYear);
		
		//clearType(waitingPeriodYear,"0");
		
		/*
		moveToElement(yearArrowDown);
		click(yearArrowDown);
		
		click(monthArrowDown);
		
		click(dayArrowDown);
		*/
		
		//clearType(waitingPeriodMonth,"0");
		//clearType(waitingPeriodDay,"0");
		
		//Remvoe 
		
		click(nelAmount);
		clearSType(nelAmount,"2000000");
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(nelAge);
		clearSType(nelAge,"58");
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(HIVLimitAmount);
		clearSType(HIVLimitAmount,"2000000");
		TimeUnit.MILLISECONDS.sleep(500);
		
		//Remove child
		click(childItem);
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(deleteProductAge);
		TimeUnit.SECONDS.sleep(1);
		click(confirmDeleteBtn);
		
		//Add Distribution Channel
		
		pageDown();
		pageDown();
	
		
		/*
		click(producerName);
		TimeUnit.MILLISECONDS.sleep(500);
		click(feeShareCell);
		
		TimeUnit.SECONDS.sleep(5);
		
		click(feeShare);
		TimeUnit.SECONDS.sleep(5);
		clearSType(feeShare,"100");
		
		click(caseCountShare);
		clearSType(caseCountShare,"100");
		
		TimeUnit.SECONDS.sleep(1);
		*/
		
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(save);
	}
	
	public void updateSecondPolicyProduct() throws Exception
	{
		clearType(modeOfPayment,"A - Annually");
		
		clearType(bizSource,"A - Agent");
		
		clearType(commissionCode,"PF150 - FLAT 15%");
		
		clearType(billType,"D - Headrate");
		
		clearType(billPlan,"T10 - THB/NC/S+A");
		
		clearType(reInsuranceTreatyCode,"");
		//scrollToElement(waitingPeriodYear);
		//click(waitingPeriodYear);
		//clearType(waitingPeriodYear,"0");
		/*
		perfromTimesTab(16);
		TimeUnit.MILLISECONDS.sleep(100);
		moveToElement(yearArrowDown);
		click(yearArrowDown);
		
		click(monthArrowDown);
		
		click(dayArrowDown);
		*/
		//clearType(waitingPeriodMonth,"0");
		//clearType(waitingPeriodDay,"0");
		clearType(bizType,"ABNA - AABN Quota Share Pooling");
		//Remove
		pageDown();
		click(nelAmount);
		
		clearSType(nelAmount,"200000");
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(nelAge);
		clearSType(nelAge,"58");
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(HIVLimitAmount);
		clearSType(HIVLimitAmount,"2000000");
		TimeUnit.MILLISECONDS.sleep(500);
		
		perfromTimesTab(4);
		//Remove child
		click(childItem);
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(deleteProductAge);
		TimeUnit.SECONDS.sleep(1);
		click(confirmDeleteBtn);
		
	
		
		/*
		click(producerName);
		TimeUnit.MILLISECONDS.sleep(500);
		click(feeShareCell);
		
		TimeUnit.SECONDS.sleep(5);
		
		click(feeShare);
		TimeUnit.SECONDS.sleep(5);
		clearSType(feeShare,"100");
		
		click(caseCountShare);
		clearSType(caseCountShare,"100");
		
		TimeUnit.SECONDS.sleep(1);
		*/
		
		TimeUnit.SECONDS.sleep(1);
		click(save);
	}
	
	public void updateSecondDirectPolicyProduct() throws Exception
	{
		clearType(bizSource,"D - Direct");
		
		clearType(billType,"D - Headrate");
		
		clearType(billPlan,"T10 - THB/NC/S+A");
		
		clearType(reInsuranceTreatyCode,"");
		//scrollToElement(waitingPeriodYear);
		//click(waitingPeriodYear);
		//clearType(waitingPeriodYear,"0");
		/*
		perfromTimesTab(16);
		TimeUnit.MILLISECONDS.sleep(100);
		moveToElement(yearArrowDown);
		click(yearArrowDown);
		
		click(monthArrowDown);
		
		click(dayArrowDown);
		*/
		//clearType(waitingPeriodMonth,"0");
		//clearType(waitingPeriodDay,"0");
		
		//Remove
		pageDown();
		click(nelAmount);
		
		clearSType(nelAmount,"200000");
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(nelAge);
		clearSType(nelAge,"58");
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(HIVLimitAmount);
		clearSType(HIVLimitAmount,"2000000");
		TimeUnit.MILLISECONDS.sleep(500);
		
		perfromTimesTab(4);
		//Remove child
		click(childItem);
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(deleteProductAge);
		TimeUnit.SECONDS.sleep(1);
		click(confirmDeleteBtn);
		
	
		
		/*
		click(producerName);
		TimeUnit.MILLISECONDS.sleep(500);
		click(feeShareCell);
		
		TimeUnit.SECONDS.sleep(5);
		
		click(feeShare);
		TimeUnit.SECONDS.sleep(5);
		clearSType(feeShare,"100");
		
		click(caseCountShare);
		clearSType(caseCountShare,"100");
		
		TimeUnit.SECONDS.sleep(1);
		*/
		
		TimeUnit.SECONDS.sleep(1);
		click(save);
	}
	
	public void addPolicyProduct1(com.aia.coast.testcase.PolicyProduct policyProduct, int i) throws Exception
	{
		clearType(product,policyProduct.getProduct());
		performTab();
		TimeUnit.SECONDS.sleep(2);
		
		clearType(bizSource,policyProduct.getBusinessSource());
		
		if(Checker.isNotNull(policyProduct.getCommissionCode()))
			clearType(commissionCode,policyProduct.getCommissionCode());
		
		clearType(billType,policyProduct.getBillType());
		
		clearType(billPlan,policyProduct.getBillPlan());
		
		//performEnter();
		
	
		
		pageDown();
		
		//scrollToElement(waitingPeriodYear);
		//click(waitingPeriodYear);
		//clearType(waitingPeriodYear,"0");
		TimeUnit.MILLISECONDS.sleep(500);
		
		switchBack();
		
		while(!elementNotDisplay(loadingbg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.MILLISECONDS.sleep(1000);
		while(!elementNotDisplay(loadingbg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingbg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		
		
		
		switchToFrame(PolicyProductFrame);
		TimeUnit.MILLISECONDS.sleep(1);
		click(reInsuranceTreatyCode);
		
		String reinsuranceTreatyCodeTxt= getVisualAttributeVal(reInsuranceTreatyCode,"value");
		System.out.println("ReInsuranceTreatyCode: "+reinsuranceTreatyCodeTxt);
		
		if(reinsuranceTreatyCodeTxt.length()<=5 && reinsuranceTreatyCodeTxt.length()>=1)
		{
			//clear(reInsuranceTreatyCode);
			System.out.println("Try Clear the reinsureanceTreatyCode. And it is Size: "+reinsuranceTreatyCodeTxt.length());
			typeQuick(reInsuranceTreatyCode,"");
		}
		
		
		
		click(yearArrowDown);
	
		
		click(monthArrowDown);
		
		click(dayArrowDown);
		
		//clearType(waitingPeriodMonth,"0");
		//clearType(waitingPeriodDay,"0");
		
		if(isNotNull(policyProduct.getNelAmount()))
		{
			click(nelAmount);
			clearSType(nelAmount,policyProduct.getNelAmount());
		}
		TimeUnit.MILLISECONDS.sleep(500);
	
		if(isNotNull(policyProduct.getNelAge()))
		{
			click(nelAge);
			TimeUnit.MILLISECONDS.sleep(500);
			clearSType(nelAge,policyProduct.getNelAge());
			TimeUnit.MILLISECONDS.sleep(500);
		}
		
		if(isNotNull(policyProduct.getNMLAmount()))
		{
			click(nmlAmount);
			clearSType(nmlAmount,policyProduct.getNMLAmount());
			TimeUnit.MILLISECONDS.sleep(500);
		}
		
		if(isNotNull(policyProduct.getNMLAge()))
		{
			pageDown();
			click(nmlAge);
			
			TimeUnit.MILLISECONDS.sleep(500);
			clearSType(nmlAge,policyProduct.getNMLAge());
			TimeUnit.MILLISECONDS.sleep(500);
		}
		clearType(ageReference,policyProduct.getAgeReference());
		TimeUnit.MILLISECONDS.sleep(500);
		
		//Check Product Premium Age is consistent with Current page item
		pageDown();
		TimeUnit.MILLISECONDS.sleep(200);
		
	
		List<String> mems=Arrays.asList(policyProduct.getMemberShipTypeAge().split(","));
		mems=mems.stream().map(item -> {return StrUtil.ManipulateBenefitCode(item);}).collect(Collectors.toList());
		List<WebElement> ageMemberList=findElements(productAgeMemberTypeList);
		
		List<String> currentMems=new ArrayList<>();
		
		String memEffectiveDate = null;
		String memMinAge=null;
		String memMaxEntryAge=null;
		String memMaxCoverAge=null;
		
		for(int index=0;index<ageMemberList.size();index++)
		{
			String effetiveDateXpath="//div[@section-name='Policy Product Age']/div/table[@role='grid']/tbody/tr["+(index+1)+"]/td[3]";
			String memMinAgeXpath="//div[@section-name='Policy Product Age']/div/table[@role='grid']/tbody/tr["+(index+1)+"]/td[4]";
			String memMaxEntryAgeXpath="//div[@section-name='Policy Product Age']/div/table[@role='grid']/tbody/tr["+(index+1)+"]/td[5]";
			String memMaxCoverAgeXpath="//div[@section-name='Policy Product Age']/div/table[@role='grid']/tbody/tr["+(index+1)+"]/td[6]";
			
			ageMemberList=findElements(productAgeMemberTypeList);
			
			String memType=ageMemberList.get(index).getText();
			System.out.println("Member Type: "+memType+" Jason");
			if(memType.contains("Member"))
			{
				memEffectiveDate=driver.findElement(By.xpath(effetiveDateXpath)).getText();
				memMinAge=driver.findElement(By.xpath(memMinAgeXpath)).getText();
				memMaxEntryAge=driver.findElement(By.xpath(memMaxEntryAgeXpath)).getText();
				memMaxCoverAge=driver.findElement(By.xpath(memMaxCoverAgeXpath)).getText();
				System.out.println("memEffectiveDate:"+memEffectiveDate);
				System.out.println("memMinAge:"+memMinAge);
				System.out.println("memMaxEntryAge:"+memMaxEntryAge);
				System.out.println("memMaxCoverAge:"+memMaxCoverAge);
			}else if(memType.contains("Child"))
			{
				System.out.println("Would check Child Age Time Frame Type, if it is null, would add accordingly......");
				String childEdit="//div[@section-name='Policy Product Age']/div/table/tbody/tr["+(index+1)+"]/td/a";
				String childFrame="//div[@section-name='Policy Product Age']/div/table[@role='grid']/tbody/tr["+(index+1)+"]/td[7]";
				
				WebElement childFrameElement=driver.findElement(By.xpath(childFrame));
				if(Checker.isNull(childFrameElement.getText()))
				{
					WebElement childEditElement=driver.findElement(By.xpath(childEdit));
					childEditElement.click();
					clearType(childAgeFrame,"D - Day");
					TimeUnit.MILLISECONDS.sleep(500);
					childEditElement=driver.findElement(By.xpath(childEdit));
					childEditElement.click();
				}
			}
			
			currentMems.add(memType);
		}
		
		if(currentMems.size()<mems.size())
		{
			System.out.println("Would Add Member Type into Product Age.");
			List<String> updateMemType=ListUtil.diffList(currentMems,mems);
			updateMemType.stream().forEach(ageMember -> System.out.println("Age: "+ageMember+" would be added."));
			
			for(String addMemType : updateMemType)
			{
				System.out.println("Add "+addMemType);
				click(addProductAge);
				
				click(memberTypeInput);
				clearType(memberTypeInput,addMemType);
				click(memberTypeInput);
				
				clearType(effectiveDateInput,memEffectiveDate);
				if(addMemType.contains("Spouse"))
				{
					typeQuickJS(minAgeInput,memMinAge);
					
					TimeUnit.MILLISECONDS.sleep(100);
					click(minAgeInputUI);
					
					typeQuickJS(maxAgeInput,memMaxEntryAge);
					click(maxAgeInputUI);
					
					//clearType(maxAgeInput,memMaxEntryAge);
					typeQuickJS(maxCoverAgeInput,memMaxCoverAge);
					click(maxCoverAgeInputUI);
					//clearType(maxCoverAgeInput,memMaxCoverAge);
				}else if(addMemType.contains("Child"))
				{
					typeQuickJS(minAgeInput,"0");
					
					TimeUnit.MILLISECONDS.sleep(100);
					click(minAgeInputUI);
					
					typeQuickJS(maxAgeInput,"22");
					click(maxAgeInputUI);
					
					typeQuickJS(maxCoverAgeInput,"23");
					click(maxCoverAgeInputUI);
					
					clearType(childAgeFrame,"D - Day");
				}
				TimeUnit.MILLISECONDS.sleep(100);
				click(editFirstProductAge);
			}
			
		}else if(currentMems.size()>mems.size())
		{
			System.out.println("We are going to remove items...");
			List<String> updateMemType=ListUtil.diffSet(currentMems,mems);
			
			
			for(int j=0;j<updateMemType.size();j++)
			{
				if(j==0)
					pageDown();
				String updateItem=updateMemType.get(j);
				if(j>0)
				{
					ageMemberList=findElements(productAgeMemberTypeList);
					currentMems.clear();
					for(int ii=0;ii<ageMemberList.size();ii++)
					{
						String memType=ageMemberList.get(ii).getText();
						System.out.println("Member Type: "+memType+" Jason");
						currentMems.add(memType);
					}
				}
				int index=currentMems.indexOf(updateItem);
				System.out.println("Index: "+index);
				List<WebElement> ageEdits=findElements(productAgeEditList);
				WebElement ageCheck=ageEdits.get(index);
				ageCheck.click();
				TimeUnit.MILLISECONDS.sleep(500);
				
				TimeUnit.SECONDS.sleep(1);
				
			}
			click(deleteProductAge);
			click(confirmDeleteBtn);
		}
		
		//Add Distribution Channel
		if(i==0)
		{
			List<com.aia.coast.testcase.DistributionChannel> productDCs=policyProduct.getDistributionChannel();
			for(com.aia.coast.testcase.DistributionChannel dc : productDCs)
			{
			click(addDisChannel);
			TimeUnit.SECONDS.sleep(1);
			
			clearType(disChanlFeeType,dc.getFeeType());
			clearType(productCode,dc.getProducerCode());
			
			performTab();
			
			switchBack();
			TimeUnit.SECONDS.sleep(1);
			
			while(!elementNotDisplay(loadingImg))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			
			TimeUnit.SECONDS.sleep(1);
			
			while(!elementNotDisplay(loadingImg))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			
			switchToFrame(PolicyProductFrame);
			TimeUnit.SECONDS.sleep(1);
			typeQuickJS(feeShareContainer,dc.getFeeShare());
			//click(feeShare);
			performTab();
			
			typeQuickJS(caseCountShareContainer,dc.getCaseCountShare());
			//click(caseCountShare);
			
			
			/*
			click(producerName);
			TimeUnit.MILLISECONDS.sleep(500);
			click(feeShareCell);
			
			TimeUnit.SECONDS.sleep(5);
			
			click(feeShare);
			TimeUnit.SECONDS.sleep(5);
			clearSType(feeShare,"100");
			
			click(caseCountShare);
			clearSType(caseCountShare,"100");
			
			TimeUnit.SECONDS.sleep(1);
			*/
			
			while(!elementNotDisplay(loadingImg))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			TimeUnit.SECONDS.sleep(1);
			while(!elementNotDisplay(loadingImg))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			
			performEnter();
			TimeUnit.SECONDS.sleep(1);
			}
		}
		
		switchBack();
		while(!elementNotDisplay(loadingText))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}

		switchToFrame(PolicyProductFrame);
		click(save);
	}
	
	public void addDirectPolicyProduct1() throws Exception
	{
		clearType(product,"20120- VOL LIFE");
		performTab();
		TimeUnit.SECONDS.sleep(2);
		
		clearType(bizSource,"D - Direct");
		
		clearType(billType,"I - Individual Billing headrate");
		
		clearType(billPlan,"T90 - IDV/THB/Age Rate");
		
		//scrollToElement(waitingPeriodYear);
		//click(waitingPeriodYear);
		//clearType(waitingPeriodYear,"0");
		moveToElement(yearArrowDown);
		TimeUnit.SECONDS.sleep(1);
		click(yearArrowDown);
		
		click(monthArrowDown);
		
		click(dayArrowDown);
		
		//clearType(waitingPeriodMonth,"0");
		//clearType(waitingPeriodDay,"0");
		
		click(nelAmount);
		clearSType(nelAmount,"1000");
		TimeUnit.MILLISECONDS.sleep(500);
	
		
		click(nelAge);
		TimeUnit.MILLISECONDS.sleep(500);
		clearSType(nelAge,"59");
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(nmlAmount);
		clearSType(nmlAmount,"1000");
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(nmlAge);
		TimeUnit.MILLISECONDS.sleep(500);
		clearSType(nmlAge,"59");
		TimeUnit.MILLISECONDS.sleep(500);
		clearType(ageReference,"I - Member anniversary date");
		TimeUnit.MILLISECONDS.sleep(500);
		
		
		/*
		click(producerName);
		TimeUnit.MILLISECONDS.sleep(500);
		click(feeShareCell);
		
		TimeUnit.SECONDS.sleep(5);
		
		click(feeShare);
		TimeUnit.SECONDS.sleep(5);
		clearSType(feeShare,"100");
		
		click(caseCountShare);
		clearSType(caseCountShare,"100");
		
		TimeUnit.SECONDS.sleep(1);
		*/
		
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		while(!elementNotDisplay(loadingText))
		{
			TimeUnit.SECONDS.sleep(1);
		}

		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.MILLISECONDS.sleep(1000);
		while(!elementNotDisplay(loadingText))
		{
			TimeUnit.SECONDS.sleep(1);
		}while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		click(save);
	}
	
	
	public void addPolicyProduct2() throws Exception
	{
		clearType(product,"31110- HI");
		performTab();
		TimeUnit.SECONDS.sleep(2);
		
		clearType(bizSource,"S - Bank (WSM)");
		
		clearType(commissionCode,"PF250 - FLAT 25%");
		
		clearType(billType,"I - Individual Billing headrate");
		
		clearType(billPlan,"T90 - IDV/THB/Age Rate");
		performEnter();
	
		TimeUnit.SECONDS.sleep(3);
		//scrollToElement(waitingPeriodYear);
		//click(waitingPeriodYear);
		//clearType(waitingPeriodYear,"0");
		
		/*click(yearArrowDown);
		
		click(monthArrowDown);
		
		click(dayArrowDown);
		*/
		//clearType(waitingPeriodMonth,"0");
		//clearType(waitingPeriodDay,"0");
		pageDown();
		
		//perfromTimesTab(13);
		
		TimeUnit.MILLISECONDS.sleep(500);
		click(ageReference);
		clearType(ageReference,"I - Member anniversary date");
		TimeUnit.MILLISECONDS.sleep(500);
		
		//Add Distribution Channel
		/*
		click(addDisChannel);
		TimeUnit.SECONDS.sleep(1);
		clearType(disChanlFeeType,"C - Commission");
		clearType(productCode,"0000506070");
		
		performTab();
		
		TimeUnit.SECONDS.sleep(5);
		
		rawInput("100");
		
		performTab();
		rawInput("100");
		/*
		click(producerName);
		TimeUnit.MILLISECONDS.sleep(500);
		click(feeShareCell);
		
		TimeUnit.SECONDS.sleep(5);
		
		click(feeShare);
		TimeUnit.SECONDS.sleep(5);
		clearSType(feeShare,"100");
		
		click(caseCountShare);
		clearSType(caseCountShare,"100");
		
		TimeUnit.SECONDS.sleep(1);
		
		TimeUnit.SECONDS.sleep(1);
		performEnter();
		TimeUnit.SECONDS.sleep(1);
		*/
		click(save);
	}
	
	public void addDirectPolicyProduct2() throws Exception
	{
		clearType(product,"31110- HI");
		performTab();
		TimeUnit.SECONDS.sleep(2);
		
		clearType(bizSource,"D - Direct");
		
		clearType(billType,"I - Individual Billing headrate");
		
		clearType(billPlan,"T90 - IDV/THB/Age Rate");
		performEnter();
	
		TimeUnit.SECONDS.sleep(3);
		//scrollToElement(waitingPeriodYear);
		//click(waitingPeriodYear);
		//clearType(waitingPeriodYear,"0");
		
		/*click(yearArrowDown);
		
		click(monthArrowDown);
		
		click(dayArrowDown);
		*/
		//clearType(waitingPeriodMonth,"0");
		//clearType(waitingPeriodDay,"0");
		pageDown();
		
		//perfromTimesTab(13);
		
		TimeUnit.MILLISECONDS.sleep(500);
		click(ageReference);
		clearType(ageReference,"I - Member anniversary date");
		TimeUnit.MILLISECONDS.sleep(500);
		
		//Add Distribution Channel
		/*
		click(addDisChannel);
		TimeUnit.SECONDS.sleep(1);
		clearType(disChanlFeeType,"C - Commission");
		clearType(productCode,"0000506070");
		
		performTab();
		
		TimeUnit.SECONDS.sleep(5);
		
		rawInput("100");
		
		performTab();
		rawInput("100");
		/*
		click(producerName);
		TimeUnit.MILLISECONDS.sleep(500);
		click(feeShareCell);
		
		TimeUnit.SECONDS.sleep(5);
		
		click(feeShare);
		TimeUnit.SECONDS.sleep(5);
		clearSType(feeShare,"100");
		
		click(caseCountShare);
		clearSType(caseCountShare,"100");
		
		TimeUnit.SECONDS.sleep(1);
		
		TimeUnit.SECONDS.sleep(1);
		performEnter();
		TimeUnit.SECONDS.sleep(1);
		*/
		click(save);
	}
	
	
	Locator save=new Locator("save");
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
