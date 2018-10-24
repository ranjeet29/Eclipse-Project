package seleniumIEWithHar;

import java.io.File;
import java.io.IOException;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DemoJCP {

	

	public static void main(String[] args) throws InterruptedException, IOException {
	
		System.setProperty("webdriver.gecko.driver","/home/netstorm/Desktop/NewJar/geckodriver");
		BrowserMobProxy bmp = new BrowserMobProxyServer();
		bmp.setTrustAllServers(true);
		bmp.start(7996);
		
		
		
		Proxy prx = ClientUtil.createSeleniumProxy(bmp);
        prx.setHttpProxy("172.24.2.188:7996");
        prx.setSslProxy("172.24.2.188:7996");
        
  

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities = DesiredCapabilities.firefox();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("46");
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setCapability("marionette", false);
        capabilities.setCapability(CapabilityType.PROXY, prx);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        bmp.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT , CaptureType.REQUEST_HEADERS , CaptureType.RESPONSE_HEADERS);
        
	
		WebDriver driver = new FirefoxDriver(capabilities);
		driver.manage().window().maximize();
		bmp.newHar("jcpenny.com");
	    driver.get("https://www.jcpenney.com/");
	    Thread.sleep(10000);
	 
	    try{
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("document.getElementById('closeButton').click();");
	    	
	    }catch(Exception e ){
	    	e.printStackTrace();
	    }
	    Actions action = new Actions(driver);
	    action.moveToElement(driver.findElement(By.xpath("//*[@id='flyout_opener']"))).build().perform();
	    
	    new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='flyout_opener']/div/div[1]/ul/li[1]/ul/li[10]/a")));
	    action.moveToElement(driver.findElement(By.xpath("//*[@id='flyout_opener']/div/div[1]/ul/li[1]/ul/li[10]/a"))).click().build().perform();
	    
	    Har har = bmp.getHar();
	    System.out.println("Har " +har.getLog());
	    
	    long onLoadTime = 0L;
        long onContentLoadTime = 0L;

	    PerformanceTiming pt = new PerformanceTiming((JavascriptExecutor)driver, har);
	    onLoadTime = pt.getOnLoadTime();
        onContentLoadTime = pt.getOnContentLoadTime();
        
        System.out.println("onLoadTime "+onLoadTime);
        System.out.println("onContentLoadTime "+onContentLoadTime);

	   File fl = new File("har.har");
	   har.writeTo(fl);

	   
	   bmp.stop();
		
         driver.close();		
	}

	}

