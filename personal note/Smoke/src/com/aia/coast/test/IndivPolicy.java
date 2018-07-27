package com.aia.coast.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.reporters.Files;

import util.Checker;

import com.aia.coast.entity.Member;
import com.aia.coast.entity.UATPolicy;
import com.aia.coast.entity.assignTask;
import com.aia.coast.entity.individualPolicy;
import com.aia.coast.entity.uatIndividualPolicy;
import com.aia.coast.pageobject.BillPage;
import com.aia.coast.pageobject.CreateJobPage;
import com.aia.coast.pageobject.HomePage;
import com.aia.coast.pageobject.LoginPage;
import com.aia.coast.pageobject.MemberMaintenancePage;
import com.aia.coast.pageobject.MemberQCPage;
import com.aia.coast.pageobject.MemberUW;
import com.aia.coast.pageobject.PMPage;
import com.aia.coast.pageobject.PolicyMstQCPage;
import com.aia.coast.pageobject.PolicyYear;
import com.aia.coast.pageobject.RCVPage;
import com.aia.coast.pageobject.iPosSubmitPage;
import com.aia.coast.pageobject.IndividualReceivable;
import com.aia.coast.pageobject.GroupReceivable;
import com.aia.coast.testcase.Client;
import com.aia.coast.testcase.CoastCase;
import com.aia.coast.testcase.IndividualBilling;
import com.aia.coast.testcase.Policy;
import com.aia.coast.testcase.PolicyProduct;
import com.aia.coast.util.ConvertPropertyToPojo;
import com.aia.cs.ipos.entity.iposSubmissionTool;
import com.aia.ui.utils.DriverFactory;

public class IndivPolicy extends AbstractCoastRT{
	
	
	
	private WebDriver driver=null;
	private static LoginPage login;
	private static HomePage home;
	private static PMPage pmPage;
	
