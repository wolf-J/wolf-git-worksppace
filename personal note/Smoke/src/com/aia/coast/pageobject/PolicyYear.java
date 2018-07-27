package com.aia.coast.pageobject;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aia.coast.entity.Client;
import com.aia.coast.entity.Policy;
import com.aia.coast.entity.individualPolicy;
import com.aia.coast.entity.uatIndividualPolicy;
import com.aia.coast.util.ConvertPropertyToPojo;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;

import static util.Checker.isNotNull;

public class PolicyYear extends BasePage{

	public PolicyYear(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	Locator update=new Locator("update");
	Locator toDate=new Locator("toDate");
	Locator editPolicyYear=new Locator("editPolicyYear");
	Locator loadingImg=new Locator("loadingImg");
	Locator loading=new Locator("loading");
	
	public void updatePolicyYear(String targetDate) throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}

		click(editPolicyYear);
		clearType(toDate,targetDate);
		
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(update);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
