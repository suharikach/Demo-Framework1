package com.DemoProject.Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.DemoProject.Factory.BrowserFactory;
import com.DemoProject.Factory.DataProviderFactory;
import com.DemoProject.Utility.utility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class BaseClass {
public ExtentReports Reports;
public ExtentTest logger;
public WebDriver driver;
	
	@BeforeSuite
	public void StatusReport()
	{
		
	
	ExtentHtmlReporter html=new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/Selenium.html");
	Reports=new ExtentReports();
	Reports.attachReporter(html);
	System.out.println("*****Log INFO   ---Reporting set---    ****");
	
	}
	@AfterMethod
	public void TearDownReport(ITestResult result)
	{
		System.out.println("Log info   ****After Test   ****"+result.getStatus());
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, "Test passed");
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			try {
				logger.log(Status.FAIL, "Test failed"+result.getThrowable().getMessage(), 
						MediaEntityBuilder.createScreenCaptureFromPath(utility.Screenshoot(driver)).build());
			} catch (IOException e) {
			System.out.println("Exception is"+e.getMessage());
			}
		}
		else if(result.getStatus()==ITestResult.SKIP)
			logger.log(Status.SKIP, "Test Skiped");
		Reports.flush();
	}

	
@AfterClass
public void EndSession()
{
	
	BrowserFactory.closeBrowser(driver);
}

@BeforeClass
public void startSession()
{
	System.out.println("*****Log INFO   ---Loading Browser config---    ****");
	String browser=DataProviderFactory.getconfig().getPropry("browser");
	String url=DataProviderFactory.getconfig().getPropry("URL");
	BrowserFactory.StartBrowser(browser, url);
	System.out.println("*****Log INFO   ---Browser config loaded---    ****");
}
}
