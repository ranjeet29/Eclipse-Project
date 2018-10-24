
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
	 

public class AndroidPhone {
	 
	  public static final String USERNAME = "ranjeet_a29";
	  public static final String ACCESS_KEY = "6c8a6ab0-1b16-4602-b41f-106f82044296";
	  public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	 
	  public static void main(String[] args) throws Exception {
	    AppiumDriver driver;
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("platformName", "Android");
	    capabilities.setCapability("deviceName", "Samsung Galaxy S4 Emulator");
	    capabilities.setCapability("platformVersion", "4.4");
	    capabilities.setCapability("app", "http://saucelabs.com/example_files/ContactManager.apk");
	    capabilities.setCapability("browserName", "");
	    capabilities.setCapability("deviceOrientation", "portrait");
	    capabilities.setCapability("appiumVersion", "1.5.3");
	    
	 
	    driver = new AndroidDriver(new URL(URL), capabilities);
	 
	    /**
	     * Test Actions here...
	     */
	    System.out.println("App invoked");
	 
	    driver.quit();
	    System.out.println("Driver closed");
	  }
	}
