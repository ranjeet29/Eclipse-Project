import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class BrowserMobHarTest {
    
	public static final String USERNAME = "anjali104";
	public static final String AUTOMATE_KEY = "qyY1RvsszavbxCZfaDHv";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static final String harname = "google.har ";
	
	public static void main(String[] args) throws IOException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
	    caps.setCapability("browser", "Chrome");
	    caps.setCapability("browser_version", "62.0");
	    caps.setCapability("os", "Windows");
	    caps.setCapability("os_version", "10");
	    caps.setCapability("resolution", "1024x768");
	    caps.setCapability("browserstack.local", "true");
	   
       

	  
		BrowserMobProxy proxy = new BrowserMobProxyServer();
	    proxy.start(7991);
	    proxy.setRequestTimeout(3, TimeUnit.SECONDS);
	    proxy.setConnectTimeout(2, TimeUnit.SECONDS);
	    proxy.setIdleConnectionTimeout(2, TimeUnit.SECONDS);
	    
	   /* System.getProperties().put("http.proxyHost", "172.24.2.188");
	    System.getProperties().put("http.proxyPort", "7991");

	    //For HTTPS
	    System.getProperties().put("https.proxyHost", "172.24.2.188");
	    System.getProperties().put("https.proxyPort", "7991");*/
	    
	    
	    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capa = new DesiredCapabilities();
        capa.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capa.setCapability(CapabilityType.VERSION, "54");
        capa.setCapability(CapabilityType.PROXY, seleniumProxy);
  
        
	    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
	    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT , CaptureType.REQUEST_HEADERS , CaptureType.RESPONSE_HEADERS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		proxy.newHar("http://www.cavisson.com");
	    driver.get("http://www.cavisson.com");
	    
	    
	    BufferedReader br = new BufferedReader(new FileReader(new File("")));
	    String st ; 
	    while ((st = br.readLine()) != null) {
			System.out.println(st);
		}
	    
	    Har har = proxy.getHar();

		//	File harFile = new File("analytics.har");
			 try{
		            FileOutputStream fos=new FileOutputStream(harname);
		            har.writeTo(fos);
		        } catch(Exception e) {
		            e.printStackTrace();
		        }
	      
			getHarPFile(harname  , har);  //Get Har File 
	    
	    
	    
	    if (driver != null ) {
			 driver.quit();
			 proxy.stop();
		}
		
	}
	
	public static void getHarPFile(String harname , Har har){
		
		 //Read har and create harp
       String harStr = "";
       BufferedReader br = null;

       try {
       
           br = new BufferedReader(new FileReader(harname));
           StringBuilder sb = new StringBuilder();

           while (true) {
               String line = br.readLine();
               if(line == null)
                   break;
               sb.append(line);
           }
           harStr = sb.toString();
           br.close();

       } catch(Exception e){
           try{
               br.close();
           }catch(Exception ex){
           }
           harStr = "";
       }
       
       //beautify harp
       harStr = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(harStr));
       harStr = "onInputData(\n" +harStr+ "\n);";

       //name of harp file
       String harpFileName = harname+"p";

       //write harp file
       BufferedWriter writer = null;
       try{
           writer = new BufferedWriter(new FileWriter(harpFileName));
           writer.write(harStr);
           writer.close();
       }catch(Exception e){
           try{
               writer.close();
           }catch(Exception ex){

           }
       }
       
        
      

	}
}

