package com.autopractice.tests;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.autopractice.pages.BasePage;
import com.autopractice.pages.HomePage;
import com.autopractice.pages.LandingHomePage;
import com.autopractice.pages.LoginPage;
import com.autopractice.pages.RegistrationPage;

public class RegistrationTest extends BaseTest {
	
	@BeforeMethod
	public void init() {
		basePage = new BasePage(driver);
		lhp = new LandingHomePage(driver);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		regPage = new RegistrationPage(driver);
		lhp.nav_LoginPage();
		loginPage.navToRegisterPage();
	}
	
	@Test(description="Validate Registration of User",priority=0)
	public void testRegistration() {
		regPage.enterDetails();
		sleep();
		
	}
	

}
