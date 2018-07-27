package com.aia.coast.pageobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aia.coast.testcase.BillResult;
import com.aia.coast.util.ConfigUtil;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;

public class BillPage extends BasePage{
	
	Locator AutoBill=new Locator("AutoBill");
	Locator DraftBill=new Locator("DraftBill");
	Locator GenerateBill=new Locator("GenerateBill");
	Locator OfficialBill=new Locator("OfficialBill");
	Locator loading=new Locator("loading");
	Locator loadingbg=new Locator("loadingbg");
	Locator subOfficeCode=new Locator("subOfficeCode");
	Locator CertNo=new Locator("CertNo");
	Locator IndividualAutoBill=new Locator("IndividualAutoBill");
	
	Locator subOfficeArrow=new Locator("subOfficeArrow");
	Locator subOfficeItem=new Locator("subOfficeItem");
	Locator CertNoArrow=new Locator("CertNoArrow");
	Locator CertNoItem=new Locator("CertNoItem");
	
	Locator MemberTab=new Locator("MemberTab");
	Locator firstMemberData=new Locator("firstMemberData");
	Locator groupMemPremium=new Locator("groupMemPremium");
	Locator FeeTab=new Locator("FeeTab");
	Locator groupFeeAmount=new Locator("groupFeeAmount");
	Locator showAllMember=new Locator("showAllMember");
	//Invidual
	Locator showAllBill=new Locator("showAllBill");
	Locator indivMemPremium=new Locator("indivMemPremium");
	Locator indivCommision=new Locator("indivCommision");
	Locator BillItem=new Locator("BillItem");
	Locator subOfficeChecks=new Locator("subOfficeChecks");
	
	Locator loadingImage=new Locator("loadingImage");
	private static Properties billList=ConfigUtil.getProjectProperties("Bill.properties");
	
