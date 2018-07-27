package com.aia.cs.ipos.entity;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.reporters.Files;

import util.DateTimeUtil;
import util.ExcelToPojo;
import util.FileUtil;
import util.HttpUtil;
import util.JncryptorTool;
import util.JsonUtil;
import util.SoapBodyUtil;
import util.SoapResultExtractor;

public class Entity {
	private Contact Contact;
	public Contact getContact() {
		return Contact;
	}
	public void setContact(Contact contact) {
		Contact = contact;
	}
	public EappInfo getEappInfo() {
		return EappInfo;
	}
	public void setEappInfo(EappInfo eappInfo) {
		EappInfo = eappInfo;
	}
	public ProposalInfo getProposalInfo() {
		return ProposalInfo;
	}
	public void setProposalInfo(ProposalInfo proposalInfo) {
		ProposalInfo = proposalInfo;
	}
	public Pdf[] getPdf() {
		return Pdf;
	}
	public void setPdf(Pdf[] pdf) {
		Pdf = pdf;
	}
	public MemberContact[] getMemberContact() {
		return MemberContact;
	}
	public void setMemberContact(MemberContact[] memberContact) {
		MemberContact = memberContact;
	}
	public AgentEmails[] getAgentEmails() {
		return AgentEmails;
	}
	public void setAgentEmails(AgentEmails[] agentEmails) {
		AgentEmails = agentEmails;
	}
	public Image[] getImage() {
		return Image;
	}
	public void setImage(Image[] image) {
		Image = image;
	}
	private EappInfo EappInfo;
	private ProposalInfo ProposalInfo;
	private Pdf[] Pdf;
	private MemberContact[] MemberContact;
	private AgentEmails[] AgentEmails;
	private Image[] Image;
	
	
	
	
	public static void main(String ...args) throws Exception
	{
		//*****************
		String contactId="152495|Company|"+DateTimeUtil.timestamp();
		String eappPDFId="152495|PDF|"+DateTimeUtil.timestamp();
		String proposalInfoId="152495|PropList|"+DateTimeUtil.timestamp();
		String memberPDFId="152495|PDF"+DateTimeUtil.timestamp();
		String refNo="152495-"+DateTimeUtil.refNoTime();
		String eappInfoId="152495|AppList|"+DateTimeUtil.timestamp();
		String PDFId="152495|PDF|"+DateTimeUtil.timestamp();
		
		//*****************
		FileInputStream fin=new FileInputStream("D:/iPosSubmission.xlsx");
		
		XSSFWorkbook workbook=new XSSFWorkbook(fin);
		Contact obj=ExcelToPojo.getContact(workbook);
		obj.setCreatetime(DateTimeUtil.getCreateTime());
		obj.setUpdatetime(DateTimeUtil.getUpdateTime());
		obj.setContactid(contactId);
		
		EappInfo eapp=ExcelToPojo.getEAppInfo(workbook);
		eapp.setEappInfoid(eappInfoId);
		eapp.setEapppdfid(eappPDFId);
		eapp.setProposalinfoid(proposalInfoId);
		eapp.setMemberpdfid(memberPDFId);
		eapp.setRefno(refNo);
		eapp.setContactid(contactId);
		eapp.setCompletedate(DateTimeUtil.getCreateTime());
		eapp.setUpdatetime(DateTimeUtil.getUpdateTime());
		eapp.setEappformid("eAppF-"+DateTimeUtil.refNoTime());
		
		//Update version 2
		ProposalInfo proposalInfo=ExcelToPojo.getProposalInfo(workbook);
		proposalInfo.setContactid(contactId);
		proposalInfo.setPdfid(PDFId);
		proposalInfo.setProposalinfoid(proposalInfoId);
		SubProposalInfo[] subProposal=proposalInfo.getSubProposalInfo();
		for(SubProposalInfo sp : subProposal)
		{
			sp.setProposalinfoid(proposalInfoId);
			sp.setSubproposalinfoid("152495|Proposal|"+DateTimeUtil.timestamp());
		}
		
		
		
		Pdf[] pdfs=ExcelToPojo.getPdf(workbook);
		String base64PDF=FileUtil.readFileAndConvertToBase64("D:/test.pdf");
		for(int i=0;i<pdfs.length;i++)
		{
			pdfs[i].setDoccontenten(base64PDF);
			pdfs[i].setDoccontentthai(base64PDF);
		}
		
		
		pdfs[0].setPdfid(PDFId);
		pdfs[1].setPdfid(eappPDFId);
		pdfs[2].setPdfid(memberPDFId);
		
		MemberContact[] members=ExcelToPojo.getMemberContact(workbook);
		
		
		int memberSize=members.length;
		//********Prepare Images for Members
		String[] imageIds=new String[memberSize];
		for(int i=0;i<memberSize;i++)
		{
			imageIds[i]="152495|PhotoHel|"+DateTimeUtil.timestamp();
		}
		//********
		
		for(int i=0;i<memberSize;i++)
		{
			members[i].setContactid(contactId);
			members[i].setEappInfoid(eappInfoId);
			String memberContactId="152495|MemberList|"+DateTimeUtil.timestamp();
			members[i].setMembercontactid(memberContactId);
			int subMemberSize=members[i].getSubMember().length;
			for(int j=0;j<subMemberSize;j++)
			{
				members[i].getSubMember()[j].setContactid(contactId);
				members[i].getSubMember()[j].setEappInfoid(eappInfoId);
				members[i].getSubMember()[j].setSubmemberid("152495|Member|"+DateTimeUtil.timestamp());
				members[i].getSubMember()[j].setMembercontactid(memberContactId);
				members[i].getSubMember()[j].setPhoto(imageIds[i]);
			}
		}
		
		
		AgentEmails[] agents=ExcelToPojo.getAgentEmail(workbook);
		Entity iposPM=new Entity();
		iposPM.setContact(obj);
		iposPM.setEappInfo(eapp);
		iposPM.setProposalInfo(proposalInfo);
		iposPM.setPdf(pdfs);
		iposPM.setMemberContact(members);
		iposPM.setAgentEmails(agents);
		Image[] images=new Image[members.length+3];
		
		
		String imageBase64=FileUtil.readFileAndConvertToBase64("D:/image1.tiff");
		for(int i=0;i<images.length;i++)
		{
			if(i<=1)
			{
				Image image=new Image();
				image.setDoccontent(imageBase64);
				image.setEappInfoid(eappInfoId);
				image.setImageid("152495|PhotoDoc|"+DateTimeUtil.timestamp());
				image.setImagetype("2");
				images[i]=image;
			}
			else if(i==2)
			{
				Image image=new Image();
				image.setDoccontent(imageBase64);
				image.setEappInfoid(eappInfoId);
				image.setImageid("152495|PhotoDoc|"+DateTimeUtil.timestamp());
				image.setImagetype("3");
				images[i]=image;
			}
			else if(i>=3)
			{
				Image image=new Image();
				image.setDoccontent(imageBase64);
				image.setEappInfoid(eappInfoId);
				image.setImageid(imageIds[i-3]);
				image.setImagetype("4");
				images[i]=image;
			}
		}
		iposPM.setImage(images);
		//System.out.println(JsonUtil.formatObjectToJson(iposPM).replace("\\\\", "\\"));
		
		
		String encryptDataFile=JsonUtil.formatObjectToJson(iposPM).replace("\\\\", "\\");
		
		Files.writeFile(encryptDataFile, new File("D:/request.txt"));
		String dataFile=JncryptorTool.encrypt(encryptDataFile, "29840ad5315c40baab688f1a59fe4fa1");
		Map<String,String> header=new HashMap<String,String>();
		header.put("Cookie", "dtCookie=81B75BE571665F2F392B33F7E310B1DB|X2RlZmF1bHR8MQ");
		//Sit env
		String result=HttpUtil.doPostRequest("http://10.72.5.218/iPoS_CS_Thai_Staging/ServerWSService", header, SoapBodyUtil.getRequestXml("ACT_SUBMITION","152495","AwEwHgizsRwsMDoVnUD1rD/pdwxSTm2RcZOxOicEAkHbK93X7RRBMzQsz8wXTwN8ThN+AuFFGtCBjGA58wiJ5/Agm736JpSTJyFCvAysWIy9tKt/Pw4KrkolMO8vvJcVsk1AjBhsvOPoHjgPIgzbXqobsi73WuOCr7tCAohSYBAnSnEHFn3SvGJGKMToFvfl/mc=",dataFile), "UTF-8");
		
		
		Files.writeFile(result, new File("D:/result.txt"));
		//System.out.println(SoapResultExtractor.extractReturnMessage(result));
		System.out.println("done");
		
		
	}
	
}
