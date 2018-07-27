package com.aia.coast.pageobject;

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

public class ProductPlanPage1 extends BasePage{

	protected ProductPlanPage1(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	WebDriver driver;
	Locator goToSection=new Locator("goToSection");
	Locator policyPart=new Locator("policyPart");
	
	Locator edit1=new Locator("edit1",5);
	Locator edit2=new Locator("edit2",5);
	Locator edit3=new Locator("edit3",5);
	Locator edit4=new Locator("edit4",5);
	Locator edit5=new Locator("edit5",5);
	Locator edit6=new Locator("edit6",5);
	
	
	Locator prEdit1=new Locator("prEdit1");
	Locator prEdit2=new Locator("prEdit2");
	Locator prEdit3=new Locator("prEdit3");
	
	Locator check1=new Locator("check1",5);
	Locator check2=new Locator("check2",5);
	Locator check3=new Locator("check3",5);
	Locator check4=new Locator("check4",5);
	Locator check5=new Locator("check5",5);
	Locator check6=new Locator("check6",5);
	
	Locator iFrame=new Locator("iFrame",5);
	
	Locator benefitPlanScheme=new Locator("benefitPlanScheme");
	Locator addPremiumRate=new Locator("addPremiumRate",5);
	Locator product_premium_rate_coverageCode_input=new Locator("product_premium_rate_coverageCode_input",5);
	Locator product_premium_rate_effectiveDate=new Locator("product_premium_rate_effectiveDate",5);
	Locator modalPremiumRate=new Locator("modalPremiumRate");
	Locator btnPopupSave=new Locator("btnPopupSave",5);
	
	Locator Benefit=new Locator("Benefit",5);
	Locator addBenefit=new Locator("addBenefit",5);
	
	Locator product_benefit_benefitId_input=new Locator("product_benefit_benefitId_input",5);
	Locator product_benefit_effectiveDate=new Locator("product_benefit_effectiveDate",5);
	Locator product_benefit_saCalculateType_input=new Locator("product_benefit_saCalculateType_input",5);
	Locator product_benefit_membershipType_input=new Locator("product_benefit_membershipType_input",5);
	
	Locator product_plan_reinsuranceSchemeId_input=new Locator("product_plan_reinsuranceSchemeId_input",5);
	
	Locator btnSubmit=new Locator("btnSubmit",5);
	

	// add by Kris
	Locator product_benefit_status_input=new Locator("product_benefit_status_input",5);

	
	public void editIndividualPlan() throws Exception
	{
		Client client=ConvertPropertyToPojo.convertToPojo(Client.class);
		TimeUnit.SECONDS.sleep(1);
		
		click(edit1);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		clearType(benefitPlanScheme,"Plan 001");
		
		pageDown();
		TimeUnit.SECONDS.sleep(1);
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"CHD");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"3000");
		performEnter();
		
		TimeUnit.SECONDS.sleep(1);
		click(prEdit2);
		performTab();
		performTab();
		rawInput("2780");
		performEnter();
		
