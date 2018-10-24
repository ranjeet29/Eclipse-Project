package Browser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;


public class FireFoxBrowser {
	
	public WebDriver driver;
	public BrowserMobProxy proxy;
	public String harname = "Analytics.har";
	
	
	public WebElement findElementByXpath(String xpath){
        try{
            WebElement element = driver.findElement(By.xpath(xpath));
            return element;
        }catch(WebDriverException e){
            e.printStackTrace();
            return null;
        }
    }

	
	@BeforeTest
	public void setUp(){
	    	
		
		System.setProperty("webdriver.gecko.driver", "/home/netstorm/Back_Up/geckodriver");
	
	/*	LoggingPreferences logPref = new LoggingPreferences();
        logPref.enable(LogType.PERFORMANCE, Level.ALL);*/
     	    
		proxy = new BrowserMobProxyServer();
	    proxy.start(7991);     	   
	    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
	    seleniumProxy.setProxyType(ProxyType.MANUAL);
	    seleniumProxy.setHttpProxy("localhost:7991");
	    seleniumProxy.setSslProxy("localhost:7991");
        DesiredCapabilities capa = new DesiredCapabilities();
        capa.setCapability(CapabilityType.BROWSER_NAME, "FireFox");
        capa.setCapability(CapabilityType.VERSION, "45");
        capa.setCapability(CapabilityType.PROXY, seleniumProxy);
  //      capa.setCapability(CapabilityType.LOGGING_PREFS, logPref);
		driver = new FirefoxDriver(capa);
		proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT , CaptureType.REQUEST_HEADERS , CaptureType.RESPONSE_HEADERS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void execute() throws Exception{
	
		proxy.newHar("http://10.10.30.37/analytics.admin");
		driver.get("http://www.google.com");
	/*	driver.findElement(By.xpath(".//*[@id='download-button' and text()='Get Started']")).click();
		driver.findElement(By.xpath(".//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath(".//input[@name='password']")).sendKeys("C@VAdmin");
		driver.findElement(By.xpath("html/body/div[3]/div/div[2]/div/div[1]/div[2]/div/form/div[3]/button")).click(); */
		
		
	}
	
	
	public void pageStatsInfo(String harname , Har har ){
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
        String browserType = "Firefox";     //browser name to be modified
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
	
	public void getHarPFile(String harname , Har har){
		
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
	
	@AfterTest
	public void exit(){
		Har har = proxy.getHar();

	//	File harFile = new File("analytics.har");
		 try{
	            FileOutputStream fos=new FileOutputStream(harname);
	            har.writeTo(fos);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
      
		getHarPFile(harname  , har);  //Get Har File 
		 
		
		if (driver != null){
			driver.quit();
		}
	}

}
