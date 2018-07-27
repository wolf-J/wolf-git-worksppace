package com.aia.coast.testcase;

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

public class Client {
	public String getInitialEffectiveDate() {
		return InitialEffectiveDate;
	}
	public void setInitialEffectiveDate(String initialEffectiveDate) {
		InitialEffectiveDate = initialEffectiveDate;
	}
	public String getClientName() {
		return ClientName;
	}
	public void setClientName(String clientName) {
		ClientName = clientName;
	}
	public String getClientEnglishName() {
		return ClientEnglishName;
	}
	public void setClientEnglishName(String clientEnglishName) {
		ClientEnglishName = clientEnglishName;
	}
	public String isReceiveAppForm() {
		return ReceiveAppForm;
	}
	public void setReceiveAppForm(String receiveAppForm) {
		ReceiveAppForm = receiveAppForm;
	}
	public String getAddress1() {
		return Address1;
	}
	public void setAddress1(String address1) {
		Address1 = address1;
	}
	public String getAddress2() {
		return Address2;
	}
	public void setAddress2(String address2) {
		Address2 = address2;
	}
	public String getAddress3() {
		return Address3;
	}
	public void setAddress3(String address3) {
		Address3 = address3;
	}
	public String getProvinceAndState() {
		return ProvinceAndState;
	}
	public void setProvinceAndState(String provinceAndState) {
		ProvinceAndState = provinceAndState;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public String getSubDistrict() {
		return SubDistrict;
	}
	public void setSubDistrict(String subDistrict) {
		SubDistrict = subDistrict;
	}
	public String getContactPerson() {
		return ContactPerson;
	}
	public void setContactPerson(String contactPerson) {
		ContactPerson = contactPerson;
	}
	public String getNatureOfBusiness() {
		return NatureOfBusiness;
	}
	public void setNatureOfBusiness(String natureOfBusiness) {
		NatureOfBusiness = natureOfBusiness;
	}
	public String getGeneratedCertNoIndicator() {
		return GeneratedCertNoIndicator;
	}
	public void setGeneratedCertNoIndicator(String generatedCertNoIndicator) {
		GeneratedCertNoIndicator = generatedCertNoIndicator;
	}
	public String getSalaryMode() {
		return SalaryMode;
	}
	public void setSalaryMode(String salaryMode) {
		SalaryMode = salaryMode;
	}
	public String getPayeeName() {
		return PayeeName;
	}
	public void setPayeeName(String payeeName) {
		PayeeName = payeeName;
	}
	public String getSubOfficeProducer() {
		return SubOfficeProducer;
	}
	public void setSubOfficeProducer(String subOfficeProducer) {
		SubOfficeProducer = subOfficeProducer;
	}
	
	
	private String InitialEffectiveDate;
	private String ClientName;
	private String ClientEnglishName;
	private String ReceiveAppForm;
	private String Address1;
	private String Address2;
	private String Address3;
	private String ProvinceAndState;
	private String District;
	private String SubDistrict;
	private String ContactPerson;
	private String PayeeName;
	private String NatureOfBusiness;
	private String GeneratedCertNoIndicator;
	private String SalaryMode;
	private String SubOfficeProducer;
	private String TaxId;
	
	public String getTaxId() {
		return TaxId;
	}
	public void setTaxId(String taxId) {
		TaxId = taxId;
	}

	
	public String getSalaryCurrency() {
		return salaryCurrency;
	}
	public void setSalaryCurrency(String salaryCurrency) {
		this.salaryCurrency = salaryCurrency;
	}
	public String getPreferLanguage() {
		return preferLanguage;
	}
	public void setPreferLanguage(String preferLanguage) {
		this.preferLanguage = preferLanguage;
	}


	private String salaryCurrency;
	private String preferLanguage;
	
	
	public static void main(String ...args) throws Exception
	{
	}
		
		
		//System.out.println("Client: \r\n"+JsonUtil.formatObjectToJson(clients));
		
		//System.out.println("Distribution Channel: \r\n"+JsonUtil.formatObjectToJson(dcs));

	
	
	
	
	
	
	
	
	
	
	
	
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
}

