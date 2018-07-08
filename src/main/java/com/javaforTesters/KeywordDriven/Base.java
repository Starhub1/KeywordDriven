package com.javaforTesters.KeywordDriven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base 
{
    WebDriver driver;
    public WebDriver initialize() {
    	System.setProperty("webdriver.chrome.driver", "\\Resources.chromedriver.exe");
    	driver = new ChromeDriver();
    	return driver;
    }
}
