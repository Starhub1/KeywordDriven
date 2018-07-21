package com.javaforTesters.KeywordDriven;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import utils.DriverFactory;

public class TestSuite {
	long stTime;
	long endTime;
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	@BeforeTest
	public void beforeclass(ITestContext context) {
		stTime = System.nanoTime();
		context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(2);
	}

	@AfterTest
	public void afterTest() {
		endTime = System.nanoTime();
		System.out
				.println("The time taken to run all the test is " + TimeUnit.NANOSECONDS.toSeconds((endTime - stTime)));
	}

	@BeforeMethod
	public void setup() {
		DriverFactory df = new DriverFactory();
		driver.set(df.getdriver());
		System.out.println("driver hascode" + driver.get().hashCode());
	}

	@AfterMethod
	public void tearDown() {
		if (null != driver.get()) {
			driver.get().quit();
			driver.remove();
		}
	}

	@DataProvider(name = "testcases", parallel = true)
	public Iterator<String> testCasePaths() {
		List<String> files = Util.getTestcasePath();
		return files.iterator();
	}
}
