package com.autopractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	WebDriver driver = null;
	public ProductsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}

