package com.autopractice.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingHomePage extends BasePage {
	
	private static Logger logger = Logger.getLogger(LandingHomePage.class);
	public LandingHomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@class='login']")
	public WebElement lnk_signIn;
	
	public void nav_LoginPage() {
		lnk_signIn.click();
		logger.info("Clicked on the SignIn Button");	
	}

}
