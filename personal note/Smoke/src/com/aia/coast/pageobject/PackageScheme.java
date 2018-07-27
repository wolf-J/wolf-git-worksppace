package com.aia.coast.pageobject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import util.Checker;
import util.DateTimeUtil;
import util.StrUtil;

import com.aia.coast.entity.Scheme;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;

public class PackageScheme extends BasePage{

	protected PackageScheme(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	Locator packageProduct=new Locator("packageProduct");
	Locator packagePlan=new Locator("packagePlan");
	Locator planDesc=new Locator("planDesc");
	Locator subProduct=new Locator("subProduct");
	Locator subProductSelect=new Locator("subProductSelect");
	Locator updateBtn=new Locator("updateBtn");
	
	Locator pkgProductArrow=new Locator("pkgProductArrow");
	Locator pkgPlanArrow=new Locator("pkgPlanArrow");
	Locator flexPackagePlan=new Locator("flexPackagePlan");
	
	Locator packageSchemesList=new Locator("packageSchemesList");
	
	Locator schemeEffectiveDate=new Locator("schemeEffectiveDate");
	Locator effectiveDate=new Locator("effectiveDate");
	public void packageSchemeChange() throws Exception
	{
		List<WebElement> packageSchemes=findElements(packageSchemesList);
		String lastPackage="//div[@section-name='Package Scheme']/div/table[@role='grid']/tbody/tr["+packageSchemes.size()+"]/td";
		System.out.println("LastPackage XPath: "+lastPackage);
		List<WebElement> packageItems=driver.findElements(By.xpath(lastPackage));
		String packageName=null;
		String packagePlan=null;
		String planDesc=null;
		String initialEffectiveDate=null;
		
		packageName=packageItems.get(0).getText();
		packagePlan=packageItems.get(1).getText();
		packageName=StrUtil.ManipulateBenefitCode(packageName);
	
		System.out.println("PackageName: "+packageName+", packagePlan: "+packagePlan+", EffectiveDate: "+packageItems.get(5).getText());
		initialEffectiveDate=DateTimeUtil.increaseOneYear(packageItems.get(5).getText());
		
		com.aia.coast.testcase.PackageScheme scheme=new com.aia.coast.testcase.PackageScheme();
		scheme.setPackageProduct(packageName);
		scheme.setPackagePlan("00"+(Integer.valueOf(packagePlan)+1));
		scheme.setPlanDesc("Employee");
		scheme.setEffectiveDate(initialEffectiveDate);
		fillPackageScheme(scheme);
	}
	
	
	
	public void fillPackageScheme(com.aia.coast.testcase.PackageScheme scheme) throws Exception
	{
		//click(packageProduct);
		clearType(packageProduct,scheme.getPackageProduct());
		TimeUnit.SECONDS.sleep(1);
		click(pkgProductArrow);
		
		TimeUnit.SECONDS.sleep(2);
		
		boolean flexPackageExist=ElementPresent(flexPackagePlan);
		if(flexPackageExist)
		{
			click(flexPackagePlan);
			clearType(flexPackagePlan,scheme.getPackagePlan());
			TimeUnit.SECONDS.sleep(1);
			TimeUnit.SECONDS.sleep(2);
			
			clearType(planDesc,scheme.getPlanDesc());
			TimeUnit.SECONDS.sleep(1);
		}else
		{
			click(packagePlan);
			clearType(packagePlan,scheme.getPackagePlan());
			TimeUnit.SECONDS.sleep(1);
			click(pkgPlanArrow);
			TimeUnit.SECONDS.sleep(2);
			
			clearType(planDesc,scheme.getPlanDesc());
			TimeUnit.SECONDS.sleep(1);
		}
		
		
		if(Checker.isNotNull(scheme.getEffectiveDate()))
		{
			clearType(schemeEffectiveDate,scheme.getEffectiveDate());
			clearType(effectiveDate,scheme.getEffectiveDate());
		}
		
		boolean subProductExits=ElementPresent(subProduct);
		if(subProductExits)
		{
			System.out.println("Would add sub product.");
			click(subProduct);
			List<WebElement> subProductItems=findElements(subProductSelect);
			
			int subProductSize=subProductItems.size();
			String[] subProducts=null;
			if(Checker.isNotNull(scheme.getSubProduct()))
			{
				subProducts=scheme.getSubProduct().split(",");
			}
			
			for(int index=0;index<subProductSize;index++)
			{
				click(subProduct);
				TimeUnit.MILLISECONDS.sleep(100);
				subProductItems=findElements(subProductSelect);
				String subProductTxt=subProductItems.get(index).getText();
				System.out.println("Sub Product: "+subProductTxt);
				
				if(subProducts!=null)
				{
					int subProductLen=subProducts.length;
					for(int i=0;i<subProductLen;i++)
					{
						if(subProductTxt.contains(scheme.getPackagePlan()) && subProductTxt.contains(subProducts[i]))
						{
							subProductItems.get(index).click();
						}
					}
				}
				else if(subProductTxt.contains(scheme.getPackagePlan()))
				{
					subProductItems.get(index).click();
				}
				TimeUnit.MILLISECONDS.sleep(100);
				
			}
			click(flexPackagePlan);
			TimeUnit.MILLISECONDS.sleep(100);
		}
		click(updateBtn);
	}
	
	public void fillFlexPackageScheme(com.aia.coast.testcase.PackageScheme scheme) throws Exception
	{
		//click(packageProduct);
		clearType(packageProduct,scheme.getPackageProduct());
		TimeUnit.SECONDS.sleep(1);
		click(pkgProductArrow);
		
		TimeUnit.SECONDS.sleep(2);
		
		click(flexPackagePlan);
		clearType(flexPackagePlan,scheme.getPackagePlan());
		TimeUnit.SECONDS.sleep(1);
		TimeUnit.SECONDS.sleep(2);
		
		clearType(planDesc,scheme.getPlanDesc());
		TimeUnit.SECONDS.sleep(1);
		
		boolean subProductExits=ElementPresent(subProduct);
		if(subProductExits)
		{
			System.out.println("Would add sub product.");
			click(subProduct);
			List<WebElement> subProductItems=findElements(subProductSelect);
			
			int subProductSize=subProductItems.size();
			for(int index=0;index<subProductSize;index++)
			{
				click(subProduct);
				TimeUnit.MILLISECONDS.sleep(100);
				subProductItems=findElements(subProductSelect);
				String subProductTxt=subProductItems.get(index).getText();
				System.out.println("Sub Product: "+subProductTxt);
				if(subProductTxt.contains(scheme.getPackagePlan()))
				{
					subProductItems.get(index).click();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}
		click(updateBtn);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
