package com.aia.coast.testcase;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.JsonUtil;

public class CaseUtil {
	
	public static List<CoastCase> getCoastTestCases() throws Exception
	{
		List<CoastCase> coastTestCases=new ArrayList<>();
		
		String template="Coast Case Data Template.xlsx";
		String path=System.getProperty("user.dir");
		String coastTemplate=path+File.separator+template;
		System.out.println("Template Location: "+coastTemplate);
		FileInputStream fin=new FileInputStream(coastTemplate);
		
		XSSFWorkbook workbook=new XSSFWorkbook(fin);
		
		XSSFSheet sheet=workbook.getSheet("Client");
	
		
		XSSFRow row=sheet.getRow(0);
		int columnNum=row.getPhysicalNumberOfCells();
		System.out.println("There are "+columnNum+" columns.");
		
		int startLine=2;
		int casesNum=sheet.getLastRowNum();
		int caseStart=0;
		int caseEnd=0;
		int clientStart=0;
		int clientEnd=0;
		//add for policy year
		int policyYearStart=0;
		int policyYearEnd=columnNum-1;
		
		final int TitleRow=1;
	
		
		
		for(int i=0;i<columnNum;i++)
		{
			Cell cell=row.getCell(i);
			if(cell==null)
			{
				continue;
			}
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("Cases"))
			{
				caseStart=i;
			}
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("Client"))
			{
				caseEnd=i-1;
				clientStart=i;
				System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
			}
			//for policy year
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("PolicyYear"))
			{
				clientEnd=i-1;
				policyYearStart=i;
			}
			
		}
		
		System.out.println("Case Start: "+caseStart+", case end: "+caseEnd+", client start: "+clientStart+", client end: "+clientEnd+", policyYear start: "+policyYearStart+", policyYearEnd: "+policyYearEnd);
		
		XSSFRow headerRow=sheet.getRow(TitleRow);
		List<Case> cases=new ArrayList<>();
		List<Client> clients=new ArrayList<>();
		List<DistributionChannel> dcs=new ArrayList<>();
		List<CoastCase> testcases=new ArrayList<>();
		
		List<Integer> runCases=new ArrayList<>();
		
		//Find need to run test case
		for(;startLine<=casesNum;startLine++)
		{
			
				CoastCase testcase=new CoastCase();
				row=sheet.getRow(startLine);
				System.out.println("Current line: "+startLine);
				Case cas=new Case();
				for(int localCaseStart=caseStart;localCaseStart<=caseEnd;localCaseStart++)
				{
					
					String fieldName=headerRow.getCell(localCaseStart).getStringCellValue();
					Cell cell=row.getCell(localCaseStart);
				
						Method m=null;
					
						m=cas.getClass().getMethod("set"+fieldName, String.class);
						System.out.println(m.getName());
						
						if(m==null)
						{
							System.out.println(m.getName()+" is null.");
						}
						m.invoke(cas, getCellValue(cell));
	
				}
			
				if(Boolean.valueOf(cas.isRun()))
				{
					System.out.println("There is a: "+cas.isRun()+" and the case id is: "+cas.getCaseId());
					cases.add(cas);
					runCases.add(startLine);
				}
			
		}
		
		if(cases.size()==0)
		{
			System.out.println("There is no cases setting to run!!!.");
		}else
		{
			for(Case c : cases)
			{
				System.out.println(c.getCaseId()+" need to run.");
			}
		}
		
		int caseSeq=0;
		for(int runRow : runCases)
		{
			
			CoastCase testcase=new CoastCase();
			testcase.setCas(cases.get(caseSeq));
			row=sheet.getRow(runRow);
			System.out.println("Current line for run case: "+runRow);
			
			Client client=new Client();
			PolicyYear policyYear=new PolicyYear();
			
			DistributionChannel dc=new DistributionChannel();
			
			
			
			for(int localClientStart=clientStart;localClientStart<=clientEnd;localClientStart++)
			{
				
				String fieldName=headerRow.getCell(localClientStart).getStringCellValue();
				Cell cell=row.getCell(localClientStart);
			
					Method m=null;
				
					m=client.getClass().getMethod("set"+fieldName, String.class);
					System.out.println(m.getName());
					
					if(m==null)
					{
						System.out.println(m.getName()+" is null.");
					}
					m.invoke(client, getCellValue(cell));
					
					
			}
			
			//add for policy Year
			for(int localPolicyYearStart=policyYearStart;localPolicyYearStart<=policyYearEnd;localPolicyYearStart++)
			{
				
				String fieldName=headerRow.getCell(localPolicyYearStart).getStringCellValue();
				Cell cell=row.getCell(localPolicyYearStart);
			
					Method m=null;
				
					m=policyYear.getClass().getMethod("set"+fieldName, String.class);
					System.out.println(m.getName());
					
					if(m==null)
					{
						System.out.println(m.getName()+" is null.");
					}
					m.invoke(policyYear, getCellValue(cell));
					
					
			}
			
			
			
				
			testcase.setClient(client);
			testcase.setPolicyYear(policyYear);
			testcases.add(testcase);
			caseSeq++;
			}

		
		//Read Policy & Individual Bill
		XSSFSheet policySheet=workbook.getSheet("Policy & IndividualBill");
		
		XSSFRow policyHeaderRow=policySheet.getRow(0);
		XSSFRow policyAndIndivFieldRow=policySheet.getRow(1);
		int policyColumnNum=policyHeaderRow.getPhysicalNumberOfCells();
		System.out.println("There are "+policyColumnNum+" columns in Policy Sheet.");
		int policyRowNum=policySheet.getLastRowNum();
		System.out.println("There are "+policyRowNum+" rows in Policy Sheet.");
		
		List<Integer> policyLines=new ArrayList<>();
		//Find if there are test case need to run.
		
		int policyStart=0;
		int policyEnd=0;
		int indivBillStart=0;
		int indivBillEnd=policyColumnNum-1;
		
		for(int i=0;i<policyColumnNum;i++)
		{
			Cell cell=policyHeaderRow.getCell(i);
			if(cell==null)
			{
				continue;
			}
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("policy"))
			{
				policyStart=i;
			}
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("IndividualBill"))
			{
				policyEnd=i-1;
				indivBillStart=i;
				System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
			}
			
		}
		
		
		if(testcases.size()>0)
		{
			for(int policyRow=2;policyRow<=policyRowNum;policyRow++)
			{
				XSSFRow pRow=policySheet.getRow(policyRow);
				Cell pCell=pRow.getCell(0);
				for(CoastCase c : testcases)
				{
					XSSFRow addedRow=null;
					if(c.getCas().getCaseId().equals(getCellValue(pCell).trim()))
					{
						Policy policy=new Policy();
						IndividualBilling indBilling=new IndividualBilling();
						policyLines.add(policyRow);
						
						addedRow=policySheet.getRow(policyRow);
						
						for(int localpolicyStart=policyStart;localpolicyStart<=policyEnd;localpolicyStart++)
						{
							
							String fieldName=policyAndIndivFieldRow.getCell(localpolicyStart).getStringCellValue();
							Cell cell=addedRow.getCell(localpolicyStart);
						
								Method m=null;
							
								m=policy.getClass().getMethod("set"+fieldName, String.class);
								System.out.println(m.getName());
								
								if(m==null)
								{
									System.out.println(m.getName()+" is null.");
								}
								m.invoke(policy, getCellValue(cell));
								
						}
						c.setPolicy(policy);
						if(Boolean.valueOf(policy.isIncludeVoluntaryPolicy()))
						{
							IndividualBilling indivBill=new IndividualBilling();
							for(int localIndivBillStart=indivBillStart;localIndivBillStart<=indivBillEnd;localIndivBillStart++)
							{
								
								String fieldName=policyAndIndivFieldRow.getCell(localIndivBillStart).getStringCellValue();
								Cell cell=addedRow.getCell(localIndivBillStart);
							
									Method m=null;
								
									m=indivBill.getClass().getMethod("set"+fieldName, String.class);
									System.out.println(m.getName());
									
									if(m==null)
									{
										System.out.println(m.getName()+" is null.");
									}
									m.invoke(indivBill, getCellValue(cell));
									
							}
							c.setIndivBilling(indivBill);
						}
						else
						{
							c.setIndivBilling(null);
						}
						System.out.println("Add the policy into test case."+policyRow);
						
					}
				}
			}
		}
		
		
		XSSFSheet productSheet=workbook.getSheet("PolicyProduct & Distribution");
		
		XSSFRow productHeaderRow=productSheet.getRow(0);
		XSSFRow product=productSheet.getRow(1);
		int productColumnNum=productHeaderRow.getPhysicalNumberOfCells();
		System.out.println("There are "+productColumnNum+" columns in Product Sheet.");
		int productRowNum=productSheet.getLastRowNum();
		System.out.println("There are "+policyRowNum+" rows in Product Sheet.");
		
		//Policy Product
		int schemeStart=0;
		int schemeEnd=0;
		int productStart=0;
		int productEnd=productColumnNum-1;
		
		for(int i=0;i<productColumnNum;i++)
		{
			Cell cell=productHeaderRow.getCell(i);
			if(cell==null)
			{
				continue;
			}
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("PackageScheme"))
			{
				schemeStart=i;
			}
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("PolicyProduct"))
			{
				schemeEnd=i-1;
				productStart=i;
				System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
			}

			
		}
	
		
		if(testcases.size()>0)
		{
			for(CoastCase c : testcases)
			{
				
				List<PackageScheme> schemes=new ArrayList<>();
				
				
				List<PolicyProduct> policyProducts=new ArrayList<>();
				
				for(int productRow=2;productRow<=productRowNum;productRow++)
				{
					XSSFRow prRow=productSheet.getRow(productRow);
					Cell pdCell=prRow.getCell(0);
					if(getCellValue(pdCell).equals(c.getCas().getCaseId()))
					{
						PackageScheme scheme=new PackageScheme();
						for(int localSchemeStart=schemeStart;localSchemeStart<=schemeEnd;localSchemeStart++)
						{
							String fieldName=product.getCell(localSchemeStart).getStringCellValue();
							Cell cell=prRow.getCell(localSchemeStart);
							
							if(cell!=null)
							{
								
								Method m=null;
								m=scheme.getClass().getMethod("set"+fieldName, String.class);
								
								m.invoke(scheme, getCellValue(cell));
								
							}
						}
						schemes.add(scheme);
						PolicyProduct pProduct=new PolicyProduct();
						for(int localProductStart=productStart;localProductStart<=productEnd;localProductStart++)
						{
							
							String fieldName=product.getCell(localProductStart).getStringCellValue();
							Cell cell=prRow.getCell(localProductStart);
						
								Method m=null;
							
								m = pProduct.getClass().getMethod("set"+fieldName, String.class);
								System.out.println(m.getName());
								
								if(m==null)
								{
									System.out.println(m.getName()+" is null.");
								}
								m.invoke(pProduct, getCellValue(cell));
								
						}
					
						
						policyProducts.add(pProduct);
					}
						
				}
				c.setProducts(policyProducts);
				c.setPackageScheme(schemes);
			}
		}
		
		//Product Plan and Benefit
			XSSFSheet planAndBenefitSheet=workbook.getSheet("ProducePlan & Benefit");
				
			XSSFRow planHeaderRow=planAndBenefitSheet.getRow(0);
			XSSFRow planAndBenefit=planAndBenefitSheet.getRow(1);
			int planColumnNum=planHeaderRow.getPhysicalNumberOfCells();
			System.out.println("There are "+planColumnNum+" columns in Product Plan Sheet.");
			int planRowNum=planAndBenefitSheet.getLastRowNum();
			System.out.println("There are "+planRowNum+" rows in Product Plan  Sheet.");
				
				
				int productPlanStart=0;
				int productPlanEnd=0;
				int benefitStart=0;
				int benefitEnd=planColumnNum-1;
				
				for(int i=0;i<planColumnNum;i++)
				{
					Cell cell=planHeaderRow.getCell(i);
					if(cell==null)
					{
						continue;
					}
					if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("ProductPlan"))
					{
						productPlanStart=i;
						System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
					}
					if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("Benefit"))
					{
						productPlanEnd=i-1;
						benefitStart=i;
						System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
					}
					
				}
			
				
				if(testcases.size()>0)
				{
					for(CoastCase c : testcases)
					{
						List<ProductPlan> productPlanss=new ArrayList<>();
						
						for(int productPlanRow=2;productPlanRow<=planRowNum;productPlanRow++)
						{
							XSSFRow ppRow=planAndBenefitSheet.getRow(productPlanRow);
							Cell ppCell=ppRow.getCell(0);
							if(getCellValue(ppCell).equals(c.getCas().getCaseId()))
							{
								ProductPlan plan=new ProductPlan();
								for(int localProductPlanStart=productPlanStart;localProductPlanStart<=productPlanEnd;localProductPlanStart++)
								{
									
									String fieldName=planAndBenefit.getCell(localProductPlanStart).getStringCellValue();
									Cell cell=ppRow.getCell(localProductPlanStart);
								
										Method m=null;
									
										m=plan.getClass().getMethod("set"+fieldName, String.class);
										System.out.println(m.getName());
										
										if(m==null)
										{
											System.out.println(m.getName()+" is null.");
										}
										m.invoke(plan, getCellValue(cell));
										
								}
								
								Benefit benefit=new Benefit();
								for(int localBenefitStart=benefitStart;localBenefitStart<=benefitEnd;localBenefitStart++)
								{
									String fieldName=planAndBenefit.getCell(localBenefitStart).getStringCellValue();
									Cell cell=ppRow.getCell(localBenefitStart);
								
										Method m=null;
										m=benefit.getClass().getMethod("set"+fieldName, String.class);
										System.out.println(m.getName());
										
										if(m==null)
										{
											System.out.println(m.getName()+" is null.");
										}
										m.invoke(benefit, getCellValue(cell));
								}
								plan.setBenefit(benefit);
								productPlanss.add(plan);
						}

						
					}
						c.setProductPlans(productPlanss);
					}
				
					
					//Distribution Channel part
					XSSFSheet distributionSheet=workbook.getSheet("DistributionChannel");
						
					XSSFRow dcHeaderRow=distributionSheet.getRow(0);
					XSSFRow dcField=distributionSheet.getRow(1);
					int dcColumnNum=dcHeaderRow.getPhysicalNumberOfCells();
					System.out.println("There are "+dcColumnNum+" columns in Distribution&Channel Sheet.");
					int dcRowNum=distributionSheet.getLastRowNum();
					System.out.println("There are "+dcRowNum+" rows in Distribution Sheet.");
						
						
						int subDCStart=0;
						int subDCEnd=0;
						int dcStart=0;
						int dcEnd=dcColumnNum-1;
						
						for(int i=0;i<dcColumnNum;i++)
						{
							Cell cell=dcHeaderRow.getCell(i);
							if(cell==null)
							{
								continue;
							}
							if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("SubDistributionChannel"))
							{
								subDCStart=i;
								System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
							}
							if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("ProductDistributionChannel"))
							{
								subDCEnd=i-1;
								dcStart=i;
								System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
							}
							
						}
					
						
						if(testcases.size()>0)
						{
							for(CoastCase c : testcases)
							{
								List<DistributionChannel> subDistributionChannel=new ArrayList<>();
								List<DistributionChannel> productDistributionChannel=new ArrayList<>();
								
								for(int dcRow=2;dcRow<=dcRowNum;dcRow++)
								{
									XSSFRow disRow=distributionSheet.getRow(dcRow);
									Cell dcCell=disRow.getCell(0);
									if(getCellValue(dcCell).equals(c.getCas().getCaseId()))
									{
										DistributionChannel subDC=new DistributionChannel();
										DistributionChannel productDC=new DistributionChannel();
										
										for(int localsubDCStart=subDCStart;localsubDCStart<=subDCEnd;localsubDCStart++)
										{
											
											String fieldName=dcField.getCell(localsubDCStart).getStringCellValue();
											Cell cell=disRow.getCell(localsubDCStart);
										
												Method m=null;
											
												m=subDC.getClass().getMethod("set"+fieldName, String.class);
												System.out.println(m.getName());
												
												if(m==null)
												{
													System.out.println(m.getName()+" is null.");
												}
												m.invoke(subDC, getCellValue(cell));
												
										}
										subDistributionChannel.add(subDC);
										
										
										for(int localdcStart=dcStart;localdcStart<=dcEnd;localdcStart++)
										{
											String fieldName=dcField.getCell(localdcStart).getStringCellValue();
											Cell cell=disRow.getCell(localdcStart);
										
												Method m=null;
												m=productDC.getClass().getMethod("set"+fieldName, String.class);
												System.out.println(m.getName());
												
												if(m==null)
												{
													System.out.println(m.getName()+" is null.");
												}
												m.invoke(productDC, getCellValue(cell));
										}
										productDistributionChannel.add(productDC);
										
								}

								
							}
								c.setDistributionChannel(subDistributionChannel);
								if(c.getProducts()!=null && c.getProducts().size()>0 && c.getProducts().get(0)!=null)
									c.getProducts().get(0).setDistributionChannel(productDistributionChannel);
							}
							
						}
				}
				
				//Member File Configuration
				XSSFSheet testConfigurationSheet=workbook.getSheet("TestConfiguration");
				
				XSSFRow tcHeaderRow=testConfigurationSheet.getRow(0);
				XSSFRow tcField=testConfigurationSheet.getRow(1);
				int tcColumnNum=tcHeaderRow.getPhysicalNumberOfCells();
				System.out.println("There are "+tcColumnNum+" columns in TestConfiguration Sheet.");
				int tcRowNum=testConfigurationSheet.getLastRowNum();
				System.out.println("There are "+tcRowNum+" rows in TestConfiguration Sheet.");
					
					
					int memberFileStart=0;
					int memberFileEnd=0;
					int billResultStart=0;
					int billResultEnd=tcColumnNum-1;
					
					for(int i=0;i<tcColumnNum;i++)
					{
						Cell cell=tcHeaderRow.getCell(i);
						if(cell==null)
						{
							continue;
						}
						if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("MemberFile"))
						{
							memberFileStart=i;
							System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
						}
						if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("BillResult"))
						{
							memberFileEnd=i-1;
							billResultStart=i;
							System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
						}
						
					}
				
					
					if(testcases.size()>0)
					{
						for(CoastCase c : testcases)
						{
							
							
							for(int tcRow=2;tcRow<=tcRowNum;tcRow++)
							{
								XSSFRow testConfigRow=testConfigurationSheet.getRow(tcRow);
								Cell tcCell=testConfigRow.getCell(0);
								System.out.println("Member Id: "+getCellValue(tcCell));
								if(getCellValue(tcCell).equals(c.getCas().getCaseId()))
								{
									MemberFile memberFile=new MemberFile();
									BillResult billResult=new BillResult();
									
									for(int localMemberFileStart=memberFileStart;localMemberFileStart<=memberFileEnd;localMemberFileStart++)
									{
										
										String fieldName=tcField.getCell(localMemberFileStart).getStringCellValue();
										Cell cell=testConfigRow.getCell(localMemberFileStart);
									
											Method m=null;
										
											m=memberFile.getClass().getMethod("set"+fieldName, String.class);
											System.out.println(m.getName());
											
											if(m==null)
											{
												System.out.println(m.getName()+" is null.");
											}
											m.invoke(memberFile, getCellValue(cell));
											
									}

									for(int localBillResultStart=billResultStart;localBillResultStart<=billResultEnd;localBillResultStart++)
									{
										String fieldName=tcField.getCell(localBillResultStart).getStringCellValue();
										Cell cell=testConfigRow.getCell(localBillResultStart);
									
											Method m=null;
											m=billResult.getClass().getMethod("set"+fieldName, String.class);
											System.out.println(m.getName());
											
											if(m==null)
											{
												System.out.println(m.getName()+" is null.");
											}
											m.invoke(billResult, getCellValue(cell));
									}
									
									c.setMemberFile(memberFile);
									c.setBillResult(billResult);
							}

								
						}
							
							
						}
						
					}
					
					//PM Change part
				
					XSSFSheet changePMSheet=workbook.getSheet("PMChange");
					
					XSSFRow chpHeaderRow=changePMSheet.getRow(0);
					XSSFRow chpField=changePMSheet.getRow(1);
					int chpColumnNum=chpHeaderRow.getPhysicalNumberOfCells();
					System.out.println("There are "+chpColumnNum+" columns in ChangePM Sheet.");
					int chpRowNum=changePMSheet.getLastRowNum();
					System.out.println("There are "+chpRowNum+" rows in ChangePM Sheet.");
						
						
					int pmChangeStart=0;
					int pmChangeEnd=chpColumnNum-1;
						
					for(int i=0;i<chpColumnNum;i++)
						{
							Cell cell=chpHeaderRow.getCell(i);
							if(cell==null)
							{
								continue;
							}
							if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("PMChange"))
							{
								pmChangeStart=i;
								System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
							}
							
						}
					
						
						if(testcases.size()>0)
						{
							for(CoastCase c : testcases)
							{
								
								
								for(int pmcRow=2;pmcRow<=chpRowNum;pmcRow++)
								{
									XSSFRow pmchRow=changePMSheet.getRow(pmcRow);
									Cell pmchCell=pmchRow.getCell(0);
									
									if(getCellValue(pmchCell).equals(c.getCas().getCaseId()))
									{
										PMChange pmChange=new PMChange();
										for(int localPMChangeStart=pmChangeStart;localPMChangeStart<=pmChangeEnd;localPMChangeStart++)
										{
											
											String fieldName=chpField.getCell(localPMChangeStart).getStringCellValue();
											Cell cell=pmchRow.getCell(localPMChangeStart);
										
												Method m=null;
											
												m=pmChange.getClass().getMethod("set"+fieldName, String.class);
												System.out.println(m.getName());
												
												if(m==null)
												{
													System.out.println(m.getName()+" is null.");
												}
												m.invoke(pmChange, getCellValue(cell));
												
										}

										c.setPmChange(pmChange);
									}									
								}			
							}
						}
				return testcases;
	}
	

	
	public static List<CoastCase> getCoastTestCases(String template) throws Exception
	{
		List<CoastCase> coastTestCases=new ArrayList<>();
		
		String path=System.getProperty("user.dir");
		String coastTemplate=path+File.separator+template;
		System.out.println("Template Location: "+coastTemplate);
		FileInputStream fin=new FileInputStream(coastTemplate);
		
		XSSFWorkbook workbook=new XSSFWorkbook(fin);
		
		XSSFSheet sheet=workbook.getSheet("Client");
	
		
		XSSFRow row=sheet.getRow(0);
		int columnNum=row.getPhysicalNumberOfCells();
		System.out.println("There are "+columnNum+" columns.");
		
		int startLine=2;
		int casesNum=sheet.getLastRowNum();
		int caseStart=0;
		int caseEnd=0;
		int clientStart=0;
		int clientEnd=0;
		//add for policy year
		int policyYearStart=0;
		int policyYearEnd=columnNum-1;
		
		final int TitleRow=1;
	
		
		
		for(int i=0;i<columnNum;i++)
		{
			Cell cell=row.getCell(i);
			if(cell==null)
			{
				continue;
			}
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("Cases"))
			{
				caseStart=i;
			}
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("Client"))
			{
				caseEnd=i-1;
				clientStart=i;
				System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
			}
			//for policy year
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("PolicyYear"))
			{
				clientEnd=i-1;
				policyYearStart=i;
			}
			
		}
		
		System.out.println("Case Start: "+caseStart+", case end: "+caseEnd+", client start: "+clientStart+", client end: "+clientEnd+", policyYear start: "+policyYearStart+", policyYearEnd: "+policyYearEnd);
		
		XSSFRow headerRow=sheet.getRow(TitleRow);
		List<Case> cases=new ArrayList<>();
		List<Client> clients=new ArrayList<>();
		List<DistributionChannel> dcs=new ArrayList<>();
		List<CoastCase> testcases=new ArrayList<>();
		
		List<Integer> runCases=new ArrayList<>();
		
		//Find need to run test case
		for(;startLine<=casesNum;startLine++)
		{
			
				CoastCase testcase=new CoastCase();
				row=sheet.getRow(startLine);
				System.out.println("Current line: "+startLine);
				Case cas=new Case();
				for(int localCaseStart=caseStart;localCaseStart<=caseEnd;localCaseStart++)
				{
					
					String fieldName=headerRow.getCell(localCaseStart).getStringCellValue();
					Cell cell=row.getCell(localCaseStart);
				
						Method m=null;
					
						m=cas.getClass().getMethod("set"+fieldName, String.class);
						System.out.println(m.getName());
						
						if(m==null)
						{
							System.out.println(m.getName()+" is null.");
						}
						m.invoke(cas, getCellValue(cell));
	
				}
			
				if(Boolean.valueOf(cas.isRun()))
				{
					System.out.println("There is a: "+cas.isRun()+" and the case id is: "+cas.getCaseId());
					cases.add(cas);
					runCases.add(startLine);
				}
			
		}
		
		if(cases.size()==0)
		{
			System.out.println("There is no cases setting to run!!!.");
		}else
		{
			for(Case c : cases)
			{
				System.out.println(c.getCaseId()+" need to run.");
			}
		}
		
		int caseSeq=0;
		for(int runRow : runCases)
		{
			
			CoastCase testcase=new CoastCase();
			testcase.setCas(cases.get(caseSeq));
			row=sheet.getRow(runRow);
			System.out.println("Current line for run case: "+runRow);
			
			Client client=new Client();
			PolicyYear policyYear=new PolicyYear();
			
			DistributionChannel dc=new DistributionChannel();
			
			
			
			for(int localClientStart=clientStart;localClientStart<=clientEnd;localClientStart++)
			{
				
				String fieldName=headerRow.getCell(localClientStart).getStringCellValue();
				Cell cell=row.getCell(localClientStart);
			
					Method m=null;
				
					m=client.getClass().getMethod("set"+fieldName, String.class);
					System.out.println(m.getName());
					
					if(m==null)
					{
						System.out.println(m.getName()+" is null.");
					}
					m.invoke(client, getCellValue(cell));
					
					
			}
			
			//add for policy Year
			for(int localPolicyYearStart=policyYearStart;localPolicyYearStart<=policyYearEnd;localPolicyYearStart++)
			{
				
				String fieldName=headerRow.getCell(localPolicyYearStart).getStringCellValue();
				Cell cell=row.getCell(localPolicyYearStart);
			
					Method m=null;
				
					m=policyYear.getClass().getMethod("set"+fieldName, String.class);
					System.out.println(m.getName());
					
					if(m==null)
					{
						System.out.println(m.getName()+" is null.");
					}
					m.invoke(policyYear, getCellValue(cell));
					
					
			}
			
			
			
				
			testcase.setClient(client);
			testcase.setPolicyYear(policyYear);
			testcases.add(testcase);
			caseSeq++;
			}

		
		//Read Policy & Individual Bill
		XSSFSheet policySheet=workbook.getSheet("Policy & IndividualBill");
		
		XSSFRow policyHeaderRow=policySheet.getRow(0);
		XSSFRow policyAndIndivFieldRow=policySheet.getRow(1);
		int policyColumnNum=policyHeaderRow.getPhysicalNumberOfCells();
		System.out.println("There are "+policyColumnNum+" columns in Policy Sheet.");
		int policyRowNum=policySheet.getLastRowNum();
		System.out.println("There are "+policyRowNum+" rows in Policy Sheet.");
		
		List<Integer> policyLines=new ArrayList<>();
		//Find if there are test case need to run.
		
		int policyStart=0;
		int policyEnd=0;
		int indivBillStart=0;
		int indivBillEnd=policyColumnNum-1;
		
		for(int i=0;i<policyColumnNum;i++)
		{
			Cell cell=policyHeaderRow.getCell(i);
			if(cell==null)
			{
				continue;
			}
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("policy"))
			{
				policyStart=i;
			}
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("IndividualBill"))
			{
				policyEnd=i-1;
				indivBillStart=i;
				System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
			}
			
		}
		
		
		if(testcases.size()>0)
		{
			for(int policyRow=2;policyRow<=policyRowNum;policyRow++)
			{
				XSSFRow pRow=policySheet.getRow(policyRow);
				Cell pCell=pRow.getCell(0);
				for(CoastCase c : testcases)
				{
					XSSFRow addedRow=null;
					if(c.getCas().getCaseId().equals(getCellValue(pCell).trim()))
					{
						Policy policy=new Policy();
						IndividualBilling indBilling=new IndividualBilling();
						policyLines.add(policyRow);
						
						addedRow=policySheet.getRow(policyRow);
						
						for(int localpolicyStart=policyStart;localpolicyStart<=policyEnd;localpolicyStart++)
						{
							
							String fieldName=policyAndIndivFieldRow.getCell(localpolicyStart).getStringCellValue();
							Cell cell=addedRow.getCell(localpolicyStart);
						
								Method m=null;
							
								m=policy.getClass().getMethod("set"+fieldName, String.class);
								System.out.println(m.getName());
								
								if(m==null)
								{
									System.out.println(m.getName()+" is null.");
								}
								m.invoke(policy, getCellValue(cell));
								
						}
						c.setPolicy(policy);
						if(Boolean.valueOf(policy.isIncludeVoluntaryPolicy()))
						{
							IndividualBilling indivBill=new IndividualBilling();
							for(int localIndivBillStart=indivBillStart;localIndivBillStart<=indivBillEnd;localIndivBillStart++)
							{
								
								String fieldName=policyAndIndivFieldRow.getCell(localIndivBillStart).getStringCellValue();
								Cell cell=addedRow.getCell(localIndivBillStart);
							
									Method m=null;
								
									m=indivBill.getClass().getMethod("set"+fieldName, String.class);
									System.out.println(m.getName());
									
									if(m==null)
									{
										System.out.println(m.getName()+" is null.");
									}
									m.invoke(indivBill, getCellValue(cell));
									
							}
							c.setIndivBilling(indivBill);
						}
						else
						{
							c.setIndivBilling(null);
						}
						System.out.println("Add the policy into test case."+policyRow);
						
					}
				}
			}
		}
		
		
		XSSFSheet productSheet=workbook.getSheet("PolicyProduct & Distribution");
		
		XSSFRow productHeaderRow=productSheet.getRow(0);
		XSSFRow product=productSheet.getRow(1);
		int productColumnNum=productHeaderRow.getPhysicalNumberOfCells();
		System.out.println("There are "+productColumnNum+" columns in Product Sheet.");
		int productRowNum=productSheet.getLastRowNum();
		System.out.println("There are "+policyRowNum+" rows in Product Sheet.");
		
		//Policy Product
		int schemeStart=0;
		int schemeEnd=0;
		int productStart=0;
		int productEnd=productColumnNum-1;
		
		for(int i=0;i<productColumnNum;i++)
		{
			Cell cell=productHeaderRow.getCell(i);
			if(cell==null)
			{
				continue;
			}
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("PackageScheme"))
			{
				schemeStart=i;
			}
			if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("PolicyProduct"))
			{
				schemeEnd=i-1;
				productStart=i;
				System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
			}

			
		}
	
		
		if(testcases.size()>0)
		{
			for(CoastCase c : testcases)
			{
				
				List<PackageScheme> schemes=new ArrayList<>();
				
				
				List<PolicyProduct> policyProducts=new ArrayList<>();
				
				for(int productRow=2;productRow<=productRowNum;productRow++)
				{
					XSSFRow prRow=productSheet.getRow(productRow);
					Cell pdCell=prRow.getCell(0);
					if(getCellValue(pdCell).equals(c.getCas().getCaseId()))
					{
						PackageScheme scheme=new PackageScheme();
						for(int localSchemeStart=schemeStart;localSchemeStart<=schemeEnd;localSchemeStart++)
						{
							String fieldName=product.getCell(localSchemeStart).getStringCellValue();
							Cell cell=prRow.getCell(localSchemeStart);
							
							if(cell!=null)
							{
								
								Method m=null;
								m=scheme.getClass().getMethod("set"+fieldName, String.class);
								
								m.invoke(scheme, getCellValue(cell));
								
							}
						}
						schemes.add(scheme);
						PolicyProduct pProduct=new PolicyProduct();
						for(int localProductStart=productStart;localProductStart<=productEnd;localProductStart++)
						{
							
							String fieldName=product.getCell(localProductStart).getStringCellValue();
							Cell cell=prRow.getCell(localProductStart);
						
								Method m=null;
							
								m = pProduct.getClass().getMethod("set"+fieldName, String.class);
								System.out.println(m.getName());
								
								if(m==null)
								{
									System.out.println(m.getName()+" is null.");
								}
								m.invoke(pProduct, getCellValue(cell));
								
						}
					
						
						policyProducts.add(pProduct);
					}
						
				}
				c.setProducts(policyProducts);
				c.setPackageScheme(schemes);
			}
		}
		
		//Product Plan and Benefit
			XSSFSheet planAndBenefitSheet=workbook.getSheet("ProducePlan & Benefit");
				
			XSSFRow planHeaderRow=planAndBenefitSheet.getRow(0);
			XSSFRow planAndBenefit=planAndBenefitSheet.getRow(1);
			int planColumnNum=planHeaderRow.getPhysicalNumberOfCells();
			System.out.println("There are "+planColumnNum+" columns in Product Plan Sheet.");
			int planRowNum=planAndBenefitSheet.getLastRowNum();
			System.out.println("There are "+planRowNum+" rows in Product Plan  Sheet.");
				
				
				int productPlanStart=0;
				int productPlanEnd=0;
				int benefitStart=0;
				int benefitEnd=planColumnNum-1;
				
				for(int i=0;i<planColumnNum;i++)
				{
					Cell cell=planHeaderRow.getCell(i);
					if(cell==null)
					{
						continue;
					}
					if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("ProductPlan"))
					{
						productPlanStart=i;
						System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
					}
					if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("Benefit"))
					{
						productPlanEnd=i-1;
						benefitStart=i;
						System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
					}
					
				}
			
				
				if(testcases.size()>0)
				{
					for(CoastCase c : testcases)
					{
						List<ProductPlan> productPlanss=new ArrayList<>();
						
						for(int productPlanRow=2;productPlanRow<=planRowNum;productPlanRow++)
						{
							XSSFRow ppRow=planAndBenefitSheet.getRow(productPlanRow);
							Cell ppCell=ppRow.getCell(0);
							if(getCellValue(ppCell).equals(c.getCas().getCaseId()))
							{
								ProductPlan plan=new ProductPlan();
								for(int localProductPlanStart=productPlanStart;localProductPlanStart<=productPlanEnd;localProductPlanStart++)
								{
									
									String fieldName=planAndBenefit.getCell(localProductPlanStart).getStringCellValue();
									Cell cell=ppRow.getCell(localProductPlanStart);
								
										Method m=null;
									
										m=plan.getClass().getMethod("set"+fieldName, String.class);
										System.out.println(m.getName());
										
										if(m==null)
										{
											System.out.println(m.getName()+" is null.");
										}
										m.invoke(plan, getCellValue(cell));
										
								}
								
								Benefit benefit=new Benefit();
								for(int localBenefitStart=benefitStart;localBenefitStart<=benefitEnd;localBenefitStart++)
								{
									String fieldName=planAndBenefit.getCell(localBenefitStart).getStringCellValue();
									Cell cell=ppRow.getCell(localBenefitStart);
								
										Method m=null;
										m=benefit.getClass().getMethod("set"+fieldName, String.class);
										System.out.println(m.getName());
										
										if(m==null)
										{
											System.out.println(m.getName()+" is null.");
										}
										m.invoke(benefit, getCellValue(cell));
								}
								plan.setBenefit(benefit);
								productPlanss.add(plan);
						}

						
					}
						c.setProductPlans(productPlanss);
					}
				
					
					//Distribution Channel part
					XSSFSheet distributionSheet=workbook.getSheet("DistributionChannel");
						
					XSSFRow dcHeaderRow=distributionSheet.getRow(0);
					XSSFRow dcField=distributionSheet.getRow(1);
					int dcColumnNum=dcHeaderRow.getPhysicalNumberOfCells();
					System.out.println("There are "+dcColumnNum+" columns in Distribution&Channel Sheet.");
					int dcRowNum=distributionSheet.getLastRowNum();
					System.out.println("There are "+dcRowNum+" rows in Distribution Sheet.");
						
						
						int subDCStart=0;
						int subDCEnd=0;
						int dcStart=0;
						int dcEnd=dcColumnNum-1;
						
						for(int i=0;i<dcColumnNum;i++)
						{
							Cell cell=dcHeaderRow.getCell(i);
							if(cell==null)
							{
								continue;
							}
							if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("SubDistributionChannel"))
							{
								subDCStart=i;
								System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
							}
							if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("ProductDistributionChannel"))
							{
								subDCEnd=i-1;
								dcStart=i;
								System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
							}
							
						}
					
						
						if(testcases.size()>0)
						{
							for(CoastCase c : testcases)
							{
								List<DistributionChannel> subDistributionChannel=new ArrayList<>();
								List<DistributionChannel> productDistributionChannel=new ArrayList<>();
								
								for(int dcRow=2;dcRow<=dcRowNum;dcRow++)
								{
									XSSFRow disRow=distributionSheet.getRow(dcRow);
									Cell dcCell=disRow.getCell(0);
									if(getCellValue(dcCell).equals(c.getCas().getCaseId()))
									{
										DistributionChannel subDC=new DistributionChannel();
										DistributionChannel productDC=new DistributionChannel();
										
										for(int localsubDCStart=subDCStart;localsubDCStart<=subDCEnd;localsubDCStart++)
										{
											
											String fieldName=dcField.getCell(localsubDCStart).getStringCellValue();
											Cell cell=disRow.getCell(localsubDCStart);
										
												Method m=null;
											
												m=subDC.getClass().getMethod("set"+fieldName, String.class);
												System.out.println(m.getName());
												
												if(m==null)
												{
													System.out.println(m.getName()+" is null.");
												}
												m.invoke(subDC, getCellValue(cell));
												
										}
										subDistributionChannel.add(subDC);
										
										
										for(int localdcStart=dcStart;localdcStart<=dcEnd;localdcStart++)
										{
											String fieldName=dcField.getCell(localdcStart).getStringCellValue();
											Cell cell=disRow.getCell(localdcStart);
										
												Method m=null;
												m=productDC.getClass().getMethod("set"+fieldName, String.class);
												System.out.println(m.getName());
												
												if(m==null)
												{
													System.out.println(m.getName()+" is null.");
												}
												m.invoke(productDC, getCellValue(cell));
										}
										productDistributionChannel.add(productDC);
										
								}

								
							}
								c.setDistributionChannel(subDistributionChannel);
								if(c.getProducts()!=null && c.getProducts().size()>0 && c.getProducts().get(0)!=null)
									c.getProducts().get(0).setDistributionChannel(productDistributionChannel);
							}
							
						}
				}
				
				
				//Member File Configuration
				XSSFSheet testConfigurationSheet=workbook.getSheet("TestConfiguration");
				
				XSSFRow tcHeaderRow=testConfigurationSheet.getRow(0);
				XSSFRow tcField=testConfigurationSheet.getRow(1);
				int tcColumnNum=tcHeaderRow.getPhysicalNumberOfCells();
				System.out.println("There are "+tcColumnNum+" columns in TestConfiguration Sheet.");
				int tcRowNum=testConfigurationSheet.getLastRowNum();
				System.out.println("There are "+tcRowNum+" rows in TestConfiguration Sheet.");
					
					
					int memberFileStart=0;
					int memberFileEnd=0;
					int billResultStart=0;
					int billResultEnd=tcColumnNum-1;
					
					for(int i=0;i<tcColumnNum;i++)
					{
						Cell cell=tcHeaderRow.getCell(i);
						if(cell==null)
						{
							continue;
						}
						if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("MemberFile"))
						{
							memberFileStart=i;
							System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
						}
						if(cell.getStringCellValue()!=null && cell.getStringCellValue().equals("BillResult"))
						{
							memberFileEnd=i-1;
							billResultStart=i;
							System.out.println(cell.getStringCellValue()+", locate at "+i+" column.");
						}
						
					}
				
					
					if(testcases.size()>0)
					{
						for(CoastCase c : testcases)
						{
							
							
							for(int tcRow=2;tcRow<=tcRowNum;tcRow++)
							{
								XSSFRow testConfigRow=testConfigurationSheet.getRow(tcRow);
								Cell tcCell=testConfigRow.getCell(0);
								if(getCellValue(tcCell).equals(c.getCas().getCaseId()))
								{
									MemberFile memberFile=new MemberFile();
									BillResult billResult=new BillResult();
									
									for(int localMemberFileStart=memberFileStart;localMemberFileStart<=memberFileEnd;localMemberFileStart++)
									{
										
										String fieldName=tcField.getCell(localMemberFileStart).getStringCellValue();
										Cell cell=testConfigRow.getCell(localMemberFileStart);
									
											Method m=null;
										
											m=memberFile.getClass().getMethod("set"+fieldName, String.class);
											System.out.println(m.getName());
											
											if(m==null)
											{
												System.out.println(m.getName()+" is null.");
											}
											m.invoke(memberFile, getCellValue(cell));
											
									}

									for(int localBillResultStart=billResultStart;localBillResultStart<=billResultEnd;localBillResultStart++)
									{
										String fieldName=tcField.getCell(localBillResultStart).getStringCellValue();
										Cell cell=testConfigRow.getCell(localBillResultStart);
									
											Method m=null;
											m=billResult.getClass().getMethod("set"+fieldName, String.class);
											System.out.println(m.getName());
											
											if(m==null)
											{
												System.out.println(m.getName()+" is null.");
											}
											m.invoke(billResult, getCellValue(cell));
									}
									
									c.setMemberFile(memberFile);
									c.setBillResult(billResult);
							}

								
						}
							
							
						}
						
					}
				return testcases;
	}
	
	
	private static String getCellValue(Cell cell){  
        String value = "";  
        if(cell!=null){  
            switch (cell.getCellType()) {  
            case Cell.CELL_TYPE_NUMERIC:  
                if(HSSFDateUtil.isCellDateFormatted(cell)){ //��������  
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());  
                    value = sdf.format(date);  
                }else{  
                    Integer data = (int) cell.getNumericCellValue();  
                    value = data.toString();  
                }  
                break;  
                
            case Cell.CELL_TYPE_STRING:  
                value = cell.getStringCellValue();  
                break;  
            case Cell.CELL_TYPE_BOOLEAN:  
                Boolean data = cell.getBooleanCellValue();  
                value = data.toString();   
                break;  
            case Cell.CELL_TYPE_ERROR:  
                System.out.println("��Ԫ�����ݳ��ִ���");  
                break;  
            case Cell.CELL_TYPE_FORMULA:  
                value = String.valueOf(cell.getNumericCellValue());    
                if (value.equals("NaN")) {
                    value = cell.getStringCellValue().toString();    
                }             
                break;            
            case Cell.CELL_TYPE_BLANK:  
                //System.out.println("��Ԫ������ Ϊ��ֵ ");  
                break;            
            default :  
                value = cell.getStringCellValue().toString();  
                break;  
            }  
        }  
        return value;  
    }  
	
	
	public static void main(String ...args) throws Exception
	{
		//System.out.println(CaseUtil.getCoastTestCases().size());
		System.out.println(JsonUtil.formatObjectToJson(CaseUtil.getCoastTestCases().get(0)));
	}
}
