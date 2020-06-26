package com.DemoProject.UnitTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.DemoProject.Factory.ConfigDataProvider;
import com.DemoProject.Factory.ExcelDataProvider;

public class DataproviderTest2 
{

	@Test
	public void excelTest1()
	{
		ExcelDataProvider excel=new ExcelDataProvider();
		String value= excel.ExcelDataValue("CRM", 0, 0);
		Assert.assertEquals("Admin", value);
	}
	@Test
	public void excelTest()
	{
		ConfigDataProvider excel=new ConfigDataProvider();
		String value= excel.getPropry("value");
		Assert.assertEquals("4.0", value);
	}
	
	
}
