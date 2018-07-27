package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aia.cs.ipos.entity.AgentEmails;
import com.aia.cs.ipos.entity.Contact;
import com.aia.cs.ipos.entity.EappInfo;
import com.aia.cs.ipos.entity.MemberContact;
import com.aia.cs.ipos.entity.Pdf;
import com.aia.cs.ipos.entity.ProposalInfo;
import com.aia.cs.ipos.entity.SubMember;
import com.aia.cs.ipos.entity.SubProposalInfo;

public class ExcelToPojo {
	
	
	private static final int COLUMNROWNUM=1;
/*	
	private static ResourceBundle resourceBundle;
	
	static 
	{
		resourceBundle = ResourceBundle.getBundle("iPosCS");
	}*/
	
	private static XSSFWorkbook workbook;
	public static void initWorkSheet(XSSFWorkbook workbook)
	{
		workbook=workbook;
	}
	
	
	
	//Get Contact
	public static Contact getContact(XSSFWorkbook workbook) throws Exception
	{
		return (Contact)parseExcelToContact(workbook, "Contact");
	}
	
	//Get EAPP Info
	public static EappInfo getEAppInfo(XSSFWorkbook workbook) throws Exception
	{
		return (EappInfo)parseExcelToContact(workbook, "EappInfo");
	}
	
	public static ProposalInfo getProposalInfo(XSSFWorkbook workbook) throws Exception
	{
		return (ProposalInfo) parseExcelToProposalInfo(workbook,"ProposalInfo");
	}
	
	//Generate PDF
	public static Pdf[] getPdf(XSSFWorkbook workbook) throws Exception
	{
		Object temp=parseExcelToObject(workbook, "pdf");
		List list=(List)temp;
		int size=list.size();
		Pdf[] pdf=new Pdf[size];
		for(int i=0;i<size;i++)
		{
			pdf[i]=(Pdf)list.get(i);
		}
		
		return pdf;
		
	}
	
	
	
	//Get Member Info
	public static MemberContact[] getMemberContact(XSSFWorkbook workbook) throws Exception
	{
		List<Object> lists=parseExcelToMemberObject(workbook,"MemberContact");
		MemberContact[] members=new MemberContact[lists.size()];
		for(int i=0;i<lists.size();i++)
		{
			members[i]=(MemberContact)lists.get(i);
		}
		return members;
	}
	
	//Get Agent Emails
	public static AgentEmails[] getAgentEmail(XSSFWorkbook workbook) throws Exception
	{
		List<Object> lists=parseExcelToAgentEmailsObject(workbook,"AgentEmails");
		AgentEmails[] agents=new AgentEmails[lists.size()];
		for(int i=0;i<lists.size();i++)
		{
			agents[i]=(AgentEmails)lists.get(i);
		}
		return agents;
	}
	
	//Get Dependent member
	public static List<SubMember> getDependentQueue(XSSFWorkbook workbook) throws Exception
	{
		Object obj=parseExcelToDepMemberObject(workbook,"Dependent");
		if(obj==null)
		{
			return new ArrayList<SubMember>();
		}
		List list=(List)obj;
		List<SubMember> queue=new ArrayList<SubMember>(list.size());
		for(int i=0;i<list.size();i++)
		{
			queue.add((SubMember)list.get(i));
		}
		return queue;
	}
	
