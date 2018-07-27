package com.aia.coast.pageobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;







import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import util.Checker;
import util.ListUtil;
import util.StrUtil;

import com.aia.coast.entity.Client;
import com.aia.coast.entity.Policy;
import com.aia.coast.entity.individualPolicy;
import com.aia.coast.util.ConvertPropertyToPojo;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.DriverFactory;
import com.aia.ui.utils.Locator;

public class ProductPlanPage extends BasePage{

	WebDriver driver;
	Locator goToSection=new Locator("goToSection");
	Locator policyPart=new Locator("policyPart");
	
	
	Locator productPlanEditList=new Locator("productPlanEditList");
	Locator productPlanCheckList=new Locator("productPlanCheckList");
	Locator edit1=new Locator("edit1",5);
	Locator edit2=new Locator("edit2",5);
	Locator edit3=new Locator("edit3",5);
	Locator edit4=new Locator("edit4",5);
	Locator edit5=new Locator("edit5",5);
	Locator edit6=new Locator("edit6",5);
	
	Locator premiumRateEditList=new Locator("premiumRateEditList");
	Locator CoverageList=new Locator("CoverageList");
	Locator prEdit1=new Locator("prEdit1");
	Locator prEdit2=new Locator("prEdit2");
	Locator prEdit3=new Locator("prEdit3");
	Locator effectiveDate=new Locator("effectiveDate");
	Locator productPlanShowAll=new Locator("productPlanShowAll");
	Locator check1=new Locator("check1",5);
	Locator check2=new Locator("check2",5);
	Locator check3=new Locator("check3",5);
	Locator check4=new Locator("check4",5);
	Locator check5=new Locator("check5");
	Locator check6=new Locator("check6");
	
	Locator benefitProductCode=new Locator("benefitProductCode");
	
	Locator benefitCode=new Locator("benefitCode");
	Locator benefitEditList=new Locator("benefitEditList");
	Locator benefitEdit1=new Locator("benefitEdit1");
	Locator IbenefitEdit1=new Locator("IbenefitEdit1");
	Locator IbenefitEdit2=new Locator("IbenefitEdit2");
	Locator IbenefitEdit3=new Locator("IbenefitEdit3");
	
	Locator iFrame=new Locator("iFrame",5);
	
	Locator benefitPlanScheme=new Locator("benefitPlanScheme");
	Locator addPremiumRate=new Locator("addPremiumRate",5);
	Locator product_premium_rate_coverageCode_input=new Locator("product_premium_rate_coverageCode_input",5);
	Locator product_premium_rate_effectiveDate=new Locator("product_premium_rate_effectiveDate",5);
	Locator modalPremiumRate=new Locator("modalPremiumRate");
	Locator modalPremiumRateContainer=new Locator("modalPremiumRateContainer");
	Locator btnPopupSave=new Locator("btnPopupSave",5);
	
	Locator Benefit=new Locator("Benefit",5);
	Locator addBenefit=new Locator("addBenefit",5);
	
	Locator product_benefit_benefitId_input=new Locator("product_benefit_benefitId_input",5);
	Locator product_benefit_effectiveDate=new Locator("product_benefit_effectiveDate",5);
	Locator product_benefit_saCalculateType_input=new Locator("product_benefit_saCalculateType_input",5);
	Locator product_benefit_membershipType_input=new Locator("product_benefit_membershipType_input",5);
	
	Locator product_plan_reinsuranceSchemeId_input=new Locator("product_plan_reinsuranceSchemeId_input",5);
	
	Locator premiumRateTable=new Locator("premiumRateTable");
	
	Locator btnSubmit=new Locator("btnSubmit",5);
	
	Locator planBenefitPlanList=new Locator("planBenefitPlanList");
	Locator planBenefitSchemeList=new Locator("planBenefitSchemeList");
	
	Locator planBenefitSummarySection=new Locator("planBenefitSummarySection");
	Locator benefitSummaryChecks=new Locator("benefitSummaryChecks");
	Locator serviceCategoryList=new Locator("serviceCategoryList");
	Locator bebefitSumyDelete=new Locator("bebefitSumyDelete");
	Locator Continue=new Locator("Continue");
	Locator benefitSumryEdits=new Locator("benefitSumryEdits");
	
	Locator MemBenefit=new Locator("MemBenefit");
	Locator SpuBenefit=new Locator("SpuBenefit");
	Locator ChdBenefit=new Locator("ChdBenefit");
	
	//benefit list
	Locator benefitList=new Locator("benefitList");
	Locator memTypeList=new Locator("memTypeList");
	Locator overlay=new Locator("overlay");
	
	Locator mem=new Locator("mem");
	Locator memContainer=new Locator("memContainer");
	
	Locator spu=new Locator("spu");
	Locator spuContainer=new Locator("spuContainer");
	
