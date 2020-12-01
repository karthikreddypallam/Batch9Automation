package com.autopractice.tests;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.autopractice.pages.BasePage;
import com.autopractice.pages.HomePage;
import com.autopractice.pages.LandingHomePage;
import com.autopractice.pages.LoginPage;
import com.autopractice.pages.RegistrationPage;
import com.aventstack.extentreports.Status;

public class LoginTest extends BaseTest {
	
	private static Logger logger = Logger.getLogger(LoginTest.class);
	
	@BeforeMethod
	public void init() {
		test = reports.createTest(new Object(){}.getClass().getEnclosingMethod().getName());
		basePage = new BasePage(driver);
		lhp = new LandingHomePage(driver);
		loginPage = new LoginPage(driver);
		regPage = new RegistrationPage(driver);
		homePage = new HomePage(driver);
		driver.navigate().to(config.getURL());
		logger.info("Launched url: "+config.getURL());
		test.log(Status.INFO, "Launched url: "+config.getURL());
		lhp.nav_LoginPage();
		logger.info("Navigated to the Login Page");
		test.log(Status.INFO, "Navigated to the Login Page");
	}
	
	@Test(description="Validate Login Page with Valid Credentials",priority=0)
	public void testLoginWithValidCredentials() {
	//	lhp.nav_LoginPage();
		loginPage.login();
		Assert.assertEquals(getTitle(), HomePage.TITLE);
		Assert.assertTrue(driver.findElement(By.cssSelector("a[title='Orders']")).isDisplayed());
		logger.info("Navigated to the HomePage Page");
		test.log(Status.PASS, "Navigated to the HomePage Page");
		homePage.logout();
		logger.info("Logged out succesfully...Navigated to the Login Page");
		test.log(Status.PASS, "Logged out succesfully...Navigated to the Login Page");


	}
	
	@Test(description="Validate Login Page with Invalid Credentials",priority=1,enabled=true)
	public void testLoginPageWithInvalidCredentials() {
	//	lhp.nav_LoginPage();
		loginPage.invalidlogin();
		Assert.assertTrue(driver.findElement(By.cssSelector("a[title='Orders']")).isDisplayed());
		
	}
	
	//, retryAnalyzer = IRetry.class
	@Test(description="Validate Login Page with Empty Credentials",priority=2,enabled=true)
	public void testLoginPageWithEmptyCredentials() {
	//	lhp.nav_LoginPage();
		loginPage.loginEmpty();
		Assert.assertTrue(driver.findElement(By.cssSelector("a[title='Orders']")).isDisplayed());
	}
	
	@Test(enabled=false)
	public void testNavigateToRegisterPage() {
		loginPage.navToRegisterPage();
		Assert.assertEquals(RegistrationPage.HEADER, getText(regPage.lbl_Header));
	}
	
	


}
