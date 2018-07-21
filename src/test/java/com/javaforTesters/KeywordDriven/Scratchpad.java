package com.javaforTesters.KeywordDriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Scratchpad {
	@Test
	public void test() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(" http://the-internet.herokuapp.com");
		driver.findElement(By.partialLinkText("Checkboxes")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
	}
}
