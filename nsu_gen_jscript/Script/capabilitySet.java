package com.cavisson.scripts.;

import pacJnvmApi.NSApi;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;
import org.openqa.selenium.remote.CapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

/* This Script is used to NATIVE APP :
 * This class is using Devices Capabilities for Android Device .
 * */
public class capabilitySet {
    public static BrowserMobProxy proxy;
    public static int sessId;
    public static String filePath;
    public static Proxy selProxy;

    public static AppiumDriver setCapabilities(NSApi nsApi , String deviceName ,String device_version ,String platform, String device_ip_port, String driver_ip_port , String appActivity , String proxy_port  , File apppath , String udid){
       	AppiumDriver ad;
    	try{
     	    System.out.println("going to set capabilities");
     	    DesiredCapabilities capabilities = new  DesiredCapabilities();
       	
            capabilities.setCapability("platformVersion",device_version  );
            capabilities.setCapability("platformName", platform);           
            capabilities.setCapability("deviceName" , deviceName);
            capabilities.setCapability("udid" , udid);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device_ip_port);
            capabilities.setCapability("noReset",true);
            HarBuilder harbuilder = new HarBuilder();
            System.out.println("har builder initiated");
        	try{
    	        selProxy = harbuilder.startProxy(proxy_port);
                capabilities.setCapability(CapabilityType.PROXY, selProxy);
                System.out.println("selproxy :"+selProxy);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
                System.out.println("har builder ");      
	        }catch(Exception e){
                e.printStackTrace();
      	    }
                System.out.println("proxy element returned");
	            try{
                    filePath = harbuilder.calculateHarPath(nsApi);
                } catch(Exception e) {
                    e.printStackTrace();
                }
	        capabilities.setCapability("app", apppath.getAbsolutePath());
            capabilities.setCapability("autoDismissAlerts",true);
            capabilities.setCapability("appActivity",appActivity);
            System.out.println("All capabilities set");
            ad = new AndroidDriver<>(new URL("http://"+driver_ip_port+"/wd/hub"), capabilities);
            System.out.println("Android driver initiated");
            Set<String> contextNames= ad.getContextHandles();
            System.out.println("inside HOME_Android");
            for(String contextName:contextNames){
	        System.out.println(contextName);
            if(contextName.contains("NATIVE"))
                {
                    ad.context(contextName);
               }
            }
            ad.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return ad;
    }
}


                                            
