import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.Proxy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.*;


public class capabilitySet {
    
	  
	    public static int sessId;
	    public static String filePath;
	    public static Proxy selProxy;
	  

	
    public static WebDriver initiateRemoteChromeBrowserMobile(String hubURL, int proxy_port, boolean performanceEnabled){
        WebDriver wd = null;
         System.setProperty("webdriver.chrome.driver","/home/netstorm/work/automation/AA/aa/CH/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("androidPackage","com.android.chrome");
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        try{
        if(performanceEnabled){
            System.out.println("har builder initiated");
            try{
      //      selProxy = harbuilder.startProxy(proxy_port);
            }catch(Exception e){
                e.printStackTrace();
                    }
            System.out.println("proxy element returned");
            try{
      //          filePath = harbuilder.calculateHarPath(nsApi);
            } catch(Exception e) {
                e.printStackTrace();
            }
            caps.setCapability(CapabilityType.PROXY, selProxy);
            caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
            }

         //    wd  =  new ChromeDriver(caps);
            wd = new RemoteWebDriver(new URL(hubURL), caps);
            wd.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
            System.out.println("Driver initiated successfully");
            }catch(Exception e ){
               e.printStackTrace();
            }
         return wd;
      }

}
