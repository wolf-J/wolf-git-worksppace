package com.aia.coast.pageobject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aia.coast.entity.Member;
import com.aia.coast.test.AbstractCoastRT;
import com.aia.coast.test.IndivPolicy;
import com.aia.coast.testcase.CaseUtil;
import com.aia.coast.testcase.CoastCase;
import com.aia.ui.utils.BasePage;
import com.aia.ui.utils.Locator;

import util.Checker;
import util.ExcelUtil;
import util.ReadExcelUtil;

public class MemberMaintenancePage extends BasePage{

	WebDriver driver;
	Locator firstJob=new Locator("firstJob",50);
	Locator openTask=new Locator("openTask",5);
	Locator userinfo=new Locator("userinfo");
	Locator logout=new Locator("logout");
	//Wait loading of tasks
	Locator finishLoading=new Locator("finishLoading");
	Locator loadingImg=new Locator("loadingImg");
	Locator loadingAniX=new Locator("loadingAniX");
	
	Locator clientCode=new Locator("clientCode");
	Locator policyarrow=new Locator("policyarrow",5);
	Locator filterspan=new Locator("filterspan",5);
	Locator filterinput=new Locator("filterinput",5);
	Locator filterbutton=new Locator("filterbutton",5);
	Locator fileUpload=new Locator("fileUpload");
	
	Locator member_file_upload_policyNo_input=new Locator("member_file_upload_policyNo_input",5);
	Locator member_file_upload_fileFormat_input=new Locator("member_file_upload_fileFormat_input",5);
	Locator member_file_upload_memberFileType_input=new Locator("member_file_upload_memberFileType_input",5);
	Locator File_Upload=new Locator("File_Upload",5);
	Locator member_file_upload_fileUpload=new Locator("member_file_upload_fileUpload",5);
	Locator memberFileCheckBtn=new Locator("memberFileCheckBtn");
	Locator Upload=new Locator("Upload");
	Locator Refresh=new Locator("Refresh");
	Locator Status=new Locator("Status");
	Locator Results=new Locator("Results");
	Locator btnSubmit=new Locator("btnSubmit");
	Locator TrailRun=new Locator("TrailRun");
	Locator loadMemberData=new Locator("loadMemberData");
	Locator memberData=new Locator("memberData");
	Locator continueBill=new Locator("continueBill");
	
	
	//Manual Input
	Locator MemberFrame=new Locator("MemberFrame");
	Locator createMember=new Locator("createMember");
	Locator subOffice=new Locator("subOffice");
	Locator title=new Locator("title");
	Locator firstName=new Locator("firstName");
	Locator lastName=new Locator("lastName");
	Locator DOB=new Locator("DOB");
	Locator martialStatus=new Locator("martialStatus");
	Locator employeeNumber=new Locator("employeeNumber");
	Locator employDate=new Locator("employDate");
	Locator initialEffDate=new Locator("initialEffDate");
	Locator effectiveDate=new Locator("effectiveDate");
	Locator salary=new Locator("salary");
	Locator addCoverageScheme=new Locator("addCoverageScheme");
	Locator schemeCode=new Locator("schemeCode");
	Locator search=new Locator("search");
	Locator planList=new Locator("planList");
	Locator schemeDropDown=new Locator("schemeDropDown");
	Locator schemeItem=new Locator("schemeItem");
	Locator select=new Locator("select");
	Locator save=new Locator("save");
	Locator testId=new Locator("testId");
	
	private String memUploadFile;
	
	public MemberMaintenancePage(WebDriver driver) throws Exception {
		super(driver);
	}
	
	public void waitLoadingOfTask() throws Exception
	{
		TimeUnit.SECONDS.sleep(15);
		// Kris Commence
		//Please add your waiting function here, which can work on your ENV
	}
	
	public void waitLoadingOfPage() throws Exception
	{
		TimeUnit.SECONDS.sleep(10);
		// Kris Commence
		//Please add your waiting function here, which can work on your ENV
	}
	
