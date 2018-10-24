package chrome;


import java.io.File;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

public class AndroiChrome {

    public static void main(String[] args) throws Exception {
    	System.setProperty("webdriver.chrome.driver", "/home/netstorm/Desktop/chromedriver");
    	//ChromeOptions chromeoptions=new ChromeOptions();
    	//chromeoptions.setExperimentalOption("androidPackage","com.android.chrome");
    	BrowserMobProxy proxy;
    	proxy = new BrowserMobProxyServer();
    	proxy.setTrustAllServers(true);
    	proxy.start(7991);
    	WebDriver ad = null;
    	//DesiredCapabilities caps = DesiredCapabilities.chrome();
    	try{
    	DesiredCapabilities caps = new DesiredCapabilities();
    	caps.setCapability("platformVersion", "7.0");
    	caps.setCapability("platformName", "Android");
    	caps.setCapability("deviceName", "Moto G4 Plus");
    	caps.setCapability("browserName", "chrome");
    	caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);        

    	Set<CaptureType> captureTypes = new TreeSet<CaptureType>();
    	captureTypes.add(CaptureType.REQUEST_HEADERS);
    	captureTypes.add(CaptureType.RESPONSE_HEADERS);
    	proxy.setHarCaptureTypes(captureTypes);
    	Proxy seleniumproxy = ClientUtil.createSeleniumProxy(proxy);
    	caps.setCapability(CapabilityType.PROXY, seleniumproxy);
    	       
    	ad= new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
    	proxy.newHar("google");
    	      
    	ad.get("https://www.facebook.com");
    	System.out.println("Title is:"+ad.getTitle());
    	Har har = proxy.getHar();
    	File fp = new File("abc.txt");
    	har.writeTo(fp);
    	System.out.println("completed har writing");
    	proxy.endHar();
    	}catch(Exception e){ e.printStackTrace();}
    	proxy.stop();
    	Thread.sleep(5000);
    	ad.quit();
	}
}