		TimeUnit.SECONDS.sleep(1);
		click(prEdit3);
		performTab();
		performTab();
		rawInput("3000");
		performEnter();
		
		
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		
		//Edit second
		click(edit2);
		switchToFrame(iFrame);
		clearType(benefitPlanScheme,"Plan 002");
		TimeUnit.SECONDS.sleep(2);
	
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		pageDown();
		TimeUnit.SECONDS.sleep(1);
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"CHD");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"3000");
		performEnter();
		
		TimeUnit.SECONDS.sleep(1);
		click(prEdit2);
		performTab();
		performTab();
		rawInput("2780");
		performEnter();
		
		TimeUnit.SECONDS.sleep(1);
		click(prEdit3);
		performTab();
		performTab();
		rawInput("3000");
		performEnter();
		
		
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		//others
		//Edit third
		click(edit3);
		switchToFrame(iFrame);
		clearType(benefitPlanScheme,"Plan 003");
		TimeUnit.SECONDS.sleep(2);
			
		performTab();
		pageDown();
		TimeUnit.SECONDS.sleep(1);
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"CHD");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"3000");
		performEnter();
				
		TimeUnit.SECONDS.sleep(1);
		click(prEdit2);
		performTab();
		performTab();
		rawInput("2780");
		performEnter();
				
		TimeUnit.SECONDS.sleep(1);
		click(prEdit3);
		performTab();
		performTab();
		rawInput("3000");
		performEnter();
				
				
		click(btnPopupSave);
		switchBack();
				
		TimeUnit.SECONDS.sleep(2);
		//edit forth
		click(edit4);
		switchToFrame(iFrame);
		clearType(benefitPlanScheme,"Plan 001");
		TimeUnit.SECONDS.sleep(2);
			
		performTab();
		pageDown();
		TimeUnit.SECONDS.sleep(1);
		click(prEdit1);
		performTab();
		performTab();
		rawInput("1.25");
		performEnter();
		
		TimeUnit.SECONDS.sleep(1);
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"SPU");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"1.35");
		performEnter();
		TimeUnit.SECONDS.sleep(1);
		
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"CHD");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"1.34");
		performEnter();
		TimeUnit.SECONDS.sleep(1);
		click(btnPopupSave);
		
		switchBack();
		
		//edit fifth
		TimeUnit.SECONDS.sleep(1);
		click(edit5);
		switchToFrame(iFrame);
		clearType(benefitPlanScheme,"Plan 002");
		TimeUnit.SECONDS.sleep(2);
			
		pageDown();
		TimeUnit.SECONDS.sleep(1);
		click(prEdit1);
		performTab();
		performTab();
		rawInput("1.25");
		performEnter();
		TimeUnit.SECONDS.sleep(1);

		TimeUnit.SECONDS.sleep(1);
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"SPU");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"1.35");
		performEnter();
		TimeUnit.SECONDS.sleep(1);
		
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"CHD");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"1.34");
		performEnter();
		TimeUnit.SECONDS.sleep(1);
		click(btnPopupSave);
		switchBack();
		
		//Edit sixth
		TimeUnit.SECONDS.sleep(1);
		click(edit6);
		switchToFrame(iFrame);
		clearType(benefitPlanScheme,"Plan 003");
		TimeUnit.SECONDS.sleep(2);
			
		pageDown();
		TimeUnit.SECONDS.sleep(1);
		click(prEdit1);
		performTab();
		performTab();
		rawInput("1.25");
		performEnter();
		TimeUnit.SECONDS.sleep(1);
		
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"SPU");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"1.35");
		performEnter();
		TimeUnit.SECONDS.sleep(1);
		
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"CHD");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"1.34");
		performEnter();
		TimeUnit.SECONDS.sleep(1);
		click(btnPopupSave);
		switchBack();
		
		
		
		
		click(prEdit2);
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"2780");
		performEnter();
				
		TimeUnit.SECONDS.sleep(1);
		click(prEdit3);
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"3000");
		performEnter();
				
				
		click(btnPopupSave);
		switchBack();
				
		TimeUnit.SECONDS.sleep(2);
		
		
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"SPU");
		clearType(product_premium_rate_effectiveDate,"01-11-2017");
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(edit3);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(product_plan_reinsuranceSchemeId_input);
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"SPU");
		clearType(product_premium_rate_effectiveDate,"01-11-2017");
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(edit4);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(product_plan_reinsuranceSchemeId_input);
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"SPU");
		clearType(product_premium_rate_effectiveDate,"01-11-2017");
		click(btnPopupSave);
		switchBack();
		
		
		// edit by Kris 
		
		TimeUnit.SECONDS.sleep(2);
		click(check1);
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(addBenefit);
		TimeUnit.SECONDS.sleep(1);
		clearType(product_benefit_benefitId_input,"I01 - HOSPITAL INCOME");
		clearType(product_benefit_effectiveDate,"01-11-2017");
		click(product_benefit_status_input);
		fillin1();
		
		click(btnPopupSave);
		switchBack();
		
		/*TimeUnit.SECONDS.sleep(2);
		click(check1);
		click(check2);
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(addBenefit);
		TimeUnit.SECONDS.sleep(1);
		clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
		clearType(product_benefit_effectiveDate,"01-11-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin2();
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(check2);
		click(check3);
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(addBenefit);
		TimeUnit.SECONDS.sleep(1);
		clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
		clearType(product_benefit_effectiveDate,"01-11-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin3();
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(check3);
		click(check4);
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(addBenefit);
		TimeUnit.SECONDS.sleep(1);
		clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
		clearType(product_benefit_effectiveDate,"01-11-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin4();
		click(btnPopupSave);
		switchBack();*/
		
		TimeUnit.SECONDS.sleep(2);
		click(btnSubmit);
	}

	public void editProductPlan() throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		
		click(edit1);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(product_plan_reinsuranceSchemeId_input);
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"SPU");
		clearType(product_premium_rate_effectiveDate,"01-11-2017");
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(edit2);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(product_plan_reinsuranceSchemeId_input);
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"SPU");
		clearType(product_premium_rate_effectiveDate,"01-11-2017");
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(edit3);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(product_plan_reinsuranceSchemeId_input);
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"SPU");
		clearType(product_premium_rate_effectiveDate,"01-11-2017");
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(edit4);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(product_plan_reinsuranceSchemeId_input);
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		performTab();
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"SPU");
		clearType(product_premium_rate_effectiveDate,"01-11-2017");
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(check1);
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(addBenefit);
		TimeUnit.SECONDS.sleep(1);
		clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
		clearType(product_benefit_effectiveDate,"01-11-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin1();
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(check1);
		click(check2);
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(addBenefit);
		TimeUnit.SECONDS.sleep(1);
		clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
		clearType(product_benefit_effectiveDate,"01-11-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin2();
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(check2);
		click(check3);
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(addBenefit);
		TimeUnit.SECONDS.sleep(1);
		clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
		clearType(product_benefit_effectiveDate,"01-11-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin3();
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(check3);
		click(check4);
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(addBenefit);
		TimeUnit.SECONDS.sleep(1);
		clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
		clearType(product_benefit_effectiveDate,"01-11-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin4();
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(btnSubmit);
	}
	
	public void fillin1() throws Exception {
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("0");
		
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("200000");
		
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("0");
		
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("200000");
	}
	
	public void fillin2() throws Exception {
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("0");
		
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("200000");
		
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("0");
		
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("200000");
	}
	
	public void fillin3() throws Exception {
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("0");
		
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("100000");
		
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("0");
		
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("100000");
	}
	
	public void fillin4() throws Exception {
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("0");
		
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("100000");
		
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("0");
		
		performTab();
		TimeUnit.SECONDS.sleep(2);
		rawInput("100000");
	}
}
