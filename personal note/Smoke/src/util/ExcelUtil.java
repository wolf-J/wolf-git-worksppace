package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	
	public static String idGen()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmm");
		return sdf.format(new Date());
		
	}
	
	public static void updateMemUploadFile(String policyNum,String fileName) throws IOException
	{
		XSSFWorkbook workbook;
		FileInputStream fin=new FileInputStream(System.getProperty("user.dir")+File.separator+fileName);
		workbook=new XSSFWorkbook(fin);
		
		XSSFSheet sheet=workbook.getSheetAt(0);
		int startRow=1;
		int lastRow=sheet.getLastRowNum();
		XSSFRow titleRow=sheet.getRow(startRow);

		String memberType="DependentType";
		int totalFields=titleRow.getPhysicalNumberOfCells();
		
		int clientIndex=0;
		int certNoIndex=0;
		int PolNoIndex=0;
		int employeeNoindex=0;
		int depTypeIndex=0;
		
		System.out.println(titleRow.getFirstCellNum());
		Iterator<Cell> it=titleRow.iterator();
		
		int i=0;
		while(it.hasNext())
		{
			Cell cell=it.next();
			
			String cellVal=cell.getStringCellValue();
			System.out.println(cellVal);
			switch(cellVal)
			{
				case "Client":
					clientIndex=i;
					i++;
					break;
				case "CertNo":
					certNoIndex=i;
					i++;
					break;
				case "PolNo":
					PolNoIndex=i;
					i++;
					break;
				case "EmployeeNo":
					employeeNoindex=i;
					i++;
					break;
				case "DependentType":
					depTypeIndex=i;
					i++;
					break;
				default:
					i++;
					break;
			}
			
		}
		
		System.out.println("CLIENT: "+ clientIndex +", Certno: "+ certNoIndex +", PolNo: "+ PolNoIndex +", EmployeeNo: "+employeeNoindex);
	
		Integer certNo=Integer.parseInt(idGen());
		
		
		for(int memRow=(startRow+1);memRow<=lastRow;memRow++)
		{
		
			XSSFRow row=sheet.getRow(memRow);
			Cell cell=row.getCell(clientIndex);
			cell.setCellValue(toUpperCaseFirstOne(policyNum));
			
			cell=row.getCell(certNoIndex);
			String employeeId=certNo.toString();
			
			cell.setCellValue(employeeId);
			cell=row.getCell(PolNoIndex);
			cell.setCellValue(policyNum);
			
			if(row.getCell(depTypeIndex).getStringCellValue().equalsIgnoreCase("M"))
			{
				cell=row.getCell(employeeNoindex);
				cell.setCellValue(employeeId);
				if(memRow!=lastRow)
				{
					int nextRow=memRow+1;
					XSSFRow followingRow=sheet.getRow(nextRow);
					if(followingRow.getCell(depTypeIndex).getStringCellValue().equalsIgnoreCase("M"))
						certNo++;
					
				}
			}else if(row.getCell(depTypeIndex).getStringCellValue().equalsIgnoreCase("C"))
			{
				certNo++;
			}
		}
		
		
		
		workbook.write(new FileOutputStream(System.getProperty("user.dir")+File.separator+fileName));
		
		fin.close();
	}

	
	public static void updateMemUploadFile1(String policyNum,String fileName) throws IOException
	{
		XSSFWorkbook workbook;
		FileInputStream fin=new FileInputStream(System.getProperty("user.dir")+File.separator+fileName);
		workbook=new XSSFWorkbook(fin);
		/*
		XSSFSheet sheet=workbook.getSheetAt(0);
		int startRow=1;
		int lastRow=sheet.getLastRowNum();
		XSSFRow titleRow=sheet.getRow(startRow);

		String memberType="DependentType";
		int totalFields=titleRow.getPhysicalNumberOfCells();
		
		int clientIndex=0;
		int certNoIndex=0;
		int PolNoIndex=0;
		int employeeNoindex=0;
		int depTypeIndex=0;
		
		System.out.println(titleRow.getFirstCellNum());
		Iterator<Cell> it=titleRow.iterator();
		
		int i=0;
		while(it.hasNext())
		{
			Cell cell=it.next();
			
			String cellVal=cell.getStringCellValue();
			System.out.println(cellVal);
			switch(cellVal)
			{
				case "Client":
					clientIndex=i;
					i++;
					break;
				case "CertNo":
					certNoIndex=i;
					i++;
					break;
				case "PolNo":
					PolNoIndex=i;
					i++;
					break;
				case "EmployeeNo":
					employeeNoindex=i;
					i++;
					break;
				case "DependentType":
					depTypeIndex=i;
					i++;
					break;
				default:
					i++;
					break;
			}
			
		}
		
		System.out.println("CLIENT: "+ clientIndex +", Certno: "+ certNoIndex +", PolNo: "+ PolNoIndex +", EmployeeNo: "+employeeNoindex);
	
		Integer certNo=Integer.parseInt(idGen());
		
		
		for(int memRow=(startRow+1);memRow<=lastRow;memRow++)
		{
		
			XSSFRow row=sheet.getRow(memRow);
			Cell cell=row.getCell(clientIndex);
			cell.setCellValue(toUpperCaseFirstOne(policyNum));
			
			cell=row.getCell(certNoIndex);
			String employeeId=certNo.toString();
			
			cell.setCellValue(employeeId);
			cell=row.getCell(PolNoIndex);
			cell.setCellValue(policyNum);
			
			if(row.getCell(depTypeIndex).getStringCellValue().equalsIgnoreCase("M"))
			{
				cell=row.getCell(employeeNoindex);
				cell.setCellValue(employeeId);
				if(memRow!=lastRow)
				{
					int nextRow=memRow+1;
					XSSFRow followingRow=sheet.getRow(nextRow);
					if(followingRow.getCell(depTypeIndex).getStringCellValue().equalsIgnoreCase("M"))
						certNo++;
					
				}
			}else if(row.getCell(depTypeIndex).getStringCellValue().equalsIgnoreCase("C"))
			{
				certNo++;
			}
		}
		
		
		
		workbook.write(new FileOutputStream(System.getProperty("user.dir")+File.separator+fileName));
		*/
		fin.close();
	}
	
	private static void copyRow(XSSFRow fromRow, XSSFRow toRow)
	{
		for(Iterator cellIt=fromRow.cellIterator();cellIt.hasNext();)
		{
			Cell tmpCell=(Cell)cellIt.next();
			Cell newCell=toRow.createCell(tmpCell.getColumnIndex());
			copyCell(tmpCell,newCell);
		}
	}
	
	private static void copyCell(Cell fromCell, Cell toCell)
	{
		toCell.setCellStyle(fromCell.getCellStyle());
		toCell.setCellType(fromCell.getCellType());
		
		int srcCellType=fromCell.getCellType();
		
		switch(srcCellType)
		{
		case Cell.CELL_TYPE_NUMERIC:
			toCell.setCellValue(fromCell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			toCell.setCellValue(fromCell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			toCell.setCellValue(fromCell.getBooleanCellValue());
			break;
		default:
			toCell.setCellValue(fromCell.getStringCellValue());
			break;
		}
	}
	
	
	public static void updateMemUploadFile(String policyNum,String fileName, int rowNumber) throws IOException
	{
		XSSFWorkbook workbook;
		FileInputStream fin=new FileInputStream(System.getProperty("user.dir")+File.separator+fileName);
		workbook=new XSSFWorkbook(fin);
		
		XSSFSheet sheet=workbook.getSheetAt(0);
		int startRow=1;
		int lastRow=sheet.getLastRowNum();
		
		int currentRowNumbers=lastRow-startRow;
		
		int addedRowNumbers=rowNumber-currentRowNumbers;
		
		if(addedRowNumbers>0)
		{
			XSSFRow copiedRow=sheet.getRow(2);
			
			for(int i=1;i<=addedRowNumbers;i++)
			{
				XSSFRow createdNewRow=sheet.createRow(i+lastRow);
				copyRow(copiedRow,createdNewRow);
			}
		}else if(addedRowNumbers<0)
		{
			System.out.println("Would remove row.");
			addedRowNumbers=Math.abs(addedRowNumbers);
			for(int i=1;i<=addedRowNumbers;i++)
			{
				XSSFRow toBeRemovedRow=sheet.getRow((rowNumber+1)+i);
				sheet.removeRow(toBeRemovedRow);
				
			}
			
		}
		
		XSSFRow titleRow=sheet.getRow(startRow);
		lastRow=sheet.getLastRowNum();
		String memberType="DependentType";
		int totalFields=titleRow.getPhysicalNumberOfCells();
		
		int clientIndex=0;
		int certNoIndex=0;
		int PolNoIndex=0;
		int employeeNoindex=0;
		int depTypeIndex=0;
		
		System.out.println(titleRow.getFirstCellNum());
		Iterator<Cell> it=titleRow.iterator();
		
		int i=0;
		while(it.hasNext())
		{
			Cell cell=it.next();
			
			String cellVal=cell.getStringCellValue();
			//System.out.println(cellVal);
			switch(cellVal)
			{
				case "Client":
					clientIndex=i;
					i++;
					break;
				case "CertNo":
					certNoIndex=i;
					i++;
					break;
				case "PolNo":
					PolNoIndex=i;
					i++;
					break;
				case "EmployeeNo":
					employeeNoindex=i;
					i++;
					break;
				case "DependentType":
					depTypeIndex=i;
					i++;
					break;
				default:
					i++;
					break;
			}
			
		}
		
		//System.out.println("CLIENT: "+ clientIndex +", Certno: "+ certNoIndex +", PolNo: "+ PolNoIndex +", EmployeeNo: "+employeeNoindex);
	
		Integer certNo=Integer.parseInt(idGen());
		
		
		for(int memRow=(startRow+1);memRow<=lastRow;memRow++)
		{
		
			XSSFRow row=sheet.getRow(memRow);
			Cell cell=row.getCell(clientIndex);
			cell.setCellValue(toUpperCaseFirstOne(policyNum));
			
			cell=row.getCell(certNoIndex);
			String employeeId=certNo.toString();
			
			cell.setCellValue(employeeId);
			cell=row.getCell(PolNoIndex);
			cell.setCellValue(policyNum);
			
			if(row.getCell(depTypeIndex).getStringCellValue().equalsIgnoreCase("M"))
			{
				cell=row.getCell(employeeNoindex);
				cell.setCellValue(employeeId);
				if(memRow!=lastRow)
				{
					int nextRow=memRow+1;
					System.out.println("Current Row Number: "+nextRow);;
					XSSFRow followingRow=sheet.getRow(nextRow);
					if(followingRow.getCell(depTypeIndex).getStringCellValue().equalsIgnoreCase("M"))
						certNo++;
					
				}
			}else if(row.getCell(depTypeIndex).getStringCellValue().equalsIgnoreCase("C"))
			{
				certNo++;
			}
		}
		
		
		
		workbook.write(new FileOutputStream(System.getProperty("user.dir")+File.separator+fileName));
		
		fin.close();
	}
	
	public static void updateGLVMemUploadFile(String policyNum,String fileName) throws IOException
	{
		XSSFWorkbook workbook;
		FileInputStream fin=new FileInputStream(System.getProperty("user.dir")+File.separator+fileName);
		workbook=new XSSFWorkbook(fin);
		
		XSSFSheet sheet=workbook.getSheetAt(0);
		int startRow=1;
		int lastRow=sheet.getLastRowNum();
		XSSFRow titleRow=sheet.getRow(startRow);

		String memberType="DependentType";
		int totalFields=titleRow.getPhysicalNumberOfCells();
		
		int clientIndex=0;
		int certNoIndex=0;
		int PolNoIndex=0;
		int employeeNoindex=0;
		int depTypeIndex=0;
		
		System.out.println(titleRow.getFirstCellNum());
		Iterator<Cell> it=titleRow.iterator();
		
		int i=0;
		while(it.hasNext())
		{
			Cell cell=it.next();
			
			String cellVal=cell.getStringCellValue();
			System.out.println(cellVal);
			switch(cellVal)
			{
				case "Client":
					clientIndex=i;
					i++;
					break;
				case "CertNo":
					certNoIndex=i;
					i++;
					break;
				case "PolNo":
					PolNoIndex=i;
					i++;
					break;
				case "EmployeeNo":
					employeeNoindex=i;
					i++;
					break;
				case "DependentType":
					depTypeIndex=i;
					i++;
					break;
				default:
					i++;
					break;
			}
			
		}
		
		System.out.println("CLIENT: "+ clientIndex +", Certno: "+ certNoIndex +", PolNo: "+ PolNoIndex +", EmployeeNo: "+employeeNoindex);
	
		Integer certNo=Integer.parseInt(idGen());
		
		
		for(int memRow=(startRow+1);memRow<=lastRow;memRow++)
		{
		
			XSSFRow row=sheet.getRow(memRow);
			Cell cell=row.getCell(clientIndex);
			
			cell.setCellValue(toUpperCaseFirstOne(policyNum));
			
			cell=row.getCell(certNoIndex);
			String employeeId=certNo.toString();
			
			cell.setCellValue(employeeId);
			cell=row.getCell(PolNoIndex);
			cell.setCellValue(policyNum);
			
			if(row.getCell(depTypeIndex).getStringCellValue().equalsIgnoreCase("M"))
			{
				cell=row.getCell(employeeNoindex);
				cell.setCellValue(employeeId);
				certNo++;
			}else if(row.getCell(depTypeIndex).getStringCellValue().equalsIgnoreCase("S"))
			{
				certNo--;
				employeeId=certNo.toString();
				cell=row.getCell(certNoIndex);
				cell.setCellValue(employeeId);
			}
		}
		workbook.write(new FileOutputStream(System.getProperty("user.dir")+File.separator+fileName));
		fin.close();
	}
	
	public static String toUpperCaseFirstOne(String s){
		  if(Character.isUpperCase(s.charAt(0)))
		    return s;
		  else
		    return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	
	public static void main(String ...args) throws IOException
	{
		
		int argLens=args.length;
		String policyNumber;
		String memberFile;
		int memberCount;
		if(argLens<3)
		{
			throw new RuntimeException("Arguments Count must be larger than 3, and following the order: 1 Policy Number, 2 Member File Name, 3 Member Count");
		}else
		{
			try
			{
				policyNumber=args[0];
				memberFile=args[1];
				memberCount=Integer.parseInt(args[2]);
				
				System.out.println("Policy Number: "+policyNumber+", Member File: "+memberFile+", MemberCount: "+memberCount);
			}catch(Exception e)
			{
				throw new RuntimeException(e.getMessage());
			}
		}
		
		
		
		//ExcelUtil.updateMemUploadFile(policyNumber, memberFile, memberCount);
		
	}
	
	
	
}
