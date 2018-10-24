package NewJarSeleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class DemoJcp {

	
	public static WebDriver driver;
	
	public static void main(String[] args) throws Exception{
	


   //    WebDriver driver=new RemoteWebDriver(new URL("http://172.24.2.188:5555/wd/hub") , capabilities);
       
		Proxy proxy = new Proxy();
		proxy.setProxyType(ProxyType.MANUAL);
		proxy.setHttpProxy("192.168.43.24:5555");
		proxy.setSslProxy("192.168.43.24:5555");
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(CapabilityType.PROXY, proxy);
		
	    driver = new ChromeDriver(caps);	
		driver.manage().window().maximize();
		
	    driver.get("https://www.jcpenney.com/");
	    Thread.sleep(10000);
	 
	    try{
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("document.getElementById('closeButton').click();");
	    	
	    }catch(Exception e ){
	    	e.printStackTrace();
	    }
	    Actions action = new Actions(driver);
	    action.moveToElement(driver.findElement(By.xpath("//*[@id='flyout_opener']"))).build().perform();
	    new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("View All Furniture")));
	    action.moveToElement(driver.findElement(By.partialLinkText("View All Furniture"))).click().build().perform();
	 
		
         driver.close();		
	}

	}

