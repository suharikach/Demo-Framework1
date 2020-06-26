package com.DemoProject.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.DemoProject.Utility.utility;

public class AddUser {

WebDriver driver;
public AddUser(WebDriver driver)
{
		
	By username=By.xpath("//b[text()='Admin']");
	By password=By.xpath("//b[text()='Admin']");
	By userRole=By.xpath("//label[text()='User Role']//following::select[1]");
	By EmployeeName=By.xpath("//label[text()='Employee Name']//following::input[1]");
	By NewUserName=By.xpath("//label[text()='Username']//following::input[1]");
	By UserStatus =By.xpath("//label[text()='Status']//following::select[1]");
	By NewUserPassword =By.xpath("//label[text()='Password']//following::input[1]");
	By ConfirmPassword=By.xpath("//label[text()='Confirm Password']//following::input[1]");
	By Save=By.id("btnSave");
	By SuccessText=By.xpath("//div[contains(text(),'Successfully')]");
	utility.WaitAndClick(driver, username);
	utility.WaitAndClick(driver, password);
	//driver.findElement(By.xpath("//b[text()='Admin']")).click();
	//driver.findElement(By.xpath("//input[@value='Add']")).click();
	utility.WaitAndSelectFromDropdown(driver, userRole, "Admin");
	//new Select(driver.findElement(By.xpath("//label[text()='User Role']//following::select[1]"))).selectByVisibleText("Admin");
	utility.WaitAndTypeValue(driver, "suharika4",EmployeeName);
	utility.WaitAndTypeValue(driver, "Suharika84", NewUserName);
	utility.WaitAndSelectFromDropdown(driver, UserStatus, "Enabled");
	utility.WaitAndTypeValue(driver, "admin123", NewUserPassword);
	utility.WaitAndTypeValue(driver, "admin123", ConfirmPassword);
	utility.WaitAndClick(driver, Save);
	
	//driver.findElement().sendKeys("John Smith");
	
	//driver.findElement(By.xpath("//label[text()='Username']//following::input[1]")).sendKeys(user);
//	new Select(driver.findElement(By.xpath("//label[text()='Status']//following::select[1]"))).selectByVisibleText("Enabled");
	//driver.findElement(By.xpath("//label[text()='Password']//following::input[1]")).sendKeys(pass);
	//driver.findElement(By.xpath("//label[text()='Confirm Password']//following::input[1]")).sendKeys(pass);
//	driver.findElement(By.id("btnSave")).click();
	if(driver.findElement(SuccessText).isDisplayed())
	{
		System.out.println("Added");
	}
}}