package com.aia.coast.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import util.ExcelUtil;

import com.aia.ui.utils.DriverFactory;

public class Main {
	
	public void performPMMasterQC(String policyNum) throws Exception
	{
		WebDriver driver=DriverFactory.getChromeDriver();
		
		LoginPage log=new LoginPage(driver);
		
		HomePage home=new HomePage(driver);
		
		home.assignTaskTo("ASNPH9Q",policyNum);
		
		System.out.println("Assign task to user.");
		
		
		log.inputUser("ASNPH9Q");
		
		log.login();
		home.filterpolicy(policyNum);
		
		PolicyMstQCPage policyMstQCPage =new PolicyMstQCPage(driver);
		policyMstQCPage.policyMasterQC();
	}
	
	
	public static void performMMUpload(String policyNum) throws Exception
	{
		WebDriver driver=DriverFactory.getChromeDriver();
		
		LoginPage log=new LoginPage(driver);
		HomePage home=new HomePage(driver);
		
		log.inputUser("ASNPG10");
		//log.inputUser("ASNPGE6");
		//UAT
		//log.inputUser("BGRR361");
		
		 
		log.login();
		
		
		home.filterpolicy(policyNum);
		MemberMaintenancePage mmp=new MemberMaintenancePage(driver);
		
		//mmp.updateIndividualMemUpload(policyNum);
		mmp.updateGLVMemUpload(policyNum);
		mmp.MemberUpload(policyNum);
		mmp.submitMMAndSendBill();
	}


	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String t="002";
		System.out.println("00"+(Integer.valueOf(t)+1));
		
		//ExcelUtil.updateGLVMemUploadFile("TM00000463",memUploadFile);
		//ExcelUtil.updateMemUploadFile("TM00000446", memUploadFile);
	}

}
