package com.javaforTesters.KeywordDriven;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Init {
	private ThreadLocal<WebDriver> driver;
	Properties prop;

	public Init() {
		System.out.println("base class contructor");
		prop = new Properties();
		try {
			prop.load(new FileReader(System.getProperty("user.dir") + "\\config\\app.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	@BeforeMethod
	public void setup() throws FileNotFoundException, IOException {
		System.out.println("Before Method");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Resources\\chromedriver.exe");
		driver = ThreadLocal.withInitial(() -> new ChromeDriver());
	}

	@AfterMethod
	public void closedriverinstace() {
		System.out.println("Close method and driver object" + driver);
		// apptest.close();
		driver.get().quit();
		driver.remove();
	}

}
