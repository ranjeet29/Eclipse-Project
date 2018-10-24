package Paytm;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class KubeDevice {
	
	private static AndroidDriver driver;
	public static void main(String[] args) throws MalformedURLException, AWTException, InterruptedException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability("deviceName", "034212487C003000");
	    desiredCapabilities.setCapability("deviceVersion", "8.1.0");
	    desiredCapabilities.setCapability("platformName", "Android");
	    desiredCapabilities.setCapability("appActivity", "com.zone24x7.kube.kubeapp.MainActivity");
	    desiredCapabilities.setCapability("appPackage", "com.zone24x7.kube.kubeapp");
	    desiredCapabilities.setCapability("autoGrantPermissions", true);
	    desiredCapabilities.setCapability("noReset", true);
	    desiredCapabilities.setCapability("orientation", "LANDSCAPE");
	    desiredCapabilities.setCapability("acceptSslCerts", true);
	    
	    URL remoteUrl = new URL("http://localhost:4723/wd/hub");

	    driver = new AndroidDriver(remoteUrl, desiredCapabilities);
	    
	    System.out.println("driver initiated ");
	    MobileElement el1 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[2]");
        el1.click();
        
	    MobileElement el2 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ImageView");
	    el1.sendKeys("12345");
	    
	    Robot rbt = new Robot();
	    rbt.keyPress(KeyEvent.VK_ENTER);
	    rbt.keyRelease(KeyEvent.VK_ENTER);
	    
	    Thread.sleep(5000);
	    
	    
	    
	}
     
	public void scroll(String xpath) {
		while(driver.findElementByXPath(xpath).isDisplayed()){
	    Dimension dimensions = driver.manage().window().getSize();
	    int Startpoint = (int) (dimensions.getHeight() * 0.5);
	    int scrollEnd = (int) (dimensions.getHeight() * 0.5);
	    driver.swipe(200, Startpoint,200,scrollEnd,2000);
		}
	}
}
