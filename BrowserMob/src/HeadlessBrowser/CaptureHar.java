package HeadlessBrowser;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.proxy.CaptureType;

public class CaptureHar {
	public static void main(String[] args) throws Exception {
		BrowserMobProxy proxy = new BrowserMobProxyServer();
		proxy.start(7991);
		
		//Proxy seleniumproxy = ClientUtil.createSeleniumProxy(proxy);
		proxy.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
		proxy.enableHarCaptureTypes(CaptureType.RESPONSE_HEADERS , CaptureType.REQUEST_HEADERS);
		
		ArrayList<String> arl = new ArrayList<>();
		arl.add("--proxy=172.24.2.188:7991");
		arl.add("--ignore-ssl-errors=yes");
		arl.add("--web-security=no");
		
		DesiredCapabilities caps = new DesiredCapabilities();
	//	caps.setCapability(CapabilityType.PROXY, seleniumproxy);
		caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		caps.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, arl);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/home/netstorm/Desktop/phantomjs");
		
		proxy.newHar("automationranjeet.com");
		
		WebDriver driver = new PhantomJSDriver(caps);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("Browser Opened");
		driver.get("https://automationranjeet.blogspot.com/");
		
		Thread.sleep(7000);
		System.out.println(driver.getTitle());
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	
		
		File dest = new File("/home/netstorm/Desktop/myblog.png");
		if (dest.exists()) {
			dest.delete();
		}
		FileUtils.moveFile(src, dest);
		
		Har har = proxy.getHar();
		
		getInfofromHar(har);
		
		FileOutputStream fos  = new FileOutputStream("/home/netstorm/Desktop/myblog.har");
		
		har.writeTo(fos);
		
		if ( proxy != null ){
			proxy.stop();
			driver.quit();
		}
		
	}
	
	private static void getInfofromHar(Har har) {
		// TODO Auto-generated method stub
       HarLog log = har.getLog();
       
       List<HarEntry> entries = log.getEntries();
       
       for ( HarEntry enrty : entries){    	 
    	   
    	   System.out.println("Url name : "+enrty.getRequest().getUrl() + "  Urlsize : "+enrty.getRequest().getHeadersSize());
    	   System.out.println("Request method : "+enrty.getRequest().getMethod()  + " Url status : " +enrty.getResponse().getStatus() );
    	   System.out.println("Time took : " + enrty.getTime() + " ms");
    	   
       }
       
	}

}
