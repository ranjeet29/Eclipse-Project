import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class captureSnapshot implements Runnable{

	boolean isRunning;
	public WebDriver driver ;
	private long interval = 10000;
	
	
	public captureSnapshot(WebDriver driver){
		this.driver = driver ;
		this.isRunning = true;
		PageFactory.initElements(driver, this);
	}
	
	private void saveFile(File src) {
	    long length = src.length();
	    File dest = new File("/home/netstorm/Desktop/Demo/demo"+System.currentTimeMillis()+".JPG");
	    try {

	        FileUtils.moveFile(src, dest);

	    }catch(IOException e) {
	      System.err.println("Failed to save file with name: " + dest);
	    }
	  }
    
		

	  public void run() {
		    while(isRunning) {
		      try {
		        File src = getScreenShot();
		        saveFile(src);
		        System.out.println("capture snapshot");
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
     
	  public void stopThread() {
		  System.out.println("stop thread called");
		    isRunning = false;
		  }

	

}
