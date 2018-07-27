package com.aia.coast.pageobject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;

public class RCVPage extends BasePage{

	Locator loading = new Locator("loading");
	Locator premiumRcvType=new Locator("premiumRcvType");
	Locator payRcvQueryType=new Locator("payRcvQueryType");
	Locator billInfoCheckList=new Locator("billInfoCheckList");
	Locator addRcvInfo=new Locator("addRcvInfo");
	Locator RcvEditList=new Locator("RcvEditList");
	Locator pendingInfoPart=new Locator("pendingInfoPart");
	Locator goToSection=new Locator("goToSection");
	Locator pendingInfoEdits=new Locator("pendingInfoEdits");
	Locator pendingInfoStatus=new Locator("pendingInfoStatus");
	Locator pendingInfoStatusArrow=new Locator("pendingInfoStatusArrow");
	Locator unPendingRemark=new Locator("unPendingRemark");
	Locator updateUnPendingInfo=new Locator("updateUnPendingInfo");
	Locator UnPending=new Locator("UnPending");
	
	public RCVPage(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void finishLoading() throws Exception
	{
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
	}
	public void goToPendingInfoSection() throws Exception
	{
		TimeUnit.SECONDS.sleep(3);
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(pendingInfoPart);
		click(goToSection);
	}
	
	
	public void finishPendingRCV() throws Exception
	{
		finishLoading();
		TimeUnit.SECONDS.sleep(1);
		finishLoading();
		goToPendingInfoSection();
		
		List<WebElement> pendingInfoLists=findElements(pendingInfoEdits);
		for(int i=0;i<pendingInfoLists.size();i++)
		{
			pendingInfoLists=findElements(pendingInfoEdits);
			TimeUnit.SECONDS.sleep(1);
			finishLoading();
			pendingInfoLists.get(i).click();
			TimeUnit.SECONDS.sleep(1);
			pageDown();
			clearType(pendingInfoStatus,"Close");
			TimeUnit.MILLISECONDS.sleep(400);
			click(pendingInfoStatusArrow);
			clearType(unPendingRemark,"Auto Testing Team");
			
			click(updateUnPendingInfo);
		}
		click(UnPending);
		
	}
	
	//Group RCV
	public void finishRcv() throws Exception
	{
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(premiumRcvType);
		pageDown();
		TimeUnit.SECONDS.sleep(1);
		click(payRcvQueryType);
		TimeUnit.SECONDS.sleep(1);
		pageDown();
		TimeUnit.SECONDS.sleep(1);
		
		List<WebElement> billList=findElements(billInfoCheckList);
		for(WebElement check : billList)
		{
			TimeUnit.MILLISECONDS.sleep(150);
			check.click();
		}
		TimeUnit.SECONDS.sleep(1);
		pageDown();
		TimeUnit.MILLISECONDS.sleep(150);
		click(addRcvInfo);
		
		List<WebElement> rcvEdits=findElements(RcvEditList);
		for(WebElement edit : rcvEdits)
		{
			TimeUnit.MILLISECONDS.sleep(150);
			edit.click();
		}
		
	}
}
