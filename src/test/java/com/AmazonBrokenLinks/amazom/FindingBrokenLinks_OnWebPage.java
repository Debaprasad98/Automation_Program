package com.AmazonBrokenLinks.amazom;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindingBrokenLinks_OnWebPage {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://amazon.com");
		driver.manage().window().maximize();

		List<WebElement> links=driver.findElements(By.tagName("a"));
		System.out.println("No of links are "+links.size());
		for(WebElement link:links) {
			String url=link.getAttribute("href");
			brokenlinks(url);
		}
		driver.quit();
	}
	public static void brokenlinks(String linkurl) {

		try {
			URL url=new URL(linkurl);
			HttpURLConnection httpurl=(HttpURLConnection) url.openConnection();
			httpurl.setConnectTimeout(5000);
			httpurl.connect();

			if (httpurl.getResponseCode()>=400) {
				System.out.println(url +" ===> "+httpurl.getResponseMessage()+" is a broken link");
			}else {	
				System.out.println(url +" ===> "+httpurl.getResponseMessage());
			}



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