	public BillPage(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	public void generateGroupBill(BillResult billResult) throws Exception
	{
		TimeUnit.SECONDS.sleep(3);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		

		TimeUnit.SECONDS.sleep(1);
		
		click(AutoBill);
		
		TimeUnit.SECONDS.sleep(1);
		
		click(DraftBill);
	
		TimeUnit.SECONDS.sleep(1);
		pageDown();
		TimeUnit.MILLISECONDS.sleep(300);
		//Check Sub Office
		/*List<WebElement> subOffChecks=findElements(subOfficeChecks);
		for(WebElement subOfficeCheck : subOffChecks)
			subOfficeCheck.click();
		*/
		

		TimeUnit.SECONDS.sleep(1);
		click(GenerateBill);
		
		TimeUnit.SECONDS.sleep(3);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		//Need to change
		TimeUnit.SECONDS.sleep(1);
		click(OfficialBill);
		
		TimeUnit.SECONDS.sleep(1);
		
		click(GenerateBill);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(3);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		pageDown();
		TimeUnit.SECONDS.sleep(1);
		
		String[] prems=billResult.getPremiums().split("/");
		//String[] prems=billList.getProperty("GLV").split(",");
		List<String> premiums=Arrays.asList(prems);
		
		List<String> fees=Arrays.asList(billResult.getFees().split("/"));
		//List<String> fees=Arrays.asList(billList.getProperty("GLVFees").split(","));
		
		//Comment out when debug
		//compareGroupMemPremiumAndFees(premiums, fees);
	}
	
	
	
	public void generateIPosBill() throws Exception
	{
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loadingbg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(AutoBill);
		
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		
		
		click(DraftBill);
	
		TimeUnit.SECONDS.sleep(1);
		
		click(GenerateBill);
		
		TimeUnit.SECONDS.sleep(3);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		//Need to change
		TimeUnit.SECONDS.sleep(1);
		click(OfficialBill);
		
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(GenerateBill);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(3);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		
		String[] prems=billList.getProperty("iPos").split("/");
		List<String> premiums=Arrays.asList(prems);
		
		List<String> fees=Arrays.asList(billList.getProperty("iPosFees").split("/"));
		compareIPosMemPremiumAndFees(premiums, fees);
	}
	
	
	public void generateIndividualBill(BillResult billResult) throws Exception
	{
		/*clearType(subOfficeCode,"100-Automation Team");
		click(subOfficeCode);
		click(CertNo);
		clearType(CertNo,"Select All");
		click(CertNo);
		*/
		
		//debug
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(2);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(2);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		click(subOfficeArrow);
		TimeUnit.SECONDS.sleep(1);
		click(subOfficeItem);
		
		//click(CertNoArrow);
		//click(CertNoItem);
		TimeUnit.SECONDS.sleep(1);
		click(IndividualAutoBill);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		click(GenerateBill);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		String[] prems=billResult.getPremiums().split("/");
		//String[] prems=billList.getProperty("Individual").split("/");
		List<String> premiums=Arrays.asList(prems);
		
		List<String> commissionAmounts=Arrays.asList(billResult.getCommissionAmounts().split("/"));
		//List<String> commissionAmounts=Arrays.asList(billList.getProperty("commissionAmount").split("/"));
		compareInvidiualMemPremiumAndFees(premiums, commissionAmounts);
		
	}

	
	public void compareIPosMemPremiumAndFees(List<String> premiums, List<String> fees) throws Exception
	{
		System.out.println("Start to compare iPos Premium and Fees");
		List<String> memPremiums=new ArrayList();
		List<String> memFees=new ArrayList();
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		click(MemberTab);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		//Click first item in Member
		
		
		click(showAllMember);
		performArrowUp();
		performArrowUp();
		performEnter();
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(3);
		
		while(!elementNotDisplay(loadingImage))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		click(firstMemberData);
		
		TimeUnit.SECONDS.sleep(1);
		
		
		performArrowRight(11);
		
		List<WebElement> memPremiumElements=findElements(groupMemPremium);
		while(!(memPremiumElements.get(0).getText().length()>1))
		{
			performArrowUp(1);
			memPremiumElements=findElements(groupMemPremium);
		}
		
		
		memPremiumElements=findElements(groupMemPremium);
		for(WebElement ele : memPremiumElements)
		{
			String premium=ele.getText().trim();
			memPremiums.add(premium);
			System.out.println("Premium: "+premium);
		}
		
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		click(FeeTab);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		List<WebElement> feeAmountElements=findElements(groupFeeAmount);
		while(!(feeAmountElements.get(0).getText().length()>0))
		{
			performArrowUp(1);
			feeAmountElements=findElements(groupFeeAmount);
		}
		
		int feeLen=feeAmountElements.size();
		for(int i=0;i<feeLen;i++)
		{
			TimeUnit.MILLISECONDS.sleep(100);
			feeAmountElements=findElements(groupFeeAmount);
			WebElement ele=feeAmountElements.get(i);
			String fee=ele.getText();
			memFees.add(fee);
			System.out.println("Fee: "+fee);
		}
		
		/*for(WebElement ele : feeAmountElements)
		{
			String fee=ele.getText();
			memFees.add(fee);
			System.out.println("Fee: "+fee);
		}*/
		
		//Compare fee and premiums
		if(!premiums.containsAll(memPremiums))
		{
			throw new Exception("The Premiums for Group is not consistent, please check!!!");
		}
		
		if(!fees.containsAll(memFees))
		{
			throw new Exception("The Fees for Group is not consistent, please check!!!");
		}
		
		System.out.println("Finish to compare Premium and Fees");
		
	}
	
	
	public void compareGroupMemPremiumAndFees(List<String> premiums, List<String> fees) throws Exception
	{
		System.out.println("Start to compare Premium and Fees");
		List<String> memPremiums=new ArrayList();
		List<String> memFees=new ArrayList();
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		click(MemberTab);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		//Click first item in Member
		click(firstMemberData);
		
		
		
		performArrowRight(9);
		
		List<WebElement> memPremiumElements=findElements(groupMemPremium);
		while(!(memPremiumElements.get(0).getText().length()>1))
		{
			memPremiumElements=findElements(groupMemPremium);
		}
		
		
		memPremiumElements=findElements(groupMemPremium);
		for(WebElement ele : memPremiumElements)
		{
			String premium=ele.getText().trim();
			memPremiums.add(premium);
			System.out.println("Premium: "+premium);
		}
		
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		click(FeeTab);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		List<WebElement> feeAmountElements=findElements(groupFeeAmount);
		while(!(feeAmountElements.get(0).getText().length()>0))
		{
			feeAmountElements=findElements(groupFeeAmount);
		}
		
		for(WebElement ele : feeAmountElements)
		{
			String fee=ele.getText();
			memFees.add(fee);
			System.out.println("Fee: "+fee);
		}
		
		//Compare fee and premiums
		if(!premiums.containsAll(memPremiums))
		{
			throw new Exception("The Premiums for Group is not consistent, please check!!!");
		}
		
		if(!fees.containsAll(memFees))
		{
			throw new Exception("The Fees for Group is not consistent, please check!!!");
		}
		
		System.out.println("Finish to compare Premium and Fees");
		
	}
	
	public void compareInvidiualMemPremiumAndFees(List<String> premiums, List<String> commissionAmounts) throws Exception
	{
		System.out.println("Start to compare Individual Premium and Commission Amounts");
		List<String> memPremiums=new ArrayList();
		List<String> memCommissAmounts=new ArrayList();
		
		pageDown();
		
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(2);
		while(!elementNotDisplay(loadingImage))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(showAllBill);
		performArrowUp();
		performArrowUp();
		performEnter();
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loadingImage))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		click(BillItem);
		performArrowRight(16);
		TimeUnit.MILLISECONDS.sleep(300);
		//click(BillItem);
		
		
		List<WebElement> memPremiumElements=findElements(indivMemPremium);
		int premiumLength=memPremiumElements.size();
		
