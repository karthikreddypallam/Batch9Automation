package com.autopractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	static String TITLE = "Login - My Store";

	@FindBy(css = "input#email")
	public WebElement txtFld_Email;

	@FindBy(css = "input#passwd")
	public WebElement txtFld_Password;

	@FindBy(css = "button#SubmitLogin")
	public WebElement btn_signIn;
	

	@FindBy(id = "email_create")
	public WebElement txtFld_Create_Email;
	
	@FindBy(id = "SubmitCreate")
	public WebElement btnCreateAccount;
	
	
	public void navToRegisterPage()
	{
		enterText(txtFld_Create_Email, "next777@gmail.com");
		jsClick(btnCreateAccount);
		sleep();
	}
	
	public void login() {
		enterText(txtFld_Email, "gadupoodi.deepthi@gmail.com");
		enterText(txtFld_Password, "#prettydeepthi");
		click(btn_signIn);
	}
	
	public void invalidlogin() {
		enterText(txtFld_Email, "deepthi@gmail.com");
		enterText(txtFld_Password, "#prettydeepthi");
		click(btn_signIn);
	}
	
	public void loginEmpty() {
		enterText(txtFld_Email, "");
		enterText(txtFld_Password, "");
		click(btn_signIn);
	}
	
	

}
