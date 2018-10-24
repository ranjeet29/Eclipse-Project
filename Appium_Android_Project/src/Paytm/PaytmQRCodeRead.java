package Paytm;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class PaytmQRCodeRead {


	    private static AndroidDriver driver ;

	    public static void main(String[] args) throws IOException, NotFoundException {
			
		
	    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability("deviceName", "ZY223PJPC3");
	    desiredCapabilities.setCapability("platformVersion", "7.0");
	    desiredCapabilities.setCapability("platformName", "android");
	    desiredCapabilities.setCapability("appPackage", "net.one97.paytm");
	    desiredCapabilities.setCapability("appActivity", "net.one97.paytm.landingpage.activity.AJRMainActivity");
	    desiredCapabilities.setCapability("noReset", true);

	    
	    
	    URL remoteUrl = new URL("http://localhost:4723/wd/hub");

	   
	    driver = new AndroidDriver(remoteUrl, desiredCapabilities);
	    
	    driver.setLocation(new Location(28.535517, 77.391029, 0));
	    
	    scrollAndClick("Best Deals on Mobiles");
	    
	    driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]").click();
	    
	    MobileElement el5 = (MobileElement) driver.findElementById("net.one97.paytm:id/user_detail_container");
	    el5.click();
	    
	   
	    
	    driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.ImageView").click();
	    String  qrcodeurl = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ImageView").getTagName();
       
	    TakesScreenshot scrShot =((TakesScreenshot)driver);
	    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	    System.out.println( "src file : "+SrcFile);
	    
	    FileUtils.copyFile(SrcFile, new File("/home/netstorm/Desktop/ranjeet.png"));
	    
	    System.out.println("url of image : "+qrcodeurl);
	    System.out.println("souce code : "+ driver.getPageSource());
	    
	    BufferedImage bufferimage = ImageIO.read(SrcFile);
		LuminanceSource luminance = new BufferedImageLuminanceSource(bufferimage);
		
		BinaryBitmap binarybitmap = new BinaryBitmap(new HybridBinarizer(luminance));
		
		Result result = new MultiFormatReader().decode(binarybitmap);
		
		String qrcoderesult = result.getText();
		System.out.println("Text inside QR Code is : "+qrcoderesult);
		

        // getting location of the iOS device
          Location location = driver.location();
         
         System.out.println("Location : "+ location);
	    
	    driver.quit();
	    
	    
	  }
	    
	    public static void scrollAndClick(String text) {
	       
	    	String uiSelector = "new UiSelector().textMatches(\"" + text+ "\")";

            String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("+ uiSelector + ");";

            driver.findElementByAndroidUIAutomator(command);
	    	
	           }
	       
	}


