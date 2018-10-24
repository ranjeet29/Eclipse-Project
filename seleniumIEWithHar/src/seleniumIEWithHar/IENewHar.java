package seleniumIEWithHar;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.proxy.CaptureType;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IENewHar {

	
	public static void main(String args[]) throws MalformedURLException{
	BrowserMobProxy proxy = new BrowserMobProxyServer();
	proxy.setTrustAllServers(true);
	proxy.start(0);	
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
    seleniumProxy.setProxyType(ProxyType.MANUAL);
  

    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    capabilities.setCapability("ie.setProxyByServer", true);
    capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, true);
    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
   
    // start the browser up
    WebDriver driver = new RemoteWebDriver(new URL("http://172.24.2.188:5555/wd/hub") , capabilities);

    // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

    // create a new HAR with the label "yahoo.com"
    proxy.newHar();

    // open yahoo.com
 
    driver.get("http://www.google.com");

    // get the HAR data
     Har har = proxy.getHar();
     har.getLog().setComment("hii this is comment added by ranjeet  ");
     System.out.println("Entries count :"+  har.getLog().getEntries().size());
     
     HarLog log = har.getLog();
     
     
     long bytesReceived = 0;
     long bytesSent = 0;
     int jsSize = 0;
     int htmlSize = 0;
     int imageSize = 0;
     int cssSize = 0;
     int pageWeight = 0;
 
     
     List<HarEntry> entries = log.getEntries();
      
     for ( HarEntry enrty : entries) {
     
     
   //calculate total bytes received including header and body size 
     bytesReceived += enrty.getResponse().getBodySize();
     bytesReceived += enrty.getResponse().getHeadersSize();
     
   //calculate total bytes sent including header and body size
     bytesSent += enrty.getRequest().getBodySize();
     bytesSent += enrty.getRequest().getHeadersSize();

     //count no. of entries with a given type of content and response size
     String type = enrty.getResponse().getContent().getMimeType();
     long size = enrty.getResponse().getBodySize();
     
     //calculate total size of javascript type response
     if (type.toLowerCase().contains("javascript")) {
         jsSize += size;
     }

     //calculate total size of html type response
     if(type.toLowerCase().contains("html")) {
         htmlSize += size;
     }

     //calculate total size of image type response
     if(type.toLowerCase().contains("image")) {
         imageSize += size;
     }

     //calculate total size of css type response
     if(type.toLowerCase().contains("css")) {
         cssSize += size;
     }

     pageWeight = htmlSize+cssSize+imageSize+jsSize;
    


     System.out.println("pagerweight : "+pageWeight);
     System.out.println("ByteReceived : "+bytesReceived);
     System.out.println("ByteSends :"+bytesSent);
     System.out.println("Time took : " + enrty.getTime() + " ms");
     System.out.println("Url name : " + enrty.getRequest().getUrl() + "  Urlsize : " + enrty.getRequest().getHeadersSize());
     System.out.println("Request method : "	+ enrty.getRequest().getMethod() + " Url status : "+ enrty.getResponse().getStatus());
     
     }
     File harFile = new File("newhar.har");
     
     System.out.println("pagerweight : "+pageWeight);
     System.out.println("ByteReceived : "+bytesReceived);
     System.out.println("ByteSends :"+bytesSent);
   
    try{
   	//harFile.mkdirs(); 
    harFile.createNewFile();
    har.writeTo(harFile);
    }catch(Exception e){
    	e.printStackTrace();
    }
    proxy.stop();
    driver.close();
    
  }
}
