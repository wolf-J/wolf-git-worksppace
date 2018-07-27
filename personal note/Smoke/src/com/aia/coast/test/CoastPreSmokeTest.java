package com.aia.coast.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aia.coast.testcase.TestHttpClient;

public class CoastPreSmokeTest {

	static String session=null;
	
	@Parameters({"loginUrl","userId"})
	@Test
	public void loginTest(String loginUrl, String userId) throws Exception
	{
		session=TestHttpClient.doLogin(loginUrl,userId,"");		
	}	
	@Parameters({"createNewtaskUrl"})
	@Test
	public void createNewPolicyTest(String createNewtaskUrl)
	{
		TestHttpClient.createNewtask(createNewtaskUrl+"&_="+System.currentTimeMillis(), session);
	}
	
	@Parameters({"getJobListUrl","logonId","status","flg"})
	@Test
	public void loadJobListTest(String getJobListUrl, String logonId, String  status, String flg) throws Exception	
	{
		TestHttpClient.getJobList(getJobListUrl, session, logonId, status, flg);
	}
	
	@Parameters({"downLoadUrlTemplate1","downLoadUrlTemplate2","startRow","excelField1","excelField2"})
	@Test
	public void checkMemberFileTemplate(String downLoadUrlTemplate1,String downLoadUrlTemplate2,int startRow ,String excelField1,String excelField2) throws Exception
	{
		//downLoadUrlTemplate1、downLoadUrlTemplate2分别是两个下载按钮
		TestHttpClient.downLoadTemplate(downLoadUrlTemplate1,startRow,excelField1);
	}
}
