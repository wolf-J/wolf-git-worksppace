package com.aia.coast.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.JsonUtil;

import com.aia.coast.testcase.CaseUtil;
import com.aia.coast.testcase.CoastCase;

public class CoastTestCaseDataProvider {
	
	@Test(dataProvider="testcases")
	public void testDataProvier(Map<String, CoastCase> cases)
	{
		for(Map.Entry<String, CoastCase> entry: cases.entrySet())
		{
			System.out.println(entry.getKey()+" : "+JsonUtil.formatObjectToJson(entry.getValue()));
		}
	}
	
	@DataProvider(name="testcases")
	public Object[][] readTestCases()
	{
		String caseId=null;
		CoastCase testcase=null;
		Map<String,CoastCase> testcases=new HashMap();
		try{
			List<CoastCase> caseList=CaseUtil.getCoastTestCases();
			System.out.println("Case Size: "+caseList.size());
			int i=caseList.size();
			for(int ii=0;ii<i;ii++)
			{
				testcases.put(caseList.get(ii).getCas().getCaseId(), caseList.get(ii));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return new Object[][]{{testcases}};
	}
}
