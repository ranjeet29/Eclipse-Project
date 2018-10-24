package parallelTest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class GeoTest {
	 @Test(description="this test for chrome" , invocationCount=50)
	  public void chromeTest() throws InterruptedException{
		  
		  System.setProperty("webdriver.chrome.driver", "/home/netstorm/Desktop/chromedriver");
	 
		  WebDriver driver = new ChromeDriver();
		
		  driver.get("https://automationranjeet.blogspot.com/p/blog-page.html");
		  
		  System.out.println("title form : "+driver.getTitle());
		  
		  if (driver != null) {
			  driver.quit();
			
		}
	  } 
	 
	  @Test(description="this test for chrome" , invocationCount=50)
	  public void chromeTest1() throws InterruptedException{
		  
		  System.setProperty("webdriver.chrome.driver", "/home/netstorm/Desktop/chromedriver");
	 
		  WebDriver driver = new ChromeDriver();

		  driver.get("https://www.automationranjeet.blogspot.in/p/use-excel-file-data-in-selenium-using.html");
		  System.out.println("title form : "+driver.getTitle());
		  
		  if (driver != null) {
			  driver.quit();
			
		}
	  } 
	  @Test(description = "this test for firefox " , invocationCount=50)
	  public void firefoxTest() throws InterruptedException{

		  WebDriver driver = new FirefoxDriver();	  
		  driver.get("https://automationranjeet.blogspot.com/p/blog-page.html");
		  System.out.println("title : "+driver.getTitle());

		  
		  if (driver != null) {
			driver.quit();
		}
	  } 
	  

	  @Test(description = "this test for firefox " , invocationCount=50)
	  public void firefoxTest1() throws InterruptedException{

		  WebDriver driver = new FirefoxDriver();	  
		  driver.get("https://automationranjeet.blogspot.in/");

		  
		  if (driver != null) {
			driver.quit();
		}
	  } 	
}
