package parallelTest;

import static org.testng.Assert.assertEquals;
import junit.framework.Assert;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Cmon_nsu_server_admin.Demo;



public class ParallelTest {
	
	 
	  @Parameters({"browsertype"})
	  @Test(description="this test for chrome" , invocationCount=50)
	  public void chromeTest(String name) throws InterruptedException{
		  
		  System.setProperty("webdriver.ie.driver", "/home/netstorm/Desktop/chromedriver");
	 
		  WebDriver driver = new ChromeDriver();
		  assertEquals("chrome", name);
		  driver.get("https://automationranjeet.blogspot.in/");
		  System.out.println("title form : "+driver.getTitle());
		  
		  if (driver != null) {
			  driver.quit();
			
		}
	  } 
	 
	  @Test(description="this test for chrome" , invocationCount=50)
	  public void chromeTest1(String name) throws InterruptedException{
		  
		  System.setProperty("webdriver.ie.driver", "/home/netstorm/Desktop/chromedriver");
	 
		  WebDriver driver = new ChromeDriver();
		  assertEquals("chrome", name);
		  driver.get("https://www.automationranjeet.blogspot.in/p/use-excel-file-data-in-selenium-using.html");
		  System.out.println("title form : "+driver.getTitle());
		  
		  if (driver != null) {
			  driver.quit();
			
		}
	  } 
	  @Test(description = "this test for firefox " , invocationCount=50)
	  public void firefoxTest() throws InterruptedException{

		  WebDriver driver = new FirefoxDriver();	  
		  driver.get("https://www.automationranjeet.blogspot.in/p/use-excel-file-data-in-selenium-using.html");
		  System.out.println("title : "+driver.getTitle());

		  
		  if (driver != null) {
			driver.quit();
		}
	  } 
	  

	  @Test(description = "this test for firefox " , invocationCount=50)
	  public void firefoxTest1() throws InterruptedException{

		  WebDriver driver = new FirefoxDriver();	  
		  driver.get("https://automationranjeet.blogspot.com");

		  
		  if (driver != null) {
			driver.quit();
		}
	  } 
	 
	}
	  


