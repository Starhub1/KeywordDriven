package com.javaforTesters.KeywordDriven;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class AppTest extends Init {
	WebDriver driver;

	public AppTest() {
		System.out.println("The constructor method has been called for AppTest" + Thread.currentThread().getName());

	}

	@Test
	public void shouldAnswerWithTrue() {
		driver = getDriver();
		System.out.println("shouldAnswerWithTrue" + Thread.currentThread().getName());
		driver.get(prop.getProperty("url"));
	}

	@Test
	public void test1() {
		driver = getDriver();
		System.out.println("test1" + Thread.currentThread().getName());
		driver.get(prop.getProperty("url"));
		AssertJUnit.assertTrue(true);
	}

	@Test
	public void test2() {
		driver = getDriver();
		System.out.println("test2" + Thread.currentThread().getName());
		driver.get(prop.getProperty("url"));
		AssertJUnit.assertTrue(true);
	}

}
