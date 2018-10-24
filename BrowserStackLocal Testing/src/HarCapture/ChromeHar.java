package HarCapture;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.browserstack.local.Local;

import org.json.*;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import net.sourceforge.htmlunit.corejs.javascript.ast.WhileLoop;

public class ChromeHar {
	
	public static final String USERNAME = "saishubham1";
	public static final String AUTOMATE_KEY = "NsURxkBQRy59HuZHeeZP";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	public static void main(String[] args) throws Exception {
		
		BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(7991);
        
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        seleniumProxy.setSslProxy("10.10.30.124:7991");
        seleniumProxy.setHttpProxy("10.10.30.124:7991");
        seleniumProxy.setFtpProxy("10.10.30.124:7991");
     /*   Local browserStackLocal = new Local();
        HashMap<String, String> browserStackLocalArgs = new HashMap<String, String>();
        browserStackLocalArgs.put("binarypath", "/home/netstorm/Desktop/BrowserStackLocal");
        browserStackLocalArgs.put("key", AUTOMATE_KEY);
        browserStackLocalArgs.put("forcelocal", "true");
        browserStackLocalArgs.put("forceproxy","true");
        browserStackLocalArgs.put("force","true");
        browserStackLocalArgs.put("v", "true");
        String host="10.10.30.124";
        String port="7991";
        browserStackLocalArgs.put("-local-proxy-host", host);
        browserStackLocalArgs.put("-local-proxy-port", port);
        
        System.out.println("HOst "+host);
        System.out.println("Port "+port);
        System.out.println(browserStackLocal);
        browserStackLocal.start(browserStackLocalArgs);*/
        System.out.println("Local browserstack started");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "62.0");
        caps.setCapability("os", "Windows");
     //   caps.setCapability(CapabilityType.PROXY, seleniumProxy);
        caps.setCapability("os_version", "10");
        caps.setCapability("browserstack.local",true);
        caps.setCapability("browserstack.networkLogs", true);
        caps.setCapability("browserstack.debug", true);

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        
        
        proxy.newHar("https://www.google.com");
        driver.get("https://www.google.com");
        
        try {
			Har har = proxy.getHar();
			har.writeTo(new  File("/home/netstorm/Desktop/Google.har"));
        	BufferedReader rd =new  BufferedReader(new FileReader("/home/netstorm/Desktop/Google.har"));
        	String line = "";
        	while ((line = rd.readLine()) != null ) {
				System.out.println(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
        proxy.stop();
        driver.quit();
        
	}

}
