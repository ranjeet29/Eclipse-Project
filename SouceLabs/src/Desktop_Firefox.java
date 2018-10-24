import java.io.File;
import java.io.IOException;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.proxy.http.BrowserMobHttpRequest;
import net.lightbody.bmp.proxy.http.RequestInterceptor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Desktop_Firefox {

	public static void main(String arg[])throws Exception{
		WebDriver driver ;
	
		
		BrowserMobProxy proxy = new BrowserMobProxyServer();
		proxy.start(8081);
		
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
	     seleniumProxy.setHttpProxy("10.10.30.217:8081");
	     seleniumProxy.setSslProxy("10.10.30.217:8081");
	    DesiredCapabilities caps = DesiredCapabilities.firefox();
	    caps.setCapability(CapabilityType.PROXY, seleniumProxy);
	    System.out.println("Proxy done");
	    
	   
	    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT,CaptureType.RESPONSE_CONTENT , CaptureType.REQUEST_HEADERS , CaptureType.RESPONSE_HEADERS);
		driver = new FirefoxDriver(caps);
		driver.manage().window().maximize();
		proxy.newHar("www.google.com");
		driver.get("http://www.google.com");
		proxy.removeHeader("User-Agent");
		proxy.addHeader("User-Agent", "Bananabot/1.0");
		System.out.println("Header is Added");
		  WebElement element = driver.findElement(By.name("q"));
		    JavascriptExecutor executor = (JavascriptExecutor) driver;
		    executor.executeScript("arguments[0].value='Kohls'", element);
		    System.out.println("Kohls submit on google page");
		    element.submit();
		Thread.sleep(20000);
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
			proxy.stop();
		 
			driver.quit();
	
	}
}
