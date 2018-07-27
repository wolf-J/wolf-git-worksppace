package com.aia.coast.pageobject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import util.CheckPolicyInCoast;
import util.JsonUtil;

import com.aia.coast.entity.assignTask;
import com.aia.coast.util.ConvertPropertyToPojo;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CreateJobPage extends BasePage{

	public CreateJobPage(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static assignTask assign;
	
	Locator loadingImg=new Locator("loadingImg");
	Locator loadingAniX=new Locator("loadingAniX");
	Locator Home=new Locator("Home");
	
	//Loading
	Locator loading=new Locator("loading");
	
	//Wait loading of tasks
	Locator finishLoading=new Locator("finishLoading");
	
	Locator policyNumber=new Locator("policyNumber");
	Locator reqType=new Locator("reqType");
	Locator selectFile=new Locator("selectFile");
	Locator upload=new Locator("upload");
	Locator docClass=new Locator("docClass");
	Locator docName=new Locator("docName");
	Locator docEditor=new Locator("docEditor");
	Locator submitBtn=new Locator("submitBtn");
	
	public void waitLoadingOfTask() throws IOException
	{
		findElement(driver,finishLoading);
	}
	
	public boolean submitSuccess() throws Exception
	{
		boolean submitSuccess=isElementPresent(Home);
		return submitSuccess;
	}
	
	public void createPMChangeJob(String policyNo) throws Exception
	{
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(2);
		while(!elementNotDisplay(loadingAniX))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		clearType(reqType,"PMC - Policy Master Change");
		
		TimeUnit.MILLISECONDS.sleep(500);
		
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		clearType(policyNumber,policyNo);
		
		pageDown();
		
		String uploadFile="PMCFile.tif";
		String filePath=System.getProperty("user.dir")+File.separator+uploadFile;
		fileUpload(selectFile,filePath);
		
		TimeUnit.SECONDS.sleep(5);
		click(upload);
		this.clearType(this.docClass, "Request for change");
		this.clearType(this.docName, "Request for change Policy(Policy)");
		click(docEditor);
		
		click(submitBtn);
	}
	
	public void createReceivableJob(String policyNo) throws Exception {
		// TODO Auto-generated method stub
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(2);
		while(!elementNotDisplay(loadingAniX))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		
		clearType(reqType,"RCVI - Individual Receivable");
		
		clearType(policyNumber,policyNo);
		
		pageDown();

		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(2);
		}
		click(submitBtn);
	} 
	
}
