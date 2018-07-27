package com.aia.coast.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import util.JsonUtil;

import com.aia.coast.pageobject.HomePage;
import com.aia.coast.testcase.CaseUtil;
import com.aia.coast.testcase.Client;
import com.aia.coast.testcase.CoastCase;
import com.aia.coast.testcase.DistributionChannel;
import com.aia.coast.testcase.IndividualBilling;
import com.aia.coast.testcase.PackageScheme;
import com.aia.coast.testcase.Policy;
import com.aia.coast.testcase.PolicyProduct;
import com.aia.coast.testcase.ProductPlan;
import com.aia.ui.utils.DriverFactory;

public class CoastWorkflowTest {
	
	public static void main(String ...args) throws Exception
	{
		
		//UAT
		//AbstractCoastRT rt=new IndivPolicy("http://thcoast-uat.aia.biz/COAST_FE/homepage/login", "BGRR018", "BGRR018");
		//AbstractCoastRT rt=new IndivPolicy("http://thadculwct01/COAST_FE/homepage/login", "BGRR018", "BGRR018");
		
		
		// 0417 PRE-SIT
		//AbstractCoastRT rt=new IndivPolicy("http://10.65.5.50:9083/COAST_FE_PRESIT/homepage/login", "ASNPHKS", "ASNPHKS");
		
		
		
		//0417 GZ sit
		//AbstractCoastRT rt=new IndivPolicy("https://10.65.5.50:9444/COAST_FE_PRESIT0531/homepage/login","ASNPHKS","ASNPHKS");
		
		
		////0531 pre-sit
		AbstractCoastRT rt=new IndivPolicy("https://cangzdlcoa02.aia.biz:9444/COAST_FE_PRESIT0531/homepage/login","ASNPHKS","ASNPH9K");
		//AbstractCoastRT rt=new IndivPolicy("http://10.65.5.50:9081/COAST_FE_PRESIT0531/homepage/login","ASNPHKS","ASNPHKS");
		
		//AbstractCoastRT rt=new IndivPolicy("thadcplwct11:9080/COAST_FE/homepage/login","BGRR047","BGRR047");
		
		//DM TH
		//AbstractCoastRT rt=new IndivPolicy("http://thadcslwct01:9080/COAST_FE/homepage/login","ASNPH9K","ASNPHKS");	
		
		//0531 sit
		//AbstractCoastRT rt=new IndivPolicy("http://10.65.5.44:9082/COAST_FE_SIT0531/homepage/login","ASNPHKS","ASNPHKS");
		//CoastCase testcase=CaseUtil.getCoastTestCases().get(0);
		
		//List<CoastCase> testcases=CaseUtil.getCoastTestCases();
		
		CoastCase testcase=CaseUtil.getCoastTestCases("Coast Case Data Template - Smoke.xlsx").get(0);

		rt.smokeTest(testcase);
		//System.out.println(JsonUtil.formatObjectToJson(testcase));
		//rt.executeTest(testcase);
		
	}
}
