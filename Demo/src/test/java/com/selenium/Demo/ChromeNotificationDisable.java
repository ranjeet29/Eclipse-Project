package com.selenium.Demo;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

 public class ChromeNotificationDisable {
	
  public WebDriver driver;
  
  @FindBy(id="username")
  public WebElement username;
  @FindBy(id="password")
  public WebElement password;
  @FindBy(id="submit")
  public WebElement login;
  
  
  ExtentReports logger = ExtentReports.get(getClass());
  
  
  @BeforeMethod
  public void perSetUp(){
	 
     
	  System.setProperty("webdriver.chrome.driver", "/home/netstorm/Downloads/chromedriver");
	  Map<String , Object> pref = new HashMap<String, Object>();
	  pref.put("profile.default_content_setting_values.notifications", 2);
	  ChromeOptions option = new ChromeOptions();
	  option.setExperimentalOption("prefs", pref);

	 logger.init("/home/netstorm/Desktop/report.html", true);
	  
	  logger.startTest("Title Validate BuildHub", "Going to validate tiltle of build Hub");
	  driver = new ChromeDriver(option);
	  driver.manage().window().maximize();
	  driver.get("http://10.10.30.16:80");
	  logger.log(LogStatus.INFO, "Build Hub Open Successfully");
	  logger.log(LogStatus.PASS, "Build Hub Open Successfully");

	  PageFactory.initElements(driver, this);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  System.out.println("URl open Successfully ");
	   
  }
 
 public void Titlecheck() {
	  
	  String title = driver.getTitle();
	  System.out.println(title);
	  AssertJUnit.assertTrue(title.contains("Build"));
	  logger.log(LogStatus.PASS, "Title is validated");
	  System.out.println("Title Validated");
  }
 
  public void Login(){
	  username.sendKeys("admin");
	  logger.log(LogStatus.INFO, "Input UserID");

	  password.sendKeys("monitor");
	  logger.log(LogStatus.INFO, "Input PassWord");

	  login.click();
	  logger.log(LogStatus.INFO, "LogIn Successfully");
	  logger.log(LogStatus.PASS, "LogIn Successfully");


	  System.out.println("Login Successfully");
  }	  
public void scrollUp(){
		  JavascriptExecutor Javascriptexecutor = (JavascriptExecutor) driver;
		  Javascriptexecutor.executeScript("scroll(0,400)");
		  System.out.println("Scroll successfully");
		  logger.endTest();
	  }
 
	 

  @Test
  public void call()
  {   
	  
	  Titlecheck();
	  Login();
	  scrollUp();
	  
  }
 
  
  @AfterMethod
  public void postSetUp(){
	  driver.close();
  }
}
