package com.DemoProject.Factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
	
	public static WebDriver StartBrowser(String browsertype, String url)
	{
		WebDriver driver=null;
	if(browsertype.equalsIgnoreCase("Chrome"))
	{
	
		System.setProperty("webdriver.chrome.driver", "D:/selinium/chromedriver/chromedriver.exe");
		driver=new ChromeDriver();
	}
	else if(browsertype.equalsIgnoreCase("IE"))
	
	{		
		System.setProperty("webdriver.ie.driver", "D:/selinium/IEDriverServer.exe");
		driver=new InternetExplorerDriver();
	}
	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	return driver;

}
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	
}
