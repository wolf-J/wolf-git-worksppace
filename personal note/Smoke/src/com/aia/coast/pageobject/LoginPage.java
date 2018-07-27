package com.aia.coast.pageobject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.aia.coast.entity.Client;
import com.aia.coast.entity.Policy;
import com.aia.coast.entity.assignTask;
import com.aia.coast.entity.individualPolicy;
import com.aia.coast.util.ConvertPropertyToPojo;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.DriverFactory;
import com.aia.ui.utils.Locator;

public class LoginPage extends BasePage{

	WebDriver driver;
	
	
	public LoginPage(WebDriver driver) throws Exception {
		super(driver);
		
		// TODO Auto-generated constructor stub
		driver.get("http://10.65.5.44:9085/COAST_FE_SIT/homepage/login");
		
		//Pre-Sit
		//driver.get("http://10.65.5.44:9082/COAST_FE_PRESIT/homepage/login");
		
		//UAT
		//driver.get("https://thcoast-uat.aia.biz/COAST_FE/homepage/login");
	}

	public LoginPage(WebDriver driver, String url) throws Exception
	{
		super(driver);
		
		driver.get(url);
	}
	
	public void inputUser(String userId) throws Exception
	{
		type(user,userId);
	}
	
	public void login() throws Exception
	{
		click(login);
	}
	
	Locator user=new Locator("user",30);
	Locator login=new Locator("login",5);
	
	
	
	
	
	public static void loginOut() throws Exception
	{
		WebDriver driver=DriverFactory.getChromeDriver();
		
		LoginPage log=new LoginPage(driver);
		log.inputUser("ASNPH9K");
		log.login();
		
		HomePage home=new HomePage(driver);
	
		
		//home.logout();
		
		//log.inputUser("Jason");
	}
	

	
	
	
	
	
	
	public static void iPosWorkflowSincePMMasterQC(String policyNo) throws Exception
	{
		WebDriver driver=DriverFactory.getChromeDriver();
		iPosSubmitPage ipos=new iPosSubmitPage(driver);
		HomePage home=new HomePage(driver);
		PMPage pmPage=new PMPage(driver);
		LoginPage log=new LoginPage(driver);


		
		System.out.println("The Policy: " +policyNo+" would perform Master QC or MM.");
		
		List<Map<String,String>> memberMMJob=home.getPolicyInfos(policyNo, false, 2);
		home.assignMemberTaskTo("asnph9k", policyNo, memberMMJob, false);
			
	
		log.inputUser("asnph9k");
		log.login();
				
		home.filterpolicy(policyNo);
		home.openFirstTask();
				
		log.pageDown();
				
		MemberMaintenancePage mm=new MemberMaintenancePage(driver);
		mm.submitMMAndSendBill();
				
				
		boolean mmSuccess = home.submitSuccess();
		System.out.println("Finish Member Maintainence, check flow into MM UW or bill now.");
				
		if(mmSuccess)
		{	
			System.out.println("Finish Member Maintainence, check flow into MM UW or bill now.");
					
			Map<Boolean,assignTask> memberQCJob=home.checkMemberQC(policyNo, false);
			if(memberQCJob!=null)
			{
				home.assignTaskTo("ASNPH9K",policyNo);
				home.logout();
				log.inputUser("ASNPH9K");
				log.login();
						
				MemberQCPage memberQC=new MemberQCPage(driver);
				memberQC.memberQC();
						
				if(!home.submitSuccess())
					throw new Exception("MM QC Submit Exception, please take a look at screen.");
				System.out.println("Finish Member QC.");
			}
					
			//Perform MM UW
			MemberUW memberUW=new MemberUW(driver);
			memberUW.assignJobForUW(policyNo, "ASNPH9K", false, 7);
			home.logout();
			log.inputUser("ASNPH9K");
			log.login();
					
			boolean UWLetterSuccess=false;
					
			for(int i= 0;i<6;i++)
			{
				home.filterpolicy(policyNo);
				home.openFirstTask();
					
				memberUW.finishLetterUW();
						
				pmPage.submit();
				UWLetterSuccess=home.submitSuccess();
			}
					
			if(UWLetterSuccess)
			{
				home.refresh();
				System.out.println("Finish UW Letter, move to MemberUW.");
				memberUW.assignJobForUW(policyNo, "ASNPH9K", false, 7);
				for(int i= 0;i<6;i++)
				{
					home.filterpolicy(policyNo);
					home.openFirstTask();
							
					memberUW.finishMemberUW();
					pmPage.submit();
							
				}
			}
		}
			
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//Package
		
		//GLVPackagePolicy();

		
		//Individual
		//IndividualPolicy();
		
		//ipos
		
		
		
		//iPosWorkflowSincePM("0000100550");
		
		//iPosWorkflowSincePMMasterQC("0000100550");
		/*
		//Fill Package Scheme
		pmPage.fillPolicyScheme();
		
		//Update Policy Product
		pmPage.editPolicyProduct();
		*/
		
		//loginOut();
		
	}

}
