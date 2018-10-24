import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;


public class MacysTest {
	
	
	public static void main(String[] args) throws Exception {
		BrowserMobProxy server = new BrowserMobProxyServer();
	    server.start(5555);
	    Proxy proxy = ClientUtil.createSeleniumProxy(server);
	    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability("automationName", "Appium");
	    desiredCapabilities.setCapability("platformName", "Android");
	    desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);
	    desiredCapabilities.setCapability("platformVersion", "7");
	    desiredCapabilities.setCapability("deviceName", "Moto G4 Plus");      
	    desiredCapabilities.setCapability("app", "/home/netstorm/Desktop/Macy's.apk");
	    desiredCapabilities.setCapability("appPackage", "com.macys.android");
	    desiredCapabilities.setCapability("appActivity", "com.macys.main.ui.activity.MainActivity");
	    desiredCapabilities.setCapability("noReset",true);
	    server.newHar("newHar");
	    AndroidDriver driver = null;
	    try {
	        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

	        driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	        
	        Thread.sleep(5000);
	        
	        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.SearchView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.EditText")).sendKeys("shirts\n");
	        
	        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.ImageView")).click();
	        
	        Thread.sleep(5000);
	        
	        
	     //   new TouchAction(driver).press(650, 1239).waitAction(30).moveTo(12, -128).release().perform();
	        
	        Dimension dimensions = driver.manage().window().getSize();
	        System.out.println("Size of Window= " +dimensions);
	        int scrollStart = (int) (dimensions.getHeight() * 0.9 );
	        System.out.println("Size of scrollStart= " +scrollStart);
	        int scrollEnd = (int) (dimensions.getHeight() * 0.1);
	        System.out.println("Size of cscrollEnd= " + scrollEnd);             
	        driver.swipe(0,scrollStart,0,scrollEnd,1000);           
	        System.out.println("Screen Swiped " );

	        Har har = server.getHar();
	        HarLog log = har.getLog();
	        List<HarEntry> entries = log.getEntries();
	        System.out.println(entries);
	        for (HarEntry entry : entries){
	            System.out.println(entry.getRequest().getUrl());
	        }
	        System.out.println("stats printed ");
         	//	File harFile = new File("analytics.har");
	    		 try{
	    			 System.out.println("Going to save har file ");
	    	            FileOutputStream fos=new FileOutputStream("macys.har");
	    	            har.writeTo(fos);
	    	        } catch(Exception e) {
	    	            e.printStackTrace();
	    	        }

	    } catch (MalformedURLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    
	    
	    server.stop();
	    driver.quit();
	}
	

}


