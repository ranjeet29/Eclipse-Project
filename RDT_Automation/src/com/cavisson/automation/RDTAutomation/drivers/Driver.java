package com.cavisson.automation.RDTAutomation.drivers;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.apache.log4j.Logger;

import com.cavisson.automation.RDTAutomation.generics.loggerLoad;

public class Driver {
	
   public static WebDriver driver ;  
   static Logger log = loggerLoad.config("Driver");
   
   
   public static WebDriver registerDriver(String name){
	   if (name.equalsIgnoreCase("chrome")){
		   driver = chromeDriver();
	   }else if (name.equalsIgnoreCase("firefox")){
		   driver = firefoxDriver();
	   }else{
		   driver = headless();
	   }
	   
	   return driver ;
   }
   
	public static WebDriver chromeDriver() {
		try {
			System.setProperty("webdriver.chrome.driver", "/home/netstorm/Desktop/chromedriver");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			log.info("chrome driver initialized");
		} catch (Exception e) {
			log.error("Error : " + e);
		}
		return driver;
	}
  
  public static WebDriver firefoxDriver() {
	 try {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  log.info("firefox driver initialized ");
	 }catch (Exception e) {
		log.error("Error : "+e);}
	  return driver;
    }
  
  public static WebDriver headless() {
	  try {
	  File file = new File("/home/netstorm/Desktop/phantomjs");
      System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
      driver = new PhantomJSDriver();
      log.info("headless driver initiated");
	  }catch (Exception e) {
		log.error("Error : "+e);
	}
	  return driver ; 
  }
  
  
  
}
