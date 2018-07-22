package com.javaforTesters.KeywordDriven;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class Scratchpad {
	WebDriver driver;

	@Test(enabled = false)
	public void test() throws IOException {
		driver.get(" http://the-internet.herokuapp.com");
		driver.findElement(By.partialLinkText("Checkboxes")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(src, new File("test.jpg"));
	}

	@Test(enabled = false)
	public void selectdropdown() {
		driver.get(" http://the-internet.herokuapp.com");
		driver.findElement(By.partialLinkText("Dropdown")).click();
		String name = "dropdown";
		WebElement dropdown = driver.findElement(By.id(name));

		Select select = new Select(dropdown);
		select.selectByIndex(1);
		for (WebElement s : select.getAllSelectedOptions()) {
			String text = s.getText();
			System.out.println(text);
		}
	}

	@Test
	public void scrolltoElement() {
		driver.get(" http://the-internet.herokuapp.com");
		WebElement ele = driver.findElement(By.partialLinkText("Typos"));
		((Locatable) ele).getCoordinates().inViewPort();
	}

	@BeforeMethod
	public void setup() throws IOException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		System.out.println("The version of the browser  " + options.getBrowserName());
		Files.touch(new File("test.txt"));
		driver = new ChromeDriver(options);

	}

	@AfterMethod
	public void teardown() {
		// driver.quit();
	}
}
