package com.DemoProject.UnitTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.DemoProject.Factory.ConfigDataProvider;
import com.DemoProject.Factory.DataProviderFactory;
import com.DemoProject.Factory.ExcelDataProvider;

public class DataproviderTest 
{

	@Test
	public void excelTest1()
	{
		
		String value= DataProviderFactory.getExcel().ExcelDataValue("CRM", 0, 0);
		Assert.assertEquals("Admin", value);
	}
	@Test
	public void excelTest()
	{
		
		String value= DataProviderFactory.getconfig().getPropry("value");
		Assert.assertEquals("4.0", value);
	}
	
	
}
