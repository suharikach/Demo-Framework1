package com.DemoProject.Pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AirTicket 
{
	WebDriver driver;
	By login=By.xpath("//li[@data-cy='account']");
	By FlightButton=By.xpath("//a[@class='active makeFlex hrtlCenter column']");
	By From= By.xpath("//input[@data-cy='fromCity']");
	By To= By.xpath("//input[@data-cy='toCity']");
	//By To= By.xpath("//div[@class='fsw_inputBox searchToCity inactiveWidget activeWidget']//input[@aria-controls='react-autowhatever-1']");
	By FromLocation=By.xpath("//input[@placeholder='From']");
	By ToLocation=By.xpath("//input[@placeholder='To']");
	By ListValue=By.xpath("//li[@id='react-autowhatever-1-section-0-item-0']");
	String url="https://www.makemytrip.com/";
	
	
	@Test
	public void Login()
	{
		System.setProperty("webdriver.chrome.driver", "D:/selinium/chromedriver/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(login).click();
		driver.findElement(FlightButton).click();
		driver.findElement(From).click();
		driver.findElement(FromLocation).sendKeys("hyd");
		driver.findElement(ListValue).click();
		driver.findElement(To).click();
		FindAndEnter(driver, To, "Blr");
		//driver.findElement(ToLocation).sendKeys("Blr");
		driver.findElement(ListValue).click();
		
	}
	
	
	public void FindAndClick(WebDriver driver, By ele)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement elem=wait.until(ExpectedConditions.elementToBeClickable(ele));
		elem.click();
	}
	public void FindAndEnter(WebDriver driver, By ele, String value)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement elem=wait.until(ExpectedConditions.elementToBeClickable(ele));
		elem.click();	
	}

}
