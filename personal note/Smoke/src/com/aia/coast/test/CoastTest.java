package com.aia.coast.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import util.Checker;

import com.aia.coast.pageobject.LoginPage;
import com.aia.coast.testcase.CaseUtil;
import com.aia.coast.testcase.CoastCase;
import com.aia.ui.utils.AIATestReporter;
import com.aia.ui.utils.DriverFactory;
import com.aia.ui.utils.TestNgRetryListener;

@Listeners(AIATestReporter.class)
public class CoastTest {
	
	@DataProvider(name="testcases")
	public Object[][] getTestCases() throws Exception
	{
		List<CoastCase> testcaseList=null;
		Map<String,CoastCase> testcases=new HashMap();
		
		testcaseList=CaseUtil.getCoastTestCases("Coast Case Data Template - RT.xlsx");
		int caseSize=testcaseList.size();
		
		Object[][] tests=new Object[caseSize][2];
		for(int i=0;i<caseSize;i++)
		{
			CoastCase coastCase=testcaseList.get(i);
			tests[i][0]=coastCase.getCas().getCaseId();
			tests[i][1]=coastCase;
		}
		return tests;
	}

	
	@DataProvider(name="smokeTestcases")
	public Object[][] getSmokeTestCases() throws Exception
	{
		List<CoastCase> testcaseList=null;
		Map<String,CoastCase> testcases=new HashMap();
		
		testcaseList=CaseUtil.getCoastTestCases("Coast Case Data Template - Smoke.xlsx");
		int caseSize=testcaseList.size();
		
		Object[][] tests=new Object[caseSize][2];
		for(int i=0;i<caseSize;i++)
		{
			CoastCase coastCase=testcaseList.get(i);
			tests[i][0]=coastCase.getCas().getCaseId();
			tests[i][1]=coastCase;
		}
		return tests;
	}
	
	@Parameters({"coast.url,coast.username"})
	@Test(dataProvider="testcases",testName="Coast RT workflow test")
	public void testCoast(String caseId, CoastCase coastCase)
	{
		System.out.println("Case Id: "+caseId+", case Name: "+coastCase.getCas().getPolicyCategory());		
		//0531 pre-sit
		//AbstractCoastRT rt=new IndivPolicy("http://10.65.5.50:9081/COAST_FE_PRESIT0531/homepage/login","ASNPHKS","ASNPHKS");
		//AbstractCoastRT rt=new IndivPolicy("https://10.65.5.50:9444/COAST_FE_PRESIT0531//homepage/login","ASNPHKS","ASNPHKS");
		
		//0531 sit
		//AbstractCoastRT rt=new IndivPolicy("http://10.65.5.44:9082/COAST_FE_SIT0531/homepage/login","ASNPHKS","ASNPHKS");
		//DM TH
		AbstractCoastRT rt=new IndivPolicy("http://thadcslwct01:9080/COAST_FE/homepage/login","ASNPH9K","ASNPH9K");
		
		
		//AbstractCoastRT rt=new IndivPolicy("http://thcoast-uat.aia.biz/COAST_FE/homepage/login", "BGRR018", "BGRR018");
		
		rt.executeTest(coastCase);
		
		//rt.smokeTest(coastCase);
	}


	@Test(dataProvider="smokeTestcases")
	public void testCoastSmoke(String caseId, CoastCase coastCase)
	{
		System.out.println("Case Id: "+caseId+", case Name: "+coastCase.getCas().getPolicyCategory());		
		//0531 pre-sit
		//AbstractCoastRT rt=new IndivPolicy("http://thadcslwct01:9080/COAST_FE/homepage/login","ASNPH9K","ASNPHKS");
		
		AbstractCoastRT rt=new IndivPolicy("https://cangzdlcoa02.aia.biz:9444/COAST_FE_PRESIT0531/homepage/login","ASNPH9k","ASNPH9K");
		//AbstractCoastRT rt = new IndivPolicy("http://cangzdlcoa01.aia.biz:9089/COAST_FE/homepage/login", "ASNPG67","ASNPH9K");
		rt.smokeTest(coastCase);
	}
}
