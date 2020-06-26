package com.DemoProject.Factory;

public class DataProviderFactory 
{
	public static ExcelDataProvider getExcel()
	{
		ExcelDataProvider excel= new ExcelDataProvider();
	return excel;
	}
	public static ConfigDataProvider getconfig()
	{
		ConfigDataProvider config= new ConfigDataProvider();
	return config;
	}
}
