package com.autopractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	static String TITLE = "Login - My Store";

	public RegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public static String HEADER = "AUTHENTICATION";
	
	@FindBy(xpath = "//h1")
	public WebElement lbl_Header;

	@FindBy(id = "customer_firstname")
	public WebElement txtFld_FirstName;
	
	@FindBy(id = "days")
	public WebElement ddDay;
	
	public void enterDetails() {
		enterText(txtFld_FirstName, "Karthik");
		selectByValue(ddDay, "5");
	}
	

}
