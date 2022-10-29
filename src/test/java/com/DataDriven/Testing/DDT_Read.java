package com.DataDriven.Testing;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DDT_Read {
	public static WebDriver driver;
	public static void main(String[] args) throws Throwable {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://demo.guru99.com/v4/index.php");
		FileInputStream fis=new FileInputStream("C:\\Users\\debap\\eclipse-workspace\\Automation_Program\\src\\test\\java\\com\\DataDriven\\Testing\\DDT_Read.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheet("Sheet1");
		int rowcount=sheet.getLastRowNum();
		for (int i = 1; i < rowcount; i++) {
			String username=sheet.getRow(i).getCell(0).getStringCellValue();
			String password=sheet.getRow(i).getCell(1).getStringCellValue();
			driver.findElement(By.name("uid")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.name("btnLogin")).click();
			if (isAlertpresent()==true) {
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				System.out.println("For Invalid data");
			} else {
				driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[15]/a")).click();
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				System.out.println("For Valid data");
			}
		}
		driver.quit();
	}
	public static boolean isAlertpresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
