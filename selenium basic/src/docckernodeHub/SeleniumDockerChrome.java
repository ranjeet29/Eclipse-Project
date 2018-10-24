package docckernodeHub;

import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumDockerChrome {
	
	public static void main(String[] args) throws Exception {
		
		
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability(CapabilityType.BROWSER_NAME,"chrome");
		caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		caps.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		
		WebDriver driver = new RemoteWebDriver(new URL("http://172.17.0.2:4444/wd/hub") , caps);
		driver.manage().window().maximize();
		
		driver.get("https://10.10.30.97");
		
		
		Thread.sleep(7000);
		
		System.out.println("Title : "+driver.getTitle());
		
		if (driver != null ) {
			driver.quit();
		}
	}

}
