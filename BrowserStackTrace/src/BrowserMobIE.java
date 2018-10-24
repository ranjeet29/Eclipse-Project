import java.io.BufferedReader;

import java.io.FileOutputStream;
import java.net.URL;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;



public class BrowserMobIE {
	public static final String USERNAME = "saishubham1";
	public static final String AUTOMATE_KEY = "NsURxkBQRy59HuZHeeZP";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
  
public static void main(String[] args) throws Exception {
     
	DesiredCapabilities caps = new DesiredCapabilities();
    BrowserMobProxyServer proxy = new BrowserMobProxyServer();
    proxy.start(7992); 
   
 /*   System.setProperty("java.net.useSystemProxies", "true");
    System.setProperty("http.proxyHost", "172.24.2.188");
    System.setProperty("http.proxyPort", "7992");
    System.setProperty("https.proxyHost", "172.24.2.188");
    System.setProperty("https.proxyPort", "7992"); */
       
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);   
    seleniumProxy.setHttpProxy("10.10.20.180:7992").setSslProxy("10.10.20.180:7992"); 
    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT , CaptureType.REQUEST_HEADERS , CaptureType.RESPONSE_HEADERS);
    
    caps.setCapability("os", "Windows");
    caps.setCapability("os_version", "10");
    caps.setCapability("browser", "Chrome");
    caps.setCapability("browser_version", "62.0");
    caps.setCapability(CapabilityType.PROXY, seleniumProxy);
    
  /*caps.setCapability("browser", "IE");
    caps.setCapability("browser_version", "11.0");
    caps.setCapability("os", "Windows");
    caps.setCapability("os_version", "10");
    caps.setCapability("resolution", "1024x768"); */
    caps.setCapability("browserstack.debug", true);
    caps.setCapability("browserstack.networkLogs", true);
    caps.setCapability("build", "ie");
    caps.setCapability("browserstack.local", "true");
    
    
    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
    
    proxy.newHar("https://www.4wheelparts.com");
    driver.get("https://www.4wheelparts.com/");
    SessionId session = ((RemoteWebDriver)driver).getSessionId();
    System.out.println("Session id: " + session.toString());
  
    System.out.println(driver.getTitle());
          
    Har har = proxy.getHar();
	try{
	            FileOutputStream fos=new FileOutputStream("/home/netstorm/Desktop/IEBrowser.har");
	            har.writeTo(fos);
	            BufferedReader br = new BufferedReader(new java.io.FileReader("/home/netstorm/Desktop/IEBrowser.har"));
	            String line = "";
	            while ((line = br.readLine())!= null) {
					System.out.println("line " + line);
				}
	}catch(Exception e) {
	     e.printStackTrace();
	}
    
	proxy.stop();
    driver.quit();
    
}
	
}
