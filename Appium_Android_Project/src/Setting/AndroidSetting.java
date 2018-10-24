package Setting;

import java.net.URL;
import java.util.HashMap;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSetting {
	
	public static void main(String[] args) throws Exception {
		
		WebDriver driver = null ;
		DesiredCapabilities caps = new DesiredCapabilities(); 
		caps.setCapability("platformVersion", "7.0");
		caps.setCapability("platformName", "Android");
		caps.setCapability("deviceName", "Moto G4 Plus");
		caps.setCapability("noReset", "true");
		caps.setCapability("appPackage", "com.android.settings");
		caps.setCapability("appActivity", "com.android.settings.Settings");
		System.out.println("All capability set successfully");
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
		
		System.out.println("Setting App open successfully");
		MobileElement apps = (MobileElement) driver.findElement(By.xpath("//*[@text=\"Apps\"]"));
		apps.click();
		
		
		MobileElement chrm = (MobileElement) driver.findElement(By.xpath("//*[@text=\"Chrome\"]"));
		chrm.click();
		
		MobileElement strg = (MobileElement) driver.findElement(By.xpath("//*[@text=\"Storage\"]"));
		strg.click();
		
		MobileElement clr = (MobileElement) driver.findElement(By.xpath("//*[@text=\"CLEAR CACHE\"]"));
		clr.click();
		
		driver.quit();
	}

}
