package com.aia.ui.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class TestNgRetryListener implements IAnnotationTransformer, ITestListener{

	@Override
	public void onFinish(ITestContext testContext) {
		// TODO Auto-generated method stub
		
		
		ArrayList<ITestResult> testsToBeRemoved=new ArrayList();
		Set<Integer> passedTestIds=new HashSet();
		for(ITestResult passedTests : testContext.getPassedTests().getAllResults())
		{
			passedTestIds.add(getId(passedTests));
			
		}
		
		Set<Integer> failedTestIds=new HashSet();
		for(ITestResult failedTest : testContext.getFailedTests().getAllResults())
		{
			int failedTestId=getId(failedTest);
			if(failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestIds))
			{
				testsToBeRemoved.add(failedTest);
			}else
			{
				failedTestIds.add(failedTestId);
			}
		}
		
		for(Iterator<ITestResult> iterator =testContext.getFailedTests().getAllResults().iterator();iterator.hasNext();)
		{
			ITestResult testResult=iterator.next();
			
			if(testsToBeRemoved.contains(testResult))
			{
				iterator.remove();
			}
		}
	}

	private int getId(ITestResult result)
	{
		int id=result.getTestClass().getName().hashCode();
		id=id+result.getMethod().getMethodName().hashCode();
		id=id+(result.getParameters()!=null ? Arrays.hashCode(result.getParameters()) : 0);
		return id;
	}
	
	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		System.out.println("Start to execute Coast Automation Test.");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor,
			Method testMethod) {
		// TODO Auto-generated method stub
		/*IRetryAnalyzer retry=annotation.getRetryAnalyzer();
		if(retry==null)
			annotation.setRetryAnalyzer(TestngRetryManager.class);*/
	}

}
