package com.DemoProject.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigDataProvider
{
	Properties pro;
public ConfigDataProvider()
{
	pro= new Properties();
	try {
		pro.load(new FileInputStream(new File(System.getProperty("user.dir")+"/Configuration/config.properties")));
	} 
	catch (IOException e)
	{

System.out.println("Unable to load config file"+e.getMessage());
	}
}
	public String getPropry(String Key)
	{
		return pro.getProperty(Key);
		
	}
	
}
