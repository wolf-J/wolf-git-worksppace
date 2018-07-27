package com.aia.coast.pageobject;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import util.Checker;

import com.aia.cs.ipos.entity.iposSubmissionTool;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.DriverFactory;
import com.aia.ui.utils.Locator;

public class ContractPage extends BasePage{

	Locator clientCode=new Locator("clientCode");
	Locator companyName=new Locator("companyName");
	Locator policyNumber=new Locator("policyNumber");
	Locator generatorDate=new Locator("generatorDate");
	Locator contractType=new Locator("contractType");
	
	Locator contractInfoChecks=new Locator("contractInfoChecks");
	Locator frameElement=new Locator("frameElement");
	
	Locator detailInThai=new Locator("detailInThai");
	Locator detailInEng=new Locator("detailInEng");
	Locator close=new Locator("close");
	Locator Frame=new Locator("Frame");
	Locator pdfPlugin=new Locator("pdfPlugin");
	
	public void extractContractPDF() throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		pageDown();
		TimeUnit.SECONDS.sleep(1);
		
		List<WebElement> contractChecks=findElements(contractInfoChecks);
		int checkLen=contractChecks.size();
		System.out.println("There are "+checkLen+" contacts to review.");
		
		for(int i=0;i<checkLen;i++)
		{
			contractChecks=findElements(contractInfoChecks);
			WebElement checkBtn=contractChecks.get(i);
			checkBtn.click();
			if(i<3)
				click(detailInThai);
			else
				click(detailInEng);
			TimeUnit.SECONDS.sleep(5);
			
			if(i==0)
			{
				final Screen screen = new Screen();  
				ExecutorService service = Executors.newSingleThreadExecutor();  
				service.execute(new Runnable() {  
				    @Override  
				    public void run() {  
				    	 screen.type("sonora" + Key.TAB + "coast123" + Key.ENTER);
				    }
				     /*   while (true){  
				        	//App.focus("Authentication Required").isRunning();
				            if (App.focus("Authentication Required").hasWindow()) {  
				                screen.type("sonora" + Key.TAB + "coast123" + Key.ENTER);  
				                return;  
				            }else
				            {
				            	System.out.println("Not foud the authentication.");
				            }
				            }
				    }  */
				});  
				service.shutdown(); 
			}
		
			
			switchToFrame(Frame);

			
			String url=getAttributeVal(pdfPlugin,"src");
			//String url=getAttributeVal(Frame,"src");
			System.out.println("PDF URL: "+url);
			
			ESC();
			TimeUnit.MILLISECONDS.sleep(500);
			switchBack();
			
			pageDown();
			contractChecks=findElements(contractInfoChecks);
			if(contractChecks==null)
			{
				System.out.println("Oops why...");
			}
			checkBtn=contractChecks.get(i);
			checkBtn.click();
		}
		
		
		
	}
	
	public ContractPage(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String ...args)
	{
		System.out.println("Start3....");
			// TODO Auto-generated method stub
			//Initialize
			WebDriver driver = DriverFactory.getChromeDriver();
			LoginPage login=null;
			HomePage home=null;
			PMPage pmPage=null;
			String url="http://thcoast-uat.aia.biz/COAST_FE/homepage/login";
			try{
				login=new LoginPage(driver,url);
				
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
			}catch(Exception e){}
			
			
			System.out.println("Finish Initialization......");

			try
			{
				
				login.inputUser("bgrr018");
				login.login();
				
			
				home.filterpolicy("0000033011");
				home.openFirstTask();
				//TimeUnit.SECONDS.sleep(22);
			
						
				pmPage.finishLoadingPolicyMaintenance();
			
				String currentUrl=driver.getCurrentUrl();
				System.out.println("Current Url: "+currentUrl);
				
				String authen="sonora:coast123@";
				String authenUrl=authen+currentUrl;
				
				
				ContractPage contractPage=new ContractPage(driver);
				contractPage.extractContractPDF();
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}

}
