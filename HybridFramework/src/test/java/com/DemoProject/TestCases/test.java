package com.DemoProject.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.DemoProject.Factory.DataProviderFactory;
import com.DemoProject.Pages.BaseClass;
import com.DemoProject.Pages.LoginPage;

public class test extends BaseClass
{
	WebDriver driver;
	LoginPage login;
	@Test
	public void loginWIthAdmin()
	{
		System.out.println("Log info **** load test case ******");
		login=PageFactory.initElements(driver, LoginPage.class);
		logger=Reports.createTest("test","Login into CRM application description");
		System.out.println(DataProviderFactory.getExcel().ExcelDataValue("CRM", 0, 0)+"    "+
				DataProviderFactory.getExcel().ExcelDataValue("CRM",0,1));
		login=PageFactory.initElements(driver, LoginPage.class);
		login.LogintoApplication(DataProviderFactory.getExcel().ExcelDataValue("CRM", 0, 0)
				,				DataProviderFactory.getExcel().ExcelDataValue("CRM",0,1));
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "url does not have dashboard");
		
	}
	
	
}
