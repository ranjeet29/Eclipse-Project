import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class SampleClickTypeScript {
     
	public static void main(String[] args) throws Exception{
		
		WebDriver driver = new ChromeDriver();
		
		
		driver.manage().window().maximize();
		
		driver.get("https://www.google.com");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	
	//	new Actions(driver).moveByOffset(953, 336).click().build().perform();
		
		Thread.sleep(5000);
		
		// Action : Browse 
		/*driver.get("https://automationranjeet.blogspot.in/");

		
		// Action : scroll , page : product_2
		JavascriptExecutor js = ( JavascriptExecutor )driver;
		js.executeScript("window.scrollTo(0,623);");   */
		
	    WebElement ele = driver.findElement(By.name("q"));
        Thread.sleep(3000);
      

        
      //  char c = '\u0031'; // ASCII code 1 for Ctrl-A
     //   driver.findElement(By.name("q")).sendKeys(Character.toString(c));
       
        
        ele.click();
		Actions action = new Actions(driver); 
		action.moveToElement(ele).click().sendKeys("aaaaaa").keyUp(Keys.CONTROL).perform();
		
		Thread.sleep(2000);
		
	/*	 int keyInput[] = 
			    {
			      KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5,

			    };
		
		   Robot robot = new Robot();
		    driver.findElement(By.name("")).click();
		    for (int i = 0; i < keyInput.length; i++)
		    {
		 
		      robot.keyPress(keyInput[i]);
		      robot.delay(100);
		 
		    }*/
		 
		if (driver != null) {
			
			driver.quit();
		}
		
	}
}
