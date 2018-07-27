package util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.aia.coast.entity.assignTask;
import com.aia.cs.ipos.entity.Tasker;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public final class CheckPolicyInCoast {

	private boolean UAT;
	Map<String,String> coastURL=new HashMap<String,String>();
	private String url=null;
	
	public CheckPolicyInCoast(boolean UAT)
	{
		this.UAT=UAT;
		coastURL.put("UAT", "https://thcoast-uat.aia.biz/COAST_FE/task/taskOperate/getTaskForLeader");
		coastURL.put("SIT", "http://10.65.5.44:9085/COAST_FE_SIT/task/taskOperate/getTaskForLeader");
	}
	
	public CheckPolicyInCoast(String url)
	{
		String queryJob="task/taskOperate/getTaskForLeader";
		this.url=url.substring(0, url.indexOf("home"))+queryJob;
		System.out.println("Query Job: "+this.url);
	}
	
	public String checkPolicyAppearInCoast(String policyNum)
	{
		String result=null;
		Tasker task=new Tasker();
		task.setPOLICY_NUM(policyNum);
		task.setPOLICY_NAME("");
		task.setLOGINID_USER("");
		task.setPAGE_NUM(1);
		task.setROW_TOTAL(1000);
		task.setWFWORKSTEPNAME("");
		Map<String,String> header=new HashMap<String,String>();
		
		long fiveMins=60*10;
		long start=0;
		long startTime=System.currentTimeMillis();
		
		//String cookie=UAT? "" : SoapBodyUtil.getCookieOfUAT();
		String cookie=SoapBodyUtil.getCookieOfAuthorization(url);
		
		boolean found=false;
		while(!found && start<fiveMins)
		{
			start=(System.currentTimeMillis()-startTime)/1000;
			header.put("Cookie", cookie);
			if(url!=null)
			{
				
				try{
					if(url.contains("https"))
					{
						result=HttpsUtil.doPostRequest(url, header, JsonUtil.formatObjectToJson(task));
					}else
					{
						result=HttpUtil.doPost(url, header, JsonUtil.formatObjectToJson(task));
					}
					if(result.contains(policyNum))
					{
						found=true;
						result="Found the "+policyNum+ " in UAT, you can check from Coast Web Site.";
						break;
					}else
					{
						result="Cound not found the in UAT"+policyNum+" within 5 minutes.";
					}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			else if(UAT)
			{
				try
				{
					result=HttpUtil.doPost(coastURL.get("UAT"),header, JsonUtil.formatObjectToJson(task));
				}catch(Exception e)
				{
					continue;
					//ignore
				}
				if(result.contains(policyNum))
				{
					found=true;
					result="Found the "+policyNum+ " in UAT, you can check from Coast Web Site.";
					break;
				}else
				{
					result="Cound not found the in UAT"+policyNum+" within 5 minutes.";
				}
			}else
			{
				try{
					result=HttpUtil.doPost(coastURL.get("SIT"),header, JsonUtil.formatObjectToJson(task));
				}catch(Exception e)
				{
					continue;
					//ignore
				}
				if(result.contains(policyNum))
				{
					found=true;
					result="Found the "+policyNum+" in SIT, you can check from Coast Web Site.";
					break;
				}else
				{
					result="Cound not found the in SIT"+policyNum+" within 5 minutes.";
				}
			}
			try {
				Thread.sleep(20000l);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			System.out.println("Retry for another time for the Policy: "+policyNum);
		}
		return result;
	}
	
	
	public String policyInCoast(String policyNum)
	{
		String result=null;
		Tasker task=new Tasker();
		task.setPOLICY_NUM(policyNum);
		task.setPOLICY_NAME("");
		task.setLOGINID_USER("");
		task.setPAGE_NUM(1);
		task.setROW_TOTAL(1000);
		task.setWFWORKSTEPNAME("");
		Map<String,String> header=new HashMap<String,String>();
		
		long fiveMins=60*5;
		long start=0;
		long startTime=System.currentTimeMillis();
		String cookie=SoapBodyUtil.getCookieOfAuthorization(url);
		header.put("Cookie", cookie);
		boolean found=false;
		System.out.println("Check Policy url: "+url);
		while(!found && start<fiveMins)
		{
			start=(System.currentTimeMillis()-startTime)/1000;
			
			if(url!=null)
			{
				try{
					
					System.out.println("Request body: "+JsonUtil.formatObjectToJson(task));
					if(url.contains("https"))
					{
						result=HttpsUtil.doPostRequest(url, header, JsonUtil.formatObjectToJson(task));
					}else
					{
						result=HttpUtil.doPost(url, header, JsonUtil.formatObjectToJson(task));
					}
					System.out.println("Result: "+result);
					JsonArray policyCaseArray=JsonUtil.formatStringToJsonArray(result);
					for(int i=0;i<policyCaseArray.size();i++)
					{
						JsonObject obj=policyCaseArray.get(i).getAsJsonObject();
						String precessName=obj.get("jobName").getAsString();
						
						if(precessName.equalsIgnoreCase("Member Update"))
						{
							continue;
						}else
						{
							found=true;
							System.out.println("Found the "+policyNum+ " in Coast, you can check from Coast Web Site.");
							System.out.println("Result: "+result);
							break;
						}
					}
					
				
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			
			if(!found)
			{
				System.out.println("Not found the in Coast: "+policyNum+", would give up when reach 10 minutes.");
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}
			System.out.println("Retry for another time for the Policy: "+policyNum);
		}
		return result;
	}
	
	
	public String getMemberUWJobs(String policyNum, int size)
	{
		String result=null;
		Tasker task=new Tasker();
		task.setPOLICY_NUM(policyNum);
		task.setPOLICY_NAME("");
		task.setLOGINID_USER("");
		task.setPAGE_NUM(1);
		task.setROW_TOTAL(1000);
		task.setWFWORKSTEPNAME("");
		Map<String,String> header=new HashMap<String,String>();
		
		long fiveMins=60*10;
		long start=0;
		long startTime=System.currentTimeMillis();
		String cookie=SoapBodyUtil.getCookieOfAuthorization(url);
		header.put("Cookie", cookie);
		boolean found=false;
		while(!found && start<fiveMins)
		{
			start=(System.currentTimeMillis()-startTime)/1000;
			if(url!=null)
			{
				
				
				try
				{
					if(url.contains("https"))
						result=HttpsUtil.doPostRequest(url, header, JsonUtil.formatObjectToJson(task));
					else
						result=HttpUtil.doPost(url,header, JsonUtil.formatObjectToJson(task));
				}catch(Exception e)
				{
					continue;
					//ignore
				}
				JsonArray array=JsonUtil.formatStringToJsonArray(result);
				if(result.contains(policyNum))
				{
					int uwCount=0;
					for(JsonElement obj : array)
					{
						if(obj.getAsJsonObject().get("processName").getAsString().equalsIgnoreCase("Member UW(Un-Pend)"))
						{
							uwCount++;
						}
					}
					if(uwCount>=size)
					{
						found=true;
						break;
					}
				}else
				{
					result="Cound not found the in Coast"+policyNum+" within 5 minutes.";
				}
			}
			try {
				Thread.sleep(20000l);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			System.out.println("Retry for another time for the Policy: "+policyNum);
		}
		return result;
	}
	
	public String getMemberUnPendUWJobs(String policyNum, int size)
	{
		String result=null;
		Tasker task=new Tasker();
		task.setPOLICY_NUM(policyNum);
		task.setPOLICY_NAME("");
		task.setLOGINID_USER("");
		task.setPAGE_NUM(1);
		task.setROW_TOTAL(1000);
		task.setWFWORKSTEPNAME("");
		Map<String,String> header=new HashMap<String,String>();
		
		long fiveMins=60*10;
		long start=0;
		long startTime=System.currentTimeMillis();
		String cookie=SoapBodyUtil.getCookieOfAuthorization(url);
		header.put("Cookie", cookie);
		boolean found=false;
		while(!found && start<fiveMins)
		{
			start=(System.currentTimeMillis()-startTime)/1000;
			if(url!=null)
			{
				
				
				try
				{
					if(url.contains("https"))
						result=HttpsUtil.doPostRequest(url, header, JsonUtil.formatObjectToJson(task));
					else
						result=HttpUtil.doPost(url,header, JsonUtil.formatObjectToJson(task));
					System.out.println("Check UW Job Result: "+result);
				}catch(Exception e)
				{
					continue;
					//ignore
				}
				JsonArray array=JsonUtil.formatStringToJsonArray(result);
				if(result.contains(policyNum))
				{
					int uwCount=0;
					for(JsonElement obj : array)
					{
						if(obj.getAsJsonObject().get("processName").getAsString().equalsIgnoreCase("Member UW(Pending)"))
						{
							uwCount++;
						}
					}
					if(uwCount>=size)
					{
						found=true;
						break;
					}
				}else
				{
					result="Cound not found the in UAT"+policyNum+" within 5 minutes.";
				}
			}
			try {
				Thread.sleep(20000l);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			System.out.println("Retry for another time for the Policy: "+policyNum);
		}
		return result;
	}
	
	public String getMemberLetterUWJobs(String policyNum, int size)
	{
		String result=null;
		Tasker task=new Tasker();
		task.setPOLICY_NUM(policyNum);
		task.setPOLICY_NAME("");
		task.setLOGINID_USER("");
		task.setPAGE_NUM(1);
		task.setROW_TOTAL(1000);
		task.setWFWORKSTEPNAME("");
		Map<String,String> header=new HashMap<String,String>();
		
		long tenMins=60*10;
		long start=0;
		long startTime=System.currentTimeMillis();
		String cookie=SoapBodyUtil.getCookieOfAuthorization(url);
		header.put("Cookie", cookie);
		boolean found=false;
		while(!found && start<tenMins)
		{
			start=(System.currentTimeMillis()-startTime)/1000;
			if(url!=null)
			{
				
				
				try
				{
					if(url.contains("https"))
						result=HttpsUtil.doPostRequest(url, header, JsonUtil.formatObjectToJson(task));
					else
						result=HttpUtil.doPost(url,header, JsonUtil.formatObjectToJson(task));
					System.out.println("Check UW Job Result: "+result);
				}catch(Exception e)
				{
					continue;
					//ignore
				}
				JsonArray array=JsonUtil.formatStringToJsonArray(result);
				if(result.contains(policyNum))
				{
					int letterCount=0;
					for(JsonElement obj : array)
					{
						if(obj.getAsJsonObject().get("processName").getAsString().equalsIgnoreCase("UW Letter Generation"))
						{
							letterCount++;
						}
					}
					if(letterCount>=size)
					{
						found=true;
						break;
					}
				}else
				{
					result="Cound not found the in UAT"+policyNum+" within 5 minutes.";
				}
			}
			try {
				Thread.sleep(20000l);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			System.out.println("Retry for another time for the Policy: "+policyNum);
		}
		return result;
	}
	
	
	
	
	
	public String assignTaskInCoast(assignTask assign)
	{
		String result=null;
		
		Map<String,String> header=new HashMap<String,String>();
		
	
		long startTime=System.currentTimeMillis();
		String cookie=SoapBodyUtil.getCookieOfAuthorization(url);
		boolean found=false;
		header.put("Cookie", cookie);
		Map<String,String> params=new HashMap();
		params.put("id", assign.getId()!=null ? assign.getId() : "");
		params.put("userId", assign.getUserId());
		params.put("caseId", assign.getCaseId());
		params.put("processStep", assign.getProcessStep());
		params.put("activityId", assign.getActivityId());
		
		String assignUrl=url.substring(0, url.indexOf("getTaskForLeader"))+"assgintask";
		System.out.println("Assign Task Url: "+assignUrl);
		if(url!=null)
		{
			if(assignUrl.contains("https"))
				result=HttpsUtil.doPostFormReq(assignUrl, header, params, "UTF-8");
			else
				result=HttpUtil.doFormPost(assignUrl, header, params);
			System.out.println("Assign Task result: "+result);
			/*while(!result.equalsIgnoreCase("200"))
			{
				result=HttpsUtil.doPostFormReq(assignUrl, header, params, "UTF-8");
				System.out.println("Retry: Assign Task result: "+result);
			}*/
		}
		return result;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//CheckPolicyInCoast cc=new CheckPolicyInCoast(true);
		//System.out.println(cc.checkPolicyAppearInCoast(SoapBodyUtil.getCookieOfUAT(), "0000106109"));
		
		String url="http://10.65.5.44:9082/COAST_FE_PRESIT/homepage/login";
		System.out.println(url.substring(0, url.indexOf("home")));
			
		/*Tasker task=new Tasker();
		task.setPOLICY_NUM("T393700235");
		task.setPOLICY_NAME("");
		task.setLOGINID_USER("");
		task.setPAGE_NUM(1);
		task.setROW_TOTAL(1000);
		task.setWFWORKSTEPNAME("");
		Map<String,String> header=new HashMap<String,String>();
		
		CheckPolicyInCoast cpi=new CheckPolicyInCoast(false);
		String result=cpi.policyInCoast("T393700235");
		System.out.println(result);
		*/
		//System.out.println(HttpUtil.doPost("http://10.65.5.44:9085/COAST_FE_SIT/task/taskOperate/getTaskForLeader", header, JsonUtil.formatObjectToJson(task)));
	}

}