	//Generate Contact Object
	@SuppressWarnings("unchecked")
	public static Object parseExcelToContact(XSSFWorkbook workbook, String sheetName) throws Exception
	{
		List<Map<String,Object>> result=new LinkedList<Map<String,Object>>();
		
		String[] columnName=null;
		
		Object retVal=null;
		
		XSSFSheet sheet=workbook.getSheet(sheetName);
		int availableColumn=sheet.getRow(COLUMNROWNUM).getPhysicalNumberOfCells();
		
		//System.out.println("Available Columns: "+availableColumn);
			
			//Skip first column of the Entity Name
		columnName=new String[availableColumn];
			
		for(int i=0;i<columnName.length;i++)
		{
			columnName[i]=sheet.getRow(COLUMNROWNUM).getCell(i).getStringCellValue();
			//System.out.println("Column Name: "+columnName[i]);
		}
			
		String className=sheet.getSheetName();
		System.out.println("Current Sheet Name: "+className);
			
		Class clazz=Class.forName("com.aia.cs.ipos.entity."+className);
			
		//System.out.println("Class Name: "+clazz.getName());
			
		if(clazz.getName().contains(sheetName))
		{
			
			for(int rowIndex=1+COLUMNROWNUM;rowIndex<sheet.getPhysicalNumberOfRows();rowIndex++)
			{
				XSSFRow row=sheet.getRow(rowIndex);
				Map<String,Object> map=new HashMap();
				for(int cellIndex=0;cellIndex<availableColumn;cellIndex++)
				{
					XSSFCell cell=row.getCell(cellIndex);
					if(columnName[cellIndex]!=null && columnName[cellIndex].trim().length()>0)
					{
						map.put(columnName[cellIndex].trim(), getCellValue(cell));
						System.out.println(columnName[cellIndex]+ " : "+getCellValue(cell));
					}
						
				}
				result.add(map);
			}
		}
		retVal=toObject(result,clazz);
		return retVal;
		//System.out.println(JSONObject.toJSONList("list<>= "+result));
	}
	
	
	//Generate Proposal
	@SuppressWarnings("unchecked")
	public static Object parseExcelToProposalInfo(XSSFWorkbook workbook, String sheetName) throws Exception
	{
		List<Map<String,Object>> result=new LinkedList<Map<String,Object>>();
		
		String[] columnName=null;
		
		Object retVal=null;
		
		XSSFSheet sheet=workbook.getSheet(sheetName);
		int availableColumn=sheet.getRow(COLUMNROWNUM).getPhysicalNumberOfCells();
		//System.out.println("Available Columns: "+availableColumn);
			
			//Skip first column of the Entity Name
		columnName=new String[availableColumn];
			
		for(int i=0;i<columnName.length;i++)
		{
			columnName[i]=sheet.getRow(COLUMNROWNUM).getCell(i).getStringCellValue();
			//System.out.println("Column Name: "+columnName[i]);
		}
			
		String className=sheet.getSheetName();
		System.out.println("Current Sheet Name: "+className);
			
		Class clazz=Class.forName("com.aia.cs.ipos.entity."+className);
			
		if(clazz.getName().contains(sheetName))
		{
			
			for(int rowIndex=1+COLUMNROWNUM;rowIndex<sheet.getPhysicalNumberOfRows()-1;rowIndex++)
			{
				XSSFRow row=sheet.getRow(rowIndex);
				Map<String,Object> map=new HashMap();
				if(row==null)
				{
					System.out.println("Row is null.");
				}
				for(int cellIndex=0;cellIndex<availableColumn;cellIndex++)
				{
					XSSFCell cell=row.getCell(cellIndex);
					if(columnName[cellIndex]!=null && columnName[cellIndex].trim().length()>0)
					{
						map.put(columnName[cellIndex].trim(), getCellValue(cell));
						//System.out.println(columnName[cellIndex]+ " : "+getCellValue(cell));
					}
						
				}
				result.add(map);
			}
		}
		retVal=toObject(result,clazz);
		
		if(retVal instanceof ProposalInfo)
		{
			//((ProposalInfo) retVal).setSubProposalInfo(new SubProposalInfo[]{(SubProposalInfo) parseExcelToObject(workbook,3)});
			//SubProposalInfo[] subProposalInfos=
			
			Object subProposals=parseExcelToObject(workbook,"SubProposalInfo");
			List list=(List)subProposals;
			SubProposalInfo[] subProposalInfo=new SubProposalInfo[list.size()];
			for(int i=0;i<list.size();i++)
			{
				subProposalInfo[i]=(SubProposalInfo)list.get(i);
			}
			((ProposalInfo) retVal).setSubProposalInfo(subProposalInfo);
		}
		
		return retVal;
		//System.out.println(JSONObject.toJSONList("list<>= "+result));
	}
	
