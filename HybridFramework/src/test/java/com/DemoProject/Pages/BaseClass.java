package com.DemoProject.Pages;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.DemoProject.Factory.BrowserFactory;
import com.DemoProject.Factory.DataProviderFactory;
import com.DemoProject.Utility.utility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.beust.jcommander.Parameter;


public class BaseClass {
public static ExtentReports Reports;
public static ExtentTest logger;
public static WebDriver driver;
	
	@BeforeSuite
	public void StatusReport()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssZ");
		 
		 //get current date time with Date()
		 Date date = new Date();
		 
		 // Now format the date  
		 String date1= dateFormat.format(date);
		 System.out.println(date1);
			ExtentHtmlReporter html=new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/Seleniumreport.html");
		 //ExtentHtmlReporter html=new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/Selenium"+date1+".html");
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
			try {
				logger.log(Status.PASS, "Test passed",MediaEntityBuilder.createScreenCaptureFromPath(utility.Screenshoot(driver)).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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


@Parameters({"browser","URL"})
@BeforeClass
public void startSession(String browserType, String urlToLoad)
{
	System.out.println("*****Log INFO   ---Loading Browser config---    ****");
	//String browser=DataProviderFactory.getconfig().getPropry("browser");
	//String url=DataProviderFactory.getconfig().getPropry("URL");
	driver=BrowserFactory.StartBrowser(browserType, urlToLoad);
	System.out.println("*****Log INFO   ---Browser config loaded---    ****");
}


/*@BeforeClass
public void startSession()
{
	System.out.println("*****Log INFO   ---Loading Browser config---    ****");
	String browser=DataProviderFactory.getconfig().getPropry("browser");
	String url=DataProviderFactory.getconfig().getPropry("URL");
	driver=BrowserFactory.StartBrowser(browser, url);
	System.out.println("*****Log INFO   ---Browser config loaded---    ****");
}*/
}
