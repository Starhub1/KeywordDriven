package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
	public DriverFactory() {
		System.out.println("This is a driver Factory method");
	}

	private WebDriver driver;
	String curdir = System.getProperty("user.dir");

	public WebDriver getdriver() {
		this.initialize();
		return driver;
	}

	public String getbrowserType() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(curdir + "\\config\\app.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String browser = prop.getProperty("browser");
		return browser;
	}

	public void initialize() {
		String browser = getbrowserType();

		switch (browser.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", curdir + "\\Resources\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().pageLoadTimeout(30l, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10l, TimeUnit.SECONDS);
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", curdir + "\\Resources\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().pageLoadTimeout(30l, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10l, TimeUnit.SECONDS);
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", curdir + "\\Resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().timeouts().pageLoadTimeout(30l, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10l, TimeUnit.SECONDS);
			break;

		}
	}
}
