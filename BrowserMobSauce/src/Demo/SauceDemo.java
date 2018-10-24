package Demo;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceDemo {
	  public static final String USERNAME = "ranjeet_a29";
	  public static final String ACCESS_KEY = "6c8a6ab0-1b16-4602-b41f-106f82044296";
	  public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	  public static BrowserMobProxy proxy;
	  public static WebDriver  driver;
	  //public static ProxyServer server;
	  
	  
	  public static void main(String[] args) throws Exception {
	    try{
	 
	   	proxy = new BrowserMobProxyServer();
	    proxy.start(8081);
        
		System.out.println("port startte");
		System.out.println(proxy.getPort());
		   
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
	    seleniumProxy.setHttpProxy("10.10.30.217:8081");
		seleniumProxy.setSslProxy("10.10.30.217:8081");
	    DesiredCapabilities caps = DesiredCapabilities.firefox();
	    caps.setCapability("platform", "Windows 8.1");
	    caps.setCapability("version", "46.0");
	    caps.setCapability(CapabilityType.PROXY, seleniumProxy);
	    System.out.println("Proxy done");
	    driver = new RemoteWebDriver(new URL(URL), caps);
	    caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT,CaptureType.RESPONSE_CONTENT , CaptureType.REQUEST_HEADERS , CaptureType.RESPONSE_HEADERS);
	 
	  
	    proxy.newHar("www.google.com");
	    driver.get("https://www.google.com");
	    WebElement element = driver.findElement(By.name("q"));
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].value='Kohls'", element);
	    System.out.println("Kohls submit on google page");
	    element.submit();
	    Har har = proxy.getHar();
	    System.out.println("title of page is: " + driver.getTitle());
	    File harFile = new File("amazon.har");
		try {
			har.writeTo(harFile);
		} catch (IOException ex) {
			System.out.println (ex.toString());
		    System.out.println("Could not find file ");
		}
		System.out.println("Going to close driver");
	    driver.close();
	    proxy.stop();
		driver.quit();
	   
		 } catch (Exception e ){
			 e.printStackTrace();
			  proxy.stop();
			  driver.quit();
				 
		 }
	  }
}
