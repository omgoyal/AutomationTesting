package com.engexcellence.qa.extentreportlistener;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.engexcellence.qa.base.TestBase;
import com.engexcellence.qa.util.TestUtil;
import com.engexcellence.qa.util.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import com.relevantcodes.extentreports.LogStatus;

import com.relevantcodes.extentreports.model.Test;

public class ExtentReport extends TestBase implements IReporter {
	
	
	public ExtentReports extent;
	
	public void start(ExtentReports extent) {
		 System.out.println("Test Case execution started....");
			
		}
	
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory){
		
		extent = new ExtentReports(outputDirectory + File.separator + "\\extentreports\\"+ "\\Gmail Application Report"+TestUtil.simpleDateFormat()+".html", true);
		
		for(ISuite suite: suites){
			Map<String, ISuiteResult> result = suite.getResults();
			
			for(ISuiteResult r : result.values()){
				ITestContext context = r.getTestContext();
				try {
					buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					buildTestNodes(context.getFailedTests(),LogStatus.FAIL);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				try {
					buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				
			}
		}
		
		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) throws IOException {
		
		ExtentTest test;
		
		
		if(tests.size()>0){
			
			for(ITestResult result : tests.getAllResults()){
				test = extent.startTest(result.getMethod().getMethodName());
				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));
				
				for(String group : result.getMethod().getGroups()){
					test.assignCategory(group);
				}
				
				if(result.getThrowable()!=null){
					//String screenshotPath = TestUtil.captureScreenshot(driver,System.getProperty("user.dir")+"\\screenshots\\"+TestUtil.simpleDateFormat()+".png");
					
					//test.log(status, test.addScreenCapture(WebEventListener.screenshotDestination));
					test.log(status, result.getMethod().getMethodName() + " test case is failed", test.addScreenCapture(WebEventListener.screenshotDestination));
					 test.log(status, result.getMethod().getMethodName() + " test failure description", result.getThrowable().fillInStackTrace());	
				}else if(status==LogStatus.SKIP){
					test.log(status, result.getMethod().getMethodName() + " test case is skipped", result.getName());
				}else if(status==LogStatus.PASS) {
					test.log(status, result.getMethod().getMethodName() + " test case is passed", result.getName());
				}
				extent.endTest(test);
			}
			
		
		}
		
	}
	
	
	private Date getTime(long millis){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
		
	}

	public void addTest(Test arg0) {
		// TODO Auto-generated method stub
		
	}

	public void flush() {
		// TODO Auto-generated method stub
		
	}

	public void setTestRunnerLogs() {
		// TODO Auto-generated method stub
		
	}

	
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	

	

	

	

	

	

}
