package com.cavisson.scripts.;

import pacJnvmApi.NSApi;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
/**
 * Base Test to demonstrate how to test native android apps and IOS app with appium. App under test is:
 * /home/netstorm/work/automation/apks/Kohls.apk
 * In this Class we are passing two devices Capabilities:-
 * (1) IOS Devices Capabilities : Drivername ,platformVersion , PlatFormName , deviceName , udid , urlhub .
 * (2) Android Devices Capabilities :  Drivername ,platformVersion , PlatFormName , deviceName , udid , urlhub ,AppName ,appActivity.
 */

public class init_script {
    public static AppiumDriver driver;          
    public static BrowserMobProxy proxy;
    public static int sessId;
    public static String filePath;
    public static Proxy selProxy;
    public static HarBuilder harbuilder ;  

    public static int execute(NSApi nsApi)
    {
        try {
	        //######### MENTION HERE  APP Path #####################
            File app=new File("appApth");
        
          //SetCapabilities( nsApi, deviceName , device_version, platform, device_ip_port ,  driver_Ip_Port , appActivity , proxy_port , appPath )  
          driver = capabilitySet.setCapabilities();


    } catch(Exception e){
        e.printStackTrace();
    }
     return 0;
    }
    
}


