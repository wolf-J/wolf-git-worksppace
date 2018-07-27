package com.aia.coast.pageobject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class iPosSubmitPage extends BasePage{

	WebDriver driver;
	Locator appForm=new Locator("appForm");
	Locator jobInProgress=new Locator("jobInProgress");
	Locator goToSection = new Locator("goToSection");
	Locator policy = new Locator("policy");
	Locator policy_product = new Locator("policy_product");
	Locator product_plan = new Locator("product_plan");
	Locator iFrame=new Locator("iFrame",5);
	
	Locator policyType=new Locator("policyType",3);
	Locator policy_servicingOfficerUserId  =new Locator("policy_servicingOfficerUserId");
	Locator policy_FirstYearSaleRep = new Locator("policy_FirstYearSaleRep");
	Locator policy_RenewalYearSale = new Locator("policy_RenewalYearSale");
	Locator policy_serviceAeUserId = new Locator("policy_serviceAeUserId");
	
	Locator reInsuranceTreatyCode=new Locator("reInsuranceTreatyCode");
	Locator policy_product_section = new Locator("policy_product_section");
	Locator product_plan_section = new Locator("product_plan_section");
	Locator policy_product_table = new Locator("policy_product_table");
	Locator product_plan_table = new Locator("product_plan_table");
	
	Locator commisCode = new Locator("commisCode",5);
	Locator billPlan = new Locator("billPlan",5);
	Locator valCode=new Locator("valCode");
	
	//page down
	Locator nelAmount=new Locator("nelAmount");
	Locator nelAge=new Locator("nelAge");
	/*
		click(nelAmount);
		clearSType(nelAmount,"2000000");
		TimeUnit.MILLISECONDS.sleep(500);
		
		click(nelAge);
		clearSType(nelAge,"58");
		TimeUnit.MILLISECONDS.sleep(500);
	 */
	
	Locator btnSubmit = new Locator("btnSubmit",5);
	Locator loading = new Locator("loading");
	Locator loadingbg=new Locator("loadingbg");
	Locator save=new Locator("save");
	
	public iPosSubmitPage(WebDriver driver) throws Exception {
		super(driver);
		this.driver=driver;
		
	}
	
	public void goToPolicySection() throws Exception
	{
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		while(!elementNotDisplay(loadingbg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(policy);
		click(goToSection);
	}
	
	public void goToPolicyProductSection() throws Exception
	{
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(policy_product);
		click(goToSection);
	}
	
	public void goToProductPlanSection() throws Exception
	{
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(product_plan);
		click(goToSection);
	}
	
	public void submitFromiPos(String policyNo) throws Exception
	{	
	
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
				
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
	
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(appForm);
		
		goToPolicySection();
		
		TimeUnit.SECONDS.sleep(1);
		
		click(policyType);
		clearType(policyType,"GL - Policy with life product only");
		click(policyType);
		
		clearType(policy_servicingOfficerUserId,"ASNPG0J - Ding, Miles-XY");
		clearType(policy_FirstYearSaleRep,"0000506070 - PARXXXXX N");
		clearType(policy_RenewalYearSale,"0000506070 - PARXXXXX N");
		clearType(policy_serviceAeUserId,"ASNPG0J - Ding, Miles-XY");
		
		goToPolicyProductSection();
		TimeUnit.SECONDS.sleep(1);
		
		
		/*goToProductPlanSection();
		TimeUnit.SECONDS.sleep(1);
		editProductPlan();*/
	
		//click(btnSubmit);
	}
	
	public void editPolicyProduct(List<com.aia.coast.testcase.PolicyProduct> products) throws Exception
	{
		click(policy_product_section);
		TimeUnit.SECONDS.sleep(1);
		performArrowUp();
		performArrowUp();
		performEnter();
		TimeUnit.SECONDS.sleep(1);
		
		List<WebElement> listPolicy_Product = findElements(policy_product_table);
		
		System.out.println("Total at :"+listPolicy_Product.size()+" products.");
		
		for (int i=0;i<listPolicy_Product.size();i++) {
			com.aia.coast.testcase.PolicyProduct product=products.get(i);
			TimeUnit.SECONDS.sleep(1);
			driver.findElement(By.xpath("//div[@section-name='Policy product']/div/table/tbody/tr["+ (i +1) +"]/td[2]/a")).click();
			
			switchToFrame(iFrame);
			if(i==3)
			{
				clearType(commisCode,product.getCommissionCode());
			}
			else{
				clearType(commisCode,product.getCommissionCode());
			}
			
			clearType(billPlan,product.getBillPlan());
			
			String reinsuranceTreatyCodeTxt= getAttributeVal(reInsuranceTreatyCode,"value");
			System.out.println("ReInsuranceTreatyCode: "+reinsuranceTreatyCodeTxt);
			
			/*
			if(reinsuranceTreatyCodeTxt.length()<=5)
			{
				clear(reInsuranceTreatyCode);
			}*/
			
			/*
			if(i<3)
			{
				clearType(valCode,"VLF01");
			}
			else
			{
				clearType(valCode,"VMD01");
			}
			*/
			//System.out.println("สคุมทรัพย์สินต่างประเทศ");
			TimeUnit.MILLISECONDS.sleep(300);
			pageDown();
			TimeUnit.MILLISECONDS.sleep(300);
			
			click(nelAmount);
			clearSType(nelAmount,product.getNelAmount());
			TimeUnit.MILLISECONDS.sleep(500);
			
			click(nelAge);
			clearSType(nelAge,product.getNelAge());
			TimeUnit.MILLISECONDS.sleep(500);
			
		
			
			TimeUnit.SECONDS.sleep(1);
			click(save);
			switchBack();
		}
		
	}
	
	/*public void editProductPlan() throws Exception
	{
		click(product_plan_section);
		TimeUnit.SECONDS.sleep(1);
		performArrowUp();
		performArrowUp();
		performEnter();
		TimeUnit.SECONDS.sleep(1);
		
		List<WebElement> listPolicy_Product = findElements(product_plan_table);
		
		for (int i=0;i<=listPolicy_Product.size();i++) {
			driver.findElement(By.xpath("//div[@section-name='Product Plan']/div/table/tbody/tr["+ (i +1) +"]/td[2]/a")).click();
			
			switchToFrame(iFrame);
			//other fields need input here
			
			
			switchBack();
		}
		
	}*/
	
	public static void main(String ...args) throws Exception
	{
		iPosSubmitPage ipos=new iPosSubmitPage(DriverFactory.getChromeDriver());
		ipos.submitFromiPos("0000100539");
	}
}
