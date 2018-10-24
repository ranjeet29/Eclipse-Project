package cloud_browser;




import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;


public class Chrome_Win8 {

  public static final String USERNAME = "ranjeet46";
  public static final String AUTOMATE_KEY = "cWx9P1dxxDLdbCKqeETU";
  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

  public static void main(String[] args) throws Exception {
    
	WebDriver driver = null;
	BrowserMobProxy proxy;

    DesiredCapabilities caps = new DesiredCapabilities();
    
    proxy = new BrowserMobProxyServer();
    proxy.start(7730);
    System.out.println("Started Port: "+proxy.getPort());
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
    
    caps.setCapability("browser", "chrome");
    caps.setCapability("browser_version", "52");
    caps.setCapability("os", "Windows");
    caps.setCapability("os_version", "8");
    caps.setCapability("browserstack.debug", "true");
    caps.setCapability("acceptSslCerts", "true");
    caps.setCapability(CapabilityType.PROXY, seleniumProxy);
    
    
    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT );
 
    proxy.newHar("gogle.com");   
    driver.get("http://www.google.com");
    WebElement element = driver.findElement(By.name("q"));
    Dimension dimensions = driver.manage().window().getSize();
    Double screenHeightStart = dimensions.getHeight() * 0.5;
    int scrollStart = screenHeightStart.intValue();
    System.out.println("s="+scrollStart);
    Double screenHeightEnd = dimensions.getHeight() * 0.2;
    int scrollEnd = screenHeightEnd.intValue();
    
    for (int i = 0; i < dimensions.getHeight(); i++) {
    	driver.findElement(By.name("YourText")).hashCode();
    	if ( driver.findElement(By.name("YourText")).hashCode() > 0 ) 
    	 break;
    	}
    	driver.findElement(By.name("YourText")).click();
    element.sendKeys("BrowserStack");
    element.submit();

    System.out.println(driver.getTitle());
    Har har = proxy.getHar();

	// Write HAR Data in a File
	File harFile = new File("googleHomePage.har");
	try {
		har.writeTo(harFile);
	} catch (IOException ex) {
		 System.out.println (ex.toString());
	     System.out.println("Could not find file ");
	}
	
	if (driver != null) {
		proxy.stop();
		driver.quit();
	}

  }
}
