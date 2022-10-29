package com.DataDriven.Testing;

import java.io.IOException;
import java.util.concurrent.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DataProviderAnnotation {

	WebDriver driver;
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test(dataProvider = "LoginData")
	public void loginTest(String username, String password,String exp) {
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");

		WebElement txtmail=driver.findElement(By.id("Email"));
		txtmail.clear();
		txtmail.sendKeys(username);
		WebElement txtpwd=driver.findElement(By.id("Password"));
		txtpwd.clear();
		txtpwd.sendKeys(password);

		driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

		String exp_title="Dashboard / nopCommerce administration";
		String act_title=driver.getTitle();
		if(exp.equals("Valid")) {
			if(act_title.equals(exp_title)) {
				driver.findElement(By.linkText("Logout")).click();
				driver.switchTo().defaultContent();
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false);
			}
		}else if(exp.equals("Invalid")){
			if(act_title.equals(exp_title)) {
				driver.findElement(By.linkText("Logout")).click();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(true);
			}
		}


	}
	@DataProvider(name="LoginData")
	public String[][] getdata() throws IOException {
		/*String [][]logindata= {
				{"admin@yourstore.com","admin","Valid"},
				{"admin@yourstore.com","adm","Invalid"},
				{"adm@yourstore.com","admin","Invalid"},
				{"adm@yourstore.com","adm","Invalid"}
		};*/
		//Read the data from excel file
		String path="C:\\Users\\debap\\eclipse-workspace\\Automation_Program\\src\\test\\java\\com\\DataDriven\\Testing\\loginData.xlsx";
		XLUtility_DDT xlutil=new XLUtility_DDT(path);
		int rowcount=xlutil.getRowCount("Sheet1");
		int cellcount=xlutil.getCellCount("Sheet1", 1);
		String [][]logindata=new String[rowcount][cellcount];
		for (int i =1; i <=rowcount; i++) {
			for (int j = 0; j <cellcount; j++) {
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata;
	}
	@AfterClass
	public void teardown() {
		driver.close();
	}
}