	@SuppressWarnings("unchecked")
	public static Object parseExcelToObject(XSSFWorkbook workbook, String sheetName) throws Exception
	{
		List<Map<String,Object>> result=new LinkedList<Map<String,Object>>();
		int availableSheets=workbook.getNumberOfSheets();
		//System.out.println("Available Sheets: "+availableSheets);
		
		String[] columnName=null;
		
		Object retVal=null;
		
		
		
		XSSFSheet sheet=workbook.getSheet(sheetName);
		int availableColumn=sheet.getRow(COLUMNROWNUM).getPhysicalNumberOfCells();
		//System.out.println("Available Columns: "+availableColumn);
		
		//Skip first column of the Entity Name
		columnName=new String[availableColumn];
		
		
		
		for(int i=0;i<columnName.length;i++)
		{
			columnName[i]=sheet.getRow(COLUMNROWNUM).getCell(i).getStringCellValue();
			//System.out.println("Column Name: "+columnName[i]);
		}
		
		
		String className=sheet.getSheetName();
		System.out.println("Current Sheet Name: "+className);
			
			
		Class clazz=Class.forName("com.aia.cs.ipos.entity."+className);
		//System.out.println("Class Name: "+clazz.getName());
			
		if(clazz.getName().contains(className))
		{
			
			for(int rowIndex=1+COLUMNROWNUM;rowIndex<sheet.getPhysicalNumberOfRows();rowIndex++)
			{
				XSSFRow row=sheet.getRow(rowIndex);
				Map<String,Object> map=new HashMap();
				for(int cellIndex=0;cellIndex<availableColumn;cellIndex++)
				{
					XSSFCell cell=row.getCell(cellIndex);
					if(columnName[cellIndex]!=null && columnName[cellIndex].trim().length()>0)
					{
						
						map.put(columnName[cellIndex].trim(), getCellValue(cell));
						//System.out.println(columnName[cellIndex]+ " : "+getCellValue(cell));
					}
	
				}
			result.add(map);
		}
		
		retVal=toObjectList(result,clazz);
		}
		
		if(retVal instanceof ProposalInfo)
		{
			((ProposalInfo) retVal).setSubProposalInfo(new SubProposalInfo[]{(SubProposalInfo) parseExcelToObject(workbook,3)});
		}
		
		return retVal;
		//System.out.println(JSONObject.toJSONList("list<>= "+result));
	}
	
	@SuppressWarnings("unchecked")
	public static Object parseExcel(XSSFWorkbook workbook) throws Exception
	{
		List<Map<String,Object>> result=new LinkedList<Map<String,Object>>();
		int availableSheets=workbook.getNumberOfSheets();
		System.out.println("Available Sheets: "+availableSheets);
		
		String[] columnName=null;
		
		Object retVal=null;
		
		
		for(int sheetIndex=2;sheetIndex<3;sheetIndex++)
		{
			
			XSSFSheet sheet=workbook.getSheetAt(sheetIndex);
			int availableColumn=sheet.getRow(COLUMNROWNUM).getPhysicalNumberOfCells();
			//System.out.println("Available Columns: "+availableColumn);
			
			//Skip first column of the Entity Name
			columnName=new String[availableColumn];
			
			for(int i=0;i<columnName.length;i++)
			{
				columnName[i]=sheet.getRow(COLUMNROWNUM).getCell(i).getStringCellValue();
				//System.out.println("Column Name: "+columnName[i]);
			}
			
			String className=sheet.getSheetName();
			System.out.println("Current Sheet Name: "+className);
			
			
			
			Class clazz=Class.forName("com.aia.cs.ipos.entity."+className);
			
			//System.out.println("Class Name: "+clazz.getName());
			
			if(clazz.getName().contains("EappInfo"))
			{
			
				for(int rowIndex=1+COLUMNROWNUM;rowIndex<sheet.getPhysicalNumberOfRows();rowIndex++)
				{
					XSSFRow row=sheet.getRow(rowIndex);
					Map<String,Object> map=new HashMap();
					for(int cellIndex=0;cellIndex<availableColumn;cellIndex++)
					{
						XSSFCell cell=row.getCell(cellIndex);
						if(columnName[cellIndex]!=null && columnName[cellIndex].trim().length()>0)
						{
							map.put(columnName[cellIndex].trim(), getCellValue(cell));
							//System.out.println(columnName[cellIndex]+ " : "+getCellValue(cell));
						}
						
					}
				result.add(map);
			}
		
			retVal=toObjectList(result,clazz);
			}
			
		}
		return retVal;
		//System.out.println(JSONObject.toJSONList("list<>= "+result));
	}