	public static String formartJs()
	{
		String js=null;
		try {
			js=Files.readFile(new File(System.getProperty("user.dir")+File.separator+"js.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return js;
	}
	
	public void alertMsg(String msg)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String jsStr=formartJs();
		js.executeScript(jsStr);
		js.executeScript("alert1('"+msg+"')");
		try{
		TimeUnit.SECONDS.sleep(2);
		}catch(InterruptedException e)
		{
			//ignore
		}
		
		

		js.executeScript("doOk()");
	}
	
	public IndivPolicy(String url, String pmAccount, String mmAccount)
	{
		initialize(url,pmAccount,mmAccount);
	}
	
	
	@Override
	public boolean policyManitenance(String policyNum)
	{
		return false;
	}
	
	@Override
	public void setTestcase(CoastCase testcase)
	{
		this.testcase=testcase;
	}
	
	
	@Override
	public boolean policyMaintenance(){
		// TODO Auto-generated method stub
		//Initialize
		driver = DriverFactory.getChromeDriver();
		try{
			login=new LoginPage(driver,url);
			
			alertMsg("Open Login Page Successfully...., Close the dialog after 2 seconds.");
			
			home=new HomePage(driver);
			pmPage=new PMPage(driver);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("Finish Initialization......");
		boolean submitPMSuccess=false;
		try
		{
				
			if(testcase.getCas().getPolicyCategory().contains("iPos") && Checker.isNull(testcase.getCas().getPolicyNum()))
			{
				String policyNum=iposSubmissionTool.submitIPos();
				testcase.getCas().setPolicyNum(policyNum);
			}
			
			
			login.inputUser(pmAccount);
			login.login();
			
			if(Checker.isNotNull(testcase.getCas().getPolicyNum()))
			{
				home.assignTaskTo(pmAccount,testcase.getCas().getPolicyNum(),url);
				
				//TimeUnit.SECONDS.sleep(10);
				System.out.println("Assign Policy : "+testcase.getCas().getPolicyNum()+" to "+pmAccount);
				
				home.filterpolicy(testcase.getCas().getPolicyNum());
				home.openFirstTask();
				//TimeUnit.SECONDS.sleep(22);
			}
			else
			{
				
				home.createANewPolicy();
				//TimeUnit.SECONDS.sleep(20);
			}
					
			pmPage.finishLoadingPolicyMaintenance();
			pmPage.fillClient(testcase.getClient());
			
			
			
			
			if(testcase.getClient().getSubOfficeProducer()!=null && Boolean.valueOf(testcase.getClient().getSubOfficeProducer()))
			{
				pmPage.fillDistributionChannel(testcase.getDistributionChannel());
			}
		
			pmPage.fillPolicyInfo(testcase.getPolicy());
			
			if(Checker.isNotNull(testcase.getPolicyYear().getToDate()))
			{
				System.out.println("Start to edit policy year.");
				pmPage.goToPolicyYear();
				PolicyYear policyYear=new PolicyYear(driver);
				policyYear.updatePolicyYear(testcase.getPolicyYear().getToDate());
			}
			
			this.policyNum=pmPage.getPolicyNo();
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Policy: "+this.policyNum);
			
			this.testcase.getCas().setPolicyNum(this.policyNum);
			
			if(testcase.getPolicy()!=null &&  Boolean.valueOf(testcase.getPolicy().isIncludeVoluntaryPolicy()))
			{
				pmPage.fillIndividualBill(testcase.getIndivBilling());
			}
			
			if(testcase.getPackageScheme()!=null && testcase.getPackageScheme().size()>0 && Checker.isNotNull(testcase.getPackageScheme().get(0).getPackageProduct()))
			{
				pmPage.fillPolicyScheme(testcase.getPackageScheme());
				System.out.println("Okay, done for GLV.");
			}
			
			
			
			if(testcase.getCas().getPolicyCategory()!=null && testcase.getCas().getPolicyCategory().equalsIgnoreCase("SM"))
			{
				pmPage.editSMPolicyProduct(testcase.getProducts());
			}else if(testcase.getCas().getPolicyCategory()!=null && testcase.getCas().getPolicyCategory().equalsIgnoreCase("iPos"))
			{
				iPosSubmitPage ipos=new iPosSubmitPage(driver);
				ipos.editPolicyProduct(testcase.getProducts());
			}
			else if(testcase.getProducts().get(0).getProduct()==null || testcase.getProducts().get(0).getProduct().isEmpty())
			{
				pmPage.editPolicyProduct(testcase.getProducts());
			}
			

			if(testcase.getProducts().get(0).getProduct()!=null && !testcase.getProducts().get(0).getProduct().isEmpty())
			{
				pmPage.addPolicyProduct(testcase.getProducts());
				pmPage.editIndividualProductPlan(testcase.getProductPlans());
			}
			
			if(testcase.getCas().getPolicyCategory()!=null && testcase.getCas().getPolicyCategory().equalsIgnoreCase("SM"))
			{
				pmPage.editSMProductPlan(testcase.getProductPlans().get(0));
			}
			
			//pmPage.editMedicalCard();
			
			pmPage.submit();
			//Check The Error Dialog not appear
			boolean errorMsg=pmPage.validationError();
			if(errorMsg)
			{
				System.out.println("There Validation error during Policy Maintenance.");
				submitPMSuccess=false;
				throw new RuntimeException("Submit Failed for the Policy: CaseId: "+ testcase.getCas().getCaseId()+" Case Name: "+testcase.getCas().getPolicyCategory());
			}else
			{
				submitPMSuccess=home.submitSuccess();
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			this.go=false;
		}
		if(submitPMSuccess)
		{
			System.out.println("Submit Policy successfully.");
		}else
		{
			System.out.println("Submit Policy failed, please check.");
			throw new RuntimeException("Submit Failed for the Policy: CaseId: "+ testcase.getCas().getCaseId()+" Case Name: "+testcase.getCas().getPolicyCategory()+", Policy Number: "+this.policyNum);
		}
	
		return submitPMSuccess;
	}

	@Override
	public boolean policyMasterQC(String policyNum) {
		System.out.println("Start Policy Master QC.");
		boolean masterQCSuccess=false;
		// TODO Auto-generated method stub
		if(driver==null)
		{
			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				
				login.inputUser(mmAccount);
				login.login();
				
			}catch(Exception e){}
		}
		else
		{
			try
			{
				if(pmAccount!=mmAccount)
				{
					home.logout();
					login.inputUser(mmAccount);
					login.login();
				}else
				{
					home.refresh();
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			home.filterpolicy(policyNum);
			home.openFirstTask();
			
			PolicyMstQCPage policyMstQCPage =new PolicyMstQCPage(driver);
			
			pmPage.submit();
			//policyMstQCPage.policyMasterQC();
			
		
			
			boolean errorMsg=pmPage.validationError();
			if(errorMsg)
			{
				System.out.println("There Validation error during Policy Maintenance Master QC.");
				masterQCSuccess=false;
				throw new RuntimeException("Submit PM QC Failed for the Policy: CaseId: "+ testcase.getCas().getCaseId()+" Case Name: "+testcase.getCas().getPolicyCategory());
			}else
			{
				masterQCSuccess=home.submitSuccess();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Failed on Policy Master QC for Policy: "+policyNum);
		}
		
		return masterQCSuccess;
	}

	@Override
	public boolean memberMaintenance(String policyNum) {
		// TODO Auto-generated method stub
		boolean mmSuccess=false;
		// TODO Auto-generated method stub
		System.out.println("Process to Member Maintenance.");
		if(driver==null)
		{
			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				
				login.inputUser(mmAccount);
				home.assignTaskTo(mmAccount, policyNum, "Upload Member", url);
				login.login();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else
		{
			try{
				System.out.println("userId: "+pmAccount+", policy: "+policyNum+", Url: "+url);
				home.assignTaskTo(mmAccount, policyNum, "Upload Member", url);
				if(pmAccount!=mmAccount)
				{	
					home.logout();
					login.inputUser(mmAccount);
					login.login();
				}else
				{
					home.refresh();
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			home.filterpolicy(policyNum);
			home.openFirstTask();
			MemberMaintenancePage mmp=new MemberMaintenancePage(driver);
			
			
			mmp.waitMemberCompleteLoading();
			
			if(testcase.getMemberFile().getMode().equalsIgnoreCase("MFU") && Checker.isNotNull(testcase.getMemberFile().getMemberFile()))
			{
				mmp.updateMemUpload(policyNum, testcase.getMemberFile().getMemberFile());
				mmp.MemberUpload(policyNum);
			}else if(testcase.getMemberFile().getMode().equalsIgnoreCase("manual"))
			{
				List<Member> members=mmp.readMembers(testcase.getMemberFile().getMemberFile());
				//Update though
				for(int i=0, len=members.size();i<len;i++)
					mmp.manualInputMember(members.get(i));
			}
			else
			{
				if(testcase.getCas().getPolicyCategory().contains("Indiv"))
				{
					mmp.updateIndividualMemUpload(policyNum);
					mmp.MemberUpload(policyNum);
				}
				//Need to update
				//mmp.updateGLVMemUpload(policyNum);
				else if(testcase.getCas().getPolicyCategory().contains("GLV"))
				{
					
					mmp.updateGLVMemUpload(policyNum);
					mmp.MemberUpload(policyNum);
				}
				else if(testcase.getCas().getPolicyCategory().contains("SM"))
				{
					mmp.updateSMMemUpload(policyNum);
					mmp.MemberUpload(policyNum);
				}
			}
			//Open next time
			mmp.submitMMAndSendBill();
			boolean errorMsg=pmPage.validationError();
			if(errorMsg)
			{
				System.out.println("There Validation error during Member Maintenance.");
				mmSuccess=false;
				throw new RuntimeException("Submit MM Failed for the Policy" + this.policyNum + ", CaseId:"+  testcase.getCas().getCaseId()+" Case Name: "+testcase.getCas().getPolicyCategory());
			}else
				mmSuccess=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();

			throw new RuntimeException("Failed on Member Maintenance for Policy: "+policyNum,e);
		}
		
		System.out.println("Passed Smoke testing for: "+this.testcase.getCas().getPolicyCategory()+" from PM to MM: "+policyNum);
		return mmSuccess;
		
	}

	@Override
	public boolean memberQC(String policyNum) {
		// TODO Auto-generated method stub
		boolean mmQCSuccess=false;
		// TODO Auto-generated method stub
		System.out.println("Finish Member Maintainence, Perform Member QC.");
		if(driver==null)
		{
			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				
				login.inputUser(mmAccount);
				login.login();
				
			}catch(Exception e){}
		}else
		{
			try
			{
				home.logout();
				login.inputUser(mmAccount);
				login.login();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			home.filterpolicy(policyNum);
			home.openFirstTask();
			MemberQCPage memberQC=new MemberQCPage(driver);
			memberQC.memberQC();
			mmQCSuccess=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(mmQCSuccess)
		{
			System.out.println("Member QC execute successfully.");
		}
		return mmQCSuccess;
	}

	@Override
	public boolean memberLetterUW(String policyNum)
	{
		System.out.println("Start Letter UnderWriting...");
		MemberUW memberUW=null;
		boolean UWLetterSuccess=false;
		if(driver==null)
		{
			driver=DriverFactory.getChromeDriver();
			try
			{
				memberUW=new MemberUW(driver);
				memberUW.assignJobForUW(policyNum, mmAccount, url, 5);
				login=new LoginPage(driver,url);
				
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				
				login.inputUser(mmAccount);
				login.login();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else
		{
			try
			{
				memberUW=new MemberUW(driver);
				memberUW.assignJobForUW(policyNum, mmAccount, url, 5);
				home.logout();
				login.inputUser(mmAccount);
				login.login();
			}catch(Exception e){}
		}
		
		try
		{
			
			
			home.refresh();
			
			for(int i= 0;i<5;i++)
			{
				home.filterpolicy(policyNum);
				home.openFirstTask();
				
				memberUW.finishLetterUW();
				
				pmPage.submit();
				
				UWLetterSuccess=home.submitSuccess();
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return UWLetterSuccess;
		
		
	}
	
	@Override
	public boolean memberPendingUW(String policyNum) {
		// TODO Auto-generated method stub
		System.out.println("Start Meber UnderWriting.");
		System.out.println("PolicyNo :"+policyNum);
		
		MemberUW memberUW=null;
		boolean memberUWSuccess=false;
		if(driver==null)
		{
			System.out.println("Initialize Coast variables...");
			driver = DriverFactory.getChromeDriver();
			try{
				
				
				memberUW=new MemberUW(driver);
				
				memberUW.assignJobForMemberUW(policyNum, mmAccount, url, 5);
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				
				login.inputUser(mmAccount);
				login.login();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				memberUW=new MemberUW(driver);
				memberUW.assignJobForMemberUW(policyNum, mmAccount, url, 5);
				home.logout();
				login.inputUser(mmAccount);
				login.login();
				home.refresh();
			}catch(Exception e){}
		}
		try
		{
			//home.GoToPendingTab();
			
			System.out.println("Finish UW Letter, move to MemberUW.");

			for(int i= 0;i<5;i++)
			{
				home.GoToPendingTab();
				home.filterPendingPolicy(policyNum);
				home.openFirstPendingTask();
					
				memberUW.unPendingMemberUW();
				memberUWSuccess=home.submitSuccess();
			}
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return memberUWSuccess;
	}

	@Override
	public boolean memberUW(String policyNum) {
		// TODO Auto-generated method stub
		System.out.println("Start Meber Un-Pending UnderWriting.");
		System.out.println("PolicyNo :"+policyNum);
		
		MemberUW memberUW=null;
		boolean memberUWSuccess=false;
		if(driver==null)
		{
			System.out.println("Initialize Coast variables...");
			driver = DriverFactory.getChromeDriver();
			try{
				
				
				memberUW=new MemberUW(driver);
				memberUW.assignJobForMemberUWUnPend(policyNum, mmAccount, url, 5);
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				
				login.inputUser(mmAccount);
				login.login();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				memberUW=new MemberUW(driver);
				memberUW.assignJobForMemberUWUnPend(policyNum, mmAccount, url, 5);
				home.logout();
				login.inputUser(mmAccount);
				login.login();
				home.refresh();
			}catch(Exception e){}
		}
		try
		{
			//home.GoToPendingTab();
			
			System.out.println("Finish UW Letter, move to MemberUW UnPend.");

			for(int i= 0;i<5;i++)
			{
				home.filterpolicy(policyNum);
				home.openFirstTask();
				memberUW.finishMemberUW();
				pmPage.submit();
				memberUWSuccess=home.submitSuccess();
			}
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return memberUWSuccess;
	}
	
	@Override
	public boolean billGeneration(String policyNum) {
		// TODO Auto-generated method stub
		System.out.println("Start Bill Generation.");
		boolean billSuccess=false;
		if(driver==null)
		{

			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				home.checkBillGeneration(policyNum, url);
				home.assignTaskTo(mmAccount, policyNum, "Request billing for NB", url);
				login.inputUser(mmAccount);
				
				login.login();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else
		{
			home.checkBillGeneration(policyNum, url);
			
			try
			{
				home.assignTaskTo(mmAccount, policyNum, "Request billing for NB", url);
				home.logout();
				login.inputUser(mmAccount);
				login.login();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		try
		{
			home.filterpolicy(policyNum);
			home.openFirstTask();
			
			//TimeUnit.SECONDS.sleep(15);
			
			BillPage billPage=new BillPage(driver);
			
			if(testcase.getCas().getPolicyCategory().matches("(?i).*glv.*") || testcase.getPolicy().getPolicyCategory().matches("(?i).*package.*"))
			{
				System.out.println("Generate Package bill.");
				billPage.generateGroupBill(testcase.getBillResult());
			}
			
			if(testcase.getCas().getPolicyCategory().matches("(?i).*indiv.*") || testcase.getCas().getPolicyCategory().matches("(?i).*sm.*"))
			{
				System.out.println("Generate Individual bill.");
				billPage.generateIndividualBill(testcase.getBillResult());
			}
			
			if(testcase.getCas().getPolicyCategory().matches("(?i).*pos.*"))
			{
				System.out.println("Generate iPos bill.");
				billPage.generateIPosBill();
			}
			
			pmPage.submitAndAccept();
			
			
			billSuccess=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Failed for Bill Generation, Polciy: "+this.policyNum+", for "+testcase.getCas().getPolicyCategory());
		}
		
		return billSuccess;
	}

	@Override
	public boolean receivablePending(String policyNum)
	{
		System.out.println("Start Receivable...");
		boolean rcvPendingSuccess=false;
		boolean UAT=url.contains("uat") ? true : false;
		
		if(driver==null)
		{

			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				home.checkRCVTask(policyNum, UAT,mmAccount);
				login.inputUser(mmAccount);
				
				login.login();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}else
		{
			home.checkRCVTask(policyNum, UAT,mmAccount);
			try
			{
				home.logout();
				login.inputUser(mmAccount);
				login.login();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			home.GoToPendingTab();
			home.filterPendingPolicy(policyNum);
			home.openFirstPendingTask();
			
			RCVPage rcv=new RCVPage(driver);

			if(this.testcase.getCas().getPolicyCategory().matches("(?i).*glv.*") || this.testcase.getCas().getPolicyCategory().matches("(?i).*ipos.*"))
			{
				rcv.finishPendingRCV();
			}
			
			if(this.testcase.getCas().getPolicyCategory().matches("(?i).*indiv.*") || this.testcase.getCas().getPolicyCategory().matches("(?i).*sm.*"))
			{
				//billPage.generateIndividualBill();
			}
			
			rcvPendingSuccess=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rcvPendingSuccess;
	}
	
	@Override
	public boolean receivable(String policyNum)
	{
		System.out.println("Start Receivable...");
		boolean rcvSuccess=false;
		boolean UAT=url.contains("uat") ? true : false;
		
		if(driver==null)
		{

			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				home.checkRCVTask(policyNum, UAT,mmAccount);
				login.inputUser(mmAccount);
				
				login.login();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}else
		{
			home.checkRCVTask(policyNum, UAT,mmAccount);
			try
			{
				home.logout();
				login.inputUser(mmAccount);
				login.login();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			home.filterpolicy(policyNum);
			home.openFirstTask();
			
			TimeUnit.SECONDS.sleep(15);
			
			RCVPage rcv=new RCVPage(driver);

			if(this.testcase.getCas().getPolicyCategory().matches("(?i).*glv.*") || this.testcase.getCas().getPolicyCategory().matches("(?i).*ipos.*"))
			{
				rcv.finishRcv();
			}
			
			if(this.testcase.getCas().getPolicyCategory().matches("(?i).*indiv.*") || this.testcase.getCas().getPolicyCategory().matches("(?i).*sm.*"))
			{
				//billPage.generateIndividualBill();
			}
			
			//pmPage.submit();

			rcvSuccess=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rcvSuccess;
	}
	
	@Override
	public boolean isMasterQC() {
		// TODO Auto-generated method stub
		Map<Boolean,assignTask> map=home.checkPolicyProcess(policyNum, url);
		
		if(map!=null)
		{
		
			System.out.println("The policy: "+policyNum +" need to perform Master QC, assign to the account. "+pmAccount);
			try
			{
				home.assignTaskTo(mmAccount, policyNum, "Approve New Policy", url);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return true;
		}else
		{
			return false;
		}
	}

	@Override
	public boolean isMemberQC() {
		// TODO Auto-generated method stub
		Map<Boolean,assignTask> memberQCJob=home.checkMemberQC(policyNum, url);
		
		
		if(memberQCJob!=null)
		{
			System.out.println("The policy: "+policyNum +" need to perform Member QC, assign to the account. "+mmAccount);
			
			try
			{
				home.assignTaskTo(mmAccount,policyNum,url);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isMemberUW() {
		// TODO Auto-generated method stub
		if(testcase.getCas().getPolicyCategory().matches("(?i).*glv.*") || testcase.getCas().getPolicyCategory().matches("(?i).*ipos.*"))
		{
			for(PolicyProduct pp : testcase.getProducts())
			{
				if(pp.getNelAmount()!=null && pp.getNelAmount().equals("0"))
					return true;
			}
		}
		return false;
		
		//(?i).*GLV.*
	}
	
	@Override
	public boolean contractCheck(String policyNum)
	{
		System.out.println("Start Receivable...");
		boolean rcvSuccess=false;
		boolean UAT=url.contains("uat") ? true : false;
		
		if(driver==null)
		{

			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				home.checkRCVTask(policyNum, UAT,mmAccount);
				login.inputUser(mmAccount);
				
				login.login();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}else
		{
			home.checkRCVTask(policyNum, UAT,mmAccount);
			try
			{
				home.logout();
				login.inputUser(mmAccount);
				login.login();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			home.filterpolicy(policyNum);
			home.openFirstTask();
			
			TimeUnit.SECONDS.sleep(15);
			
			RCVPage rcv=new RCVPage(driver);

			if(this.testcase.getCas().getPolicyCategory().matches("(?i).*glv.*") || this.testcase.getCas().getPolicyCategory().matches("(?i).*ipos.*"))
			{
				rcv.finishRcv();
			}
			
			if(this.testcase.getCas().getPolicyCategory().matches("(?i).*indiv.*") || this.testcase.getCas().getPolicyCategory().matches("(?i).*sm.*"))
			{
				//billPage.generateIndividualBill();
			}
			
			//pmPage.submit();

			rcvSuccess=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rcvSuccess;
	}
	
	@Override
	public boolean isIPos()
	{
		return false;
	}
	
	
	@Override
	public boolean createChangeJob(String policyNum)
	{
		System.out.println("Go to Create Job..., the Policy Number is: "+policyNum);
		boolean createSuccess=false;
	
		if(driver==null)
		{

			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				login.inputUser(pmAccount);
				
				login.login();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}else
		{
			
			try
			{
				home.backHome();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			
			home.createAJob();
			
			CreateJobPage createJob=new CreateJobPage(driver);
			
			createJob.createPMChangeJob(policyNum);

			createSuccess=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return createSuccess;
	}
	
	@Override
	public boolean PMChange(String policyNum)
	{
		System.out.println("Go to Change Policy Master..., the Policy Number is: "+policyNum);
		boolean changePMSuccess=false;
	
		if(driver==null)
		{
			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				login.inputUser(pmAccount);
				
				home.assignTaskTo(pmAccount, policyNum, "Change Policy Master", url);
				login.login();

			}catch(Exception e){
				e.printStackTrace();
			}
		}else
		{
			
			try
			{
				home.assignTaskTo(pmAccount, policyNum, "Change Policy Master", url);
				if(pmPage==null)
					pmPage=new PMPage(driver);
				//home.backHome();
				home.logout();
				login.inputUser(pmAccount);
				login.login();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			home.filterpolicy(policyNum);
			home.openFirstTask();
			//TimeUnit.SECONDS.sleep(40);
			pmPage.performPMChange(testcase.getPmChange());
			pmPage.submit();
			changePMSuccess=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return changePMSuccess;
	}
	
	
	@Override
	public boolean billGenerationForChange(String policyNum)
	{
		System.out.println("Start Bill Generation for Change Process.");
		boolean billSuccess=false;
		if(driver==null)
		{

			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				home.checkBillGenerationForChange(policyNum, url);
				home.assignTaskTo(mmAccount, policyNum, "Request Billing for Policy Change", url);
				login.inputUser(mmAccount);
				
				login.login();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else
		{
			home.checkBillGenerationForChange(policyNum, url);
			
			try
			{
				home.assignTaskTo(mmAccount, policyNum, "Request Billing for Policy Change", url);
				home.logout();
				login.inputUser(mmAccount);
				login.login();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		try
		{
			home.filterpolicy(policyNum);
			home.openFirstTask();
			
			//TimeUnit.SECONDS.sleep(15);
			
			BillPage billPage=new BillPage(driver);
			
			if(testcase.getCas().getPolicyCategory().matches("(?i).*glv.*") || testcase.getPolicy().getPolicyCategory().matches("(?i).*package.*"))
			{
				System.out.println("Generate Package bill.");
				billPage.generateGroupBill(testcase.getBillResult());
			}
			
			if(testcase.getCas().getPolicyCategory().matches("(?i).*indiv.*") || testcase.getCas().getPolicyCategory().matches("(?i).*sm.*"))
			{
				System.out.println("Generate Individual bill.");
				billPage.generateIndividualBill(testcase.getBillResult());
			}
			
			if(testcase.getCas().getPolicyCategory().matches("(?i).*pos.*"))
			{
				System.out.println("Generate iPos bill.");
				billPage.generateIPosBill();
			}
			
			pmPage.submitAndAccept();
			
			
			billSuccess=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Failed for Bill Generation for Change, Polciy: "+this.policyNum+", for "+testcase.getCas().getPolicyCategory());
		}
		
		return billSuccess;
	}
	
	
	@Override
	public boolean MemberBillGenerationChange(String policyNum) {
		// TODO Auto-generated method stub
		System.out.println("Start Change Member Bill Generation.");
		boolean billSuccess=false;
		if(driver==null)
		{

			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				pmPage=new PMPage(driver);
				//home.checkBillGeneration(policyNum, url);
				System.out.println("driver==null====");
				
				//home.checkMemberBillGeneration(policyNum, url);
				
				home.assignTaskTo(mmAccount, policyNum, "Request Bill for Member Change", url);
				login.inputUser(mmAccount);
				
				login.login();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("driver!!==null====");
			//home.checkBillGeneration(policyNum, url);
			home.checkMemberBillGeneration(policyNum, url);
			try
			{
				home.assignTaskTo(mmAccount, policyNum, "Request Bill for Member Change", url);
				home.logout();
				login.inputUser(mmAccount);
				login.login();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		try
		{
			home.filterpolicy(policyNum);
			home.openFirstTask();
			
			TimeUnit.SECONDS.sleep(12);
			
			BillPage billPage=new BillPage(driver);
			
			if(testcase.getCas().getPolicyCategory().matches("(?i).*glv.*") || testcase.getPolicy().getPolicyCategory().matches("(?i).*package.*"))
			{
				System.out.println("Generate Package bill.");
				billPage.generateGroupBill(testcase.getBillResult());
			}
			
			if(testcase.getCas().getPolicyCategory().matches("(?i).*indiv.*") || testcase.getCas().getPolicyCategory().matches("(?i).*sm.*"))
			{
				System.out.println("Generate Individual bill.");
				billPage.generateIndividualBill(testcase.getBillResult());
			}
			
			if(testcase.getCas().getPolicyCategory().matches("(?i).*pos.*"))
			{
				System.out.println("Generate iPos bill.");
				billPage.generateIPosBill();
			}
			
			//pmPage.submitAndAccept();
			
			
			//billSuccess=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Failed for Bill Generation, Polciy: "+this.policyNum+", for "+testcase.getCas().getPolicyCategory());
		}
		
		return billSuccess;
	}
	
	@Override
	public boolean CreateReceivableJob(String policyNum) {
		// TODO Auto-generated method stub
		System.out.println("Go to Create Receivable Job..., the Policy Number is: "+policyNum);
		boolean createSuccess=false;
	
		if(driver==null)
		{

			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				login.inputUser(pmAccount);
				
				login.login();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}else
		{
			
			try
			{
				home.backHome();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			
			home.createAJob();
			
			CreateJobPage createJob=new CreateJobPage(driver);
			
			createJob.createReceivableJob(policyNum);

			createSuccess=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return createSuccess;
	}
	
	@Override
	public boolean IndividualReceivable(String policyNum)
	{
		System.out.println("Go to Individual Receivable..., the Policy Number is: "+policyNum);
		boolean inReceivable=false;
	
		if(driver==null)
		{

			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				
				pmPage=new PMPage(driver);
				login.inputUser(pmAccount);
				System.out.println("#######driver==null######");
				home.assignTaskTo(pmAccount, policyNum, "Individual Receivable", url);
				login.login();

			}catch(Exception e){
				e.printStackTrace();
			}
		}else
		{
			
			try
			{
				System.out.println("#######driver!!==null######");
				home.assignTaskTo(pmAccount, policyNum, "Individual Receivable", url);
				if(pmPage==null)
					pmPage=new PMPage(driver);
				home.backHome();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			home.filterpolicy(policyNum);
			
			home.openFirstTask();
			IndividualReceivable indiviReceivable = new IndividualReceivable(driver);
			
			System.out.print("Product:"+testcase.getProducts().get(0).getProduct());

			indiviReceivable.filesUpload(policyNum);
			
			indiviReceivable.submit();
			
			inReceivable=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return inReceivable;
	}
/*	@Override
	public boolean IndividualReceivableManual(String policyNum)
	{
	
	}*/
	@Override
	public boolean InReceivableRefund(String policyNum) {
		// TODO Auto-generated method stub
		System.out.println("Go to Individual Receivable Manual..., the Policy Number is: "+policyNum);
		boolean inReceivable=false;
	
		if(driver==null)
		{

			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				
				pmPage=new PMPage(driver);
				login.inputUser(pmAccount);
				home.assignTaskTo(pmAccount, policyNum, "Individual Receivable", url);
				login.login();

			}catch(Exception e){
				e.printStackTrace();
			}
		}else
		{
			
			try
			{
				home.assignTaskTo(pmAccount, policyNum, "Individual Receivable", url);
				if(pmPage==null)
					pmPage=new PMPage(driver);
				home.backHome();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			home.filterpolicy(policyNum);
			//TimeUnit.SECONDS.sleep(20);
			home.openFirstTask();
			IndividualReceivable indiviReceivable = new IndividualReceivable(driver);
			
			indiviReceivable.refund(policyNum);
			
			indiviReceivable.submit();
			
			inReceivable=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return inReceivable;
	}

	@Override
	public boolean InReceivablePending(String policyNum) {
		// TODO Auto-generated method stub
		System.out.println("Go to Individual Receivable Manual..., the Policy Number is: "+policyNum);
		boolean inReceivable=false;
	
		if(driver==null)
		{

			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				
				pmPage=new PMPage(driver);
				login.inputUser(pmAccount);
				home.assignTaskTo(pmAccount, policyNum, "Individual Receivable", url);
				login.login();

			}catch(Exception e){
				e.printStackTrace();
			}
		}else
		{
			
			try
			{
				home.assignTaskTo(pmAccount, policyNum, "Individual Receivable", url);
				if(pmPage==null)
					pmPage=new PMPage(driver);
				home.backHome();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			home.filterpolicy(policyNum);
			//TimeUnit.SECONDS.sleep(20);
			home.openFirstTask();
			IndividualReceivable indiviReceivable = new IndividualReceivable(driver);
			
			
			indiviReceivable.pending(policyNum);
			indiviReceivable.submit();
			
			inReceivable=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return inReceivable;
	}
	@Override
	public boolean GroupReceivable(String policyNum) {
		System.out.println("Go to Group Receivable ..., the Policy Number is: "+policyNum);
		boolean gReceivable=false;
	
		if(driver==null)
		{

			driver = DriverFactory.getChromeDriver();
			try{
				login=new LoginPage(driver,url);
				home=new HomePage(driver);
				
				pmPage=new PMPage(driver);
				login.inputUser(pmAccount);
				home.assignTaskTo(pmAccount, policyNum, "Premium Receivable", url);
				login.login();

			}catch(Exception e){
				e.printStackTrace();
			}
		}else
		{
			
			try
			{
				home.assignTaskTo(pmAccount, policyNum, "Premium Receivable", url);
				if(pmPage==null)
					pmPage=new PMPage(driver);
				home.backHome();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			//home.filterpolicy(policyNum);
			TimeUnit.SECONDS.sleep(20);
			//home.openFirstTask();
			GroupReceivable groupReceivable = new GroupReceivable(driver);
			
			//groupReceivable.refund();
			//groupReceivable.transfer(policyNum);
			//groupReceivable.adjust();
			groupReceivable.pending();
			
			//groupReceivable.submit();

			
			//groupReceivable=home.submitSuccess();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return gReceivable;
	}
	public static void main(String ...args) throws Exception
	{
	}

}
