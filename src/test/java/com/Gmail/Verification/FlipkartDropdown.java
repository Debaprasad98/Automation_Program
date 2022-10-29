package com.Gmail.Verification;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class FlipkartDropdown {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://flipkart.com");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/button")).click();
		driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/div/div/div[2]/a/div[2]")).click();
		Thread.sleep(3000);
		WebElement wb=driver.findElement(By.xpath("//span[text()='TVs & Appliances']"));
		Actions act=new Actions(driver);
		act.moveToElement(wb);
		act.perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/div/div/div/div[4]/a[4]")).click();
		System.out.println(driver.getTitle());
		String s=driver.getTitle();
		if (driver.getTitle().equals(s)) {
			Assert.assertTrue(true,"PASS");
			System.out.println("PASS");
		}else {
			Assert.assertFalse(false,"FAIL");
			System.out.println("FAIL");
		}
		driver.close();
	}

}
