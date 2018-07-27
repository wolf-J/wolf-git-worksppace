package com.aia.coast.pageobject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;

public class MemberUW extends BasePage {

	Locator GenMedicalReq=new Locator("GenMedicalReq");
	Locator editUWRequirement=new Locator("editUWRequirement");
	Locator receiveDate=new Locator("receiveDate");
	Locator updateUWRequirement=new Locator("updateUWRequirement");
	Locator DecisionInfoEdits=new Locator("DecisionInfoEdits");
	Locator UWStatus=new Locator("UWStatus");
	Locator loading=new Locator("loading");
	Locator loadingAni=new Locator("loadingAni");
	Locator UWRequirementInfo=new Locator("UWRequirementInfo");
	Locator UnPending=new Locator("UnPending");
	
	Locator goToSection=new Locator("goToSection");
	Locator pendingInfoPart=new Locator("pendingInfoPart");
	
	Locator pendingInfoEdits=new Locator("pendingInfoEdits");
	Locator pendingInfoStatus=new Locator("pendingInfoStatus");
	Locator pendingInfoStatusArrow=new Locator("pendingInfoStatusArrow");
	Locator unPendingRemark=new Locator("unPendingRemark");
	Locator updateUnPendingInfo=new Locator("updateUnPendingInfo");
	
	public void goToPendingInfoSection() throws Exception
	{
		TimeUnit.SECONDS.sleep(3);
		click(goToSection);
		TimeUnit.SECONDS.sleep(1);
		click(pendingInfoPart);
		click(goToSection);
	}
	
	public MemberUW(WebDriver driver) throws Exception
	{
		super(driver);
		
	}
	
	public static String today;
	
	static{
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-YYYY");
		today=sdf.format(new Date());
	}
	
	public void assignJobForUW(String policyNo, String userId, boolean UAT, int size) throws Exception
	{
		List<Map<String,String>> results=HomePage.getLetterUWTasks(policyNo, UAT, size);
		
		while(results.size()<size)
		{
			TimeUnit.SECONDS.sleep(10);
			results=HomePage.getLetterUWTasks(policyNo, UAT, size);
		}
			
			
		results.stream().forEach( item -> {
			System.out.println("caseId: "+item.get("caseId")+", activityId: "+item.get("activityId"));
		});
		
		HomePage.assignMemberTaskTo(userId, policyNo, results, UAT);
	}
	
	
	public void assignJobForUW(String policyNo, String userId, String url, int size) throws Exception
	{
		List<Map<String,String>> results=HomePage.getLetterUWTasks(policyNo, url, size);
		
		while(results.size()<size)
		{
			TimeUnit.SECONDS.sleep(10);
			results=HomePage.getLetterUWTasks(policyNo, url, size);
		}
			
			
		results.stream().forEach( item -> {
			System.out.println("caseId: "+item.get("caseId")+", activityId: "+item.get("activityId"));
		});
		
		HomePage.assignMemberTaskTo(userId, policyNo, results, url);
	}
	
	public void assignJobForMemberUW(String policyNo, String userId, boolean UAT, int size) throws Exception
	{
		List<Map<String,String>> results=HomePage.getMemberUWTasks(policyNo, UAT, size);
		
		while(results.size()<size)
		{
			TimeUnit.SECONDS.sleep(10);
			results=HomePage.getMemberUWTasks(policyNo, UAT, size);
		}
			
			
		results.stream().forEach( item -> {
			System.out.println("caseId: "+item.get("caseId")+", activityId: "+item.get("activityId"));
		});
		
		HomePage.assignMemberTaskTo(userId, policyNo, results, UAT);
	}
	
	
	public void assignJobForMemberUW(String policyNo, String userId, String url, int size) throws Exception
	{
		List<Map<String,String>> results=HomePage.getMemberUWTasks(policyNo, url, size);
		
		while(results.size()<size)
		{
			TimeUnit.SECONDS.sleep(10);
			results=HomePage.getMemberUWTasks(policyNo, url, size);
		}
			
			
		results.stream().forEach( item -> {
			System.out.println("caseId: "+item.get("caseId")+", activityId: "+item.get("activityId"));
		});
		
		HomePage.assignMemberTaskTo(userId, policyNo, results, url);
	}
	
	
	public void assignJobForMemberUWUnPend(String policyNo, String userId, boolean UAT, int size) throws Exception
	{
		List<Map<String,String>> results=HomePage.getMemberUnPendUWTasks(policyNo, UAT, size);
		
		while(results.size()<size)
		{
			TimeUnit.SECONDS.sleep(10);
			results=HomePage.getMemberUnPendUWTasks(policyNo, UAT, size);
		}
			
			
		results.stream().forEach( item -> {
			System.out.println("caseId: "+item.get("caseId")+", activityId: "+item.get("activityId"));
		});
		
		HomePage.assignMemberTaskTo(userId, policyNo, results, UAT);
	}
	
	
	public void assignJobForMemberUWUnPend(String policyNo, String userId, String url, int size) throws Exception
	{
		List<Map<String,String>> results=HomePage.getMemberUnPendUWTasks(policyNo, url, size);
		
		while(results.size()<size)
		{
			TimeUnit.SECONDS.sleep(10);
			results=HomePage.getMemberUnPendUWTasks(policyNo, url, size);
		}
			
			
		results.stream().forEach( item -> {
			System.out.println("caseId: "+item.get("caseId")+", activityId: "+item.get("activityId"));
		});
		
		HomePage.assignMemberTaskTo(userId, policyNo, results, url);
	}
	
