


import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeBrowserTesting {
  
	public static WebDriver driver;
	
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/home/netstorm/Desktop/chromedriver");
		driver = new ChromeDriver();
	//	captureSnapshot cap = new captureSnapshot(driver);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("******navigate command uses start********");
		
		

		
		driver.get("http://www.google.com");
		System.out.println("google Open Successfully");
		Thread.sleep(5000);	
		Point pt = driver.manage().window().getPosition();
		System.out.println(pt.getX());
		System.out.println(pt.getY());
	
		
		
		
		/*FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(250, TimeUnit.MILLISECONDS);
		wait.withTimeout(1, TimeUnit.MINUTES);
		
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

		
			public Boolean apply(WebDriver driver) {
			
		/*		if(nothanks_button.isDisplayed()){
					nothanks_button.click();
					return true;
				}else
				{
					return false;
				}
				
				
			}
		};
		
		wait.until(function);*/
		captureSnapshot cap =  new captureSnapshot(driver);
		new Thread(new captureSnapshot(driver)).start();
		
		driver.findElement(By.name("q")).sendKeys("kohls");
		
		driver.findElement(By.name("btnK")).click();
		cap.stopThread();
	
		
		driver.navigate().back();
		System.out.println("back command run successfully");
		Thread.sleep(5000);
       
	
		new Thread(new captureSnapshot(driver)).start();
		driver.findElement(By.name("q")).sendKeys("macys");
		
		driver.findElement(By.name("btnK")).click();
		cap.stopThread();
		driver.navigate().forward();
		System.out.println("forward command run successfully");
		Thread.sleep(5000);
		driver.navigate().refresh();
	
		driver.quit();
		System.out.println("Script run successfully");
		

	}


}
