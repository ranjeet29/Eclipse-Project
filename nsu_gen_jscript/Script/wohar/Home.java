package com.cavisson.scripts.;
 
 import pacJnvmApi.NSApi;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import java.util.Set;
 import org.openqa.selenium.By;
 import org.openqa.selenium.remote.DesiredCapabilities;
 import org.openqa.selenium.support.ui.ExpectedConditions;
 import org.openqa.selenium.support.ui.WebDriverWait;
 import java.util.concurrent.TimeUnit;
 import io.appium.java_client.AppiumDriver;
 import io.appium.java_client.android.AndroidDriver;
 import io.appium.java_client.remote.MobileCapabilityType;
 import org.openqa.selenium.support.PageFactory;
 import java.net.URI;
 import java.util.Date;
 import org.openqa.selenium.support.FindBy;
 import net.lightbody.bmp.BrowserMobProxy;
 import net.lightbody.bmp.BrowserMobProxyServer;
 import net.lightbody.bmp.client.ClientUtil;
 import net.lightbody.bmp.core.har.Har;
 import net.lightbody.bmp.proxy.CaptureType;
 import org.openqa.selenium.Proxy;
 import com.google.gson.GsonBuilder;
 import com.google.gson.JsonParser;
 import java.util.concurrent.TimeUnit;

/*This class declare Android Home Activity and clicking Menu button */


public class Home implements NsFlow{
    public static AppiumDriver  driver;

    public Home() {
        driver = init_script.driver;
    }
     
    public Home(AppiumDriver pDriver) {
        driver = pDriver;
     }
  
    public int execute(NSApi nsApi){
        System.out.println("Going inside Home Page Successfully........");
        nsApi.ns_start_transaction("Home");
        nsApi.ns_web_url("Home","URL=http://127.0.0.1/tours/index.html", "METHOD=GET");
        Home home = PageFactory.initElements(init_script.driver, Home.class);
        SnapShotThread snapshot = new SnapShotThread( nsApi, driver, 10000L);
        Thread snapshotThread = new Thread(snapshot);
        snapshotThread.start();
        try{
            
        	/*#############################################
        	 *#############################################
        	 *#                                           #
        	 *#         PLACE YOUR CODE HERE              #
        	 *#                                           #
        	 *#############################################
        	 *#############################################
        	 */
   
           
         }catch(Exception ie){
            ie.printStackTrace();
         }
       
        nsApi.ns_end_transaction("Home", 0);
        snapshot.stopThread();
        return 0;
     }
 }