	public void filterpolicy(String polNo) throws Exception
	{
		click(policyarrow);
		TimeUnit.SECONDS.sleep(1);
		click(filterspan);
		TimeUnit.SECONDS.sleep(1);
		clearType(filterinput,polNo);
		TimeUnit.SECONDS.sleep(1);
		click(filterbutton);
		TimeUnit.SECONDS.sleep(1);
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
		
		click(userinfo);
		TimeUnit.MILLISECONDS.sleep(300);
		click(logout);
	}
	
	public void waitMemberCompleteLoading() throws Exception
	{
		WebElement cCode=getElement(clientCode);
		String clientC=cCode.getAttribute("value");
		while(clientC.length()<2)
		{
			TimeUnit.SECONDS.sleep(2);
			cCode=getElement(clientCode);
			clientC=cCode.getAttribute("value");
		}
		System.out.println("Client Code: "+clientC);
	}
	
	public void manualInputMember(Member member) throws Exception
	{
		if(Checker.isNull(member.getTitle()))
			return;
	
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		scrollToElement(createMember);
		TimeUnit.SECONDS.sleep(1);
		click(createMember);
		
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
		
		this.switchToFrame(MemberFrame);
		clearType(this.subOffice,member.getSuboffice());
		clearType(this.title,member.getTitle());
		clearType(this.firstName,member.getFirstName());
		clearType(this.lastName,member.getLastName());
		clearType(this.DOB,member.getDOB());
		clearType(this.martialStatus,member.getMaritalStatus());
		pageDown();
		clearType(this.employDate,member.getEmploymentDate());
		
		clearType(this.employeeNumber,member.getEmployeeNo());
		typeQuickJS(this.salary,member.getSalary());
		//clearType(this.salary,);
		
		clearType(this.initialEffDate,member.getEffectiveDate());
		clearType(this.effectiveDate,member.getEffectiveDate());
		
		pageDown();
		
		click(this.addCoverageScheme);
		
		this.switchToFrame(MemberFrame);
		click(schemeDropDown);
		
		click(schemeItem);
		
		click(search);
		
		List<WebElement> listOfPlan=findElements(planList);
		Random random=new Random();

		listOfPlan.get(random.nextInt(listOfPlan.size())).click();
		
		
		click(select);
		TimeUnit.SECONDS.sleep(1);
		
		
		switchBack();
		TimeUnit.SECONDS.sleep(1);
		switchToFrame(MemberFrame);
		TimeUnit.SECONDS.sleep(1);
		

		click(testId);
		click(save);
		switchBack();
		
	}

