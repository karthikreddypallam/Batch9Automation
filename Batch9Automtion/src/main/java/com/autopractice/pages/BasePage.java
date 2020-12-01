package com.autopractice.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver = null;
	WebDriverWait wait = null;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterText(WebElement ele, String text) {
		ele.sendKeys(text);
	}
	
	public void click(WebElement ele) {
		if(isElementVisibile(ele))
			ele.click();
		else
			System.out.println("Element is not present ");		
	}
	
	public void mousehover(WebElement ele) {
		if(isElementVisibile(ele)) {
			Actions action = new Actions(driver);
			action.moveToElement(ele).build().perform();	
		}else {
			System.out.println("Element is not present ");		
		}
	}
	
	public void mousehoverAndclick(WebElement ele1,WebElement ele2) {
		Actions action = new Actions(driver);
		action.moveToElement(ele1).click(ele2).build().perform();	
	}
	
	public boolean isElementVisibile(WebElement ele) {
		boolean isDisplayed = false;
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
	
	public boolean isElementVisibile(WebElement ele,int timeInSec) {
		boolean isDisplayed = false;
		try {
			wait = new WebDriverWait(driver, timeInSec);
			wait.until(ExpectedConditions.visibilityOf(ele));
			isDisplayed = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return isDisplayed;	
	}
	
	public void selectByIndex(WebElement dropdown,int index) {
		Select select = new Select(dropdown);
		select.selectByIndex(index);
	}
	
	public void selectByValue(WebElement dropdown,String value) {
		Select select = new Select(dropdown);
		select.selectByValue(value);
	}
	
	public void selectByText(WebElement dropdown,String text) {
		Select select = new Select(dropdown);
		select.selectByVisibleText(text);
	}
	
	public static void sleep() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void jsClick(WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", ele);
	}
	
	// dropdown
	// windowhandles
	// frames
	// random elements 
	// get List of elements
	// is displayed

}
