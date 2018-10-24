package Demo;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class SeleniumDemo {
   
	public static void main(String arg[]) throws Exception{
		WebDriver driver = null;
		
		System.setProperty("webdriver.chrome.driver","/home/netstorm/Downloads/chromedriver");
		
		driver = new ChromeDriver();
		/*ChromeOptions option = new ChromeOptions();
		DesiredCapabilities  capa = DesiredCapabilities.firefox();
		capa.setCapability(CapabilityType.BROWSER_NAME, "firefox");*/
		System.out.println("driver is invoked");
		CommandBrowserCommand command = new CommandBrowserCommand(driver);
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		command.openUrl("https://www.kohls.com/");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("title:"+command.getTitle());
	    
		driver.findElement(By.id("search")).sendKeys("jeans");
		driver.findElement(By.name("submit-search")).click();
		
		Thread.sleep(5000);
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	       ImageIO.write(screenshot.getImage(),"PNG",new File("/home/netstorm/Desktop/FullPageScreenshot.png"));
		driver.findElement(By.xpath("//*[@id='content']/div[2]/div[2]/div[1]/div[4]/ul/li[1]/div[1]/a/img[1]")).click();
		
		/*JavascriptExecutor js = (JavascriptExecutor)driver;
		String sText =  js.executeScript("return document.title;").toString();
		System.out.println("sText "+sText);
		js.executeScript("window.scrollBy(0,150)");
	//	js.executeScript("alert('hello world');");
	  //  Alert alert = driver.switchTo().alert();
	   // alert.dismiss();
		System.out.println("url opened");
	    
		Cookie name = new Cookie("name", "ranjeet");
		driver.manage().addCookie(name);
		driver.manage().deleteAllCookies();
		Set<Cookie> st= driver.manage().getCookies();
		for(Cookie cookie : st ){
			System.out.println("cookie list:"+cookie);
		}
		
		WebElement ele = driver.findElement(By.name("q"));
		ele.sendKeys("Kohls");
        ele.submit();
        js.executeScript("window.scrollBy(0,400)");
       
        command.waitForElementToBeClickable(CommandBrowserCommand.SelectorType.XPATH, "//*[@id='rso']/div[1]/div/div/table/tbody/tr[2]/td[1]/div/span/h3/a", 10).click();
        
		Thread.sleep(5000);*/
		driver.quit();
		
	}
}
