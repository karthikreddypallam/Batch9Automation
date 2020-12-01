package com.autopractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	public final static String TITLE = "My account - My Store";
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "a.logout")
	private WebElement lnk_signout;
	
	public void logout() {
		click(lnk_signout);
	}
	
	// put weblements needed
	
	//women
	// t-shirts

}
