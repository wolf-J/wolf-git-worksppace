package com.aia.coast.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import util.Checker;

import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;

public class DistributionChannel extends BasePage{

	Locator subOfficeArrow=new Locator("subOfficeArrow");
	Locator subOfficeItem=new Locator("subOfficeItem");
	Locator feeType=new Locator("feeType");
	Locator producerCode=new Locator("producerCode");
	Locator producerName=new Locator("producerName");
	Locator feeShare=new Locator("feeShare");
	Locator caseCountShare=new Locator("caseCountShare");
	Locator Update=new Locator("Update");
	Locator loadingImg=new Locator("loadingImg");
	
	
	protected DistributionChannel(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public void fillDistributionChannel(com.aia.coast.testcase.DistributionChannel distributionChannel) throws Exception
	{
		click(subOfficeArrow);
		click(subOfficeItem);
		clearType(feeType,distributionChannel.getFeeType());
		clearType(producerCode,distributionChannel.getProducerCode());
		performEnter();
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		
		click(feeShare);
		clearSType(feeShare,distributionChannel.getFeeShare());
		
		TimeUnit.SECONDS.sleep(5);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		click(caseCountShare);
		clearSType(caseCountShare,distributionChannel.getCaseCountShare());
		
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		//Check the producer name is not empty, if it is empty, would re-try to retrieve the product name
		String producerNameTxt=getAttributeVal(producerName,"value");
	/*
		while(Checker.isNull(producerNameTxt))
		{
			click(producerCode);
			click(producerName);
			
			while(!elementNotDisplay(loadingImg))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			
			while(!elementNotDisplay(loadingImg))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			
			producerNameTxt=getAttributeVal(producerName,"value");
		}
		System.out.println("ProducerName: "+producerNameTxt);*/
		click(Update);
		TimeUnit.SECONDS.sleep(1);
	}

}
