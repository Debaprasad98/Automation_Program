package com.googlesearch.extentreport;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport_Using_TestNG {
	public static WebDriver driver;
	ExtentHtmlReporter report;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void ExtentReport() {
		//Start the reporter
		report=new ExtentHtmlReporter("extent-report_TestNG.html");
		report.config().setReportName("ExtentReport");
		report.config().setTheme(Theme.DARK);

		//Create extent report and attach reporter
		extent=new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("OS", "Windows11");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Author", "Debaprasad");

	}
	@BeforeMethod
	public void LunchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://google.com");
	}
	@Test
	public void MainMethod() {
		test=extent.createTest("ExtentReport_TestNG","This is a Extent report using TestNG");
		driver.findElement(By.name("q")).sendKeys("Automation");
		test.pass("Passed the value into search box");

		Actions act=new Actions(driver);
		act.sendKeys(Keys.ARROW_DOWN).perform();
		act.sendKeys(Keys.ENTER).perform();
		test.pass("Entered in google search button");
	}
	@AfterMethod
	public void closebrowser() {
		driver.close();
		test.pass("Browser closed");
	}
	@AfterTest
	public void teardown() {
		extent.flush();
		test.info("writes everything to the log file");
	}
}
