package com.DataDriven.Testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataReadFrom_Excel {

	WebDriver driver;
	DataReadFrom_Excel data;
	FileInputStream fis;
	Workbook wb;

	@Test
	public void readdata() {
		System.out.println("Title name====>"+driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
	}
	@BeforeClass
	public void Lunchbrowser() {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
	}
	@BeforeMethod
	public void loginpage() throws Throwable {
		data=new DataReadFrom_Excel();
		WebElement UN=driver.findElement(By.id("Email"));
		UN.clear();
		UN.sendKeys(data.usern());
		WebElement PWD=driver.findElement(By.id("Password"));
		PWD.clear();
		PWD.sendKeys(data.pwdn());
		driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button")).click();

	}
	public String usern() throws Throwable {
		String path="C:/Users/debap/eclipse-workspace/Automation_Program/src/test/java/com/DataDriven/Testing/Datadriven.xlsx";
		fis=new FileInputStream(path);
		wb=WorkbookFactory.create(fis);
		String Username=wb.getSheet("Sheet1").getRow(1).getCell(0).getStringCellValue();
		System.out.println(Username);
		return Username;
	}
	public String pwdn() throws Throwable {
		String path="C:/Users/debap/eclipse-workspace/Automation_Program/src/test/java/com/DataDriven/Testing/Datadriven.xlsx";
		fis=new FileInputStream(path);
		wb=WorkbookFactory.create(fis);
		String Password=wb.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue();
		System.out.println(Password);
		return Password;	
	}
}
