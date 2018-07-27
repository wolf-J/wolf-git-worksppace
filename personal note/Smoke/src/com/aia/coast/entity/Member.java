package com.aia.coast.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Member {
	
	SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	SimpleDateFormat sdf2=new SimpleDateFormat("MM-dd-yyyy");
	
	
	public String getSuboffice() {
		return suboffice;
	}
	public void setSuboffice(String subOffice) {
		this.suboffice = subOffice;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getDOB() {
		return DOB;
	}
	
	public void setDOB(String dOB) {
		
		try {
			Date date = sdf1.parse(dOB);
			DOB=sdf2.format(date);
			System.out.println("DOB: "+DOB);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//DOB = dOB.substring(0,dOB.indexOf(" "));
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getMaritalStatus() {
		return MaritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		MaritalStatus = maritalStatus;
	}
	public String getEmployeeNo() {
		return EmployeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		EmployeeNo = employeeNo;
	}
	public String getEmploymentDate() {
		return EmploymentDate;
	}
	public void setEmploymentDate(String employmentDate) {
		
		try {
			Date date = sdf1.parse(employmentDate);
			this.EmploymentDate=sdf2.format(date);
			System.out.println("EmploymentDate: "+EmploymentDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		
		try {
			Date date = sdf1.parse(effectiveDate);
			this.effectiveDate=sdf2.format(date);
			System.out.println("effectiveDate: "+effectiveDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getVIP() {
		return VIP;
	}
	public void setVIP(String vIP) {
		VIP = vIP;
	}
	
	private String suboffice;
	private String Title;
	private String firstName;
	private String lastName;
	private String DOB;
	private String Sex;
	private String MaritalStatus;
	private String EmployeeNo;
	private String EmploymentDate;
	private String salary;
	private String effectiveDate;
	private String VIP;
	
}
