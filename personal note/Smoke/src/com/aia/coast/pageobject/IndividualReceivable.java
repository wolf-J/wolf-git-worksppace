package com.aia.coast.pageobject;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.aia.coast.entity.assignTask;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;

public class IndividualReceivable extends BasePage{

	public IndividualReceivable(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	Locator loadingImg=new Locator("loadingImg");
	Locator loadingAniX=new Locator("loadingAniX");
	Locator Home=new Locator("Home");
	
	//Loading
	Locator loading=new Locator("loading");
	
	//Wait loading of tasks
	Locator finishLoading=new Locator("finishLoading");
	
	Locator fileUpload=new Locator("fileUpload");
	Locator selectFiles=new Locator("selectFiles");
	Locator upload=new Locator("upload");
	
	Locator batchId=new Locator("batchId");

	Locator manual=new Locator("manual");
	
	Locator search= new Locator("search");
	  
	Locator billChecked1= new Locator("billChecked1");
	
	Locator billChecked2= new Locator("billChecked2");
	
	Locator receivableChecked1= new Locator("receivableChecked1");

	Locator receivableChecked2= new Locator("receivableChecked2");
	
	Locator calculate = new Locator("calculate");

	Locator refund = new Locator("refund");
	
	Locator pending = new Locator("pending");
	
	Locator edit = new Locator("edit");
		
	Locator refundPolicyNo = new Locator("refundPolicyNo");
	
	Locator refundTypeClick = new Locator("refundTypeClick");
	
	Locator refundType = new Locator("refundType");

	Locator refundMethodClick = new Locator("refundMethodClick");
	
	Locator refundMethod = new Locator("refundMethod");
	
	Locator iReceivableReason = new Locator("iReceivableReason");

	Locator submitBtn=new Locator("submit");
	
	public void filesUpload(String policyNo) throws Exception
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
		
		click(fileUpload);
		
		TimeUnit.SECONDS.sleep(5);
		pageDown();
		
		String uploadFile="upload File Information.xlsx";
		String filePath=System.getProperty("user.dir")+File.separator+uploadFile;
		System.out.print("filePath:"+filePath);
		
		fileUpload(selectFiles,filePath);
		
		TimeUnit.SECONDS.sleep(5);
		click(upload);
		
		TimeUnit.SECONDS.sleep(5);
		clearType(batchId,"12");
		
		//click(submitBtn);		
	}
	public void refund(String policyNo) throws Exception
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
		
		click(search);
		TimeUnit.SECONDS.sleep(1);
		
		click(billChecked1);
		//click(billChecked2);
		TimeUnit.SECONDS.sleep(1);
		
		click(receivableChecked1);
		
		click(calculate);
		TimeUnit.SECONDS.sleep(1);
		click(refund);
		click(edit);
		TimeUnit.SECONDS.sleep(1);
		
		click(refundPolicyNo);
		this.performArrowRight(25);
		performTab();
		performTab();
		click(refundTypeClick);
		clearSType(refundType,"CFPDF - REFUND ADDITIONAL PR");
		TimeUnit.SECONDS.sleep(1);
		
		click(refundMethodClick);
		clearSType(refundMethod,"A - AutoPay");
		TimeUnit.SECONDS.sleep(1);
		
		click(edit);
		pageDown();
		
		clearType(iReceivableReason,"I Like");
		
		//click(submitBtn);		
	}
	public void pending(String policyNo) throws Exception{
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
		
		click(search);
		TimeUnit.SECONDS.sleep(1);
		
		click(billChecked1);
		
		TimeUnit.SECONDS.sleep(1);
		
		click(receivableChecked1);
		
		click(calculate);
		TimeUnit.SECONDS.sleep(1);
		click(pending);
		
	}
	public void submit() throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		click(submitBtn);
	}
	

}
