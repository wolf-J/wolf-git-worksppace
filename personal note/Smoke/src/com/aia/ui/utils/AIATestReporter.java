package com.aia.ui.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IInvokedMethod;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.InvokedMethod;
import org.testng.reporters.Files;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class AIATestReporter implements IReporter {

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		// TODO Auto-generated method stub
		for(ISuite suite : suites)
		{
			String suiteName=suite.getName();
			
			System.out.println("Suite Name: "+suiteName);
			
			List<IInvokedMethod>  methods=suite.getAllInvokedMethods();
			
			System.out.println("Method Len: "+methods.size());
			
			StringBuilder sb=new StringBuilder();
			for(IInvokedMethod m : methods)
			{
				
				XmlTest xt=m.getTestMethod().getXmlTest();
				
				System.out.println(m.toString());
				//System.out.println();
				ITestResult result=m.getTestResult();
				Object[] o=result.getParameters();
				sb.append(result.isSuccess() ? "PASSED: " : "FAILED: ");
				sb.append(m.getTestMethod().getMethodName());
				sb.append("(");
				sb.append("\"").append(o[0]).append("\", ").append(o[1]).append(")");
				//System.out.format("{0}: testCoastSmoke(\"{1}\",{2}",result.isSuccess() ? "PASSED" : "FAILED",o[0],o[1]);
				//System.out.println(result.isSuccess() ? "PASSED: " : "FAILED: "+m.getTestMethod().getMethodName()+o[0]+", "+o[1]);
				//System.out.println("Method Name: "+m.getTestMethod().getMethodName());
				sb.append("\r\n");
			}
			
			String result=sb.toString().substring(0,sb.toString().length()-1);

			try {
				Files.writeFile(result, new File("D:\\Smoke\\SmokeResult.txt"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			
		}
	}

}
