package com.aia.coast.pageobject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.text.translate.LookupTranslator;
import org.openqa.selenium.WebDriver;

import util.CheckPolicyInCoast;
import util.JsonUtil;

import com.aia.coast.entity.assignTask;
import com.aia.coast.testcase.Policy;
import com.aia.coast.util.ConvertPropertyToPojo;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public static final String PolicyMasterQC="Approve New Policy";
	public static final String MemberQC="Member QC";
	public static final String BillGeneration="Request billing for NB";
	public static final String BillGenerationForChange="Request Billing for Policy Change";
	
	public static assignTask assign;
	
	static{
		try {
			assign=ConvertPropertyToPojo.convertToPojo(assignTask.class);
		} catch (InstantiationException | IllegalAccessException
				| NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	Locator queryField=new Locator("query",60);
	Locator getJob=new Locator("getJob",10);
	Locator firstJob=new Locator("firstJob",50);
	
	Locator firstPolicyNumber=new Locator("firstPolicyNumber");
	Locator firstPolicyName=new Locator("firstPolicyName");
	
	Locator jobList=new Locator("jobList");
	Locator openTask=new Locator("openTask",5);
	Locator loadingImg=new Locator("loadingImg");
	Locator loadingAniX=new Locator("loadingAniX");
	Locator Home=new Locator("Home");
	Locator userinfo=new Locator("userinfo");
	Locator logout=new Locator("logout");
	
	//Loading
	Locator loading=new Locator("loading");
	Locator policyNoArrow=new Locator("policyNoArrow");
	Locator processArrow=new Locator("processArrow");
	
	Locator filter=new Locator("filter");
	Locator filterType=new Locator("filterType");
	Locator processFilter=new Locator("processFilter");
	Locator filterInput=new Locator("filterInput");
	Locator filterBtn=new Locator("filterBtn");
	
	//Wait loading of tasks
	Locator finishLoading=new Locator("finishLoading");
	
	
	Locator pendingBtn=new Locator("pendingBtn");
	Locator pendingPolicyNoArrow=new Locator("pendingPolicyNoArrow");
	Locator firstPendingJob=new Locator("firstPendingJob");
	
	Locator processName=new Locator("processName");
	Locator activityName=new Locator("activityName");
	
	//Create Job
	Locator createTask=new Locator("createTask");
	Locator activityDropDown=new Locator("activityDropDown");
	Locator createNewPolicy=new Locator("createNewPolicy");
	Locator backHome=new Locator("backHome");
	
	//Locator notifBox=new Locator("notifBox");
	Locator closeNotif=new Locator("closeNotif");
	
	public void waitLoadingOfTask() throws IOException
	{
		findElement(driver,finishLoading);
	}
	
	
	public void checkNotifBoxAndClose() throws Exception
	{
		if(ElementPresent(closeNotif))
		{
			click(closeNotif);
		}
	}
	
	public boolean submitSuccess() throws Exception
	{
		boolean submitSuccess=isElementPresent(Home);
		return submitSuccess;
	}
	
	public void logout() throws Exception
	{
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		while(!elementNotDisplay(loadingAniX))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(userinfo);
		TimeUnit.MILLISECONDS.sleep(300);
		click(logout);
	}
	
	public void createANewPolicy() throws Exception
	{
		
		
		//clearType(processName,"NEW BIZ");
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(2);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		checkFirstPolicy();
		clearType(processName,"NEW BUSINESS ");
		
		TimeUnit.MILLISECONDS.sleep(500);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		checkNotifBoxAndClose();
		click(activityName);
		
		TimeUnit.SECONDS.sleep(2);

		click(activityDropDown);
		
		click(createNewPolicy);
		
		click(getJob);
		
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		
		
		TimeUnit.SECONDS.sleep(2);
		while(!elementNotDisplay(loadingAniX))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		checkFirstJobValid();

		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(firstJob);
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
		click(firstJob);
		
		TimeUnit.MILLISECONDS.sleep(300);
		while(!elementNotDisplay(loadingAniX))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(openTask);
	}
	
	
	public void checkFirstJobValid()
	{
		long waitSec=3*60*1000;
		
		long start=System.currentTimeMillis();
		long timeSpent=0;
		
		String policyNumber=null;
		
		try
		{
			getElement(firstJob);
			policyNumber=getElement(firstPolicyNumber).getText();
			System.out.println("Policy Number: "+policyNumber+", len: "+policyNumber.length());
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
		while(! (policyNumber.length()>=10) && waitSec > timeSpent)
		{
			System.out.println("Try Acquire the Policy Number..."+policyNumber);
			try
			{
				policyNumber=getElement(firstPolicyNumber).getText();
				System.out.println("PolicyNumber: "+policyNumber);
				TimeUnit.SECONDS.sleep(5);
			}catch(Exception e)
			{
				throw new RuntimeException(e);
			}
			timeSpent=System.currentTimeMillis()-start;
		}
		
		if(timeSpent > waitSec)
		{
			throw new RuntimeException("Time Out to wait policy Number.");
		}
		
		String policyName=null;
		
		try{
			policyName=getElement(firstPolicyName).getText();
			System.out.println("Policy Name: "+policyName);
		}catch(Exception e)
		{
			throw new RuntimeException("Get Policy Name exception: "+policyName,e);
		}
		
		if(policyName.length()>0)
		{
			throw new RuntimeException("Policy Name: "+policyName);
		}
	}
	
	
	public void checkFirstPolicy()
	{
		long waitSec=3*60*1000;
		
		long start=System.currentTimeMillis();
		long timeSpent=0;
		
		String policyNumber=null;
		
		try
		{
			while(!ElementPresent(firstJob))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			policyNumber=getElement(firstPolicyNumber).getText();
			System.out.println("Policy Number: "+policyNumber+", len: "+policyNumber.length());
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
		while(! (policyNumber.length()>=10) && waitSec > timeSpent)
		{
			System.out.println("Try Acquire the Policy Number..."+policyNumber);
			try
			{
				policyNumber=getElement(firstPolicyNumber).getText();
				System.out.println("PolicyNumber: "+policyNumber);
				TimeUnit.SECONDS.sleep(5);
			}catch(Exception e)
			{
				throw new RuntimeException(e);
			}
			timeSpent=System.currentTimeMillis()-start;
		}
		
		
	}
	
	public void backHome() throws Exception
	{
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(2);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingAniX))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(backHome);
		submitSuccess();
	}
	
	public void createAJob() throws Exception
	{
		
		ElementPresent(processName);
		
		TimeUnit.SECONDS.sleep(1);
		
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(2);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingAniX))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		
		click(createTask);
		TimeUnit.SECONDS.sleep(1);
	}
	
	
	public void openFirstTask() throws Exception
	{	
		//click(firstJob);
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		click(firstJob);
		
		click(openTask);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
	}
	
	
	public void openFirstPendingTask() throws Exception
	{	
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		click(firstPendingJob);
		
		TimeUnit.SECONDS.sleep(1);
		
		click(openTask);
		
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
	}
	
	public void GoToPendingTab() throws Exception
	{	
		//click(firstJob);
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
		
		click(pendingBtn);
	}
	
	public void filterPolicy(String policyNum) throws Exception
	{
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		TimeUnit.MILLISECONDS.sleep(500);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		click(policyNoArrow);
		TimeUnit.SECONDS.sleep(1);
		click(filter);
		TimeUnit.SECONDS.sleep(1);
		clearType(filterInput,policyNum);
		TimeUnit.SECONDS.sleep(1);
		click(filterBtn);
	}
	
	public void filterpolicy(String polNo) throws Exception
	{
		
		System.out.println("Filter: "+polNo);
		TimeUnit.SECONDS.sleep(1);
		int i=0;
		do{
			if(i!=0)
			{
				refresh();
				System.out.println("Refresh");
			}
			i++;
			
			TimeUnit.SECONDS.sleep(1);
			while(!elementNotDisplay(loadingImg))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			
			TimeUnit.SECONDS.sleep(1);
			while(!elementNotDisplay(loadingAniX))
			{
				TimeUnit.SECONDS.sleep(1);
			}
		
			TimeUnit.SECONDS.sleep(1);
			while(!elementNotDisplay(loadingImg))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			
			checkFirstPolicy();
			
			
			try{
				click(firstJob);	
				
				click(policyNoArrow);
				
				TimeUnit.SECONDS.sleep(1);
				try
				{
					click(filter);
					TimeUnit.MILLISECONDS.sleep(100);
					click(filterType);
				}
				catch(org.openqa.selenium.NoSuchElementException e)
				{
					e.printStackTrace();
					click(policyNoArrow);
					TimeUnit.SECONDS.sleep(1);
					click(filter);
					click(filterType);
				}
				TimeUnit.SECONDS.sleep(1);
				clearType(filterInput,polNo);
				TimeUnit.SECONDS.sleep(1);
				
				click(filterBtn);
				TimeUnit.SECONDS.sleep(1);
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
				refresh();
			}
			
			
		}while(findElements(jobList)==null || findElements(jobList).isEmpty());
	}
	
	
	public void filterPendingPolicy(String polNo) throws Exception
	{
		
		System.out.println("Filter: "+polNo);
		TimeUnit.SECONDS.sleep(1);
		int i=0;
		do{
			if(i!=0)
			{
				refresh();
				TimeUnit.SECONDS.sleep(1);
				while(!elementNotDisplay(loading))
				{
					TimeUnit.SECONDS.sleep(1);
				}
				click(pendingBtn);
				System.out.println("Refresh");
			}
			i++;
			while(!elementNotDisplay(loading))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			try{
				click(firstPendingJob);	
				
				click(pendingPolicyNoArrow);
				TimeUnit.SECONDS.sleep(1);
				try
				{
					click(filter);
				}
				catch(org.openqa.selenium.NoSuchElementException e)
				{
					click(policyNoArrow);
					TimeUnit.SECONDS.sleep(1);
					click(filter);
				}
				TimeUnit.SECONDS.sleep(1);
				clearType(filterInput,polNo);
				TimeUnit.SECONDS.sleep(1);
				
				click(filterBtn);
				TimeUnit.SECONDS.sleep(1);
			}catch(Exception e)
			{
				refresh();
			}
			
			
		}while(findElements(jobList)==null);
	}
	
	public static Map<Boolean,assignTask> checkPolicyProcess(String policyNum, String url)
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		String result=check.policyInCoast(policyNum);
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		Map<Boolean,assignTask> map=new HashMap();
		for(int i=0;i<array.size();i++)
		{
			JsonObject obj=array.get(i).getAsJsonObject();
			String precessName=obj.get("jobName").getAsString();
			if(precessName.equalsIgnoreCase(PolicyMasterQC))
			{
				System.out.println("OK, found the PM Master QC");
				assign.setActivityId(obj.get("activityId").getAsString());
				assign.setCaseId(obj.get("caseId").getAsString());
				map.put(true, assign);
				return map;
			}
		}
		return null;
	}
	
	public static void queryBillInfo(String policyNum, String url)
	{
		CheckPolicyInCoast check = new CheckPolicyInCoast(url);
		String result =null;
		
	}
	
	public static Map<Boolean,assignTask> checkMemberQC(String policyNum, boolean UAT)
	{
		System.out.println("Check Member QC.");
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		String result=check.policyInCoast(policyNum);
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		Map<Boolean,assignTask> map=new HashMap();
		
		while(array.size()==1 && array.get(0).getAsJsonObject().get("processName").getAsString().contains("Contract"))
		{
			long start=System.currentTimeMillis();
			System.out.println("Wait Case Route");
			while((System.currentTimeMillis()-start)<10000l)
			{
				//wait 10 seconds.
			}
			result=check.policyInCoast(policyNum);
			System.out.println(result);
			array=JsonUtil.formatStringToJsonArray(result);
		}
		for(int i=0;i<array.size();i++)
		{	
			JsonObject obj=array.get(i).getAsJsonObject();
			String precessName=obj.get("processName").getAsString();
			if(precessName.equalsIgnoreCase(MemberQC))
			{
				System.out.println("OK, found the Member QC");
				assign.setActivityId(obj.get("activityId").getAsString());
				assign.setCaseId(obj.get("caseId").getAsString());
				map.put(true, assign);
				check.assignTaskInCoast(assign);
				return map;
			}
		}
		return null;
	}
	
	
	public static Map<Boolean,assignTask> checkMemberQC(String policyNum, String url)
	{
		System.out.println("Check Member QC.");
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		String result=check.policyInCoast(policyNum);
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		Map<Boolean,assignTask> map=new HashMap();
		
		while(array.size()==1 && array.get(0).getAsJsonObject().get("jobName").getAsString().contains("Contract"))
		{
			long start=System.currentTimeMillis();
			System.out.println("Wait Case Route");
			while((System.currentTimeMillis()-start)<10000l)
			{
				//wait 10 seconds.
			}
			result=check.policyInCoast(policyNum);
			System.out.println(result);
			array=JsonUtil.formatStringToJsonArray(result);
		}
		for(int i=0;i<array.size();i++)
		{	
			JsonObject obj=array.get(i).getAsJsonObject();
			String precessName=obj.get("jobName").getAsString();
			if(precessName.equalsIgnoreCase(MemberQC))
			{
				System.out.println("OK, found the Member QC");
				assign.setActivityId(obj.get("activityId").getAsString());
				assign.setCaseId(obj.get("caseId").getAsString());
				map.put(true, assign);
				check.assignTaskInCoast(assign);
				return map;
			}
		}
		return null;
	}
	
	public static Map<Boolean,assignTask> checkMemberMM(String policyNum, boolean UAT)
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		String result=check.policyInCoast(policyNum);
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		Map<Boolean,assignTask> map=new HashMap();
		for(int i=0;i<array.size();i++)
		{
			JsonObject obj=array.get(i).getAsJsonObject();
			String precessName=obj.get("processName").getAsString();
			if(precessName.equalsIgnoreCase("Member Maintenance"))
			{
				System.out.println("OK, found the Member QC");
				assign.setActivityId(obj.get("activityId").getAsString());
				assign.setCaseId(obj.get("caseId").getAsString());
				map.put(true, assign);
				return map;
			}
		}
		return null;
	}
	
	public static Map<Boolean,assignTask> checkBillGeneration(String policyNum, boolean UAT)
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		
		
		
		Map<Boolean,assignTask> map=null;
		boolean done=false;
		while(!done)
		{
			long start=System.currentTimeMillis();
			while((System.currentTimeMillis()-start)<15000)
			{}
			String result=check.policyInCoast(policyNum);
			System.out.println(result);
			JsonArray array=JsonUtil.formatStringToJsonArray(result);
			System.out.println("Check get the Bill job.");
			for(int i=0;i<array.size();i++)
			{
				JsonObject obj=array.get(i).getAsJsonObject();
				String precessName=obj.get("processName").getAsString();
				if(precessName.equalsIgnoreCase(BillGeneration))
				{
					System.out.println("OK, found the Bill Generation");
					done=true;
					assign.setActivityId(obj.get("activityId").getAsString());
					assign.setCaseId(obj.get("caseId").getAsString());
					map=new HashMap();
					map.put(true, assign);
					
				}
			}
		}
		return map;
	}
	
	
	public static Map<Boolean,assignTask> checkBillGeneration(String policyNum, String url)
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		
		
		
		Map<Boolean,assignTask> map=null;
		boolean done=false;
		while(!done)
		{
			long start=System.currentTimeMillis();
			while((System.currentTimeMillis()-start)<15000)
			{}
			String result=check.policyInCoast(policyNum);
			System.out.println(result);
			JsonArray array=JsonUtil.formatStringToJsonArray(result);
			System.out.println("Check get the Bill job.");
			for(int i=0;i<array.size();i++)
			{
				JsonObject obj=array.get(i).getAsJsonObject();
				String precessName=obj.get("jobName").getAsString();
				if(precessName.equalsIgnoreCase(BillGeneration))
				{
					System.out.println("OK, found the Bill Generation");
					done=true;
					assign.setActivityId(obj.get("activityId").getAsString());
					assign.setCaseId(obj.get("caseId").getAsString());
					map=new HashMap();
					map.put(true, assign);
					
				}
			}
		}
		return map;
	}
	
	public static Map<Boolean,assignTask> checkBillGenerationForChange(String policyNum, String url)
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		
		
		
		Map<Boolean,assignTask> map=null;
		boolean done=false;
		while(!done)
		{
			long start=System.currentTimeMillis();
			while((System.currentTimeMillis()-start)<15000)
			{}
			String result=check.policyInCoast(policyNum);
			System.out.println(result);
			JsonArray array=JsonUtil.formatStringToJsonArray(result);
			System.out.println("Check get the Bill job.");
			for(int i=0;i<array.size();i++)
			{
				JsonObject obj=array.get(i).getAsJsonObject();
				String precessName=obj.get("jobName").getAsString();
				if(precessName.equalsIgnoreCase(BillGenerationForChange))
				{
					System.out.println("OK, found the Bill Generation");
					done=true;
					assign.setActivityId(obj.get("activityId").getAsString());
					assign.setCaseId(obj.get("caseId").getAsString());
					map=new HashMap();
					map.put(true, assign);
					
				}
			}
		}
		return map;
	}
	
	public static Map<Boolean,assignTask> checkRCVTask(String policyNum, boolean UAT, String user)
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		
		Map<Boolean,assignTask> map=null;
		boolean done=false;
		while(!done)
		{
			long start=System.currentTimeMillis();
			while((System.currentTimeMillis()-start)<15000)
			{}
			String result=check.policyInCoast(policyNum);
			System.out.println(result);
			JsonArray array=JsonUtil.formatStringToJsonArray(result);
			System.out.println("Check get the Receiveable job.");
			for(int i=0;i<array.size();i++)
			{
				JsonObject obj=array.get(i).getAsJsonObject();
				String processName=obj.get("processName").getAsString();
				if(processName.equalsIgnoreCase("Receivable(Dummy)") || processName.equalsIgnoreCase("Receivable(pending)"))
				{
					System.out.println("OK, found the Receivable task.");
					done=true;
					assign.setActivityId(obj.get("activityId").getAsString());
					assign.setCaseId(obj.get("caseId").getAsString());
					assign.setUserId(user);
					check.assignTaskInCoast(assign);
					map=new HashMap();
					map.put(true, assign);
					
				}
			}
		}
		return map;
	}
	
	public static Map<String,String> getPolicyInfo(String policyNum) throws Exception
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(false);
		String result=check.policyInCoast(policyNum);
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		if(array.size()>1)
			throw new Exception("The Policy: "+policyNum+" is not in TimeUnit.MILLISECONDS.sleep(200); policy phase, please check.");
		Map<String,String> policyData=new HashMap();
		JsonObject policyInfo=array.get(0).getAsJsonObject();
		policyData.put("caseId", policyInfo.get("caseId").getAsString());
		policyData.put("activityId", policyInfo.get("activityId").getAsString());
		return policyData;
	}
	
	
	public static Map<String,String> getPolicyInfo(String policyNum, boolean UAT) throws Exception
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		String result=check.policyInCoast(policyNum);
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		if(array.size()>1)
			throw new Exception("The Policy: "+policyNum+" is not in create new policy phase, please check.");
		Map<String,String> policyData=new HashMap();
		JsonObject policyInfo=array.get(0).getAsJsonObject();
		policyData.put("caseId", policyInfo.get("caseId").getAsString());
		policyData.put("activityId", policyInfo.get("activityId").getAsString());
		return policyData;
	}
	
	public static Map<String,String> getPolicyInfo(String policyNum, String url) throws Exception
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		String result=check.policyInCoast(policyNum);
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		if(array.size()>1)
			throw new Exception("The Policy: "+policyNum+" is not in create new policy phase, please check.");
		Map<String,String> policyData=new HashMap();
		JsonObject policyInfo=array.get(0).getAsJsonObject();
		policyData.put("caseId", policyInfo.get("caseId").getAsString());
		policyData.put("activityId", policyInfo.get("activityId").getAsString());
		return policyData;
	}
	
	
	
	public static Map<String,String> getPolicyInProcess(String policyNum, String processName,boolean UAT) throws Exception
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		String result=null;
		JsonArray array=null;
		boolean notFound=true;
		JsonObject item=null;
		do
		{
			result=check.policyInCoast(policyNum);
			System.out.println(result);
			array=JsonUtil.formatStringToJsonArray(result);
			TimeUnit.SECONDS.sleep(15);
			for(int i=0;i<array.size();i++)
			{
				item=array.get(i).getAsJsonObject();
				if(item.get("processName").getAsString().equalsIgnoreCase(processName))
				{
					notFound=false;
					break;
				}
			}
		}while(notFound);
		
		
		Map<String,String> policyData=new HashMap();
		
		policyData.put("caseId", item.get("caseId").getAsString());
		policyData.put("activityId", item.get("activityId").getAsString());
		return policyData;
	}
	
	
	public static Map<String,String> getPolicyInProcess(String policyNum, String processName, String url) throws Exception
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		String result=null;
		JsonArray array=null;
		boolean notFound=true;
		JsonObject item=null;
		do
		{
			result=check.policyInCoast(policyNum);
			System.out.println(result);
			array=JsonUtil.formatStringToJsonArray(result);
			TimeUnit.SECONDS.sleep(15);
			for(int i=0;i<array.size();i++)
			{
				item=array.get(i).getAsJsonObject();
				if(item.get("jobName").getAsString().equalsIgnoreCase(processName))
				{
					notFound=false;
					break;
				}
			}
		}while(notFound);
		
		
		Map<String,String> policyData=new HashMap();
		
		policyData.put("caseId", item.get("caseId").getAsString());
		policyData.put("activityId", item.get("activityId").getAsString());
		
		return policyData;
	}
	
	public static List<Map<String,String>> getPolicyInfos(String policyNum, boolean UAT, int size) throws Exception
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		String result=check.getMemberUWJobs(policyNum, size);
		
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		List<Map<String,String>> results=new ArrayList<Map<String,String>>();
		for(int i=0;i<array.size();i++)
		{
			JsonObject policyData=array.get(i).getAsJsonObject();
			if(!policyData.get("processName").getAsString().equalsIgnoreCase("Contract Review"))
			{
				Map<String,String> policyDetail=new HashMap();
				policyDetail.put("caseId", policyData.get("caseId").getAsString());
				policyDetail.put("activityId", policyData.get("activityId").getAsString());
				results.add(policyDetail);
			}
		}
		
		return results;
	}
	
	

	public static List<Map<String,String>> getLetterUWTasks(String policyNum, boolean UAT, int size) throws Exception
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		String result=check.getMemberLetterUWJobs(policyNum, size);
		
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		List<Map<String,String>> results=new ArrayList<Map<String,String>>();
		for(int i=0;i<array.size();i++)
		{
			JsonObject policyData=array.get(i).getAsJsonObject();
			if(policyData.get("processName").getAsString().equalsIgnoreCase("UW Letter Generation"))
			{
				Map<String,String> policyDetail=new HashMap();
				policyDetail.put("caseId", policyData.get("caseId").getAsString());
				policyDetail.put("activityId", policyData.get("activityId").getAsString());
				results.add(policyDetail);
			}
		}
		
		return results;
	}
	
	public static List<Map<String,String>> getLetterUWTasks(String policyNum, String url, int size) throws Exception
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		String result=check.getMemberLetterUWJobs(policyNum, size);
		
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		List<Map<String,String>> results=new ArrayList<Map<String,String>>();
		for(int i=0;i<array.size();i++)
		{
			JsonObject policyData=array.get(i).getAsJsonObject();
			if(policyData.get("jobName").getAsString().equalsIgnoreCase("Generate MUW letter"))
			{
				Map<String,String> policyDetail=new HashMap();
				policyDetail.put("caseId", policyData.get("caseId").getAsString());
				policyDetail.put("activityId", policyData.get("activityId").getAsString());
				results.add(policyDetail);
			}
		}
		
		return results;
	}
	
	
	public static List<Map<String,String>> getMemberUWTasks(String policyNum, boolean UAT, int size) throws Exception
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		String result=check.getMemberUnPendUWJobs(policyNum, size);
		
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		List<Map<String,String>> results=new ArrayList<Map<String,String>>();
		for(int i=0;i<array.size();i++)
		{
			JsonObject policyData=array.get(i).getAsJsonObject();
			if(policyData.get("processName").getAsString().equalsIgnoreCase("Member UW(Pending)"))
			{
				Map<String,String> policyDetail=new HashMap();
				policyDetail.put("caseId", policyData.get("caseId").getAsString());
				policyDetail.put("activityId", policyData.get("activityId").getAsString());
				results.add(policyDetail);
			}
		}
		
		return results;
	}
	
	public static List<Map<String,String>> getMemberUWTasks(String policyNum, String url, int size) throws Exception
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		String result=check.getMemberUnPendUWJobs(policyNum, size);
		
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		List<Map<String,String>> results=new ArrayList<Map<String,String>>();
		for(int i=0;i<array.size();i++)
		{
			JsonObject policyData=array.get(i).getAsJsonObject();
			if(policyData.get("jobName").getAsString().equalsIgnoreCase("Member UW(Pending)"))
			{
				Map<String,String> policyDetail=new HashMap();
				policyDetail.put("caseId", policyData.get("caseId").getAsString());
				policyDetail.put("activityId", policyData.get("activityId").getAsString());
				results.add(policyDetail);
			}
		}
		
		return results;
	}
	
	
	public static List<Map<String,String>> getMemberUnPendUWTasks(String policyNum, boolean UAT, int size) throws Exception
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		String result=check.getMemberUWJobs(policyNum, size);
		
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		List<Map<String,String>> results=new ArrayList<Map<String,String>>();
		for(int i=0;i<array.size();i++)
		{
			JsonObject policyData=array.get(i).getAsJsonObject();
			if(policyData.get("processName").getAsString().equalsIgnoreCase("Member UW(Un-Pend)"))
			{
				Map<String,String> policyDetail=new HashMap();
				policyDetail.put("caseId", policyData.get("caseId").getAsString());
				policyDetail.put("activityId", policyData.get("activityId").getAsString());
				results.add(policyDetail);
			}
		}
		
		return results;
	}
	
	public static List<Map<String,String>> getMemberUnPendUWTasks(String policyNum, String url, int size) throws Exception
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		String result=check.getMemberUWJobs(policyNum, size);
		
		System.out.println(result);
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		List<Map<String,String>> results=new ArrayList<Map<String,String>>();
		for(int i=0;i<array.size();i++)
		{
			JsonObject policyData=array.get(i).getAsJsonObject();
			if(policyData.get("jobName").getAsString().equalsIgnoreCase("Member UW(Un-Pend)"))
			{
				Map<String,String> policyDetail=new HashMap();
				policyDetail.put("caseId", policyData.get("caseId").getAsString());
				policyDetail.put("activityId", policyData.get("activityId").getAsString());
				results.add(policyDetail);
			}
		}
		
		return results;
	}
	
	public static boolean assignTaskTo(String userId,String policyNo) throws Exception
	{
		String result="";
		Map<String,String> policyData=getPolicyInfo(policyNo);
		if(userId!=null && userId.length()!=0)
		{
			assign.setUserId(userId);
			assign.setCaseId(policyData.get("caseId"));
			assign.setActivityId(policyData.get("activityId"));
		}
		
		
		CheckPolicyInCoast check=new CheckPolicyInCoast(false);
		if(assign!=null)
		{
			System.out.println(assign+" assignTask");
			result=check.assignTaskInCoast(assign);
		}
		System.out.println(result);
		return true;
	}
	
	
	public static boolean assignMemberTaskTo(String userId,String policyNo, List<Map<String,String>> policyDatas, Boolean UAT) throws Exception
	{
		String result="";
		
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		
		for(Map<String,String> policyData : policyDatas)
		{
			if(userId!=null && userId.length()!=0)
			{
				assign.setUserId(userId);
				assign.setCaseId(policyData.get("caseId"));
				assign.setActivityId(policyData.get("activityId"));
			}
			
			if(assign!=null)
			{
				System.out.println(assign+" assignTask");
				result=check.assignTaskInCoast(assign);
			}
			
			System.out.println(result);
		}

		return true;
	}
	
	public static boolean assignMemberTaskTo(String userId,String policyNo, List<Map<String,String>> policyDatas, String url) throws Exception
	{
		String result="";
		
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		
		for(Map<String,String> policyData : policyDatas)
		{
			if(userId!=null && userId.length()!=0)
			{
				assign.setUserId(userId);
				assign.setCaseId(policyData.get("caseId"));
				assign.setActivityId(policyData.get("activityId"));
			}
			
			if(assign!=null)
			{
				System.out.println(assign+" assignTask");
				result=check.assignTaskInCoast(assign);
			}
			
			System.out.println(result);
		}

		return true;
	}
	
	
	public static boolean assignTaskTo(String userId,String policyNo, boolean UAT) throws Exception
	{
		String result="";
		Map<String,String> policyData=getPolicyInfo(policyNo,UAT);
		if(userId!=null && userId.length()!=0)
		{
			assign.setUserId(userId);
			assign.setCaseId(policyData.get("caseId"));
			assign.setActivityId(policyData.get("activityId"));
		}
		
		
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		if(assign!=null)
		{
			System.out.println(assign+" assignTask");
			result=check.assignTaskInCoast(assign);
			
		}
		System.out.println(result);
		return true;
	}
	
	
	public static boolean assignTaskTo(String userId,String policyNo, String url) throws Exception
	{
		String result="";
		Map<String,String> policyData=getPolicyInfo(policyNo,url);
		if(userId!=null && userId.length()!=0)
		{
			assign.setUserId(userId);
			assign.setCaseId(policyData.get("caseId"));
			assign.setActivityId(policyData.get("activityId"));
		}
		
		
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		if(assign!=null)
		{
			System.out.println(assign+" assignTask");
			result=check.assignTaskInCoast(assign);
			
		}
		System.out.println(result);
		return true;
	}
	
	
	public static boolean assignTaskTo(String userId,String policyNo, String processName,boolean UAT) throws Exception
	{
		
		String result="";
		Map<String,String> policyData=getPolicyInProcess(policyNo,processName, UAT);
		if(userId!=null && userId.length()!=0)
		{
			assign.setUserId(userId);
			assign.setCaseId(policyData.get("caseId"));
			assign.setActivityId(policyData.get("activityId"));
		}
		
		
		CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
		if(assign!=null)
		{
			System.out.println(assign+" assignTask");
			result=check.assignTaskInCoast(assign);
			
		}
		System.out.println(result);
		return true;
	}
	
	
	public static boolean assignTaskTo(String userId,String policyNo, String processName,String url) throws Exception
	{
		
		String result="";
		System.out.println("assignTask PolicyNumber: "+policyNo);
		Map<String,String> policyData=getPolicyInProcess(policyNo,processName, url);
		if(userId!=null && userId.length()!=0)
		{
			assign.setUserId(userId);
			assign.setCaseId(policyData.get("caseId"));
			assign.setActivityId(policyData.get("activityId"));
			if(processName.equalsIgnoreCase("Upload Member"))
				assign.setProcessStep("");
			
		}
		
		
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		if(assign!=null)
		{
			System.out.println(assign+" assignTask");
			result=check.assignTaskInCoast(assign);
			
		}
		System.out.println(result);
		return true;
	}
	
	public static Map<Boolean,assignTask> checkMemberBillGeneration(String policyNum, String url)
	{
		CheckPolicyInCoast check=new CheckPolicyInCoast(url);
		
		
		
		Map<Boolean,assignTask> map=null;
		boolean done=false;
		while(!done)
		{
			long start=System.currentTimeMillis();
			while((System.currentTimeMillis()-start)<15000)
			{}
			String result=check.policyInCoast(policyNum);
			System.out.println(result);
			JsonArray array=JsonUtil.formatStringToJsonArray(result);
			System.out.println("Check get the Bill job.");
			System.out.println("array.size():"+array.size());
			for(int i=0;i<array.size();i++)
			{
				JsonObject obj=array.get(i).getAsJsonObject();
				String precessName=obj.get("jobName").getAsString();
				System.out.println("precessName--->"+precessName);
				if(precessName.equalsIgnoreCase("Request Bill for Member Change"))
				{
					System.out.println("OK, found the Bill Generation");
					done=true;
					assign.setActivityId(obj.get("activityId").getAsString());
					assign.setCaseId(obj.get("caseId").getAsString());
					map=new HashMap();
					map.put(true, assign);
					
				}
			}
		}
		return map;
	} 
	
	public static void main(String ...args) throws Exception
	{
	
		assignTaskTo("ASNPHKS","0825000073","Upload Member","https://10.65.5.50:9444/COAST_FE_PRESIT0531/homepage/login");
		//System.out.println(URLEncoder.encode("{\"params\":{\"policyNo\":\"0913300067\",\"subOfficeCode\":\"100\",\"billType\":\"I\",\"billStatus\":\"\",\"outstandingBillsOnly\":\"N\",\"billNo\":\"\",\"billperiodFrom\":\"2017-01-01\",\"billperiodTo\":\"2017-12-31\",\"keyWord\":\"\",\"certNoFrom\":\"\",\"certNoTo\":\"\",\"fullData\":\"N\",\"isExport\":0,\"currentPage\":1,\"pageSize\":10,\"orderBy\":\"\",\"sord\":\"\"}}"));
		
		/*Map<Boolean, assignTask> result=checkPolicyProcess("0343700191",true);
		if(result!=null)
		{
			System.out.println("OK");
		}*/
		
		//assignTaskTo("ASNPH9Q","TM00000428");
		/*String result="[{\"id\":null,\"urgentFlag\":\"N\",\"policyNo\":\"T393700235\",\"policyName\":\"\",\"formName\":\"Policy Maintenance\",\"submissionDate\":1484041832000,\"requestNo\":\"116390\",\"businessChannel\":\"Agent\",\"jobDueDate\":1484041837000,\"getJobDate\":null,\"pendingDueDate\":null,\"pendingDate\":null,\"pendingReason\":null,\"completedDate\":null,\"status\":null,\"userId\":null,\"caseId\":\"116390\",\"formId\":null,\"activityCode\":\"NB_002\",\"processStep\":null,\"formDataId\":null,\"activityId\":\"2.3.45926.45926\",\"flowCode\":null,\"typeCount\":null,\"processName\":\"Policy Master QC\",\"reqType\":\"NBP\",\"mainCaseId\":\"null\",\"mainReqType\":\"null\",\"requestId\":null,\"recordStatus\":null,\"memberShipId\":null,\"jobStatus\":\"UNASSIGNED\",\"handledBy\":\"\",\"wfStepEntryTime\":1485067849000,\"sladueDate\":1485069650000}]";
		JsonArray array=JsonUtil.formatStringToJsonArray(result);
		Map<Boolean,assignTask> map=new HashMap();
		for(int i=0;i<array.size();i++)
		{
			JsonObject obj=array.get(i).getAsJsonObject();
			String precessName=obj.get("processName").getAsString();
			if(precessName.equalsIgnoreCase(PolicyMasterQC))
			{
				assign.setActivityId(obj.get("activityId").getAsString());
				assign.setCaseId(obj.get("caseId").getAsString());
				map.put(true, assign);
			}
		}
		
		Set<Entry<Boolean,assignTask>> entries= map.entrySet();
		for(Entry<Boolean,assignTask> entry : entries)
		{
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		assignTaskTo(null);*/
		
	}
}