	public void finishLetterUW() throws Exception
	{
		finishLoading();
		pageDown();
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingAni))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingAni))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(GenMedicalReq);
		TimeUnit.SECONDS.sleep(2);
		pageDown();
		List<WebElement> requireInfo=findElements(UWRequirementInfo);
		if(requireInfo.isEmpty())
		{
			throw new Exception("The RequireInfo is not loaded!!!");
		}
	}

	public void unPendingMemberUW() throws Exception
	{
		finishLoading();
		TimeUnit.SECONDS.sleep(1);
		finishLoading();
		goToPendingInfoSection();
		
		List<WebElement> pendingInfoLists=findElements(pendingInfoEdits);
		for(int i=0;i<pendingInfoLists.size();i++)
		{
			pendingInfoLists=findElements(pendingInfoEdits);
			TimeUnit.SECONDS.sleep(1);
			finishLoading();
			pendingInfoLists.get(i).click();
			TimeUnit.SECONDS.sleep(1);
			pageDown();
			clearType(pendingInfoStatus,"Close");
			TimeUnit.MILLISECONDS.sleep(400);
			click(pendingInfoStatusArrow);
			clearType(unPendingRemark,"Auto Testing Team");
			
			click(updateUnPendingInfo);
		}
		click(UnPending);
		
	}
	
	public void finishMemberUW() throws Exception
	{
		finishLoading();
		pageDown();
		click(editUWRequirement);
		clearType(receiveDate,today);
		click(updateUWRequirement);
		
		List<WebElement> edits=findElements(DecisionInfoEdits);
		
		System.out.println("Edits Length: "+edits.size());
		
		int editsLen=edits.size();
		
		for(int i=0; i<editsLen;i++)
		{
			edits=findElements(DecisionInfoEdits);
			TimeUnit.MILLISECONDS.sleep(300);
			
			
			WebElement edit=edits.get(i);
			
			System.out.println("Start edit Descition Info field.");
			edit.click();
			TimeUnit.MILLISECONDS.sleep(200);
			clearType(UWStatus,"A - Approved");
			TimeUnit.MILLISECONDS.sleep(200);
			edit=findElements(DecisionInfoEdits).get(i);
			edit.click();
			TimeUnit.MILLISECONDS.sleep(200);
			System.out.println("Finish edit Descition Info field.");
			
		}
		
		
	}
	
	private void updateDecisionStatus(WebElement edit) throws Exception
	{
		try
		{
			performArrowDown();
			System.out.println("Start edit Descition Info field.");
			edit.click();
			TimeUnit.MILLISECONDS.sleep(200);
			clearType(UWStatus,"A - Approved");
			TimeUnit.MILLISECONDS.sleep(200);
			edit.click();
			TimeUnit.MILLISECONDS.sleep(200);
			System.out.println("Finish edit Descition Info field.");
		}catch(StaleElementReferenceException e)
		{
			System.out.println(e.getMessage());
			System.out.println(edit.toString());
			
			updateDecisionStatus(edit);
		}
	}
	
	public void finishLoading() throws Exception
	{
		while(!elementNotDisplay(loading))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*List<Map<String,String>> results=HomePage.getPolicyInfos("2336690022",false,6);
		
		results.stream().forEach( item -> {
			System.out.println("Key: "+item.get("caseId")+", "+item.get("activityId"));
		});
		
		HomePage.assignMemberTaskTo("ASNPH9K", "2336690022", results, false);
		*/
		
		System.out.println(today);
		
	}
	
	
	

}
