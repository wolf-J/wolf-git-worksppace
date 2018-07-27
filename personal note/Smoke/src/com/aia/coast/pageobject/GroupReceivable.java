package com.aia.coast.pageobject;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import util.Checker;

import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;

public class GroupReceivable extends BasePage{

	public GroupReceivable(WebDriver driver) throws Exception {
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
	
	Locator search=new Locator("search");
	Locator billCheck1=new Locator("billCheck1");
	Locator receivableCheck1=new Locator("receivableCheck1");
	Locator calculate=new Locator("calculate");
	
	Locator refund=new Locator("refund");
	Locator transfer=new Locator("transfer");
	Locator adjust=new Locator("adjust");
	Locator pending=new Locator("pending");

	
	Locator refundPolicyNo=new Locator("refundPolicyNo");

	Locator refundEdit=new Locator("refundEdit");
	Locator refoundType=new Locator("refoundType");
	Locator refoundTypeValue=new Locator("refoundTypeValue");
	Locator refoundMethod=new Locator("refoundMethod");	
	Locator refoundMethodValue=new Locator("refoundMethodValue");

	
	Locator transferEdit=new Locator("transferEdit");
	Locator transferType=new Locator("transferType");
	Locator transferTypeValue=new Locator("transferTypeValue");
	Locator transferPoliyNo=new Locator("transferPoliyNo");	
	Locator transferSubCode=new Locator("transferSubCode");
	
	Locator adjustEdit=new Locator("adjustEdit");
	Locator adjustType=new Locator("adjustType");
	Locator adjustTypeValue=new Locator("adjustTypeValue");
	Locator adjustProduct=new Locator("adjustProduct");
	Locator adjustProductValue=new Locator("adjustProductValue");
	
	Locator reason=new Locator("reason");
	Locator submitBtn=new Locator("submit");
	

	
	public void refund() throws Exception {
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
		TimeUnit.SECONDS.sleep(10);
		
		click(search);
		TimeUnit.SECONDS.sleep(1);
		click(billCheck1);
		TimeUnit.SECONDS.sleep(1);
		click(receivableCheck1);
		TimeUnit.SECONDS.sleep(1);
		click(calculate);
		TimeUnit.SECONDS.sleep(1);
		click(refund);
		
		pageDown();

		
		click(refundEdit);
		
		click(refundPolicyNo);	
		TimeUnit.MILLISECONDS.sleep(300);
		this.performArrowRight(25);
		TimeUnit.MILLISECONDS.sleep(300);

		performTab();
		performTab();
		performTab();
		click(refoundType);
		clearSType(refoundTypeValue,"CFPDF - REFUND ADDITIONAL PR");
		performTab();
		TimeUnit.SECONDS.sleep(1);
		
		//typeQuickJS(transferPoliyNo,policyNum);	
		TimeUnit.SECONDS.sleep(1);
		
		click(refoundMethod);
		clearSType(refoundMethodValue,"A - AutoPay");
		TimeUnit.SECONDS.sleep(2);
		click(refundEdit);

		pageDown();
		clearType(reason,"I Like");
		
	}
	public void transfer(String policyNum) throws Exception {
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
		click(billCheck1);
		TimeUnit.SECONDS.sleep(4);
		click(receivableCheck1);
		TimeUnit.SECONDS.sleep(1);
		click(calculate);
		TimeUnit.SECONDS.sleep(1);
		click(transfer);
		
		pageDown();
		
		click(transferEdit);
		TimeUnit.MILLISECONDS.sleep(300);
		performArrowRight(6);
		TimeUnit.MILLISECONDS.sleep(300);

		click(transferType);
		clearSType(transferTypeValue,"EB Group - 251001");
		performTab();
		performTab();
		TimeUnit.SECONDS.sleep(1);
		clearSType(transferPoliyNo,policyNum);	
		performTab();
		TimeUnit.SECONDS.sleep(1);
		clearSType(transferSubCode,"100");
		TimeUnit.SECONDS.sleep(1);
		//performTab();
		click(transferEdit);
		
		
	}

	public void adjust() throws Exception {
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
		
		pageDown();
		
		click(billCheck1);
		TimeUnit.SECONDS.sleep(1);
		click(receivableCheck1);
		TimeUnit.SECONDS.sleep(1);
		click(calculate);
		pageDown();
		
		TimeUnit.SECONDS.sleep(1);
		click(adjust);
		
		click(adjustEdit);
		TimeUnit.SECONDS.sleep(2);
		

		click(adjustType);
		clearSType(adjustTypeValue,"Withholding tax renewyear - 220902");
		TimeUnit.SECONDS.sleep(1);
		
		performTab();
		
		click(adjustProduct);
		clearSType(adjustProductValue,"0000506070-นางXXXXXีย์ หนูเทพย์");
		TimeUnit.SECONDS.sleep(1);
		click(adjustEdit);
		pageDown();
		
		TimeUnit.SECONDS.sleep(1);
		clearType(reason,"I Like");
		
		
	}
	public void pending() throws Exception {
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
		click(billCheck1);
		TimeUnit.SECONDS.sleep(1);
		click(receivableCheck1);
		TimeUnit.SECONDS.sleep(1);
		click(calculate);
		
		click(pending);
		
		
	}
	public void submit() throws Exception {
		TimeUnit.SECONDS.sleep(1);
		click(submitBtn);
		
	}

	

}
