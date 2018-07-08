package com.javaforTesters.KeywordDriven;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class AppTest extends Base{
	AppTest apptest = new AppTest();
	WebDriver driver;
	
	@Test
	public void shouldAnswerWithTrue() {
		driver = apptest.initialize();
		driver.get("https://www.seleniumhq.org/download/");
	}
}
