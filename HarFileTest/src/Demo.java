import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarNameValuePair;
import net.lightbody.bmp.proxy.CaptureType;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;


public class Demo{
	
	
	public WebDriver driver;
	public static BrowserMobProxy proxy;
	public static String harname = "google.har";
	
	
	
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
       
       pageStatsInfo(harname , har);    // Har Stats Info
      

	}
	
	
	public static  void  pageStatsInfo(String harname , Har har ){
		 //Calculate RBU stats
       String message = "";
       long onLoadTime = 0L;
       long onContentLoadTime = 0L;
       long overallTime = 0L;
       int timeToInteract = -1;
       int startRenderTime = -1;
       int visuallyCompleteTime = -1;
       int httpRequests = 0;
       int httpRequestsFromCache = 0;
       int bytesReceived = 0;
       int bytesSent = 0;
       int pageAvailability = 0;
       int jsSize = 0;
       int htmlSize = 0;
       int imageSize = 0;
       int cssSize = 0;
       int pageWeight = 0;
       int domElements = 0;
       int pageSpeedMetrics = -1;
       long backendResponse = 0L;
       int sessionCompleted = 0;
       int sessionSuccess = 0;
       long entryTime = 0L;
       long minStartTime = 0L;
       long maxEndTime = 0L;
       int akamaiOffLoad = 0;
       long firstStartTime = 0L;
       String browserType = "Internet Explorer";     //browser name to be modified
       String harName = harname;
       
       HarLog logs = har.getLog();

   //    browserType = logs.getBrowser().getName();
       System.out.println(browserType);
       //parse all entries in the logs to get all entries and their parameters
       for(HarEntry entry : logs.getEntries()){
           
           entryTime = entry.getTime();
           List<HarNameValuePair> browserType1 = entry.getRequest().getHeaders();
           for (int i =0 ; i< browserType1.size();i++){
           	System.out.println(browserType1.get(i).getValue());
           }
             
           if (httpRequests == 0) {
               firstStartTime = entry.getStartedDateTime().getTime();
               minStartTime = entry.getStartedDateTime().getTime();
               maxEndTime = entry.getStartedDateTime().getTime() + entry.getTime();
               backendResponse += (entry.getTimings().getDns() > 0) ? entry.getTimings().getDns() : 0 +
                   ((entry.getTimings().getConnect() > 0)? entry.getTimings().getConnect() : 0) +
                   ((entry.getTimings().getWait() > 0)?entry.getTimings().getWait() : 0 )+
                   ((entry.getTimings().getReceive() > 0)? entry.getTimings().getReceive() : 0);
           }
           
           if (httpRequests >= 1) {
               minStartTime = minStartTime > entry.getStartedDateTime().getTime() ? entry.getStartedDateTime().getTime() : minStartTime;
               long tmp = entry.getStartedDateTime().getTime() + entry.getTime();
               maxEndTime = maxEndTime < tmp ? tmp : maxEndTime;
           }
           //counter to calculate number of entries
           httpRequests++;

           //calculate total bytes received including header and body size 
           bytesReceived += entry.getResponse().getBodySize();
           bytesReceived += entry.getResponse().getHeadersSize();

           //calculate total bytes sent including header and body size
           bytesSent += entry.getRequest().getBodySize();
           bytesSent += entry.getRequest().getHeadersSize();
           //count no. of entries with a given type of content and response size
           String type = entry.getResponse().getContent().getMimeType();
           long size = entry.getResponse().getBodySize();

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

           //check status of response to verify page availability
           int status = entry.getResponse().getStatus();
           if(status == 301 || status == 302 || status == 307 || status == 401 || status == 407){
               continue;
           }
           else{
               if(status == 304 || status == 200){
                   pageAvailability = 1;
               }
           }
       }

       overallTime = maxEndTime - minStartTime;

       message = "HarStats : " + onContentLoadTime + "|" + onLoadTime + "|" +overallTime+ "|" +timeToInteract+ "|" +startRenderTime+ "|" +visuallyCompleteTime+ "|" +httpRequests+ "|" +httpRequestsFromCache+ "|" +bytesReceived+ "|" +bytesSent+ "|" +pageWeight+ "|" +jsSize+ "|" +cssSize+ "|" +imageSize+ "|" +domElements+ "|" +pageSpeedMetrics+ "|" +akamaiOffLoad+ "|" +backendResponse+ "|" +pageAvailability+ "|" +sessionCompleted+ "|" +sessionSuccess + "|" + harName + "|" +browserType+ "|" +firstStartTime;



       System.out.println("message:"+message);


	}
	
	public static void main(String[] args) throws Exception {
		
		String hubURL = "http://10.10.30.199:5555/wd/hub"; 
		System.out.println("going to set capabilities");
		System.setProperty("webdriver.ie.driver", "/home/netstorm/work/automation/IE/IEDriverServer.exe");
		System.out.println("ie driver");
		proxy = new BrowserMobProxyServer();
		proxy.setTrustAllServers(true);
		InetAddress intd = InetAddress.getByName("10.10.30.199");
	    proxy.start(7991, intd) ; 
	    
	    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
	    seleniumProxy.setProxyType(ProxyType.MANUAL);
	    seleniumProxy.setHttpProxy("10.10.30.199:7991");
	    seleniumProxy.setSslProxy("10.10.30.199:7991");
	    
	    
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability("ie.ensureCleanSession", true);
		capabilities.setCapability("ie.setProxyByServer", true);
	    capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, true);
		 
		
		
		System.out.println("Internet Explorer capabilities are set");
		RemoteWebDriver wd = new RemoteWebDriver(new URL(hubURL), capabilities);
		 proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT , CaptureType.REQUEST_HEADERS , CaptureType.RESPONSE_HEADERS);
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.manage().window().maximize();
		 System.out.println("driver init done");
		System.out.println("webdriver "+wd);
		System.out.println("Internet driver initiated");
		
		proxy.newHar("https://macys.loadtest.conceptshare.com/");
	    // Going to Open url
		wd.get("https://macys.loadtest.conceptshare.com/");
		System.out.println("driver get title: "+ wd.getTitle());
		
		// Going to Login 
		wd.findElement(By.xpath("//*[@id='csIdLoginEmail']/INPUT[1]")).sendKeys("load.test1@macys.com");  //User Field
		
		wd.findElement(By.xpath("//*[@id='csIdLoginPassword']/INPUT[1]")).sendKeys("ABCabc");     // Password Field
		
		wd.findElement(By.xpath("//*[@id='loginButton']")).click();   // Click on Login
		
		JavascriptExecutor js = ( JavascriptExecutor)wd;
		
		// going to click selected project 
		js.executeScript("arguments[0].click()", wd.findElement(By.xpath("*//div[@class='ui-grid-cell-contents ng-binding ng-scope' and text()='2018_2FEB_FEB2 PROMO VDAY GIFT SUN INS_I8010003' ]")));   // going to click project
		
		// Going to click on Assets
		js.executeScript("arguments[0].click()", wd.findElement(By.xpath("//*[@id='csIdTabNavigation']/DIV[1]/A[6]/SPAN[1]")));    // Click on Assest 
		
		Thread.sleep(5000);
		
		// Click on Given Image 
		js.executeScript("arguments[0].click()", wd.findElement(By.xpath(".//div[@class='cardViewItem ng-scope ng-isolate-scope noStatus' and @data-asset-name='I8010003_114.jpg']/a/div")));   // Click on Image 
		
		
		
		
	    Thread.sleep(5000);
		Har har = proxy.getHar();

		//	File harFile = new File("analytics.har");
			 try{
		            FileOutputStream fos=new FileOutputStream(harname);
		            har.writeTo(fos);
		        } catch(Exception e) {
		            e.printStackTrace();
		        }
	      
	    getHarPFile(harname  , har);  //Get Har File 
		proxy.stop();
		if (wd != null) {
			System.out.println("going to close ");
			wd.quit();
		    
		}
		
		
	}
	
}