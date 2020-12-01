package com.autopractice.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Step;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class DpTest {
	
	private static Logger logger = Logger.getLogger(DpTest.class);

	WebDriver driver = null;
	SoftAssert sa= null;
	
	WebElement email = null;
	WebElement password = null;
	WebElement signIn = null;
	WebElement orders = null;

	@BeforeTest(enabled=true)
	public void launchBrowser() {
		logger.debug("Launching Chrome Driver");
	//	PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/log4j.properties");
		System.setProperty("webdriver.chrome.driver","/Users/karthikreddy/Desktop/Softwares/libs/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger.debug("Launched Chrome Browser");
	}

	@BeforeMethod
	public void enterURL() {
	
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		logger.info("Launched url ==> http://automationpractice.com/index.php?controller=authentication&back=my-account");

		email = driver.findElement(By.cssSelector("input#email"));
		password = driver.findElement(By.cssSelector("input#passwd"));
		signIn = driver.findElement(By.cssSelector("button#SubmitLogin"));
		sa = new SoftAssert();
	}

	@Test(dataProvider = "UserData",enabled=true)
	public void testLoginWithValidCredentials(String username, String pass) {
		email.sendKeys(username);
		logger.info("Entered username: "+username);
		password.sendKeys(pass);
		logger.info("Entered password: "+pass);
		signIn.click();
		logger.info("Clicked on SignIn Button");
		sa.assertEquals(driver.getTitle(), "My Account - My Store");
	//	sa.assertTrue(driver.findElement(By.cssSelector("a[title='Orders']")).isDisplayed());
		System.out.println("Test Execution Completed");
		sa.assertAll();
	}

	@DataProvider(name="UserData")
	public Object[][] getUserData(){
		return getExcelData(System.getProperty("user.dir")+"/src/main/resources/userdata.xls");
	}
	
	public String[][] getExcelData(String filePath){
		String[][] userdata= null;
		
		try {
			FileInputStream fis = new FileInputStream(new File(filePath));
			Workbook wb = Workbook.getWorkbook(fis);
			Sheet sh = wb.getSheet(0);
			
			int rows = sh.getRows();
			int cols = sh.getColumns();
			
			System.out.println("Rows: "+rows);
			System.out.println("Cols: "+cols);
			userdata = new String[rows-1][cols];
			
			for(int i=1;i<rows;i++) {
				for(int j=0;j<cols;j++) {
					userdata[i-1][j] = 	sh.getCell(j, i).getContents();
				}
			}
			
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return userdata;	
		
	}

	@AfterTest(enabled=true)
	public void closeBrowser() {
		driver.close();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {

		if(result.getStatus() == ITestResult.SUCCESS) {
			System.out.println(result.getName()+" Result is Pass ");
			logger.info(result.getName()+" Result is Passed ");	
		}else {
			System.out.println(result.getName()+" Result is Failed ");
			logger.error(result.getName()+" Result is Failed ");
	
		}
	}

}
