package com.aia.coast.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;


import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;
import static util.Checker.isNotNull;
public class ClientInfo extends BasePage{

	protected ClientInfo(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	Locator initialEffectiveDate=new Locator("initialEffectiveDate");
	Locator clientName=new Locator("clientName");
	Locator effectiveDate=new Locator("effectiveDate");
	Locator Address1=new Locator("address1");
	Locator province=new Locator("province");
	Locator district=new Locator("district");
	Locator postCode=new Locator("postCode");
	Locator subDistrict=new Locator("subDistrict");
	Locator contactPerson=new Locator("contactPersion");
	Locator payeeName=new Locator("payeeName");
	Locator bizType=new Locator("BizType");
	Locator salaryMode=new Locator("salaryMode");
	Locator generateCert=new Locator("generateCert");
	Locator salaryCurrency=new Locator("salaryCurrency");
	Locator preferLanguage=new Locator("preferLanguage");
	
	Locator taxId=new Locator("taxId");
	
	Locator districtDropDown=new Locator("districtDropDown");
	Locator districtD15=new Locator("districtD15");
	
	Locator subDistrictDropDown=new Locator("subDistrictDropDown");
	Locator subDistrictU130=new Locator("subDistrictU130");
	
	Locator stateDropDown=new Locator("stateDropDown");
	Locator stateItems=new Locator("stateItems");
	Locator state001=new Locator("state001");
	Locator loadingbg=new Locator("loadingbg");
	Locator appform=new Locator("appform");
	
	Locator districtItems=new Locator("districtItems");
	Locator subdistrictItems=new Locator("subdistrictItems");
	public void fillClient(com.aia.coast.testcase.Client client) throws Exception
	{
		if(isNotNull(client.getInitialEffectiveDate()))
			type(initialEffectiveDate,client.getInitialEffectiveDate());
		if(isNotNull(client.getClientName()))
			type(clientName,client.getClientName());
		TimeUnit.MILLISECONDS.sleep(500);
		while(!elementNotDisplay(loadingbg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		if(Boolean.valueOf(client.isReceiveAppForm()))
		{
			scrollToElement(appform);
			click(appform);
		}
		
		//type(effectiveDate,client.getEffectiveDate());
		if(isNotNull(client.getAddress1()))
			type(Address1,client.getAddress1());
		
		//MainPage.dropDownSetting(client_state_input, client.getProvince());
		
		//type(province,client.getProvince());
		TimeUnit.SECONDS.sleep(3);
		if(isNotNull(client.getProvinceAndState()))
		{
			TimeUnit.MILLISECONDS.sleep(500);
			while(!elementNotDisplay(loadingbg))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			click(province);
			selectValue(stateDropDown,stateItems,client.getProvinceAndState());
		}
		/*click(district);
		clearType(district,"D15 - 喙�喔笝喔� - (Sena)");
		*/
		if(isNotNull(client.getDistrict()))
		{
			click(district);
			selectValue(districtDropDown,districtItems,client.getDistrict());
		
		
		}
	
		if(isNotNull(client.getSubDistrict()))
		{
			click(subDistrict);
			selectValue(subDistrictDropDown,subdistrictItems,client.getSubDistrict());
		}
		
		if(isNotNull(client.getContactPerson()))
			type(contactPerson,client.getContactPerson());
		if(isNotNull(client.getPayeeName()))
			clearType(payeeName,client.getPayeeName());
		if(isNotNull(client.getNatureOfBusiness()))
		{
			clearType(bizType,client.getNatureOfBusiness());
			//click(bizType);
		}
		if(isNotNull(client.getSalaryMode()))
		{	
			clearType(salaryMode,client.getSalaryMode());
			click(salaryMode);
		}
		if(isNotNull(client.getGeneratedCertNoIndicator()))
			clearType(generateCert,client.getGeneratedCertNoIndicator());
		
		if(isNotNull(client.getSalaryCurrency()))
		{
			clearType(salaryCurrency,client.getSalaryCurrency());
		}
		
		if(isNotNull(client.getPreferLanguage()))
		{
			clearType(preferLanguage, client.getPreferLanguage());
		}
		
		if(isNotNull(client.getTaxId()))
		{
			clearType(taxId, client.getTaxId());
		}
		
		TimeUnit.SECONDS.sleep(2);
		//pageDown();
		//click(taxId);
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