	public static boolean containsElement(String[] entityName, String element)
	{
		boolean result=false;
		for(String s : entityName)
		{
			if(s.equalsIgnoreCase(element))
			{
				result=true;
				break;
			}
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public static Object parseExcelToObject(XSSFWorkbook workbook, int index) throws Exception
	{
		List<Map<String,Object>> result=new LinkedList<Map<String,Object>>();
		int availableSheets=workbook.getNumberOfSheets();
		//System.out.println("Available Sheets: "+availableSheets);
		
		String[] columnName=null;
		
		Object retVal=null;
		
		
		
		XSSFSheet sheet=workbook.getSheetAt(index);
		int availableColumn=sheet.getRow(COLUMNROWNUM).getPhysicalNumberOfCells();
		//System.out.println("Available Columns: "+availableColumn);
		
		//Skip first column of the Entity Name
		columnName=new String[availableColumn];
		
		
		
		for(int i=0;i<columnName.length;i++)
		{
			columnName[i]=sheet.getRow(COLUMNROWNUM).getCell(i).getStringCellValue();
			//System.out.println("Column Name: "+columnName[i]);
		}
		
		
		String className=sheet.getSheetName();
		System.out.println("Current Sheet Name: "+className);
			
			
		Class clazz=Class.forName("com.aia.cs.ipos.entity."+className);
		//System.out.println("Class Name: "+clazz.getName());
		
		if(clazz.getName().contains("ProposalInfo"))
		{	
			//System.out.println("Total rows: "+(sheet.getPhysicalNumberOfRows()-(1+COLUMNROWNUM))+clazz.getName());
			for(int rowIndex=1+COLUMNROWNUM;rowIndex<sheet.getPhysicalNumberOfRows();rowIndex++)
			{
				XSSFRow row=sheet.getRow(rowIndex);
				Map<String,Object> map=new HashMap();
				for(int cellIndex=0;cellIndex<availableColumn;cellIndex++)
				{
					XSSFCell cell=row.getCell(cellIndex);
					if(columnName[cellIndex]!=null && columnName[cellIndex].trim().length()>0)
					{
						
						map.put(columnName[cellIndex].trim(), getCellValue(cell));
						//System.out.println(columnName[cellIndex]+ " : "+getCellValue(cell));
					}
	
				}
			result.add(map);
		}
		
		retVal=toObject(result,clazz);
		}
		
		if(retVal instanceof ProposalInfo)
		{
			((ProposalInfo) retVal).setSubProposalInfo(new SubProposalInfo[]{(SubProposalInfo) parseExcelToObject(workbook,3)});
		}
		
		return retVal;
		//System.out.println(JSONObject.toJSONList("list<>= "+result));
	}
	
	
	public static <T> List<T> toSubPropObjectList(List<Map<String,Object>> list, Class<T> clazz) throws Exception
	{
		List<T> obj=new ArrayList<T>(list.size());
		
		for(int i=0; i<list.size();i++)
		{
			Set<Map.Entry<String,Object>> set=list.get(i).entrySet();
			Iterator<Entry<String,Object>> it=set.iterator();
			T temp=clazz.newInstance();
			Method[] methods=clazz.getDeclaredMethods();
			
			while(it.hasNext())
			{
				Map.Entry<String, Object> entry=(Map.Entry<String, Object>)it.next();
				
				//System.out.println(entry.getKey()+"="+entry.getValue());
				
				String methodName=entry.getKey().toString();
				StringBuffer sb=new StringBuffer(methodName);
				sb.replace(0, 1, (methodName.charAt(0)+"").toUpperCase());
				methodName="set"+sb.toString();
				if(!methodName.contains("SubProposalInfo"))
				{
					Method m=null;
					if(methodName.contains("Members") || methodName.contains("Dependents") || methodName.contains("Employeenum")||methodName.contains("Dependentnum"))
					{
						m=clazz.getMethod(methodName, int.class);
						//System.out.println("Invoke Method0: "+m.getName());
						m.invoke(temp, entry.getValue()==null ? 0 : Integer.parseInt((String) entry.getValue()));
					}
					else
					{	
						m=clazz.getMethod(methodName, entry.getValue().getClass());
						//System.out.println("Invoke Method1: "+m.getName());
						m.invoke(temp, entry.getValue()==null ? "" : entry.getValue());
					}
					
					
				}
				
			}
			obj.add(temp);
		}
		//System.out.println("size= "+returnList.size());
		return obj;
	}
	
	public static <T> List<T> toObjectList(List<Map<String,Object>> list, Class<T> clazz) throws Exception
	{
		List<T> obj=new ArrayList<T>(list.size());
		
		for(int i=0; i<list.size();i++)
		{
			Set<Map.Entry<String,Object>> set=list.get(i).entrySet();
			Iterator<Entry<String,Object>> it=set.iterator();
			T temp=clazz.newInstance();
			Method[] methods=clazz.getDeclaredMethods();
			
			while(it.hasNext())
			{
				Map.Entry<String, Object> entry=(Map.Entry<String, Object>)it.next();
				
				//System.out.println(entry.getKey()+"="+entry.getValue());
				
				String methodName=entry.getKey().toString();
				StringBuffer sb=new StringBuffer(methodName);
				sb.replace(0, 1, (methodName.charAt(0)+"").toUpperCase());
				methodName="set"+sb.toString();
				if(!methodName.contains("SubProposalInfo"))
				{
					Method m=null;
					if(methodName.contains("Members") || methodName.contains("Dependents") || methodName.contains("Employeenum")||methodName.contains("Dependentnum"))
					{
						m=clazz.getMethod(methodName, int.class);
						//System.out.println("Invoke Method0: "+m.getName());
						m.invoke(temp, entry.getValue()==null ? 0 : Integer.parseInt((String) entry.getValue()));
					}
					else
					{	
						m=clazz.getMethod(methodName, entry.getValue().getClass());
						//System.out.println("Invoke Method1: "+m.getName());
						m.invoke(temp, entry.getValue()==null ? "" : entry.getValue());
					}
					
					
				}
				
			}
			obj.add(temp);
		}
		//System.out.println("size= "+returnList.size());
		return obj;
	}

	//Create Member Json String
	@SuppressWarnings("unchecked")
	public static List<Object> parseExcelToMemberObject(XSSFWorkbook workbook, String sheetName) throws Exception
	{
		//TEMP
		ExcelToPojo.workbook=workbook;
		
		List<Map<String,Object>> result=new LinkedList<Map<String,Object>>();
		int availableSheets=workbook.getNumberOfSheets();
		//System.out.println("Available Sheets: "+availableSheets);
		
		String[] columnName=null;
		
		XSSFSheet sheet=workbook.getSheet("MemberContact");
		int availableColumn=sheet.getRow(COLUMNROWNUM).getPhysicalNumberOfCells();
		int memberCount=sheet.getPhysicalNumberOfRows()-(1+COLUMNROWNUM);
		List<Object> retVal=new ArrayList<Object>(memberCount);
		//System.out.println("Available Columns: "+availableColumn);
		
		//Skip first column of the Entity Name
		columnName=new String[availableColumn];
		
		for(int i=0;i<columnName.length;i++)
		{
			columnName[i]=sheet.getRow(COLUMNROWNUM).getCell(i).getStringCellValue();
			//System.out.println("Column Name: "+columnName[i]);
		}
		
		String className=sheet.getSheetName();
		System.out.println("Current Sheet Name: "+className);
			
		Class clazz=Class.forName("com.aia.cs.ipos.entity."+className);
		//System.out.println("Class Name: "+clazz.getName());
		
		if(clazz.getName().contains("MemberContact"))
		{
			
			for(int rowIndex=1+COLUMNROWNUM;rowIndex<sheet.getPhysicalNumberOfRows();rowIndex++)
			{
				XSSFRow row=sheet.getRow(rowIndex);
				Map<String,Object> map=new HashMap();
				for(int cellIndex=0;cellIndex<availableColumn;cellIndex++)
				{
					XSSFCell cell=row.getCell(cellIndex);
					if(columnName[cellIndex]!=null && columnName[cellIndex].trim().length()>0)
					{
						map.put(columnName[cellIndex].trim(), getCellValue(cell));
						//System.out.println(columnName[cellIndex]+ " : "+getCellValue(cell));
					}
				}
			result.add(map);
		}
		
		retVal=toMemberObjectList(result,clazz);
		}
		
		return retVal;
		//System.out.println(JSONObject.toJSONList("list<>= "+result));
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Object> parseExcelToDepMemberObject(XSSFWorkbook workbook, String sheetName) throws Exception
	{
		//TEMP
		ExcelToPojo.workbook=workbook;
		
		List<Map<String,Object>> result=new LinkedList<Map<String,Object>>();
		int availableSheets=workbook.getNumberOfSheets();
		//System.out.println("Available Sheets: "+availableSheets);
		
		String[] columnName=null;
		
		XSSFSheet sheet=workbook.getSheet(sheetName);
		int availableColumn=sheet.getRow(COLUMNROWNUM).getPhysicalNumberOfCells();
		int memberCount=sheet.getPhysicalNumberOfRows()-(1+COLUMNROWNUM);
		List<Object> retVal=new ArrayList<Object>(memberCount);
		//System.out.println("Available Columns: "+availableColumn);
		
		//Skip first column of the Entity Name
		columnName=new String[availableColumn];
		
		for(int i=0;i<columnName.length;i++)
		{
			columnName[i]=sheet.getRow(COLUMNROWNUM).getCell(i).getStringCellValue();
			//System.out.println("Column Name: "+columnName[i]);
		}
		
		String className="SubMember";
		System.out.println("Current Sheet Name: "+className);
			
		Class clazz=Class.forName("com.aia.cs.ipos.entity."+className);
		System.out.println("Class Name: "+clazz.getName());
		
		if(sheet.getPhysicalNumberOfRows()<(2+COLUMNROWNUM))
		{
			return null;
		}
		
		if(clazz.getName().contains("SubMember"))
		{
			
			for(int rowIndex=1+COLUMNROWNUM;rowIndex<sheet.getPhysicalNumberOfRows();rowIndex++)
			{
				XSSFRow row=sheet.getRow(rowIndex);
				Map<String,Object> map=new HashMap();
				for(int cellIndex=0;cellIndex<availableColumn;cellIndex++)
				{
					XSSFCell cell=row.getCell(cellIndex);
					if(columnName[cellIndex]!=null && columnName[cellIndex].trim().length()>0)
					{
						map.put(columnName[cellIndex].trim(), getCellValue(cell));
						//System.out.println(columnName[cellIndex]+ " : "+getCellValue(cell));
					}
				}
			result.add(map);
		}
		
		retVal=toObjectList(result,clazz);
		}
		
		return retVal;
		//System.out.println(JSONObject.toJSONList("list<>= "+result));
	}
	
	public static <T> List<T> toMemberObjectList(List<Map<String,Object>> list, Class<T> clazz) throws Exception
	{
		List<T> obj=new ArrayList<T>(list.size());
		
		for(int i=0; i<list.size();i++)
		{
			Set<Map.Entry<String,Object>> set=list.get(i).entrySet();
			Iterator<Entry<String,Object>> it=set.iterator();
			T temp=clazz.newInstance();
			Method[] methods=clazz.getDeclaredMethods();
			
			while(it.hasNext())
			{
				Map.Entry<String, Object> entry=(Map.Entry<String, Object>)it.next();
				
				
				String methodName=entry.getKey().toString();
				StringBuffer sb=new StringBuffer(methodName);
				sb.replace(0, 1, (methodName.charAt(0)+"").toUpperCase());
				methodName="set"+sb.toString();
				/*	
				if(!methodName.contains("SubMember"))
				{
					Method m=null;
					m=clazz.getMethod(methodName, entry.getValue().getClass());				
					m.invoke(temp, entry.getValue()==null ? "" : entry.getValue());
					//System.out.println("Invoke Method: "+m.getName());
				}
				*/
				
				Method m=null;
				if(methodName.contains("Members") || methodName.contains("Dependents") || methodName.contains("Employeenum")||methodName.contains("Dependentnum")||methodName.contains("DependentCount"))
				{
					try{
						m=clazz.getMethod(methodName, int.class);
						m.invoke(temp, entry.getValue()==null || entry.getValue().toString().equals("") ? 0 : Integer.parseInt((String) entry.getValue()));
					}catch(NumberFormatException e)
					{
						throw new NumberFormatException(e.getMessage()+": The method is: "+ methodName+" The value is: "+entry.getValue());
					}
				}
				else if(!methodName.contains("SubMember"))
				{	
					m=clazz.getMethod(methodName, entry.getValue().getClass());
					try{
					m.invoke(temp, entry.getValue()==null ? "" : entry.getValue());
					}catch(Exception e)
					{
						System.out.println(m.getName()+entry.getValue());
					}
				}
				
			}
			Object subMember=toObjectAtIndex(generateListFromExcel(workbook,"SubMember"),i,SubMember.class);
			((MemberContact)temp).setSubMember(new SubMember[]{(SubMember)subMember});
			obj.add(temp);
		}
		//System.out.println("size= "+returnList.size());
		return obj;
	}
	
	
	
	public static <T> T toObject(List<Map<String,Object>> list, Class<T> clazz) throws Exception
	{
		T obj=null;
		
		if(list.size()==1)
		{
			//Placeholder
		}
		
		for(int i=0; i<list.size();i++)
		{
			Set<Map.Entry<String,Object>> set=list.get(i).entrySet();
			Iterator<Entry<String,Object>> it=set.iterator();
			obj=clazz.newInstance();
			Method[] methods=clazz.getDeclaredMethods();
			
			while(it.hasNext())
			{
				Map.Entry<String, Object> entry=(Map.Entry<String, Object>)it.next();
				
				//System.out.println(entry.getKey()+"="+entry.getValue());
				
				String methodName=entry.getKey().toString();
				StringBuffer sb=new StringBuffer(methodName);
				sb.replace(0, 1, (methodName.charAt(0)+"").toUpperCase());
				methodName="set"+sb.toString();
				if(!methodName.contains("SubProposalInfo"))
				{
					Method m=null;
					if(methodName.contains("Members") || methodName.contains("Dependents") || methodName.contains("Employeenum")||methodName.contains("Dependentnum"))
					{
						try{
							m=clazz.getMethod(methodName, int.class);
							m.invoke(obj, entry.getValue()==null ? 0 : Integer.parseInt((String) entry.getValue()));
						}catch(NumberFormatException e)
						{
							throw new NumberFormatException(e.getMessage()+": The method is: "+ methodName+" The value is: "+entry.getValue());
						}
					}
					else
					{	
						m=clazz.getMethod(methodName, entry.getValue().getClass());				
						m.invoke(obj, entry.getValue()==null ? "" : entry.getValue());
					}
					
					//System.out.println("Invoke Method: "+m.getName());
				}
				
			}
			
		}
		//System.out.println("size= "+returnList.size());
		return obj;
	}
	
	
	
	//Retrieve index item in the list
	public static <T> T toObjectAtIndex(List<Map<String,Object>> list, int index, Class<T> clazz) throws Exception
	{
		T obj=null;
		

		Set<Map.Entry<String,Object>> set=list.get(index).entrySet();
		Iterator<Entry<String,Object>> it=set.iterator();
		obj=clazz.newInstance();
		Method[] methods=clazz.getDeclaredMethods();
			
		while(it.hasNext())
		{
			Map.Entry<String, Object> entry=(Map.Entry<String, Object>)it.next();
				
			//System.out.println(entry.getKey()+"="+entry.getValue());
				
			String methodName=entry.getKey().toString();
			StringBuffer sb=new StringBuffer(methodName);
			sb.replace(0, 1, (methodName.charAt(0)+"").toUpperCase());
			methodName="set"+sb.toString();
			if(!methodName.contains("SubProposalInfo"))
			{
				Method m=null;
				if(methodName.contains("Members") || methodName.contains("Dependents") || methodName.contains("Employeenum")||methodName.contains("Dependentnum"))
				{
					m=clazz.getMethod(methodName, int.class);
					m.invoke(obj, entry.getValue()==null ? 0 : Integer.parseInt((String) entry.getValue()));
				}
				else
				{	
					m=clazz.getMethod(methodName, entry.getValue().getClass());				
					m.invoke(obj, entry.getValue()==null ? "" : entry.getValue());
				}
					
				//System.out.println("Invoke Method: "+m.getName());
			}				
		}
			
		//System.out.println("size= "+returnList.size());
		return obj;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> generateListFromExcel(XSSFWorkbook workbook, String sheetName) throws Exception
	{
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		//int availableSheets=workbook.getNumberOfSheets();
		//System.out.println("Available Sheets: "+availableSheets);
		
		String[] columnName=null;
		XSSFSheet sheet=ExcelToPojo.workbook.getSheet(sheetName);
		int availableColumn=sheet.getRow(COLUMNROWNUM).getPhysicalNumberOfCells();

		//System.out.println("Available Columns: "+availableColumn);
		
		//Skip first column of the Entity Name
		columnName=new String[availableColumn];
		
		for(int i=0;i<columnName.length;i++)
		{
			columnName[i]=sheet.getRow(COLUMNROWNUM).getCell(i).getStringCellValue();
			//System.out.println("Column Name: "+columnName[i]);
		}
		
		

		for(int rowIndex=1+COLUMNROWNUM;rowIndex<sheet.getPhysicalNumberOfRows();rowIndex++)
		{
				XSSFRow row=sheet.getRow(rowIndex);
				System.out.println(rowIndex+" row index");
				Map<String,Object> map=new HashMap();
				for(int cellIndex=0;cellIndex<availableColumn;cellIndex++)
				{
					if(row==null)
					{
						continue;
					}
					XSSFCell cell=row.getCell(cellIndex);
					if(columnName[cellIndex]!=null && columnName[cellIndex].trim().length()>0)
					{
						//System.out.print(getCellValue(cell)+" : ");
						map.put(columnName[cellIndex].trim(), getCellValue(cell));
						//System.out.println(columnName[cellIndex]+ " : "+getCellValue(cell));
					}
				}
				result.add(map);
		}
		
		return result;
	}
	
	
	private static String getCellValue(Cell cell){  
        String value = "";  
        if(cell!=null){  
            switch (cell.getCellType()) {  
            case Cell.CELL_TYPE_NUMERIC:  
                if(HSSFDateUtil.isCellDateFormatted(cell)){ 
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());  
                    value = sdf.format(date);  
                }else{  
                    Double data = cell.getNumericCellValue();  
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
                System.out.println("锟斤拷元锟斤拷锟斤筹拷锟街达拷锟斤拷");  
                break;  
            case Cell.CELL_TYPE_FORMULA:  
                value = String.valueOf(cell.getNumericCellValue());    
                if (value.equals("NaN")) {// 锟斤拷锟斤拷锟饺★拷锟斤拷锟斤拷锟街碉拷欠锟�,锟酵斤拷锟斤拷装锟斤拷为锟斤拷应锟斤拷锟街凤拷锟斤拷  
                    value = cell.getStringCellValue().toString();    
                }             
                break;            
            case Cell.CELL_TYPE_BLANK:  
                //System.out.println("锟斤拷元锟斤拷锟斤拷锟斤拷 为锟斤拷值 ");  
                break;            
            default :  
                value = cell.getStringCellValue().toString();  
                break;  
            }  
        }  
        return value;  
    }  
	
	
	//To Agent Email
	public static List<Object> parseExcelToAgentEmailsObject(XSSFWorkbook workbook, String sheetName) throws Exception
	{
		//TEMP
		//ExcelToPojo.workbook=workbook;
		
		List<Object> retVal=new ArrayList();
		List<Map<String,Object>> result=new LinkedList<Map<String,Object>>();
		int availableSheets=workbook.getNumberOfSheets();
		System.out.println("Available Sheets: "+availableSheets);
		
		String[] columnName=null;
		
		XSSFSheet sheet=workbook.getSheet(sheetName);
		
		int availableColumn=sheet.getRow(COLUMNROWNUM).getPhysicalNumberOfCells();
		
		//System.out.println("Available Columns: "+availableColumn);
		
		//Skip first column of the Entity Name
		columnName=new String[availableColumn];
		
		for(int i=0;i<columnName.length;i++)
		{
			columnName[i]=sheet.getRow(COLUMNROWNUM).getCell(i).getStringCellValue();
			//System.out.println("Column Name: "+columnName[i]);
		}
		
		String className=sheet.getSheetName();
		System.out.println("Current Sheet Name: "+className);
			
		Class clazz=Class.forName("com.aia.cs.ipos.entity."+className);
		//System.out.println("Class Name: "+clazz.getName());
		
		if(clazz.getName().contains("AgentEmails"))
		{
			
			for(int rowIndex=1+COLUMNROWNUM;rowIndex<sheet.getPhysicalNumberOfRows();rowIndex++)
			{
				XSSFRow row=sheet.getRow(rowIndex);
				Map<String,Object> map=new HashMap();
				for(int cellIndex=0;cellIndex<availableColumn;cellIndex++)
				{
					XSSFCell cell=row.getCell(cellIndex);
					if(columnName[cellIndex]!=null && columnName[cellIndex].trim().length()>0)
					{
						map.put(columnName[cellIndex].trim(), getCellValue(cell));
						//System.out.println(columnName[cellIndex]+ " : "+getCellValue(cell));
					}
				}
			result.add(map);
		}
		
		retVal=toObjectList(result,clazz);
		}
		
		return retVal;
		//System.out.println(JSONObject.toJSONList("list<>= "+result));
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
