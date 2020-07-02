package com.DemoProject.Utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class utility {
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
	
	// using xpath
	public static void WaitAndTypeValue(WebDriver driver, String value, String xpath )
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).sendKeys(value);
			System.out.println("**** Value Entered in the field****");
	}
	
	//using BY element
	public static void WaitAndTypeValue(WebDriver driver, String value, By ele  )
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.elementToBeClickable(ele)).sendKeys(value);
			System.out.println("**** Value Entered in the field****");
	}

	public static String Screenshoot(WebDriver driver)
	{
		DateFormat dateFormat =new SimpleDateFormat("yyMMddHHmmssZ");
		 
		 //get current date time with Date()
		 Date date = new Date();
		 
		 // Now format the date
		 String date1= dateFormat.format(date);
		 
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		
		File dest=new File(System.getProperty("user.dir")+"/Screenshot/selenium"+date1+".img");
		try {
			FileHandler.copy(src,dest);
			}
		catch (IOException e) {
			
			System.out.println("File not copied"+e.getMessage());
			
		}
		return System.getProperty("user.dir")+"/screenshot/selenium.img";
	}
	//using BY 
	public static void WaitAndClick(WebDriver driver, By ele)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
		System.out.println("****Click performed****");
	}
	public static void WaitAndSelectFromDropdown(WebDriver driver, By ele, String VisibleText )
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		//new Select(driver.findElement()).selectByVisibleText("Admin");
		WebElement selectdropdown =wait.until(ExpectedConditions.elementToBeClickable(ele));
		Select dropdown=new Select(selectdropdown);
		dropdown.selectByValue(VisibleText);
		System.out.println("****Dropdown Value selected****");
	}

	
	//using path
public static void WaitAndClick(WebDriver driver, String xpath)
{
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
	System.out.println("****Click performed****");
}
}

