package Native_App_Testing;

import java.net.URL;
import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
 
public class MacysApp {
  
  public static String userName = "ranjeet58";
  public static String accessKey = "LQ1UwKzrHhmBQo7xhwqo";

  public static void main(String args[]) throws MalformedURLException, InterruptedException {
    DesiredCapabilities caps = new DesiredCapabilities();

    caps.setCapability("device", "Google Nexus 6");
    caps.setCapability("os_version", "6.0");
    caps.setCapability("app", "bs://12bb62cae1e40169d88d1f28ea54ae7a218edacc");
    caps.setCapability("browserstack.debug", true);
    caps.setCapability("browserstack.networkLogs", true);
    caps.setCapability("networkProfile", "4g-lte-advanced-good");
    caps.setCapability("browserstack.gpsLocation", "26.595248 , 85.480839");   // latitude, longitude 
    caps.setCapability("noReset", true);
    System.out.println("driver initialized ");

    AndroidDriver<WebElement> driver = new AndroidDriver<>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);
    System.out.println("driver initiated ");
   
    
    Thread.sleep(1000);
    driver.quit();
    
    System.out.println("Test finished ");
  }
}