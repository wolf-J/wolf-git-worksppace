package com.aia.cs.ipos.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.ExcelToPojo;
import util.JsonUtil;

public class Debug {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FileInputStream fin=new FileInputStream("C:/Users/bsnpbyg/meux/MyTest/iPosSubmission.xlsx");
		
		XSSFWorkbook workbook=new XSSFWorkbook(fin);
		
		Contact c= ExcelToPojo.getContact(workbook);
		/*MemberContact[] members=ExcelToPojo.getMemberContact(workbook);
		for(MemberContact member: members)
		{
			SubMember[] sm=member.getSubMember();
			for(SubMember s : sm)
			{
				s.setAge(s.getAge().substring(0,2));
			}
		}*/
		//List<SubMember> dependent=ExcelToPojo.getDependentQueue(workbook);
		//List<SubMember> mDependent=new ArrayList<>(dependent);
		
		
		//ProposalInfo proposalInfo=ExcelToPojo.getProposalInfo(workbook);
		System.out.println(JsonUtil.formatObjectToJson(c));
		
		
		
	}

}
