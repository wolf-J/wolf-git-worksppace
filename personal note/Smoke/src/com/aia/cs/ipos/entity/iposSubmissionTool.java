package com.aia.cs.ipos.entity;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.reporters.Files;

import util.CheckPolicyInCoast;
import util.DateTimeUtil;
import util.ExcelToPojo;
import util.FileUtil;
import util.HttpUtil;
import util.HttpsUtil;
import util.JncryptorTool;
import util.JsonUtil;
import util.SoapBodyUtil;
import util.SoapResultExtractor;

public class iposSubmissionTool {
	
	
	public static String submitIPos() throws Exception
	{
		boolean UAT=false;
		
		System.out.println("สคุมทรัพย์สินต่างประเทศ");
		
		Properties prop=new Properties();
		prop.load(new FileInputStream(System.getProperty("user.dir")+File.separator+"Environment.properties"));
		
		
		String environment=prop.getProperty("Environment");
		String template=prop.getProperty("template");
		
		if(environment.equalsIgnoreCase("UAT"))
		{
			UAT=true;
			System.out.println("Would submit policy into UAT Environment.");
		}
		else
		{
			System.out.println("Would submit policy into SIT Environment.");
		}
		// TODO Auto-generated method stub
		//*****************
				String contactId="152495|Company|"+DateTimeUtil.timestamp();
				String eappPDFId="152495|PDF|"+DateTimeUtil.timestamp();
				String proposalInfoId="152495|PropList|"+DateTimeUtil.timestamp();
				String memberPDFId="152495|PDF"+DateTimeUtil.timestamp();
				String refNo="152495-"+DateTimeUtil.refNoTime();
				String eappInfoId="152495|AppList|"+DateTimeUtil.timestamp();
				String PDFId="152495|PDF|"+DateTimeUtil.timestamp();
				
				//*****************
				
			/*	System.out.println("Usage: ");
				System.out.println("=====================================================================");
				System.out.println("There is one input for the tool - Excel Template for the tool");
				System.out.println("=====================================================================");
				
				System.out.println("Please input the excel template of iPOS CS");
				Scanner scan=new Scanner(System.in);
				String filename=null;
				File f=null;
			
				filename=scan.nextLine();
				f=new File(filename);
				while(!f.exists() || !f.getName().contains(".xlsx"))
				{
					System.out.println("Incorrect input, please re-input the tempalte!!!");
					filename=scan.nextLine();
					f=new File(filename);
				}
				
				System.out.println("Template is ready, start submit policy...");
				
				*/
				
				
				System.out.println("Current directory: "+System.getProperty("user.dir"));
				
				String path=System.getProperty("user.dir");
				
				
				
				FileInputStream fin=null;
				try{
					fin=new FileInputStream(path+File.separator+template);
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
					e.printStackTrace();
					System.exit(1);
				}
				
				System.out.println("Open the template successfully, would load the template data then.");
				XSSFWorkbook workbook=new XSSFWorkbook(fin);
				
				Contact obj=ExcelToPojo.getContact(workbook);
				System.out.println("Company Name: "+obj.getCompanyname());
				System.out.println("Contact: "+JsonUtil.formatObjectToJson(obj));
				obj.setCreatetime(DateTimeUtil.getCreateTime());
				obj.setUpdatetime(DateTimeUtil.getUpdateTime());
				obj.setContactid(contactId);
				
				EappInfo eapp=ExcelToPojo.getEAppInfo(workbook);
				eapp.setSigneddate(DateTimeUtil.getCreateTime());
				eapp.setEappInfoid(eappInfoId);
				eapp.setEapppdfid(eappPDFId);
				eapp.setProposalinfoid(proposalInfoId);
				eapp.setMemberpdfid(memberPDFId);
				eapp.setRefno(refNo);
				eapp.setContactid(contactId);
				eapp.setCompletedate(DateTimeUtil.getCreateTime());
				eapp.setUpdatetime(DateTimeUtil.getUpdateTime());
				eapp.setEappformid("eAppF-"+DateTimeUtil.refNoTime());
				
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
				String base64PDF=FileUtil.readFileAndConvertToBase64(path+File.separator+"test_en.pdf");
				for(int i=0;i<pdfs.length;i++)
				{
					pdfs[i].setDoccontenten(base64PDF);
					pdfs[i].setDoccontentthai(base64PDF);
				}
				
				
				pdfs[0].setPdfid(PDFId);
				pdfs[1].setPdfid(eappPDFId);
				pdfs[2].setPdfid(memberPDFId);
				
				MemberContact[] members=ExcelToPojo.getMemberContact(workbook);
				
				int totalSize=members.length+proposalInfo.getDependents();
				System.out.println("Total number for Member + Dependent: "+totalSize);
				
				//System.out.println(totalSize);
				int memberSize=members.length;
				System.out.println("Total Member numbner: "+memberSize);
				//System.out.println(memberSize);
				//********Prepare Images for Members
				String[] imageIds=new String[totalSize];
				for(int i=0;i<totalSize;i++)
				{
					imageIds[i]="152495|PhotoHel|"+DateTimeUtil.timestamp();
				}
				//********
				int addedImageIndex=0;
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
						members[i].getSubMember()[j].setBankcode(members[i].getBankcode());
						members[i].getSubMember()[j].setBankaccount(members[i].getBankaccount());
						addedImageIndex=i;
					}
				}
				
				
				List<String> notAssignImages=null;
				if(addedImageIndex!=(totalSize-1))
				{
					notAssignImages=new ArrayList<String>((totalSize-addedImageIndex-1));
					for(int i=addedImageIndex+1;i<totalSize;i++)
					{
						notAssignImages.add(imageIds[i]);
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
				
				//Prepare Image
				Image[] images=null;
				
				//List<String> ids=new ArrayList<String>();
				
				if(iposPM.getProposalInfo().getProductname()==null)
				{
					System.out.println("getProposalInfo == null");
				}
				
				
				
				
				if(iposPM.getProposalInfo().getProductname().contains("5UP"))
				{
					images=new Image[totalSize+3];
					
					String imageBase64=FileUtil.readFileAndConvertToBase64(path+File.separator+"image1.tiff");
					
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
				}else
				{
					images=new Image[3];				
					String imageBase64=FileUtil.readFileAndConvertToBase64(path+File.separator+"image1.tiff");
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
					}
				}
				
				/*else if(iposPM.getProposalInfo().getProductname().equalsIgnoreCase("5UP+"))
				{
					images=new Image[members.length+iposPM.getProposalInfo().getDependents()+9];
					String imageBase64=FileUtil.readFileAndConvertToBase64("D:/image1.tiff");
					for(int i=0;i<images.length;i++)
					{
						if(i<=7)
						{
							Image image=new Image();
							image.setDoccontent(imageBase64);
							image.setEappInfoid(eappInfoId);
							image.setImageid("152495|PhotoDoc|"+DateTimeUtil.timestamp());
							image.setImagetype("2");
							images[i]=image;
						}
						else if(i==8)
						{
							Image image=new Image();
							image.setDoccontent(imageBase64);
							image.setEappInfoid(eappInfoId);
							image.setImageid("152495|PhotoDoc|"+DateTimeUtil.timestamp());
							image.setImagetype("3");
							images[i]=image;
						}
						else if(i>8)
						{
							Image image=new Image();
							image.setDoccontent(imageBase64);
							image.setEappInfoid(eappInfoId);
							image.setImageid(imageIds[i-9]);
							image.setImagetype("4");
							images[i]=image;
						}
					}
				}
				*/
				iposPM.setImage(images);
				//System.out.println(JsonUtil.formatObjectToJson(iposPM).replace("\\\\", "\\"));
				
				
				iposPM.setProposalInfo(proposalInfo);
				List<SubMember> dependent=ExcelToPojo.getDependentQueue(workbook);
				if(dependent.size()==0)
				{
					System.out.println("The dependent list is empty, if you want to add, please fill the sheet, otherwise, ignore...");
				}
				
				//Remember dependent items get added
				List<Integer> addedItems=new ArrayList<Integer>();
				
				
				
				//There is a issue for adding dependents
				for(MemberContact contact : members )
				{
					if(contact.getDependentCount()>0)
					{
						int addedCount=0;
						int dependentCount=contact.getDependentCount();
						
						for(int i=0;i<dependentCount;i++)
						{
							for(int j=0;j<dependent.size() && addedCount<dependentCount;j++)
							{
								if(dependent.get(j).getPosition().equalsIgnoreCase(contact.getPosition()))
								{
									addedCount++;
									SubMember dep=dependent.get(j);
									dep.setContactid(contactId);
									dep.setMembercontactid(contact.getMembercontactid());
									dep.setSubmemberid("152495|Member|"+DateTimeUtil.timestamp());
									String photoId=notAssignImages.get(0);
									notAssignImages.remove(photoId);
									dep.setPhoto(photoId);
									dep.setEappInfoid(eappInfoId);
									dep.setBankcode(contact.getBankcode());
									dep.setBankaccount(contact.getBankaccount());
									SubMember[] subs=ArrayUtils.add(contact.getSubMember(), dep);
									dependent.remove(dep);
									contact.setSubMember(subs);
								}
							}
							
						}
					}
				}
				
				
				for(MemberContact member: members)
				{
					SubMember[] sm=member.getSubMember();
					for(SubMember s : sm)
					{
						int dotIndex=s.getAge().indexOf(".");
						if(dotIndex!=-1)
						{
							s.setAge(s.getAge().substring(0,dotIndex));
						}
					}
				}
				/*
				for(SubProposalInfo s : iposPM.getProposalInfo().getSubProposalInfo())
				{
					if(s.getDependentnum()>0)
					{
						int depenNum=s.getDependentnum();
						System.out.println("There are "+depenNum+" dependents.");
						for(int i=0;i<depenNum;)
						{
							for(MemberContact m : members)
							{
								if(m.getPosition().equalsIgnoreCase(s.getClassification()))
								{
									SubMember subMember=null;
									
									for(SubMember sm : dependent)
									{
										if(sm.getPosition().equalsIgnoreCase(m.getPosition()))
										{
											if(!addedItems.contains(dependent.indexOf(sm)) && i<depenNum)
											{
												subMember=sm;
												subMember.setContactid(contactId);
												subMember.setMembercontactid(m.getMembercontactid());
												subMember.setSubmemberid("152495|Member|"+DateTimeUtil.timestamp());
												String photoId=notAssignImages.get(0);
												notAssignImages.remove(photoId);
												subMember.setPhoto(photoId);
												//Noted
												SubMember[] newOne=ArrayUtils.add(m.getSubMember(), subMember);
												m.setSubMember(newOne);
												addedItems.add(dependent.indexOf(sm));
												i++;
											}else
											{
												continue;
											}
											
										}
									}
									
								}
							}
						}
					}
				}
				*/
				iposPM.setMemberContact(members);
				
				
				System.out.println("Loading the template data done, try to submit to policy...");
				String encryptDataFile=JsonUtil.formatObjectToJson(iposPM).replace("\\\\", "\\");
				
				
		
				for(Pdf p : iposPM.getPdf())
				{
					p.setDoccontenten("");
					p.setDoccontentthai("");
				}
				
				for(Image i : iposPM.getImage())
				{
					i.setDoccontent("");
				}
				
				//朱建
				Files.writeFile(new String(JsonUtil.formatObjectToJson(iposPM).replace("\\\\", "\\").getBytes(),"UTF-8"), new File(path+File.separator+"request"+DateTimeUtil.timestamp()+".txt"));
				
				String dataFile=JncryptorTool.encrypt(encryptDataFile, "29840ad5315c40baab688f1a59fe4fa1");
				Map<String,String> header=new HashMap<String,String>();
				header.put("Cookie", "dtCookie=81B75BE571665F2F392B33F7E310B1DB|X2RlZmF1bHR8MQ");
				
				
				
				String result=null;
				//UAT env
				if(UAT)
				{
					System.out.println("Calling UAT service: "+prop.getProperty("CoastUAT"));
					result=HttpsUtil.doPostRequest(prop.getProperty("CoastUAT"), header, SoapBodyUtil.getRequestXml("ACT_SUBMITION","152495","AwEwHgizsRwsMDoVnUD1rD/pdwxSTm2RcZOxOicEAkHbK93X7RRBMzQsz8wXTwN8ThN+AuFFGtCBjGA58wiJ5/Agm736JpSTJyFCvAysWIy9tKt/Pw4KrkolMO8vvJcVsk1AjBhsvOPoHjgPIgzbXqobsi73WuOCr7tCAohSYBAnSnEHFn3SvGJGKMToFvfl/mc=",dataFile), "UTF-8");
				}else
				//Sit env
				{
					result=HttpUtil.doPostRequest(prop.getProperty("CoastSIT"), header, SoapBodyUtil.getRequestXml("ACT_SUBMITION","152495","AwEwHgizsRwsMDoVnUD1rD/pdwxSTm2RcZOxOicEAkHbK93X7RRBMzQsz8wXTwN8ThN+AuFFGtCBjGA58wiJ5/Agm736JpSTJyFCvAysWIy9tKt/Pw4KrkolMO8vvJcVsk1AjBhsvOPoHjgPIgzbXqobsi73WuOCr7tCAohSYBAnSnEHFn3SvGJGKMToFvfl/mc=",dataFile), "UTF-8");
				}
				
				
				
				String encrpptMessage=SoapResultExtractor.extractReturnMessage(result);
				
				System.out.println(UAT? "The policy under UAT" : "The policy under SIT");
				String decryptMessage=JncryptorTool.decrypt(encrpptMessage, "29840ad5315c40baab688f1a59fe4fa1");
				System.out.println(decryptMessage);
				System.out.println("Gonna to check the policy number in coast.");
				iPOSCSResult r=(iPOSCSResult) JsonUtil.formatJsonToObject(decryptMessage, iPOSCSResult.class);
				
				String policyNum=r.getPolicyNo();
				System.out.println("policy Num: "+policyNum);
				
				CheckPolicyInCoast check=new CheckPolicyInCoast(UAT);
				String resultInCoast=null;
				if(UAT)
				{
					resultInCoast=check.policyInCoast(policyNum);
				}else
				{
					resultInCoast=check.policyInCoast(policyNum);
				}
				
				
				
				System.out.println(resultInCoast);
				//System.out.println(SoapResultExtractor.extractReturnMessage(result));
				
				System.out.println("OK, Found the Policy "+policyNum+" in Coast, Please start at Coast.");
				return policyNum;
	}
	
	public static void main(String ...args) throws Exception
	{
		submitIPos();
	}
}
