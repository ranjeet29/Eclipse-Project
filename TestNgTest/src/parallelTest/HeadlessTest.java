package parallelTest;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.Test;


public class HeadlessTest {
	 

	  @Test(description="this test for chrome" , invocationCount=50)
	  public void chromeTest(String name) throws InterruptedException{
		  
		  File file = new File("/home/netstorm/Desktop/phantomjs");				
	      System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
	      WebDriver driver = new PhantomJSDriver();
		  driver.get("https://automationranjeet.blogspot.in/");
		  System.out.println("title form : "+driver.getTitle());
		  
		  if (driver != null) {
			  driver.quit();
			
		}
	  } 
	 
	  @Test(description="this test for chrome" , invocationCount=50)
	  public void chromeTest1(String name) throws InterruptedException{
		  
		  
	  
		  File file = new File("/home/netstorm/Desktop/phantomjs");				
	      System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
	      WebDriver driver = new PhantomJSDriver();
		  driver.get("https://www.automationranjeet.blogspot.in/p/use-excel-file-data-in-selenium-using.html");
		  System.out.println("title form : "+driver.getTitle());
		  
		  if (driver != null) {
			  driver.quit();
			
		}
	  } 
	  @Test(description = "this test for firefox " , invocationCount=50)
	  public void firefoxTest() throws InterruptedException{

		  File file = new File("/home/netstorm/Desktop/phantomjs");				
        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
        WebDriver driver = new PhantomJSDriver();	  
		  driver.get("https://www.automationranjeet.blogspot.in/p/use-excel-file-data-in-selenium-using.html");
		  System.out.println("title : "+driver.getTitle());

		  
		  if (driver != null) {
			driver.quit();
		}
	  } 
	  

	  @Test(description = "this test for firefox " , invocationCount=50)
	  public void firefoxTest1() throws InterruptedException{

		  File file = new File("/home/netstorm/Desktop/phantomjs");				
        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
        WebDriver driver = new PhantomJSDriver();	  
		  driver.get("https://automationranjeet.blogspot.in/");

		  
		  if (driver != null) {
			driver.quit();
		}
	  } 
	 
	}
	  