package Native_App_Testing;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;



public class IPhone_Native_App {
	
	 public static String userName = "ranjeet58";
	  public static String accessKey = "LQ1UwKzrHhmBQo7xhwqo";

	  public static void main(String args[]) throws MalformedURLException, InterruptedException {
	    DesiredCapabilities caps = new DesiredCapabilities();

	    caps.setCapability("device", "iPhone 7 Plus");
	    caps.setCapability("os_version", "10.3");
	    caps.setCapability("app", "bs://c01b3cc063e6a2d79c76623a5398722ed8dc829c");
	    caps.setCapability("browserstack.debug", true);
	    caps.setCapability("browserstack.networkLogs", true);
	    caps.setCapability("networkProfile", "4g-lte-advanced-good");
	    caps.setCapability("browserstack.gpsLocation", "26.595248 , 85.480839");   // latitude, longitude 
	    caps.setCapability("noReset", true);
	    System.out.println("driver initialized ");

	    IOSDriver driver = new IOSDriver<>(new URL("http://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);
	    System.out.println("driver initiated ");
	   
	    
	    Thread.sleep(1000);
	    driver.quit();
	    
	    System.out.println("Test finished ");
	  }

}
