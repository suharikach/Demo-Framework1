package com.DemoProject.Pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AirTicket {
	WebDriver driver;
	By FlightName = By.xpath("//div[@class='pull-left airways-info-sect']//p[@class='fli-code']");
	By FlightPRC = By.xpath("//div[@class='dept-options-section clearfix']//span[@class='actual-price']");
	By search = By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn' or text()='Search']");
	By login = By.xpath("//li[@data-cy='account']");
	By FlightButton = By.xpath("//a[@class='active makeFlex hrtlCenter column']");
	By From = By.xpath("//input[@data-cy='fromCity']");
	By To = By.xpath("//input[@data-cy='toCity']");
	// By To= By.xpath("//div[@class='fsw_inputBox searchToCity inactiveWidget
	// activeWidget']//input[@aria-controls='react-autowhatever-1']");
	By FromLocation = By.xpath("//input[@placeholder='From']");
	By ToLocation = By.xpath("//input[@placeholder='To']");
	By ListValue = By.xpath("//div[@class='pushRight font14 lightGreyText latoBold']");
	String url = "https://www.makemytrip.com/";
	By DepDate = By.xpath("//p[@data-cy='departureDate']");
	By month = By.xpath("//div[@class='DayPicker-Months']/div/div[@role='heading'][1]/div[1]");
	By NextMonth = By.xpath("//span[@aria-label='Next Month']");
	By day = By.xpath("//div[@class='dateInnerCell']/p[text()='15'][1]");
	By popup = By.xpath("//div[@class='autopop__wrap makeFlex column defaultCursor']");

	@Test
	public void Login() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/sai.suharika.chakka/suharika/selenium/chromedriver_win32/chromedriver83.exe");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();

		if (driver.findElements(popup) != null) {
			driver.findElement(login).click();
		} else {
		}
		FindAndClick(driver, FlightButton);
		FindAndEnter(driver, From, "hyd");
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);

		List<WebElement> options = Findlist(driver, ListValue);

		for (WebElement op : options) {
			System.out.println(op.getText());
			if (op.getText().equalsIgnoreCase("HYD")) {
				op.click();
				System.out.println("Click performed");
				break;
			}
		}
		driver.findElement(To).sendKeys("del");
		List<WebElement> options1 = driver.findElements(ListValue);
		for (WebElement op : options1) {
			if (op.getText().equalsIgnoreCase("del")) {
				op.click();
				break;
			}
		}

		CalDate();
		FindAndClick(driver, search);
		// driver.findElement(search);
		Thread.sleep(3000);
		FLightResults();
	}

	public void CalDate() {
		driver.findElement(DepDate).click();
		String ExpectedMonth = "September 2020";
		String ActMon = driver
				.findElement(By.xpath("//div[@class='DayPicker-Months']/div/div[@role='heading'][1]/div[1]")).getText();
		while ((ExpectedMonth.equalsIgnoreCase(ActMon)) != true) {
			ActMon = driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div/div[@role='heading'][1]/div[1]"))
					.getText();
			if (ExpectedMonth.equalsIgnoreCase(ActMon)) {
				break;
			}

			driver.findElement(NextMonth).click();
		}
		driver.findElement(day).click();

	}

	public int TotalFlights() {
		List<WebElement> DifFlights = driver
				.findElements(By.xpath("//p[text()='Airlines']/following-sibling::div //div[@class='flexOne']/p"));
		int Total = 0;
		for (WebElement fli : DifFlights) {
			String num = fli.getText();
			String a[] = num.split("[()]");
			int value = Integer.parseInt(a[1]);
			Total = Total + value;
		}

		System.out.println("Total flights based on criteria are " + Total);
		return Total;
	}

	public void FLightResults() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		int count = TotalFlights();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		System.out.println("Printing the flight results");
		List<WebElement> FName = driver.findElements(FlightName);
		List<WebElement> FPRC = driver.findElements(FlightPRC);
		int TotalFlights = FName.size();
		while (TotalFlights < count) {
			WebElement ele = FName.get(TotalFlights - 1);
			js.executeScript("arguments[0].scrollIntoView();", ele);
			FName = driver.findElements(FlightName);
			FPRC = driver.findElements(FlightPRC);
			TotalFlights = FName.size();
		}
		int prc[] = new int[TotalFlights];
		for (int i = 0; i < TotalFlights; i++) {
			String k = FName.get(i).getText();
			String j = (String) FPRC.get(i).getText().subSequence(2, 7);
			String l = j.replace(",", "");
			prc[i] = Integer.parseInt(l);
			System.out.println(i + "The FlightName is" + k + "price is " + prc[i]);
		}
		// find the least value

		int index=0, first = prc[0];
		for (int i = 1; i <TotalFlights; i++) {
			if (prc[i] < first) {
				first = prc[i];
				index = i;
			}
			
		}
	System.out.println("Flight with low fare is"+FName.get(index).getText()+"  Lost fair is "+FPRC.get(index).getText());

	}

	public void FindAndClick(WebDriver driver, By ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(ele));
		elem.click();
	}

	public void FindAndClick(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(ele));
		elem.click();
	}

	public void FindAndEnter(WebDriver driver, By ele, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(ele));
		elem.sendKeys(value);
	}

	public List<WebElement> Findlist(WebDriver driver, By ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		List<WebElement> elem = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ele));
		return elem;
	}

}
