package com.aia.coast.pageobject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.aia.coast.entity.Client;
import com.aia.coast.entity.Policy;
import com.aia.coast.entity.individualPolicy;
import com.aia.coast.util.ConvertPropertyToPojo;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.DriverFactory;
import com.aia.ui.utils.Locator;

public class PolicyMstQCPage extends BasePage{

	WebDriver driver;
	Locator firstJob=new Locator("firstJob",50);
	Locator openTask=new Locator("openTask",5);
	Locator userinfo=new Locator("userinfo");
	Locator logout=new Locator("logout");
	//Wait loading of tasks
	
	Locator loading=new Locator("loading");

	
	Locator btnSubmit=new Locator("btnSubmit");
	
	public PolicyMstQCPage(WebDriver driver) throws Exception {
		super(driver);
	}
	
	public void waitLoadingOfTask() throws Exception
	{
		TimeUnit.SECONDS.sleep(15);
		// Kris Commence
		//Please add your waiting function here, which can work on your ENV
	}
	
	public void waitLoadingOfPage() throws Exception
	{
		TimeUnit.SECONDS.sleep(10);
		// Kris Commence
		//Please add your waiting function here, which can work on your ENV
	}
	
	

	public void policyMasterQC() throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.MILLISECONDS.sleep(200);
		}
		
		
		TimeUnit.SECONDS.sleep(2);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.MILLISECONDS.sleep(200);
		}
		
		click(btnSubmit);
		
	
		//Pelease remove
		/*TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.MILLISECONDS.sleep(200);
		}
		click(btnSubmit);
		*/
	}
}
