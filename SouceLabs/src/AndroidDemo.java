import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

 
public class AndroidDemo {
	public static final String USERNAME = "vishal_a29";   
	public static final String ACCESS_KEY = "08ca81c1-ccda-4b4b-b1d8-c7880ee327bd";
  public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
  public static BrowserMobProxy proxy;
  public static WebDriver  driver;
  public static void main(String[] args) throws Exception {
	
	 try {
	 
	 
	   proxy = new BrowserMobProxyServer();
	   proxy.start(7993);
		   System.out.println("port startte");
		   
		   //get the Selenium proxy object - org.openqa.selenium.Proxy;
		   Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
		   
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("testobject_api_key", "E6403B73904340D3999776CA786C3D54");
    capabilities.setCapability("testobject_device", "LG_Nexus_5X_real");
    capabilities.setCapability("testobject_appium_version", "1.6.4");
    capabilities.setCapability("testobject_cache_device", "false");
    capabilities.setCapability("testobject_app_id", "1");
    capabilities.setCapability("testobject_suite_name", "kohlstest");
    capabilities.setCapability("testobject_test_name", "kohlstest");
    capabilities.setCapability("noReset",true);
    System.out.println("Reset done");
    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
    System.out.println("Proxy done");

      capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
      proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT,CaptureType.RESPONSE_CONTENT , CaptureType.REQUEST_HEADERS , CaptureType.RESPONSE_HEADERS);
    
    // create a new HAR with the label "seleniumeasy.com"
    
    System.out.println("ProxyType done");

 
    driver = new AndroidDriver (new java.net.URL("https://us1.appium.testobject.com/wd/hub"), capabilities);
    driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
    WebDriverWait wait = new WebDriverWait(driver,30);
    proxy.newHar("Amazon");
   // WebDriverWait wait = new WebDriverWait(driver,100);    
    proxy.newHar("Michele");     
    WebElement lang = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='UNITED STATES']")));     lang.click();      System.out.println("lang accept");     
    Thread.sleep(20000);         
    WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/home")));    
    accept.click();
     
    
    System.out.println("Going to collect har");
    Har har = proxy.getHar();
    Thread.sleep(5000);

	// Write HAR Data in a File
	File harFile = new File("amazon.har");
	try {
		har.writeTo(harFile);
	} catch (IOException ex) {
		System.out.println (ex.toString());
	    System.out.println("Could not find file ");
	}
	System.out.println("Going to close driver");
   // driver.close();
	proxy.stop();
	driver.quit();
   
	 } catch (Exception e ){
		 e.printStackTrace();
		    proxy.stop();
		    driver.quit();
			 
	 }
  }

}