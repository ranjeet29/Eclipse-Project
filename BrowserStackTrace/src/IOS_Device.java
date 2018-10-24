




import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.URL;
import java.util.logging.Level;

public class IOS_Device {
	
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

    DesiredCapabilities caps = new DesiredCapabilities();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    
   /* caps.setCapability("browserName", "iPhone");
    caps.setCapability("device", "iPhone 7");
    caps.setCapability("realMobile", "true");
    caps.setCapability("os_version", "10.3");*/
  /*  caps.setCapability("browserName", "iPhone");
    caps.setCapability("device", "iPhone SE");
    caps.setCapability("realMobile", "true");
    caps.setCapability("os_version", "11.2");  */
    
    
  /*  caps.setCapability("browser", "Safari");
    caps.setCapability("browser_version", "11.0");
    caps.setCapability("os", "OS X");
    caps.setCapability("os_version", "High Sierra");
    caps.setCapability("resolution", "1024x768");  */
    
    caps.setCapability("browser", "Firefox");
    caps.setCapability("browser_version", "56.0");
    caps.setCapability("os", "Windows");
    caps.setCapability("os_version", "10");
    caps.setCapability("resolution", "1024x768");
    caps.setCapability("browserstack.debug", true); 
    caps.setCapability("browserstack.networkLogs", true);
    caps.setCapability("enablePerformanceLogging",true);
    caps.setCapability("loggingPrefs.performance", "ALL");

    caps.setCapability("pageLoadStrategy", "none");
  
	 
    
    driver = new RemoteWebDriver(new URL(URL), caps);
    driver.get("http://www.google.com");
    waitForPageLoad(60000);
 
    
    System.out.println(driver.getTitle());
    
    System.out.println("type"+driver.manage().logs().getAvailableLogTypes());
    try{
    System.out.println(driver);
    System.out.println("Performance  : "+ driver.manage().logs().get(LogType.PERFORMANCE).getAll());
    } catch(Exception e ){
    	driver.quit();
    	e.printStackTrace();}
    driver.quit();

  }
}

