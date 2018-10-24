import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import io.appium.java_client.android.AndroidDriver;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;



public class Android {
	
	public void setUp() throws Exception {
		
		WebDriver driver;
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		    
		capabilities.setCapability("testobject_api_key", "E368BA5E155A4935A7044D8C98E118D8");
		
        capabilities.setCapability("AndroidVersion", "8.0.0"); // Optional
        capabilities.setCapability("deviceName", "LG Nexus 5X Free"); // Optional
        capabilities.setCapability("platformName", "Android"); // Optional
        capabilities.setCapability("browserName", "chrome");  
        capabilities.setCapability("enablePerformanceLogging",true);
        capabilities.setCapability("loggingPrefs.performance", "ALL"); 

        capabilities.setCapability("pageLoadStrategy", "none");
		
        driver = new AndroidDriver<>(new URL("https://us1.appium.testobject.com/wd/hub"),capabilities);
        System.out.println("driver initiated");
	    driver.get("http://www.google.com");
	    System.out.println(driver.getTitle());
	    
	    System.out.println("Performance  : "+ driver.manage().logs().get(LogType.PERFORMANCE).getAll());
	    
	    System.out.println("Browser hit");
	    waitForPageLoad(60000, driver);
	    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    try {
	      FileUtils.copyFile(srcFile, new File("/home/netstorm/Desktop/Screenshot.png"));
	      System.out.println("Snapshot captured");
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	} 
	
	  public static void waitForPageLoad(int timeout , WebDriver driver) throws Exception{
	      JavascriptExecutor js = (JavascriptExecutor) driver;
		  int timed = 0;
	      if(timeout == 0) timeout = 60000;
	      boolean loaded = false;
	      //check for readyState if complete then return. 
	      //FIXME: remove this intial delay
	      Thread.sleep(5000);
	      while(timed < timeout)
	      {
	        String state = "complete";

	        if(loaded != true)
	          state =  (String) js.executeScript("return document.readyState;");
	        if(state != null && state.equals("complete"))
	        {
	          if(loaded == false)
	          {
	            System.out.println("Page State Changed to complete");
	            loaded = true;
	          }
	          //check for timing.
	          long loadTime = (long) js.executeScript("return (window.performance.timing.loadEventEnd)");
	          if(loadTime != 0)
	          {
	            String url = (String) js.executeScript("return window.location.href;");
	            System.out.println("++++++++++++onLoad Time - " +  loadTime + ", URL - " +  url);
	            break;
	          }
	        }
	        timed += 500;
	        Thread.sleep(500);
	      }
	   }
   public static void main(String[] args) throws Exception {
        Android ad = new Android();
        ad.setUp();
} 
}
