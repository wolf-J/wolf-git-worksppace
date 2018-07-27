package com.aia.coast.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;

public class IndividualBilling extends BasePage{

	
	public IndividualBilling(WebDriver driver) throws Exception {
		super(driver);
		scrollToElement(paySchedule);
	}
	
	Locator paySchedule=new Locator("paySchedule");
	Locator payByCheckFirstXMonth=new Locator("payByCheckFirstXMonth");
	Locator waivePreniumOnMonth=new Locator("waivePreniumOnMonth");
	Locator autoRenew=new Locator("autoRenew");
	Locator renewBillDayMonth=new Locator("renewBillDayMonth");
	Locator floatRate=new Locator("floatRate");
	
	public void fillIndividualBilling(com.aia.coast.testcase.IndividualBilling indivBilling) throws Exception
	{
		clearType(paySchedule, indivBilling.getDefaultPaySchedule());
		
		click(payByCheckFirstXMonth);
		clearSType(payByCheckFirstXMonth,indivBilling.getPayByChequeInFirstMonth());
		
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(waivePreniumOnMonth);
		clearSType(waivePreniumOnMonth,indivBilling.getWaivePremiumOnMonth());
		
		if(Boolean.valueOf(indivBilling.isAutoRenew()))
		{
			click(autoRenew);
		}
		
		TimeUnit.MILLISECONDS.sleep(500);
		
		clearType(renewBillDayMonth,indivBilling.getRenewBillingDayMonth());
		if(Boolean.valueOf(indivBilling.isFloatRate()))
		{
			click(floatRate);
		}
	}
	
}
