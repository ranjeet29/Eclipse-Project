import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class IOSBrowser {
     
	public static WebDriver driver;
	public static String screenshot_path = "/home/netstorm/Desktop/Screenshots/safari";
	public static void main(String[] args) throws Exception {
		
		String appium = "http://appium.testdroid.com/wd/hub";
		DesiredCapabilities capas = new DesiredCapabilities();
		LoggingPreferences logPrefs = new LoggingPreferences();
	    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
	    capas.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
	    capas.setCapability("enablePerformanceLogging",true);
	    
		capas.setCapability("testdroid_username", "ranjeet.kumar@cavisson.com");
		capas.setCapability("testdroid_password", "ranjeet_a29");
		capas.setCapability("testdroid_target", "safari");
		capas.setCapability("testdroid_project", "Safari Browser");
		capas.setCapability("testdroid_testrun", "safari 1");
		capas.setCapability("testdroid_device", "iPhone 6S");
		capas.setCapability("platformName", "iOS");
		capas.setCapability("deviceName", "iPhone device");
		capas.setCapability("browserName", "safari");
		
		driver = new AndroidDriver<>(new URL(appium) , capas);
		driver.get("http://www.cavisson.com");
		System.out.println("Title::"+driver.getTitle());
		getscreenshot("google.png");

		System.out.println("snapshot done");
		driver.quit();
		
		
		

	}
	
	public static void waitForPageLoad(int timeout) throws Exception{
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

	public static void getscreenshot(String name) throws Exception 
    {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with name "screenshot.png"
            FileUtils.copyFile(scrFile, new File(screenshot_path+"/"+name));
    }
}