		System.out.println("There are "+premiumLength + " premiums.");
		
		memPremiumElements=findElements(indivMemPremium);
		for(int i=0; i<premiumLength; i++)
		{
			memPremiumElements=findElements(indivMemPremium);
			String premium=memPremiumElements.get(i).getText();
			while(premium.length()==0)
			{
				TimeUnit.MILLISECONDS.sleep(400);
				System.out.println("Check the empty Premium. -Jason");
				performArrowDown(i+1);
				memPremiumElements=findElements(indivMemPremium);
				TimeUnit.MILLISECONDS.sleep(400);
				premium=memPremiumElements.get(i).getText();
			}
			memPremiums.add(premium);
		}
		
		for(String s : memPremiums)
		{
			System.out.println("Premium: "+s);
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
		performArrowUp(20);
		
		List<WebElement> commisAmountElements=findElements(indivCommision);
		int commisAmountLength=commisAmountElements.size();
		System.out.println("There are "+commisAmountLength + " commission amounts.");
		
		for(int i=0; i<commisAmountLength; i++)
		{
			commisAmountElements=findElements(indivCommision);
			String commission=commisAmountElements.get(i).getText();
			while(commission.length()==0)
			{
				TimeUnit.MILLISECONDS.sleep(400);
				System.out.println("Check the empty Commission Amount. -Jason");
				performArrowDown(i+1);
				commisAmountElements=findElements(indivCommision);
				TimeUnit.MILLISECONDS.sleep(400);
				commission=memPremiumElements.get(i).getText();
			}
			memCommissAmounts.add(commission);
		}
		
		for(String ss : memCommissAmounts)
		{
			System.out.println("Commission Amount: "+ss);
		}
		
		//Compare fee and premiums
		if(!premiums.containsAll(memPremiums))
		{
			throw new Exception("The Premiums for Group is not consistent, please check!!!");
		}
		
		
		if(!commissionAmounts.containsAll(memCommissAmounts))
		{
			throw new Exception("The commissionAmounts for Individual is not consistent, please check!!!");
		}
		
		System.out.println("Finish to compare Premium and Fees");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//System.out.println(billList.getProperty("GLV"));
		System.out.println(billList.getProperty("commissionAmount"));
		
	}

}