	public void MemberUpload (String polNo) throws Exception
	{
		TimeUnit.SECONDS.sleep(1);	
		//click(firstJob);
		TimeUnit.SECONDS.sleep(1);
		//click(openTask);
		
		//waitLoadingOfPage();
		
		while(!elementNotDisplay(loadingAniX))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(6);
		while(!elementNotDisplay(loadingAniX))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		TimeUnit.SECONDS.sleep(1);
		
		click(File_Upload);
		TimeUnit.SECONDS.sleep(1);
		clearType(member_file_upload_fileFormat_input,"S - Standard Format");
		clearType(member_file_upload_policyNo_input,polNo);
		
		clearType(member_file_upload_memberFileType_input,"1 - New and Change");
		TimeUnit.SECONDS.sleep(1);
		
		String filePath=System.getProperty("user.dir")+File.separator+memUploadFile;
		fileUpload(fileUpload,filePath);
		TimeUnit.SECONDS.sleep(1);
		click(Upload);
		TimeUnit.SECONDS.sleep(5);
		
		while(!elementNotDisplay(loadingAniX))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);	
		}
		
		click(member_file_upload_memberFileType_input);
		this.pageDown();
		
		WebElement eResults = findHideElement(Status);
		System.out.println("Before Trial Ran Status: " + eResults.getText()); //	Format checking
		
		TimeUnit.SECONDS.sleep(1);
		
		for (int i=1;i<60;i++) {
			while(!elementNotDisplay(loadingImg))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			click(Refresh);
			eResults = findHideElement(Status);
			System.out.println("Status: " + eResults.getText());
			if (eResults.getText().equals("Validated")) {
				TimeUnit.SECONDS.sleep(1);
				//click(btnSubmit);
				break;
			}
			else {
				System.out.println(" ==== "+eResults.getText().trim()+" ==== ");
				TimeUnit.SECONDS.sleep(10);
			}
		}
		
		TimeUnit.SECONDS.sleep(1);
		click(memberFileCheckBtn);	
		TimeUnit.MILLISECONDS.sleep(300);;
	
		click(TrailRun);
		TimeUnit.SECONDS.sleep(1);
		//Trigger Trail Run
		for (int i=1;i<60;i++) {
			while(!elementNotDisplay(loadingImg))
			{
				TimeUnit.SECONDS.sleep(1);
			}
			click(Refresh);
			eResults = findHideElement(Status);
			System.out.println("Trail Ruan Status" + eResults.getText());
			if (eResults.getText().equals("Trial run")) {
				TimeUnit.SECONDS.sleep(1);
				//click(btnSubmit);
				System.out.println("Trail Ran Success");
				break;
			}
			else {
				TimeUnit.SECONDS.sleep(10);
			}
		}
		
		
		//Load Member Data
		click(loadMemberData);
		
		//Verify the data get loaded successfully
		if(findElements(memberData).size()>0)
		{
			System.out.println("The Member Data get uploaded successfully.");
		}else
		{
			System.out.println("The Member Data failed uploading.");
		}
		
		
		
		//TimeUnit.SECONDS.sleep(5);
		//logout();
	}
	
	
	public void submitMMAndSendBill() throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		click(btnSubmit);
		
		
		//Please remove
		/*TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		click(btnSubmit);*/
		
		
		TimeUnit.SECONDS.sleep(1);
		while(alertShowup()){
			this.alertConfirm();
		}
		
		TimeUnit.SECONDS.sleep(1);
		while(!elementNotDisplay(loadingImg))
		{
			TimeUnit.SECONDS.sleep(1);
		}
		
		if(!elementNotDisplay(continueBill))
		{
			click(continueBill);
		}
		
	}
	
	public void updateIndividualMemUpload(String policyNum) throws IOException
	{
		memUploadFile="Multiple Plan.xlsx";
		ExcelUtil.updateMemUploadFile(policyNum,memUploadFile);
	}
	
	public void updateGLVMemUpload(String policyNum) throws IOException
	{
		memUploadFile="Single Plan.xlsx";
		ExcelUtil.updateGLVMemUploadFile(policyNum,memUploadFile);
	}
	
	public void updateMemUpload(String policyNum,String memUploadFile) throws IOException
	{
		this.memUploadFile=memUploadFile;
		ExcelUtil.updateGLVMemUploadFile(policyNum,memUploadFile);
	}
	
	public List<Member> readMembers(String memUploadFile) throws Exception
	{
		
		String memberFile=System.getProperty("user.dir")+File.separator+memUploadFile;
		ReadExcelUtil reu=new ReadExcelUtil<Member>(){};
		reu.enablePageRead(new File(memberFile), 0, 2, 1);
		List<Member> members=reu.getDataSet();
		return members;
	}
	
	public void updateSMMemUpload(String policyNum) throws IOException
	{
		memUploadFile="SM Plan.xlsx";
		ExcelUtil.updateGLVMemUploadFile(policyNum,memUploadFile);
	}
	
//	public static void main(String ...args) throws IOException
//	{
//		String memUploadFile="Multiple630.xlsx";
//		ExcelUtil.updateMemUploadFile("0823100425",memUploadFile);
//		
//		//ExcelUtil.updateMemUploadFile("THS0010205", memUploadFile, 100);
//		
//	}
	 public static void main(String... args) throws Exception {
	        AbstractCoastRT rt = new IndivPolicy("https://cangzdlcoa02.aia.biz:9444/COAST_FE_PRESIT0531/homepage/login", "ASNPH9K", "ASNPH9K");
	        List<CoastCase> testcaseList = null;
	        String policyNum = "0823100230";
	        testcaseList = CaseUtil.getCoastTestCases("Coast Case Data Template - Smoke.xlsx");
	        //3 is Glv
	        CoastCase coastCase = testcaseList.get(0);
	        //   policyNum = coastCase.getCas().getPolicyNum();
	        rt.setTestcase(coastCase);
	        rt.memberMaintenance(policyNum);

	    }
}
