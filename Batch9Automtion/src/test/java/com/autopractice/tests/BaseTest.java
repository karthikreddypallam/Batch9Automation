package com.autopractice.tests;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.autopractice.constants.IConstants;
import com.autopractice.pages.BasePage;
import com.autopractice.pages.HomePage;
import com.autopractice.pages.LandingHomePage;
import com.autopractice.pages.LoginPage;
import com.autopractice.pages.RegistrationPage;
import com.autopractice.utils.ConfigReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class BaseTest {
	
	ExtentHtmlReporter htmlReporter = null;
	ExtentReports reports = null;
	ExtentTest test = null;
	private static Logger logger = Logger.getLogger(BaseTest.class);
	
	WebDriver driver = null;
	LandingHomePage lhp = null;
	LoginPage loginPage = null;
	BasePage basePage = null;
	HomePage homePage = null;
	RegistrationPage regPage = null;
	
	ConfigReader config = null;
	
	@BeforeSuite
	public void beforeSuite() {
		htmlReporter = new ExtentHtmlReporter("extentReport.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		config = new ConfigReader();
		config.readConfig();
	}
	
	@BeforeTest
	public void launchBrowser(ITestContext context) {
		test = reports.createTest(context.getName());
		logger.info("Chrome Driver Opening");
	//	System.setProperty("webdriver.chrome.driver",IConstants.chromedriverPath);
		 DesiredCapabilities capability = DesiredCapabilities.chrome();
         capability.setBrowserName("chrome");
         capability.setPlatform(Platform.ANY);
         try {
			driver = new RemoteWebDriver(new URL("http://192.168.0.89:4444/wd/hub"), capability);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(config.getURL());
	//	driver.get(config.getStoreURL());
		logger.info("Chrome Browser Opened");

	}
	
	@AfterTest(alwaysRun=true)
	public void closeBrowser() {
		driver.quit();
		logger.info("Chrome Browser Closed");

	}
	
	public String getTitle() {
		String title = driver.getTitle();
		logger.info("Getting Current Page Title: "+title);
		return title;
	}
	
	public String getURL() {
		return driver.getCurrentUrl();
	}
	
	public String getText(WebElement ele) {
		return ele.getText();
	}
	
	public boolean isElementVisibile(WebElement ele) {
		boolean isDisplayed = false;
		WebDriverWait wait = null;
		try {
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));
			isDisplayed = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isDisplayed;	
	}
	
	public static void sleep() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