	Locator chd=new Locator("chd");
	Locator chdContainer=new Locator("chdContainer");
	
	Locator cardBenefitIndicator=new Locator("cardBenefitIndicator");
	Locator applyProRate=new Locator("applyProRate");
	public ProductPlanPage(WebDriver driver) throws Exception {
		super(driver);
	}
	
	public void goToPolicySection() throws Exception
	{
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(policyPart);
		click(goToSection);
	}
	
	public void showAllProductPlan() throws Exception
	{
		click(productPlanShowAll);
		TimeUnit.MILLISECONDS.sleep(300);
		performArrowUp();
		performArrowUp();
		performEnter();
	}
	
	public void editIndividualPlan(List<com.aia.coast.testcase.ProductPlan> productPlans) throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		
		List<WebElement> planEditorList=findElements(productPlanEditList);
		
		int productPlanCount=planEditorList.size();
		int coupleCount=productPlanCount/2;
		System.out.println("There are "+productPlanCount+" plans need to edit.");
		String effDate=null;
		
		for(int i=0;i<planEditorList.size();i++)
		{
			TimeUnit.MILLISECONDS.sleep(500);
			planEditorList=findElements(productPlanEditList);
			planEditorList.get(i).click();
			TimeUnit.MILLISECONDS.sleep(500);
			switchToFrame(iFrame);
			TimeUnit.SECONDS.sleep(2);
			clearType(benefitPlanScheme,"Plan 00"+(i%coupleCount+1));
			
			pageDown();
			TimeUnit.SECONDS.sleep(1);
			effDate=getElement(effectiveDate).getText();
			List<String> coveragesCode=Arrays.asList(productPlans.get(i).getCoverageCode().split(","));
			//productPlans.get(i).getCoverageCode().split(",");
			int coveragesSize=coveragesCode.size();
			System.out.println("There are "+coveragesSize+" need to be edited and added into product plan.");
			
			List<WebElement> premiumRates=findElements(premiumRateEditList);
			int currentPremiumCount=premiumRates.size();
			int addCoverageCount=coveragesSize-currentPremiumCount;
			
			//Current Coverage codes
			List<WebElement> coverageList=findElements(CoverageList);
			List<String> currCoverageList=new ArrayList<>();
			
			for(int ii=0;ii<coverageList.size();ii++)
			{
				currCoverageList.add(coverageList.get(ii).getText());
				System.out.println(coverageList.get(ii).getText()+": Jason");
			}
			
			
			
			//The Coverage need to be added
			List<String> premiumCoverageToBeAdd=null;
			if(coveragesSize!=currentPremiumCount)
			{
				premiumCoverageToBeAdd=ListUtil.diffSet(coveragesCode, currCoverageList);
				premiumCoverageToBeAdd.stream().forEach(item -> System.out.println("Coverage "+item+" are going to be added..."));
			}
			
			for(int jj=0;jj<currentPremiumCount;jj++)
			{
				if(jj==0)
					pageDown();
				String cover=currCoverageList.get(jj);
				int index=coveragesCode.indexOf(cover);
				premiumRates=findElements(premiumRateEditList);
				
				WebElement prEdit=premiumRates.get(jj);
				TimeUnit.SECONDS.sleep(1);
				
				prEdit.click();
				performTab();
				performTab();
				//Place Holder
				//rawInput(productPlans.get(i).getModalPremiumRate().split(",")[index]);
				typeQuickJS(modalPremiumRateContainer,productPlans.get(i).getModalPremiumRate().split(",")[index]);
				
				TimeUnit.SECONDS.sleep(1);
				prEdit.click();
			}
			
			
		
			
			if(addCoverageCount>0)
			{
				
				//Find current item and add others
				
				for(int ij=0;ij<addCoverageCount;ij++)
				{
					click(addPremiumRate);
					String cover=premiumCoverageToBeAdd.get(ij);
					clearType(product_premium_rate_coverageCode_input,cover);
					int index=coveragesCode.indexOf(cover);
					clearType(product_premium_rate_effectiveDate,effDate);
					click(modalPremiumRate);
					//Place holder
					clearSType(modalPremiumRate,productPlans.get(i).getModalPremiumRate().split(",")[index]);
					TimeUnit.SECONDS.sleep(1);
					click(prEdit1);
					
					//premiumCoverageToBeAdd
				}
			}
			click(btnPopupSave);
			switchBack();
		}
		
	}
	
	
	public void editSMPlan(com.aia.coast.testcase.ProductPlan productPlan) throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		
		List<WebElement> planEditorList=findElements(productPlanEditList);
		
		if(planEditorList.size()==10)
		{
			showAllProductPlan();
			planEditorList=findElements(productPlanEditList);
		}
		int planSize=planEditorList.size();
		
		System.out.println("There are total "+planSize+" product plan.");
		
		Locator planBenefitPlanList=new Locator("planBenefitPlanList");
		Locator planBenefitSchemeList=new Locator("planBenefitSchemeList");
		List<WebElement> benefitSchemeList=findElements(planBenefitSchemeList);
		for(int j=0;j<benefitSchemeList.size();j++)
		{
			benefitSchemeList=findElements(planBenefitSchemeList);
			String benefPlanSchemeTxt=benefitSchemeList.get(j).getText();
			System.out.println("Benefit Plan Scheme "+j+" is: "+benefPlanSchemeTxt);
			if(benefPlanSchemeTxt==null || benefPlanSchemeTxt.isEmpty())
			{
				List<WebElement> benefitPlanList=findElements(planBenefitPlanList);
				String planTxt=benefitPlanList.get(j).getText();
				System.out.println("Plan Text: "+planTxt);
				planEditorList=findElements(productPlanEditList);
				WebElement editItem=planEditorList.get(j);
				
				TimeUnit.MILLISECONDS.sleep(800);
				while(!elementNotDisplay(overlay))
				{
					TimeUnit.SECONDS.sleep(1);
				}
				
				editItem.click();
				switchToFrame(iFrame);
				TimeUnit.SECONDS.sleep(1);
				
				clearType(benefitPlanScheme,planTxt);
				pageDown();
				List<WebElement> premiumRates=findElements(premiumRateEditList);
				int preMuimRatesLen=premiumRates.size();
				for(int jj=0;jj<preMuimRatesLen;jj++)
				{
					premiumRates=findElements(premiumRateEditList);
					WebElement preMiumRateEdit=premiumRates.get(jj);
					TimeUnit.MILLISECONDS.sleep(100);
					preMiumRateEdit.click();
					click(modalPremiumRate);
					//Place holder
					TimeUnit.MILLISECONDS.sleep(400);
					if(Checker.isNotNull(productPlan.getModalPremiumRate()))
						clearSType(productPlan.getModalPremiumRate());
					else
						clearSType("3000");
					TimeUnit.SECONDS.sleep(1);
					preMiumRateEdit.click();
				}
				click(btnPopupSave);
				switchBack();
			}
		}
		/*
		for(int i=0;i<planEditorList.size();i++)
		{
			TimeUnit.MILLISECONDS.sleep(500);
			planEditorList=findElements(productPlanEditList);
			planEditorList.get(i).click();
			TimeUnit.MILLISECONDS.sleep(500);
			switchToFrame(iFrame);
			TimeUnit.SECONDS.sleep(2);
			clearType(benefitPlanScheme,"Plan 00"+(i%coupleCount+1));
			
			pageDown();
			TimeUnit.SECONDS.sleep(1);
			effDate=getElement(effectiveDate).getText();
			List<String> coveragesCode=Arrays.asList(productPlans.get(i).getCoverageCode().split(","));
			//productPlans.get(i).getCoverageCode().split(",");
			int coveragesSize=coveragesCode.size();
			System.out.println("There are "+coveragesSize+" need to be edited and added into product plan.");
			
			List<WebElement> premiumRates=findElements(premiumRateEditList);
			int currentPremiumCount=premiumRates.size();
			int addCoverageCount=coveragesSize-currentPremiumCount;
			
			//Current Coverage codes
			List<WebElement> coverageList=findElements(CoverageList);
			List<String> currCoverageList=new ArrayList<>();
			
			for(int ii=0;ii<coverageList.size();ii++)
			{
				currCoverageList.add(coverageList.get(ii).getText());
				System.out.println(coverageList.get(ii).getText()+": Jason");
			}
			
			
			
			//The Coverage need to be added
			List<String> premiumCoverageToBeAdd=null;
			if(coveragesSize!=currentPremiumCount)
			{
				premiumCoverageToBeAdd=ListUtil.diffSet(coveragesCode, currCoverageList);
				premiumCoverageToBeAdd.stream().forEach(item -> System.out.println("Coverage "+item+" are going to be added..."));
			}
			
			for(int jj=0;jj<currentPremiumCount;jj++)
			{
				
				String cover=currCoverageList.get(jj);
				int index=coveragesCode.indexOf(cover);
				premiumRates=findElements(premiumRateEditList);
				
				WebElement prEdit=premiumRates.get(jj);
				TimeUnit.SECONDS.sleep(1);
				prEdit.click();
				performTab();
				performTab();
				//Place Holder
				rawInput(productPlans.get(i).getModalPremiumRate().split(",")[index]);
				TimeUnit.SECONDS.sleep(1);
				prEdit.click();
			}
			
			
		
			
			if(addCoverageCount>0)
			{
				
				//Find current item and add others
				
				for(int ij=0;ij<addCoverageCount;ij++)
				{
					click(addPremiumRate);
					String cover=premiumCoverageToBeAdd.get(ij);
					clearType(product_premium_rate_coverageCode_input,cover);
					int index=coveragesCode.indexOf(cover);
					clearType(product_premium_rate_effectiveDate,effDate);
					click(modalPremiumRate);
					//Place holder
					clearSType(modalPremiumRate,productPlans.get(i).getModalPremiumRate().split(",")[index]);
					TimeUnit.SECONDS.sleep(1);
					click(prEdit1);
					
					//premiumCoverageToBeAdd
				}
			}
			click(btnPopupSave);
			switchBack();
		}*/
		
	}
	
	
	public void editUATIndividualPlan() throws Exception
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
		click(prEdit1);
		
		TimeUnit.SECONDS.sleep(1);
		click(prEdit2);
		performTab();
		performTab();
		rawInput("2780");
		click(prEdit2);
		
		TimeUnit.SECONDS.sleep(1);
		click(prEdit3);
		performTab();
		performTab();
		rawInput("3000");
		click(prEdit3);
		
		
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
		pageDown();
		TimeUnit.SECONDS.sleep(1);
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"CHD");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"3100");
		click(prEdit1);
		
		TimeUnit.SECONDS.sleep(1);
		click(prEdit2);
		performTab();
		performTab();
		rawInput("2880");
		click(prEdit2);
		
		TimeUnit.SECONDS.sleep(1);
		click(prEdit3);
		performTab();
		performTab();
		rawInput("3100");
		click(prEdit3);
		
		
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
		clearSType(modalPremiumRate,"3200");
		click(prEdit1);
				
		TimeUnit.SECONDS.sleep(1);
		click(prEdit2);
		performTab();
		performTab();
		rawInput("2980");
		click(prEdit2);
				
		TimeUnit.SECONDS.sleep(1);
		click(prEdit3);
		performTab();
		performTab();
		rawInput("3200");
		click(prEdit3);
				
				
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
		TimeUnit.MILLISECONDS.sleep(300);
		click(prEdit1);
		
		TimeUnit.SECONDS.sleep(1);
		click(prEdit2);
		performTab();
		performTab();
		rawInput("1.35");
		TimeUnit.MILLISECONDS.sleep(300);
		click(prEdit2);
		TimeUnit.SECONDS.sleep(1);
		
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"CHD");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"1.34");
		click(prEdit1);
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
		rawInput("1.35");
		TimeUnit.MILLISECONDS.sleep(300);
		click(prEdit1);
		TimeUnit.SECONDS.sleep(1);

		TimeUnit.SECONDS.sleep(1);
		click(prEdit2);
		performTab();
		performTab();
		rawInput("1.45");
		TimeUnit.MILLISECONDS.sleep(300);
		click(prEdit2);
		TimeUnit.SECONDS.sleep(1);
		
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"CHD");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"1.44");
		click(prEdit1);
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
		rawInput("1.45");
		TimeUnit.MILLISECONDS.sleep(300);
		click(prEdit1);
		TimeUnit.SECONDS.sleep(1);
		
		
		click(prEdit2);
		performTab();
		performTab();
		rawInput("1.55");
		TimeUnit.MILLISECONDS.sleep(300);
		click(prEdit2);
		TimeUnit.SECONDS.sleep(1);
		
		click(addPremiumRate);
		clearType(product_premium_rate_coverageCode_input,"CHD");
		clearType(product_premium_rate_effectiveDate,client.getInitialEffectiveDate());
		click(modalPremiumRate);
		clearSType(modalPremiumRate,"1.54");
		click(prEdit1);
		TimeUnit.SECONDS.sleep(1);
		click(btnPopupSave);
		switchBack();
	
	}
	
	
	public void editIndividualBenefit(List<com.aia.coast.testcase.ProductPlan> productPlans) throws Exception
	{
		
		List<WebElement> planBenefitList=findElements(productPlanCheckList);
		int benefitSize=planBenefitList.size();
		
		System.out.println("There are "+planBenefitList.size()+" benefits need to edit.");
		String productCode=null;
		
		for(int i=0;i<benefitSize;i++)
		{
			com.aia.coast.testcase.Benefit benef=productPlans.get(i).getBenefit();
			TimeUnit.SECONDS.sleep(1);
			WebElement planCheck=findElements(productPlanCheckList).get(i);
			planCheck.click();
			TimeUnit.MILLISECONDS.sleep(100);
			click(Benefit);
			TimeUnit.MILLISECONDS.sleep(100);
			switchToFrame(iFrame);
			TimeUnit.SECONDS.sleep(3);
			
			productCode=getElement(benefitProductCode).getAttribute("value");
			System.out.println("Product Code is: "+productCode+": Jason");
			String benefitCodeTxt=null;
			//To be updated
			if(productCode.startsWith("3"))
			{
				System.out.println("Medical Insurance.");
				boolean benefitSummaryExist=ElementPresent(planBenefitSummarySection);
				if(benefitSummaryExist)
				{
					//Activate the Page
					click(planBenefitSummarySection);
					
					boolean needToDelete=false;
					System.out.println("There are Benefit Summary would edit this section.");
					pageDown();
					List<WebElement> benefitSumryChecks=findElements(benefitSummaryChecks);
					int benefitLen=benefitSumryChecks.size();
					System.out.println("There are "+benefitLen+" item, would remove or add accordingly");
					
					for(int j=0;j<benefitLen;j++)
					{
						List<WebElement> serviceCategories=findElements(serviceCategoryList);
						WebElement serviceCategory=serviceCategories.get(j);
						String serviceCategoryTxt=serviceCategory.getText();
						if(serviceCategoryTxt.equalsIgnoreCase("A - Amount"))
						{
							needToDelete=true;
							benefitSumryChecks=findElements(benefitSummaryChecks);
							WebElement benefitSumryCheck=benefitSumryChecks.get(j);
							benefitSumryCheck.click();
						}
					}
					
					if(needToDelete)
					{
						click(bebefitSumyDelete);
						TimeUnit.MILLISECONDS.sleep(500);
						click(Continue);
					}
					
					//edit benefit
					List<WebElement> benefitItemEditors=findElements(benefitSumryEdits);
					
					int editLen=benefitItemEditors.size();
					System.out.println("There are "+editLen+" need to edit.");
					
					List<WebElement> serviceCategories=findElements(serviceCategoryList);
					List<String> serviceCategoryTxtList=serviceCategories.stream().map(element -> {return element.getText();}).collect(Collectors.toList());
					
					for(int editSize=0;editSize<editLen;editSize++)
					{
						
						String serviceCategoryTxt=serviceCategoryTxtList.get(editSize);
						System.out.println("Service Category: "+serviceCategoryTxt);
						
						if(serviceCategoryTxt.equalsIgnoreCase("C - Calls/days"))
						{
							benefitItemEditors=findElements(benefitSumryEdits);
							WebElement benefitEditItem=benefitItemEditors.get(editSize);
							benefitEditItem.click();
							perfromTimesTab(5);
							
							typeQuickJS(memContainer,benef.getBenAmount());
							//click(mem);
							performTab();
							if(Checker.isNotNull(benef.getSPU()))
							{
								typeQuickJS(spuContainer,benef.getSPU());
								//click(spu);	
							}
							
							
							performTab();
							TimeUnit.MILLISECONDS.sleep(100);
							
							if(Checker.isNotNull(benef.getCHD()))
							{
								System.out.println("Benef CHD: "+benef.getCHD());
								typeQuickJS(chdContainer,benef.getCHD());
								//click(chd);	
							}
							TimeUnit.MILLISECONDS.sleep(300);
							click(applyProRate);
							System.out.println("Click Apply");
						
							TimeUnit.MILLISECONDS.sleep(300);
							performArrowRight(6);
							TimeUnit.MILLISECONDS.sleep(300);
							click(cardBenefitIndicator);
							 
						}else if(serviceCategoryTxt.equalsIgnoreCase("R - Reinbursement Percentage"))
						{
							benefitItemEditors=findElements(benefitSumryEdits);
							WebElement benefitEditItem=benefitItemEditors.get(editSize);
							benefitEditItem.click();
							perfromTimesTab(4);
							
							typeQuickJS(memContainer,"100");
							//click(mem);
							performTab();
							if(Checker.isNotNull(benef.getSPU()))
							{
								typeQuickJS(spuContainer,"100");
								//click(spu);	
							}
							
							
							performTab();
							TimeUnit.MILLISECONDS.sleep(100);
							
							if(Checker.isNotNull(benef.getCHD()))
							{
								typeQuickJS(chdContainer,"100");
								//click(chd);	
							}
						}
						benefitItemEditors=findElements(benefitSumryEdits);
						WebElement benefitEditItem=benefitItemEditors.get(editSize);
						benefitEditItem.click();
					}
					
				}
				else{
				
					click(benefitEdit1);
					TimeUnit.MILLISECONDS.sleep(300);
					perfromTimesTab(4);
					performDelete(9);
					rawInput(benef.getBenAmount());
					TimeUnit.MILLISECONDS.sleep(300);
					perfromTimesTab(6);
					rawInput(benef.getSPU());
					TimeUnit.MILLISECONDS.sleep(300);
					performTab();
					rawInput(benef.getCHD());
					performEnter();
				}
				
			}else if(productCode.startsWith("2"))
			{
				System.out.println("Life Insurance.");
				List<WebElement> memberTypeList=findElements(memTypeList);
				List<String> memList=new ArrayList<>();
				for(int j=0;j<memberTypeList.size();j++)
				{
					String memT=memberTypeList.get(j).getText();
					System.out.println("Member Type: "+memT);
					memList.add(StrUtil.ManipulateBenefitCode(memT));
				}
				
				
				List<String> benefitMembers=Arrays.asList(benef.getMembershipType().split(",")).stream().map(item -> {return StrUtil.ManipulateBenefitCode(item);}).collect(Collectors.toList());
				int targetBenefitCount=benefitMembers.size();
				System.out.println("There are "+targetBenefitCount+" need to be edited and added into Benefit.");
				
				List<String> addMemberType=ListUtil.diffSet(benefitMembers, memList);
				
				List<WebElement> benefitEdits=findElements(benefitEditList);
				int currentBenefitCount=benefitEdits.size();
				
				if(currentBenefitCount>0)
				{
					WebElement benefCode=getElement(benefitCode);
					benefitCodeTxt=benefCode.getText();
					benefitCodeTxt=StrUtil.ManipulateBenefitCode(benefitCodeTxt);
					System.out.println("Benefit Code is: "+benefitCodeTxt+", Jason");
					
					for(int k=0;k<currentBenefitCount;k++)
					{
						benefitEdits=findElements(benefitEditList);
						WebElement benefitEdit=benefitEdits.get(k);
						benefitEdit.click();
						
						TimeUnit.MILLISECONDS.sleep(300);
						clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
						perfromTimesTab(3);
						rawInput(benef.getFlatAmount());
						TimeUnit.MILLISECONDS.sleep(200);
						performEnter();
						
					}
				}
				if((targetBenefitCount-currentBenefitCount)>0)
				{
					int addCount=targetBenefitCount-currentBenefitCount;
					for(int kk=0;kk<addCount;kk++)
					{
						TimeUnit.SECONDS.sleep(2);
						click(addBenefit);
						TimeUnit.SECONDS.sleep(1);
						clearType(product_benefit_benefitId_input,benefitCodeTxt);
						clearType(product_benefit_effectiveDate,"01-01-2017");
						clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
						clearType(product_benefit_membershipType_input,StrUtil.ManipulateBenefitCode(addMemberType.get(kk)));
						fillin(benef.getMultipleFactory(),benef.getFlatAmount(),"","");
						TimeUnit.SECONDS.sleep(1);
					}
				}
				
				
			}
			click(btnPopupSave);
			switchBack();
			planCheck=findElements(productPlanCheckList).get(i);
			TimeUnit.SECONDS.sleep(1);
			planCheck.click();
			
		}
		System.out.println("Done...");
	}

	
	public void editUATIndividualBenefit() throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		click(check1);
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		
		TimeUnit.SECONDS.sleep(1);
		
		click(benefitEdit1);
		TimeUnit.MILLISECONDS.sleep(300);
		perfromTimesTab(4);
		
		performDelete(9);
		rawInput("600");
		
		TimeUnit.MILLISECONDS.sleep(300);
		perfromTimesTab(6);
		rawInput("600");
		TimeUnit.MILLISECONDS.sleep(300);
		performTab();
		rawInput("600");
		performEnter();
		
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(1);
		click(check1);
		
		//second
		click(check2);
		
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		
		TimeUnit.SECONDS.sleep(1);
		
		click(benefitEdit1);
		TimeUnit.MILLISECONDS.sleep(300);
		perfromTimesTab(4);
		
		performDelete(9);
		rawInput("800");
		
		TimeUnit.MILLISECONDS.sleep(300);
		perfromTimesTab(6);
		rawInput("800");
		TimeUnit.MILLISECONDS.sleep(300);
		performTab();
		rawInput("800");
		performEnter();
		
		click(btnPopupSave);
		switchBack();
		TimeUnit.SECONDS.sleep(1);
		click(check2);
		
		//third
		click(check3);
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		
		click(benefitEdit1);
		TimeUnit.MILLISECONDS.sleep(300);
		perfromTimesTab(4);
		
		performDelete(9);
		rawInput("1000");
		
		TimeUnit.MILLISECONDS.sleep(300);
		perfromTimesTab(6);
		rawInput("1000");
		TimeUnit.MILLISECONDS.sleep(300);
		performTab();
		rawInput("1000");
		performEnter();
		
		click(btnPopupSave);
		switchBack();
		TimeUnit.SECONDS.sleep(1);
		
		////////////////////////////////////////////////////
		//forth
		TimeUnit.SECONDS.sleep(2);
		click(check3);
		click(check4);
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.MILLISECONDS.sleep(300);
		
		
		List<WebElement> benefits=findElements(benefitList);
		if(benefits.size()>1)
		{
			System.out.println("The Benefit list contains: "+benefits.size()+" benefits.");
			
			click(IbenefitEdit1);
			TimeUnit.MILLISECONDS.sleep(300);
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			perfromTimesTab(3);
			rawInput("300000");
			TimeUnit.MILLISECONDS.sleep(200);
			performEnter();
			
			TimeUnit.SECONDS.sleep(2);
			click(IbenefitEdit2);
			TimeUnit.SECONDS.sleep(1);
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			perfromTimesTab(3);
			rawInput("300000");
			performEnter();
			
			TimeUnit.SECONDS.sleep(1);
			click(IbenefitEdit3);
			TimeUnit.SECONDS.sleep(1);
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			perfromTimesTab(3);
			rawInput("300000");
			performEnter();
			click(btnPopupSave);
			switchBack();
			
			//Fifth
			TimeUnit.SECONDS.sleep(2);
			click(check4);
			click(check5);
			click(Benefit);
			switchToFrame(iFrame);
			
			TimeUnit.SECONDS.sleep(2);
			
			click(IbenefitEdit1);
			TimeUnit.MILLISECONDS.sleep(300);
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			perfromTimesTab(3);
			rawInput("320000");
			TimeUnit.MILLISECONDS.sleep(300);
			performEnter();
			TimeUnit.MILLISECONDS.sleep(300);
			
			click(IbenefitEdit2);
			TimeUnit.SECONDS.sleep(1);
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			perfromTimesTab(3);
			rawInput("320000");
			performEnter();
			
			TimeUnit.SECONDS.sleep(1);
			click(IbenefitEdit3);
			TimeUnit.SECONDS.sleep(1);
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			perfromTimesTab(3);
			rawInput("320000");
			performEnter();
			click(btnPopupSave);
			switchBack();
			
			TimeUnit.SECONDS.sleep(2);
			click(check5);
			click(check6);
			click(Benefit);
			TimeUnit.SECONDS.sleep(1);
			
			switchToFrame(iFrame);
			TimeUnit.SECONDS.sleep(1);
			click(IbenefitEdit1);
			TimeUnit.MILLISECONDS.sleep(300);
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			perfromTimesTab(3);
			rawInput("340000");
			TimeUnit.MILLISECONDS.sleep(300);
			performEnter();
			TimeUnit.MILLISECONDS.sleep(300);
			
			click(IbenefitEdit2);
			TimeUnit.SECONDS.sleep(1);
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			perfromTimesTab(3);
			rawInput("340000");
			performEnter();
			
			TimeUnit.SECONDS.sleep(1);
			click(IbenefitEdit3);
			TimeUnit.SECONDS.sleep(1);
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			perfromTimesTab(3);
			rawInput("340000");
			performEnter();
			click(btnPopupSave);
			switchBack();
			
			TimeUnit.SECONDS.sleep(2);
		}else
		{
			click(benefitEdit1);
			TimeUnit.MILLISECONDS.sleep(300);
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			perfromTimesTab(3);
			rawInput("300000");
			TimeUnit.MILLISECONDS.sleep(200);
			performEnter();
			
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Would add Benefit:)");
			click(addBenefit);
			TimeUnit.SECONDS.sleep(1);
			clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
			clearType(product_benefit_effectiveDate,"01-01-2017");
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			clearType(product_benefit_membershipType_input,"S - Spouse");
			fillin("0","300000","","");
			TimeUnit.SECONDS.sleep(1);
			click(addBenefit);
			TimeUnit.SECONDS.sleep(1);
			clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
			clearType(product_benefit_effectiveDate,"01-01-2017");
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			clearType(product_benefit_membershipType_input,"C - Child");
			fillin("0","300000","","");
			click(btnPopupSave);
			switchBack();
			
			//Fifth
			TimeUnit.SECONDS.sleep(2);
			click(check4);
			click(check5);
			click(Benefit);
			switchToFrame(iFrame);
			
			TimeUnit.SECONDS.sleep(2);
			
			click(benefitEdit1);
			TimeUnit.MILLISECONDS.sleep(300);
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			perfromTimesTab(3);
			rawInput("320000");
			TimeUnit.MILLISECONDS.sleep(300);
			performEnter();
			TimeUnit.MILLISECONDS.sleep(300);
			
			click(addBenefit);
			TimeUnit.SECONDS.sleep(1);
			clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
			clearType(product_benefit_effectiveDate,"01-01-2017");
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			clearType(product_benefit_membershipType_input,"S - Spouse");
			fillin("0","320000","","");
			TimeUnit.SECONDS.sleep(1);
			click(addBenefit);
			TimeUnit.SECONDS.sleep(1);
			clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
			clearType(product_benefit_effectiveDate,"01-01-2017");
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			clearType(product_benefit_membershipType_input,"C - Child");
			fillin("0","320000","","");
			
			click(btnPopupSave);
			switchBack();
			
			TimeUnit.SECONDS.sleep(2);
			click(check5);
			click(check6);
			click(Benefit);
			TimeUnit.SECONDS.sleep(1);
			
			switchToFrame(iFrame);
			TimeUnit.SECONDS.sleep(1);
			click(benefitEdit1);
			TimeUnit.MILLISECONDS.sleep(300);
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			perfromTimesTab(3);
			rawInput("340000");
			TimeUnit.MILLISECONDS.sleep(300);
			performEnter();
			TimeUnit.MILLISECONDS.sleep(300);
			
			click(addBenefit);
			TimeUnit.SECONDS.sleep(1);
			clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
			clearType(product_benefit_effectiveDate,"01-01-2017");
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			clearType(product_benefit_membershipType_input,"S - Spouse");
			fillin("0","340000","","");
			TimeUnit.SECONDS.sleep(1);
			click(addBenefit);
			TimeUnit.SECONDS.sleep(1);
			clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
			clearType(product_benefit_effectiveDate,"01-01-2017");
			clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
			clearType(product_benefit_membershipType_input,"C - Child");
			fillin("0","340000","","");
			click(btnPopupSave);
			switchBack();
			
			TimeUnit.SECONDS.sleep(2);
		}
		
		
		
		
	}

	
	public void editProductPlan() throws Exception
	{
		TimeUnit.SECONDS.sleep(2);
		goToPolicySection();
		TimeUnit.SECONDS.sleep(2);
		
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
		if(findElements(premiumRateTable).size()<2)
		{
			System.out.println("The premium rate not includes needed rate.");
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
			clearType(product_premium_rate_effectiveDate,"01-01-2017");
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
			clearType(product_premium_rate_effectiveDate,"01-01-2017");
			click(btnPopupSave);
			switchBack();
		}else
		{
			System.out.println("The premium rate includes needed rate.");
			click(btnPopupSave);
			switchBack();
		}
		
		
		TimeUnit.SECONDS.sleep(2);
		click(check1);
		click(Benefit);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(addBenefit);
		TimeUnit.SECONDS.sleep(1);
		clearType(product_benefit_benefitId_input,"L01 - DEATH BENEFIT");
		clearType(product_benefit_effectiveDate,"01-01-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin("0","100000","0","999999999");
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
		clearType(product_benefit_benefitId_input,"L11 - ACCIDENTAL DEATH");
		clearType(product_benefit_effectiveDate,"01-01-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin("0","100000","0","999999999");
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(Benefit);
		TimeUnit.SECONDS.sleep(2);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(addBenefit);
		TimeUnit.SECONDS.sleep(1);
		clearType(product_benefit_benefitId_input,"L12 - ACCIDENTAL DISMEMBERMENT (US)");
		clearType(product_benefit_effectiveDate,"01-01-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin("0","200000","0","999999999");
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
		clearType(product_benefit_effectiveDate,"01-01-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin("0","100000","0","999999999");
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
		clearType(product_benefit_benefitId_input,"L11 - ACCIDENTAL DEATH");
		clearType(product_benefit_effectiveDate,"01-01-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin("0","100000","0","999999999");
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
		click(Benefit);
		TimeUnit.SECONDS.sleep(2);
		switchToFrame(iFrame);
		TimeUnit.SECONDS.sleep(2);
		click(addBenefit);
		TimeUnit.SECONDS.sleep(1);
		clearType(product_benefit_benefitId_input,"L12 - ACCIDENTAL DISMEMBERMENT (US)");
		clearType(product_benefit_effectiveDate,"01-01-2017");
		clearType(product_benefit_saCalculateType_input,"M - Multiple Factor + Flat Amount");
		clearType(product_benefit_membershipType_input,"S - Spouse");
		fillin("0","200000","0","999999999");
		click(btnPopupSave);
		switchBack();
		
		TimeUnit.SECONDS.sleep(2);
	}
	
	public void fillin(String data1,String data2,String data3,String data4) throws Exception {
		performTab();
		TimeUnit.MILLISECONDS.sleep(300);
		rawInput(data1);
		
		performTab();
		TimeUnit.MILLISECONDS.sleep(300);
		rawInput(data2);
		
		performTab();
		TimeUnit.MILLISECONDS.sleep(300);
		rawInput(data3);
		
		performTab();
		TimeUnit.MILLISECONDS.sleep(300);
		rawInput(data4);
	}
	
	public static void main(String ...args)
	{
		System.out.println(3 % 3);
	}
}
