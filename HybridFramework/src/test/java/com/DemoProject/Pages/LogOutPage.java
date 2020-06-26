package com.DemoProject.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.DemoProject.Utility.utility;

public class LogOutPage {
	WebDriver driver;
	By welcomLink=By.id("welcome");
	By LogoutButton= By.linkText("Logout");
	public LogOutPage(WebDriver ldriver)
	{
		driver=ldriver;
		utility.WaitAndClick(ldriver, welcomLink);
		utility.WaitAndClick(ldriver, LogoutButton);
	}
	}
	
