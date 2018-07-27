package com.aia.coast.pageobject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aia.coast.entity.Client;
import com.aia.coast.entity.Policy;
import com.aia.coast.entity.individualPolicy;
import com.aia.coast.util.ConvertPropertyToPojo;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.DriverFactory;
import com.aia.ui.utils.Locator;

public class MedicalCardPage extends BasePage{

	WebDriver driver;
	Locator subOfficeList=new Locator("subOfficeList");
	Locator medCardSendTo=new Locator("medCardSendTo");
	
	Locator planSettingList=new Locator("planSettingList");
	Locator cardVersion=new Locator("cardVersion");
	
	Locator cardVerDropDown=new Locator("cardVerDropDown");
	Locator carVerList=new Locator("carVerList");
	
	Locator benefSumVerDropDown=new Locator("benefSumVerDropDown");
	Locator benefSumVerList=new Locator("benefSumVerList");
	
	Locator benefitSumVersion=new Locator("benefitSumVersion");
	Locator applyAll=new Locator("applyAll");
	
	Locator policyNo=new Locator("policyNo");
	
	Locator save=new Locator("save");
	
	public MedicalCardPage(WebDriver driver) throws Exception {
		super(driver);
	}
	
	public void fillMedicalCard() throws Exception
	{
		pageDown();
		List<WebElement> subOffList=findElements(subOfficeList);
		int  subOffListLen=subOffList.size();
		log.info("The SubOffice List size: "+subOffListLen);
		for(int i=0;i<subOffListLen;i++)
		{
			subOffList=findElements(subOfficeList);
			subOffList.get(i).click();
			clearType(medCardSendTo,"M - Member");
			subOffList=findElements(subOfficeList);
			subOffList.get(i).click();
		}
		
		List<WebElement> planList=findElements(planSettingList);
		int planListLen=planList.size();
		if(planListLen==0)
			throw new RuntimeException("Medical Card List is empty.");
		WebElement planEdit=planList.get(0);
		planEdit.click();
		
		selectValue(cardVerDropDown, carVerList, "Jason");
		
		click(policyNo);
		
		this.performArrowRight(8);
		
		selectValue(benefSumVerDropDown, benefSumVerList, "Jason");
		
		
		scrollToElement(applyAll);
		
		click(applyAll);
		planList=findElements(planSettingList);
		planEdit=planList.get(0);
		planEdit.click();
		TimeUnit.MILLISECONDS.sleep(400);
		click(save);
	}
	
}
