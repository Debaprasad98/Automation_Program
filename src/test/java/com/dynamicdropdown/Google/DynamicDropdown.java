package com.dynamicdropdown.Google;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class DynamicDropdown {
	/*
	 * Author: Debaprasad Das
	 * Date: 16/10/2022
	 */
	public static WebDriver driver;

	@Test
	public void dropdown() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://google.com");
		titleverification();//Search Page title Verification
		dropdownverification();//Drop-down Page Verification

	}
	public static void titleverification() {
		if (driver.getTitle().equals("Google")) {
			Assert.assertTrue(true);
		}else {
			Assert.assertFalse(false);
		}
	}
	public static void dropdownverification() throws Throwable {
		WebElement wb=driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
		wb.sendKeys("te");
		Actions act=new Actions(driver);
		Thread.sleep(2000);
		act.sendKeys(Keys.ARROW_DOWN).perform();
		act.sendKeys(Keys.ENTER).perform();
		if(driver.getPageSource().contains("Telegram Web")) {
			Assert.assertTrue(true);
		}else {
			Assert.assertFalse(false);
		}
	}
	@AfterClass
	public void closebrowser() {
		driver.close();
	}
}
