package com.googlesearch.extentreport;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport_Program {
	static WebDriver driver;

	public static void main(String[] args) {
		// start reporters
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-report.html");
		htmlReporter.config().setReportName("Functional Report");
		htmlReporter.config().setTheme(Theme.DARK);

		// create ExtentReports and attach reporter(s)
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Hostname", "LocalHost");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("TesterName", "Deba");
		extent.setSystemInfo("Browser", "Chrome");

		// creates a toggle for the given test, adds all log events under it    
		ExtentTest test = extent.createTest("Google Search", "This is a Test Auto for Google Search");

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		test.log(Status.INFO, "Test case started");

		driver.get("https://google.com");
		test.pass("Navigated to google.com");

		driver.findElement(By.name("q")).sendKeys("Automation");
		test.pass("Passed the data into search box");

		Actions act=new Actions(driver);
		act.sendKeys(Keys.ARROW_DOWN).perform();
		act.sendKeys(Keys.ENTER).perform();
		test.pass("Entered in google search button");

		driver.close();
		test.pass("Closed the browser");

		test.info("Test Completed");
		
		// calling flush writes everything to the log file
        extent.flush();
	}
}
