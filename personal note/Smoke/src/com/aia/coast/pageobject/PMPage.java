package com.aia.coast.pageobject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import util.Checker;

import com.aia.coast.entity.Client;
import com.aia.coast.entity.Policy;
import com.aia.coast.entity.Scheme;
import com.aia.coast.testcase.PMChange;
import com.aia.coast.util.ConvertPropertyToPojo;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.DriverFactory;
import com.aia.ui.utils.Locator;

import static util.Checker.isNull;

public class PMPage extends BasePage {
	
	
	public PMPage(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void goToPolicySection() throws Exception
	{
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(policyPart);
		click(goToSection);
	}
	
	
	public void goToPolicyYear() throws Exception
	{
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(policyYearPart);
		click(goToSection);
	}
	
	public void goToPackageSchemeSection() throws Exception
	{
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(packageSchemePart);
		click(goToSection);
	}
	
	public void goToIndividualBillSection() throws Exception
	{
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(individualBill);
		click(goToSection);
	}
	
	public void goToPolicyProductSection() throws Exception
	{
		TimeUnit.SECONDS.sleep(5);
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(policyProductPart);
		click(goToSection);
	}
	
	public void goToPolicyProductPlanSection() throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(policyProductPlanPart);
		click(goToSection);
	}
	
	
	public void goToDistributionChannelSection() throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(distributionChannel);
		click(goToSection);
		TimeUnit.MILLISECONDS.sleep(500);
	}
	
	public String getPolicyNo() throws Exception
	{
		String policyNumber=getAttributeVal(policyNo,"value");
		return policyNumber;
	}
	
	Locator goToSection=new Locator("goToSection");
	Locator policyPart=new Locator("policyPart");
	Locator packageSchemePart=new Locator("packageSchemePart");
	Locator individualBill=new Locator("individualBill");
	Locator policyYearPart=new Locator("policyYearPart");
	
	Locator addPackageScheme=new Locator("addPackageScheme");
	Locator addDistributionChannel=new Locator("addDistributionChannel");
	Locator editFirstPolicyProduct=new Locator("firstProduct");
	Locator editsecondPolicyProduct=new Locator("secondProduct");
	
	Locator policyProductPart=new Locator("policyProductPart");
	
	Locator PolicyProductFrame=new Locator("PolicyProductFrame");
	
	Locator policyProductEditList=new Locator("policyProductEditList");
	Locator addPolicyProduct=new Locator("addPolicyProduct");
	
	Locator policyProductPlanPart=new Locator("policyProductPlanPart");
	
	Locator distributionChannel=new Locator("distributionChannel");
	
	Locator policyNo=new Locator("policyNo");
	Locator submit=new Locator("submit");
	Locator submitSuccess=new Locator("submitSuccess");
	Locator saveSuccess=new Locator("saveSuccess");
	Locator subOfficeProducer=new Locator("subOfficeProducer");
	Locator loading=new Locator("loading");
	Locator orgCode=new Locator("orgCode");
	
	Locator validationError=new Locator("validationError");
	
	Locator otherInfo=new Locator("otherInfo");
	Locator medicalCard=new Locator("medicalCard");
	
	public void finishLoadingPolicyMaintenance() throws Exception
	{
		int tryTimes=3;
		
		int startTime=0;
		
		String policyNumber=null;
		
		do{
			startTime++;
			if(startTime!=1)
				this.refresh();
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
			
			TimeUnit.SECONDS.sleep(2);
			while(!elementNotDisplay(loading))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			policyNumber=getAttributeVal(orgCode, "value");
			System.out.println("Policy Number: "+policyNumber);
		}while( startTime<tryTimes && policyNumber.length()<9);
	}
	
	
	public boolean validationError() throws Exception
	{
		return ElementPresent(validationError);
	}
	
	public void fillClient(com.aia.coast.testcase.Client client) throws Exception
	{
		if(client==null)
		{
			return;
		}
		//Fill Client Info
		ClientInfo clientInfo=new ClientInfo(driver);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		clientInfo.fillClient(client);
	}
	
	public void fillDistributionChannel(List<com.aia.coast.testcase.DistributionChannel> distributionChannel) throws Exception
	{
		click(subOfficeProducer);
		goToDistributionChannelSection();
		
		for(com.aia.coast.testcase.DistributionChannel dc : distributionChannel)
		{
			while(!elementNotDisplay(loading))
			{
				TimeUnit.SECONDS.sleep(1);
			}
		
			click(addDistributionChannel);

			DistributionChannel channel=new DistributionChannel(driver);
			channel.fillDistributionChannel(dc);
		
		}
	}
	
	public void performPMChange(PMChange changePM) throws Exception
	{
		finishLoadingPolicyMaintenance();
		
		switch(changePM.getChangeSection())
		{
		case "PackageScheme":
			goToPackageSchemeSection();
			PackageScheme ps=new PackageScheme(driver);
			TimeUnit.SECONDS.sleep(1);
			while(!elementNotDisplay(loading))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			click(addPackageScheme);
			TimeUnit.SECONDS.sleep(1);
			ps.packageSchemeChange();
			break;
		default:
			throw new RuntimeException("Invalid PM Change Section: "+changePM.getChangeSection());
		}
	}
	
	public void fillPolicyInfo(com.aia.coast.testcase.Policy policy) throws Exception
	{
		//Go to the Policy Info Area
		goToPolicySection();
		
		PolicyInfo policyInfo=new PolicyInfo(driver);
		
		if(policy!=null)
		{
			policyInfo.fillPolicy(policy);
		}else
		{
			System.out.println("Policy is null.");
		}
	}
	
	public void fillPolicyScheme(List<com.aia.coast.testcase.PackageScheme> packageSchemes) throws Exception
	{
		goToPackageSchemeSection();
		//Add Package Scheme
		
		PackageScheme ps=new PackageScheme(driver);
		for(int i=0;i<packageSchemes.size();i++)
		{
			while(!elementNotDisplay(loading))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			
			if(i==0)
			{
				TimeUnit.SECONDS.sleep(1);
			}
			else
			{
				TimeUnit.SECONDS.sleep(3);
			}
			if(Checker.isNotNull(packageSchemes.get(i).getPackageProduct()))
			{
				click(addPackageScheme);
				TimeUnit.SECONDS.sleep(1);
				ps.fillPackageScheme(packageSchemes.get(i));
			}
		}
	}
	
	public void editIndividualProductPlan(List<com.aia.coast.testcase.ProductPlan> productPlans) throws Exception
	{
		ProductPlanPage productPlanPage=new ProductPlanPage(driver);
		goToPolicyProductPlanSection();
		TimeUnit.SECONDS.sleep(1);
		//Update
		productPlanPage.editIndividualPlan(productPlans);
		
		productPlanPage.editIndividualBenefit(productPlans);
	}
	
	public void editSMProductPlan(com.aia.coast.testcase.ProductPlan productPlan) throws Exception
	{
		ProductPlanPage productPlanPage=new ProductPlanPage(driver);
		goToPolicyProductPlanSection();
		TimeUnit.SECONDS.sleep(1);
		//Update
		productPlanPage.editSMPlan(productPlan);
		
	}
	
	
	public void editUATIndividualProductPlan() throws Exception
	{
		ProductPlanPage productPlanPage=new ProductPlanPage(driver);
		goToPolicyProductPlanSection();
		TimeUnit.SECONDS.sleep(1);
		productPlanPage.editUATIndividualPlan();
		
		productPlanPage.editUATIndividualBenefit();
	}
	
	public void editPolicyProduct(List<com.aia.coast.testcase.PolicyProduct> policyProducts) throws Exception
	{
		goToPolicyProductSection();
		
		TimeUnit.SECONDS.sleep(1);
		
		List<WebElement> productEdits=findElements(policyProductEditList);
		int productsCount=productEdits.size();
		System.out.println("There are "+productsCount+" need to be edited.");
		
		for(int i=0;i<productsCount;i++)
		{
			productEdits=findElements(policyProductEditList);
			WebElement productEditor=productEdits.get(i);
			productEditor.click();
			TimeUnit.MILLISECONDS.sleep(100);
			switchToFrame(PolicyProductFrame);

			PolicyProduct pp=new PolicyProduct(driver);
			
			pp.updatePolicyProduct(policyProducts.get(i),i);
			
			
			TimeUnit.SECONDS.sleep(2);
			switchBack();
		}
		
	}
	
	
	public void editSMPolicyProduct(List<com.aia.coast.testcase.PolicyProduct> policyProducts) throws Exception
	{
		goToPolicyProductSection();
		
		TimeUnit.SECONDS.sleep(1);
		
		List<WebElement> productEdits=findElements(policyProductEditList);
		int productsCount=productEdits.size();
		System.out.println("There are "+productsCount+" need to be edited.");
		
		for(int i=0;i<productsCount;i++)
		{
			productEdits=findElements(policyProductEditList);
			WebElement productEditor=productEdits.get(i);
			productEditor.click();
			TimeUnit.MILLISECONDS.sleep(100);
			switchToFrame(PolicyProductFrame);

			PolicyProduct pp=new PolicyProduct(driver);
			
			pp.updateSMPolicyProduct(policyProducts.get(i));
			TimeUnit.SECONDS.sleep(2);
			switchBack();
		}
		
	}
	
	public void editDirectPolicyProduct() throws Exception
	{
		goToPolicyProductSection();
		
		TimeUnit.SECONDS.sleep(1);
		
		click(editFirstPolicyProduct);
		//Fill first Policy Product
		switchToFrame(PolicyProductFrame);
		
		PolicyProduct pp=new PolicyProduct(driver);
		pp.updateDirectPolicyProduct();
		
		TimeUnit.SECONDS.sleep(2);
		switchBack();
		
		click(editsecondPolicyProduct);
		switchToFrame(PolicyProductFrame);
		pp.updateSecondDirectPolicyProduct();
		switchBack();
		TimeUnit.SECONDS.sleep(1);
	}
	
	public void fillIndividualBill(com.aia.coast.testcase.IndividualBilling indivBilling) throws Exception
	{
		goToIndividualBillSection();
		IndividualBilling iBilling=new IndividualBilling(driver);
		iBilling.fillIndividualBilling(indivBilling);
	}
	
	public void addPolicyProduct(List<com.aia.coast.testcase.PolicyProduct> policyProducts) throws Exception
	{
		goToPolicyProductSection();
		
		TimeUnit.SECONDS.sleep(1);
		
		for(int i=0;i<policyProducts.size();i++)
		{
			click(addPolicyProduct);
			TimeUnit.SECONDS.sleep(1);
			//Fill first Policy Product
			switchToFrame(PolicyProductFrame);
			
			PolicyProduct pp=new PolicyProduct(driver);
			pp.addPolicyProduct1(policyProducts.get(i),i);
			
			TimeUnit.SECONDS.sleep(2);
			switchBack();
		}
	}
	
	public void addDirectPolicyProduct() throws Exception
	{
		goToPolicyProductSection();
		
		TimeUnit.SECONDS.sleep(1);
		
		click(addPolicyProduct);
		TimeUnit.SECONDS.sleep(1);
		//Fill first Policy Product
		switchToFrame(PolicyProductFrame);
		
		PolicyProduct pp=new PolicyProduct(driver);
		pp.addDirectPolicyProduct1();
		
		TimeUnit.SECONDS.sleep(2);
		switchBack();
		
		click(addPolicyProduct);
		switchToFrame(PolicyProductFrame);
		pp.addDirectPolicyProduct2();
		
		switchBack();
		
	}
	
	public void editProductPlan() throws Exception
	{
		ProductPlanPage productPlanPage=new ProductPlanPage(driver);
		TimeUnit.SECONDS.sleep(1);
        productPlanPage.editProductPlan();
	}
	
	public void editMedicalCard() throws Exception
	{
		click(otherInfo);
		click(medicalCard);
		
		MedicalCardPage mcp=new MedicalCardPage(driver);
		switchToFrame(this.PolicyProductFrame);
		mcp.fillMedicalCard();
		switchBack();
	}
	
	Locator Continue=new Locator("Continue");
	public void submit() throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		
		click(submit);
		
		TimeUnit.SECONDS.sleep(1);
		if(!elementNotDisplay(Continue))
		{
			click(Continue);
		}
	}
	
	public void submitAndAccept() throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		click(submit);
		TimeUnit.SECONDS.sleep(1);
		Alert alert=driver.switchTo().alert();
		if(alert!=null)
		{
			System.out.println("Accept Alert...");
			alert.accept();
		}
	}

	public void acceptAlert() throws Exception
	{
		TimeUnit.SECONDS.sleep(2);
		this.alertConfirm();
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
