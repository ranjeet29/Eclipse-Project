package Browser;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;

public class Capture_SnapShot {
	
	private static WebDriver driver = null; 
	

	  private long interval = 1000;
	

	  public void capture(boolean isRunning) {
		    while(isRunning) {
		      try {
		        File src = getScreenShot();
		        File src1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		        File dest = new File("/home/netstorm/Desktop/Demo/demo_data.jpg");
		        saveFile(src);
		        Thread.sleep(interval);
		      }catch (InterruptedException e) {
		        System.err.println("Interrupted while sleeping");
		      }
		    }
		    System.out.println("SnapShot not capturing");
		  }
	  
	  private File getScreenShot() {
		    return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  }


	  private void saveFile(File src) {
		    long length = src.length();
		    File dest = new File("/home/netstorm/Desktop/Demo/demo"+System.currentTimeMillis()+".jpg");
		    try {

		        FileUtils.moveFile(src, dest);

		    }catch(IOException e) {
		      System.err.println("Failed to save file with name: " + dest);
		    }
		  }

	  
	public static void main(String arg []){
		System.setProperty("webdriver.gecko.driver", "/home/netstorm/Back_Up/geckodriver");
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Capture_SnapShot cap = new Capture_SnapShot();
		cap.capture(true);
		driver.get("http://10.10.30.37");
		cap.capture(false);
		driver.findElement(By.xpath(".//*[@id='download-button' and text()='Get Started']")).click();
		driver.findElement(By.xpath(".//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath(".//input[@name='password']")).sendKeys("C@VAdmin");
		cap.capture(true);
		driver.findElement(By.xpath("html/body/div[3]/div/div[2]/div/div[1]/div[2]/div/form/div[3]/button")).click();
		cap.capture(false);
		
		
	}

}
