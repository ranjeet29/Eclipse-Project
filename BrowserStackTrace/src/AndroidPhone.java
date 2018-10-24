import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

	
	
	public class AndroidPhone {
		
		  /*public static final String USERNAME = "ranjeet46";
		  public static final String AUTOMATE_KEY = "cWx9P1dxxDLdbCKqeETU";
		  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";*/
		public static final String USERNAME = "adityasinha2";
		  public static final String AUTOMATE_KEY = "qiz7gUdPY74g6qN2dQ4v";
		  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
		  public static  WebDriver driver;
		  
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
	
		  
		  public static void main(String[] args) throws Exception {
	
		//    DesiredCapabilities caps = new DesiredCapabilities();
		    
			    DesiredCapabilities caps = new DesiredCapabilities();
			 
			    LoggingPreferences logPrefs = new LoggingPreferences();
			    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			    caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			    caps.setCapability("enablePerformanceLogging",true);
			/*    caps.setCapability("browser", "Chrome");
			    caps.setCapability("browser_version", "62.0");
			    caps.setCapability("os", "Windows");
			    caps.setCapability("os_version", "10");
			    caps.setCapability("resolution", "1024x768"); */
			    caps.setCapability("browserstack.debug", true);
			    caps.setCapability("browserstack.networkLogs", true);
			    caps.setCapability("enablePerformanceLogging",true);
			    caps.setCapability("browserName", "chrome");
			    caps.setCapability("device", "Samsung Galaxy Note 8");
			    caps.setCapability("realMobile", "true");
			    caps.setCapability("os_version", "7.1");   
	
		    driver = new RemoteWebDriver(new URL(URL), caps);
		    driver.get("http://www.google.com");
		 //   System.out.println("Performance : "+driver.manage().logs().get(LogType.PERFORMANCE).getAll());
		    System.out.println("Performance  : "+ driver.manage().logs().get(LogType.PERFORMANCE).getAll());
		    
		    System.out.println("Browser hit");
		    waitForPageLoad(60000);
		    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    try {
		      FileUtils.copyFile(srcFile, new File("/home/netstorm/Desktop/Screenshot.png"));
		      System.out.println("Snapshot captured");
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    
		    driver.quit();
		  }
		  
		  
		  
		}
	
